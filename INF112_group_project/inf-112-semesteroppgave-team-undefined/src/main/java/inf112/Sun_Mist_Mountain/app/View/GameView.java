package inf112.Sun_Mist_Mountain.app.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import inf112.Sun_Mist_Mountain.app.Model.Coordinates;
import inf112.Sun_Mist_Mountain.app.View.Player.PlayerView;
import inf112.Sun_Mist_Mountain.app.View.Player.ViewablePlayer;
import inf112.Sun_Mist_Mountain.app.View.Sound.Ambiance;
import inf112.Sun_Mist_Mountain.app.View.Sound.GdxTrackLoader;
import inf112.Sun_Mist_Mountain.app.View.World.ViewableWorld;
import inf112.Sun_Mist_Mountain.app.View.World.Weather;
import inf112.Sun_Mist_Mountain.app.View.World.WorldView;

/**
 * The view is responsible for drawing the game.
 */
public class GameView {

    private final Rectangle screen = new Rectangle();

    private final Ambiance ambiance;
    private final Coordinates coordinates;
    private final ViewableCamera camera;
    private final WorldView worldView;
    private final ViewableWorld world;
    private final ViewableModel ui;

    private final Animator animator;
    private final Weather weather;
    private final SpriteBatch batch;

    private final OrthographicCamera worldCamera;
    private final OrthographicCamera uiCamera;

    /**
     * Create a new game view to draw to the given batch, with a camera that
     * follows the player.
     */
    public GameView(Coordinates coordinates, SpriteBatch batch, ViewableCamera camera, ViewableWorld world, ViewablePlayer player, Animator animator, ViewableModel ui) {
        this.ambiance = new Ambiance(new GdxTrackLoader());
        this.coordinates = coordinates;
        this.camera = camera;
        this.worldView = WorldView.create(world, new PlayerView(animator, player));
        this.world = world;
        this.ui = ui;

        this.animator = animator;
        this.weather = new Weather();
        this.batch = batch;

        this.worldCamera = new OrthographicCamera();
        this.uiCamera = new OrthographicCamera();

        world.addSeasonChangeListener(this.ambiance::changeSeason);
    }

    /**
     * Draw the game
     */
    public void draw() {
        this.animator.update(Gdx.graphics.getDeltaTime());
        this.updateWorldCamera();
        this.updateUiCamera();
        this.updateViewport();

        ScreenUtils.clear(Color.WHITE);

        this.batch.setProjectionMatrix(this.worldCamera.combined);
        this.batch.begin();

        // Draw the world
        this.worldView.drawWorld(this.batch);

        // Draw the UI
        this.batch.setProjectionMatrix(this.uiCamera.combined);
        this.worldView.drawUi(this.batch);
        this.ui.draw(this.batch);
        if (this.world.isRaining()) {
            this.weather.update(Gdx.graphics.getDeltaTime());
            TextureRegion frame = this.weather.getCurrentFrame();
            float originalWidth = frame.getRegionWidth();
            float targetWidth = this.screen.getWidth();
            float scaleFactorWidth = targetWidth / originalWidth;
            float originalHeight = frame.getRegionHeight();
            float targetHeight = this.screen.getHeight();
            float scaleFactorHeight = targetHeight / originalHeight;
            this.batch.draw(frame, 0, 0, 0, 0, frame.getRegionWidth(), frame.getRegionHeight(), scaleFactorWidth, scaleFactorHeight, 0);
        }
        this.batch.end();
    }

    /**
     * Call this when showing or resuming this view.
     */
    public void shown() {
        this.ambiance.resume();
        this.worldView.show();
    }

    /**
     * Call this when temporarily hiding this view.
     */
    public void hidden() {
        this.ambiance.pause();
        this.worldView.hide();
    }

    /**
     * Resize the render view.
     */
    public void resize(int newWidth, int newHeight) {
        this.screen.width = newWidth;
        this.screen.height = newHeight;

        this.worldCamera.setToOrtho(false, newWidth, newHeight);
        this.uiCamera.setToOrtho(false, newWidth, newHeight);

        this.worldView.resize(this.screen);
    }

    /**
     * Dispose of the view.
     */
    public void dispose() {
        this.worldView.dispose();
    }

    /**
     * @return a rectangle representing the screen.
     */
    public Rectangle getScreen() {
        return this.screen;
    }

    /**
     * Inform {@code this.worldView} about which parts of the world are visible.
     */
    private void updateViewport() {
        var topLeft = this.coordinates.screenToWorld(0, 0);
        var bottomRight = this.coordinates.screenToWorld((int) Math.ceil(this.screen.width), (int) Math.ceil(this.screen.height));
        this.worldView.setViewport(topLeft, bottomRight);
    }

    private void updateWorldCamera() {
        var target = this.camera.getTarget();
        this.worldCamera.position.x = (float) target.x();
        this.worldCamera.position.y = (float) target.y();
        this.worldCamera.update();
    }

    private void updateUiCamera() {
        this.uiCamera.update();
    }

}
