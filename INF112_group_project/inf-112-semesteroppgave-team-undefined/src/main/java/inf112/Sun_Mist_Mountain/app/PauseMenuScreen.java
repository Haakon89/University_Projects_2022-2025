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

public class PauseMenuScreen implements Screen {

	    private Stage stage;
	    private Skin skin;
	    private TextButton pauseButton;
	    private TextButton instructionsButton;
	    private final Conductor conductor;
	

	public PauseMenuScreen(Conductor conductor, SpriteBatch spriteBatch) {
		this.conductor = conductor;
		this.stage = new Stage(new StretchViewport(2000, 1000), spriteBatch);
		Gdx.input.setInputProcessor(this.stage);
		this.skin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));

		this.pauseButton = this.createPauseButton();
		this.instructionsButton = this.createInstructionsButton();
	}



	@Override
	public void show() {
		Gdx.input.setInputProcessor(this.stage);
		this.stage.addActor(this.pauseButton);
		this.stage.addActor(this.instructionsButton);
	}
	
	/**
	 * Create button that must be pressed to resume the game
	 * @return resume game button
	 */

	private TextButton createPauseButton() {
		BitmapFont font = this.skin.getFont("font");
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.fontColor = Color.BLACK;
		textButtonStyle.up = this.skin.getDrawable("button"); 
		textButtonStyle.down = this.skin.getDrawable("button-pressed"); 
		textButtonStyle.over = this.skin.getDrawable("button-over");

		var button = new TextButton("RESUME GAME", textButtonStyle);
		button.setPosition(800, 160);
		button.setSize(400, 200);
		
	   

	 
		button.addListener(new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		    	PauseMenuScreen.this.conductor.resumeGame();
		    }
		});

		return button;
	}
	
	/**
	 * Create button to show instructions for the game.
	 * @return instructions button
	 */
	private TextButton createInstructionsButton() {
		BitmapFont font = this.skin.getFont("font");
		font.getData().setScale(2f);

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.fontColor = Color.BLACK;
		textButtonStyle.up = this.skin.getDrawable("button"); 
		textButtonStyle.down = this.skin.getDrawable("button-pressed"); 
		textButtonStyle.over = this.skin.getDrawable("button-over");

		var button = new TextButton("VIEW INSTRUCTIONS", textButtonStyle);
		button.setPosition(800, 560);
		button.setSize(400, 200);

		button.addListener(new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		    	PauseMenuScreen.this.conductor.viewInstructions();
		    }
		});

		return button;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ScreenUtils.clear(.25f, .9f, .9f, 1f);
		this.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		this.stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		this.stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		this.stage.dispose();
	}
}
