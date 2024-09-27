package inf112.Sun_Mist_Mountain.app;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class TestMainMenuScreen extends ScreenLifecycleTest {

	@Test
	public void startButtonStartsGame() {
		var conductor = mock(Conductor.class);
		var batch = this.getSpriteBatch();
		var screen = new MainMenuScreen(conductor, batch);
		screen.show();

		var start = screen.getStartButton();
		TestMainMenuScreen.click(start);

		verify(conductor).startGame();
	}

	@Test
	public void instructionsButtonShowsInstructions() {
		var conductor = mock(Conductor.class);
		var batch = this.getSpriteBatch();
		var screen = new MainMenuScreen(conductor, batch);
		screen.show();

		var instructions = screen.getInstructionsButton();
		TestMainMenuScreen.click(instructions);

		verify(conductor).viewInstructions();
	}

	@Override
	protected Screen getScreen(Conductor conductor, SpriteBatch spriteBatch) {
		return new MainMenuScreen(conductor, spriteBatch);
	}

	/**
	 * Perform a click event on the given button.
	 */
	private static void click(Button button) {
		var downEvent = new InputEvent();
		var upEvent = new InputEvent();

		downEvent.setType(InputEvent.Type.touchDown);
		upEvent.setType(InputEvent.Type.touchUp);

		button.fire(downEvent);
		button.fire(upEvent);
	}

}
