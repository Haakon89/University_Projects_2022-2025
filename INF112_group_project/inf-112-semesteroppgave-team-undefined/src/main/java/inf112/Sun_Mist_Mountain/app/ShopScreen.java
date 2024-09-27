package inf112.Sun_Mist_Mountain.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import inf112.Sun_Mist_Mountain.app.Model.Shop.Shop;
import inf112.Sun_Mist_Mountain.app.Model.Shop.ShopActor;

public class ShopScreen implements Screen {

    private Stage stage;
    private Skin skin;
    private GameScreen game;
    private final Conductor conductor;

    private ShopActor shopActor;
    private TextButton resumeButton;


    public ShopScreen(Conductor conductor, GameScreen game, SpriteBatch batch) {
        this.conductor = conductor;
        this.game = game;
        this.stage = new Stage(new StretchViewport(1280, 720), batch);
        this.skin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));

        Shop shop = new Shop();
        this.resumeButton = this.createResumeButton();
        this.shopActor = new ShopActor(skin, shop, this.game.getInventory(), this.game.getWallet());

    }
    
    /**
     * Create button to resume the game.
     * @return resume button.
     */


    private TextButton createResumeButton() {
        BitmapFont font = this.skin.getFont("font");
		font.getData().setScale(1.5f);
        
        
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
		textButtonStyle.fontColor = Color.BLACK;
		textButtonStyle.up = this.skin.getDrawable("button"); 
		textButtonStyle.down = this.skin.getDrawable("button-pressed"); 
		textButtonStyle.over = this.skin.getDrawable("button-over");

        TextButton button = new TextButton("RESUME GAME", textButtonStyle);
        
        button.setSize(200, 100);
    
        
        button.setPosition(this.stage.getWidth() / 2 - button.getWidth() / 2, 
        this.stage.getHeight() - 100);
        
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ShopScreen.this.conductor.resumeGame();
            }
        });
        
        return button;
    }
    

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
        this.stage.addActor(this.shopActor);
        this.stage.addActor(this.resumeButton);
        this.resumeButton.setPosition(560, 40);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ScreenUtils.clear(.25f, .9f, .9f, 1f);
		this.stage.act(delta);
		this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        this.skin.dispose();
        this.stage.dispose();
    }
    
}
