package inf112.Sun_Mist_Mountain.app;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import inf112.Sun_Mist_Mountain.app.Controller.Conntroller;
import inf112.Sun_Mist_Mountain.app.Controller.PauseController;
import inf112.Sun_Mist_Mountain.app.Controller.ShopController;
import inf112.Sun_Mist_Mountain.app.Controller.SoundController;
import inf112.Sun_Mist_Mountain.app.Controller.TimerController;
import inf112.Sun_Mist_Mountain.app.Controller.ToolbarController;
import inf112.Sun_Mist_Mountain.app.Model.Coordinates;
import inf112.Sun_Mist_Mountain.app.Model.UIElements;
import inf112.Sun_Mist_Mountain.app.Model.Camera.Camera;
import inf112.Sun_Mist_Mountain.app.Model.Inventory.InventoryActor;
import inf112.Sun_Mist_Mountain.app.Model.Inventory.Inventory;
import inf112.Sun_Mist_Mountain.app.Model.Player.Player;
import inf112.Sun_Mist_Mountain.app.Model.Player.Wallet;
import inf112.Sun_Mist_Mountain.app.Model.World.Generator;
import inf112.Sun_Mist_Mountain.app.Model.World.World;
import inf112.Sun_Mist_Mountain.app.View.Animator;
import inf112.Sun_Mist_Mountain.app.View.GameView;
import inf112.Sun_Mist_Mountain.app.View.Sound.BackgroundMusic;
import inf112.Sun_Mist_Mountain.app.View.Sound.GdxTrackLoader;
import inf112.Sun_Mist_Mountain.app.View.Sound.OggStepSounds;
import inf112.Sun_Mist_Mountain.app.View.Sound.OggUseSounds;
import inf112.Sun_Mist_Mountain.app.View.Sound.PlayerSounds;

public class GameScreen implements Screen {

    /**
     * The maximum amount of time allowed to pass after a track has completed
     * before another is played.
     */
    private static final double MAX_SECONDS_OF_SILENCE = 150.0f;

    private final Random rng;

    private final Conntroller controller;
    private final TimerController timer;
    private final SoundController soundController;
    private final ToolbarController toolbarController;
    private final Coordinates coordinates;
    private final GameView view;
    private final Camera camera;
    private final World world;
    public final Player player;
    private final Animator animator;
    private final Inventory inventory;
    private final UIElements ui;
    private final Conductor conductor;
    private final SpriteBatch batch;

    private final BackgroundMusic music;
    private double currentThreshold;

    private final InputProcessor inputProcessor;
    private Stage stage;
    private InventoryActor inventoryActor;

    public GameScreen(SpriteBatch batch, Conductor conductor) {
        this.rng = new Random();
    
    	this.conductor = conductor;
        this.batch = batch;

        this.animator = new Animator();

        this.world = new Generator(this.rng, 500, 500).generate();

        this.player = new Player(this.world);
        this.inventory = new Inventory();
        Skin skin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));
        this.inventoryActor = new InventoryActor(this.inventory, skin);

        this.stage = new Stage(new StretchViewport(1280, 720), batch);




        this.ui = new UIElements(this.player, this.world, this.inventory);
        this.camera = new Camera(this.player);
        this.coordinates = new Coordinates(this.camera);

        var timer = new GdxTimer();
        var sounds = new PlayerSounds(this.rng, new OggStepSounds(), new OggUseSounds());
        this.soundController = new SoundController(timer, sounds);

        this.controller = new Conntroller(this.coordinates, this.player, this.animator, this.soundController);

        this.timer = new TimerController(timer, this.world, this.player);
        this.toolbarController = new ToolbarController(this.inventory, this.player, this.inventoryActor, this.soundController);
    	this.view = new GameView(this.coordinates, this.batch, this.camera, this.world, this.player, this.animator, this.ui);

        var pauseController = new PauseController(this.conductor);

        var shopController = new ShopController(this.conductor);

        this.music = new BackgroundMusic(this.rng, new GdxTrackLoader());
        this.currentThreshold = this.rng.nextDouble();

        // add the pause controller first so it gets the highest priority
        this.inputProcessor = new InputMultiplexer(pauseController, shopController, this.stage, this.toolbarController, this.controller);

    }

    @Override
    public void render(float delta) {
        this.controller.update(delta);
        this.camera.update(delta);
        this.view.draw();

        // As the amount of time since the last time a song played increases,
        // this fraction will increase, hitting `1` when `MAX_SECONDS_OF_SILENCE`
        // has passed.  At that point, `currentThreshold` will almost surely be
        // less than `normalized`, so a new track will play.
        var normalized = (double) this.music.secondsSinceLastTrack() / MAX_SECONDS_OF_SILENCE;
        if (this.currentThreshold < normalized) {
            this.music.play();
            this.currentThreshold = this.rng.nextDouble();
        }

        this.stage.act(delta);
        this.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        this.view.resize(width, height);
        this.coordinates.resizeScreen(width, height);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.inputProcessor);

        this.soundController.start();
        this.timer.start();
        this.view.shown();

        this.stage.addActor(this.inventoryActor);
    }

    @Override
    public void hide() {
        this.soundController.stop();
        this.timer.stop();
        this.view.hidden();
    }

    @Override
    public void pause() {
        this.soundController.stop();
        this.timer.stop();
    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(this.inputProcessor);

        this.soundController.start();
        this.timer.start();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
        this.music.dispose();
        this.view.dispose();
    }

    /**
     * @return the player's inventory.
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * @return the player's wallet.
     */
    public Wallet getWallet() {
        return this.player;
    }

}
