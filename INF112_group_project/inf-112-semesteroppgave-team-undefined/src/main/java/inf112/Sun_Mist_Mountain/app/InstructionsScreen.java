package inf112.Sun_Mist_Mountain.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class InstructionsScreen implements Screen {

	private final Stage stage;
	private final Skin skin;
	private final Conductor conductor;
	private final Image controls;
	private final TextButton backButton;

	public InstructionsScreen(Conductor conductor, SpriteBatch batch) {
		Texture controls = new Texture(Gdx.files.internal("metalui/controls.png"));

		this.conductor = conductor;
		this.controls = new Image(controls);

		this.skin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));
		this.stage = new Stage(new StretchViewport(2000, 1000), batch);

		this.backButton = this.createBackButton();
		this.stage.addActor(this.controls);

		Gdx.input.setInputProcessor(this.stage);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this.stage);
		this.stage.addActor(this.backButton);
		this.backButton.setPosition(900, 50);
		this.controls.setPosition(0, -50);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		this.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		this.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		this.stage.getViewport().update(width, height, true);
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
	 * Create button that returns to the previous screen.
	 * @return Back Button
	 */

	private TextButton createBackButton() {
		BitmapFont font = this.skin.getFont("font");
		font.getData().setScale(4f);

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.fontColor = Color.BLACK;
		textButtonStyle.up = this.skin.getDrawable("button"); 
		textButtonStyle.down = this.skin.getDrawable("button-pressed"); 
		textButtonStyle.over = this.skin.getDrawable("button-over");

		var button = new TextButton("BACK", textButtonStyle);
		button.setSize(200, 100);

		button.addListener(new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		    	InstructionsScreen.this.conductor.viewLastScreen();
		    }
		});

		return button;
	}

}
