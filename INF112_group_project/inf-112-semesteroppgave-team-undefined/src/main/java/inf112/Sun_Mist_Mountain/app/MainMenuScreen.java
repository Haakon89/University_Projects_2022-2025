package inf112.Sun_Mist_Mountain.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import inf112.Sun_Mist_Mountain.app.View.AnimationState;
import inf112.Sun_Mist_Mountain.app.View.Animator;

public class MainMenuScreen implements Screen {

	private final Conductor conductor;
	private final Stage stage;
	private final Skin skin;
	private final Image character;
	private final Image SunMistImage;
	private final Animator animator;
	private final TextButton startButton, instructionsButton;

	public MainMenuScreen(Conductor conductor, SpriteBatch batch) {
		this.animator = new Animator();
		this.conductor = conductor;
		this.stage = new Stage(new StretchViewport(1920, 1000), batch);

		Texture SunMistMountain = new Texture(Gdx.files.internal("metalui/startskjerm.png"));
		Texture SunMistCharacter = new Texture(Gdx.files.internal("Player/character.png"));
	

		this.SunMistImage = new Image(SunMistMountain);
		this.character = new Image(SunMistCharacter);
		this.stage.addActor(this.SunMistImage);
		this.stage.addActor(this.character);
		this.skin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));
		this.startButton = this.createStartButton();
		this.instructionsButton = this.createInstructionsButton();
	}

	/**
	 * @return the start button
	 */
	public Button getStartButton() {
		return this.startButton;
	}

	/**
	 * @return the instructions button
	 */
	public Button getInstructionsButton() {
		return this.instructionsButton;
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this.stage);

		this.stage.addActor(this.startButton);
		this.stage.addActor(instructionsButton);
		
		this.startButton.setPosition(1120, 120);
		
		this.instructionsButton.setPosition(400, 120);

		this.SunMistImage.setPosition(0, 0);
		this.SunMistImage.addAction(Actions.sequence(Actions.alpha(0f), Actions.fadeIn(5f)));
		
		

		this.character.setPosition(0, 400);
		this.character.addAction(Actions.sequence(Actions.alpha(0.7f),Actions.moveBy(2000, 0, 100)));

		this.animator.setState(AnimationState.WALKING_RIGHT);
	}

	@Override
	public void render(float delta) {
    	this.stage.act(delta);
    	this.animator.update(delta);

		ScreenUtils.clear(.25f, .9f, .9f, 1f);

    	TextureRegion texture = this.animator.getCurrentFrame();
    	TextureRegionDrawable texture_d = new TextureRegionDrawable(texture);
    	this.character.setDrawable(texture_d);

    	this.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// empty
	}

	@Override
	public void pause() {
		// empty
	}

	@Override
	public void resume() {
		// empty
	}

	@Override
	public void hide() {
		// empty
	}

	@Override
	public void dispose() {
		// empty
	}

	/**
	 * Create the button that starts up the game.
	 * @return the button to start the game.
	 */
	private TextButton createStartButton() {
		BitmapFont font = this.skin.getFont("font");

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.fontColor = Color.BLACK;
		textButtonStyle.up = this.skin.getDrawable("button"); 
		textButtonStyle.down = this.skin.getDrawable("button-pressed"); 
		textButtonStyle.over = this.skin.getDrawable("button-over");

		var button = new TextButton("NEW GAME", textButtonStyle);
		button.setSize(400, 200);

		button.addListener(new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		    	MainMenuScreen.this.conductor.startGame();
		    }
		});

		return button;
	}
	
	/**
	 * Create button to show the instructions for the game.
	 * @return Instruction button
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
		button.setSize(400, 200);

		button.addListener(new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		    	MainMenuScreen.this.conductor.viewInstructions();
		    }
		});

		return button;
	}
}
