package inf112.Sun_Mist_Mountain.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public abstract class ScreenLifecycleTest extends GameTest {

	private Conductor conductor;
	private SpriteBatch spriteBatch;

	@BeforeEach
	public void setUp() {
        // In headless GDX, the spritebatch does not work. In order to test
        // Screens, it must be sufficiently mocked
		this.spriteBatch = mock(SpriteBatch.class);
		when(this.spriteBatch.getTransformMatrix()).thenReturn(new Matrix4());
		when(this.spriteBatch.getColor()).thenReturn(new Color());

		this.conductor = mock(Conductor.class);
	}

	@Test
	public void lifecycleIsOk() {
		Screen screen = this.getScreen(this.conductor, this.spriteBatch);

		assertDoesNotThrow(() -> {
			screen.show();
			screen.resize(123, 456);
			screen.show();
			screen.render(0.1f);
			screen.pause();
			screen.resume();
			screen.render(0.1f);
			screen.hide();
			screen.dispose();
		});
	}

    protected abstract Screen getScreen(Conductor conductor, SpriteBatch spriteBatch);

	/**
	 * @return a sufficiently mocked sprite batch
	 */
	protected SpriteBatch getSpriteBatch() {
		return this.spriteBatch;
	}

}
