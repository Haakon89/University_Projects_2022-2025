package inf112.Sun_Mist_Mountain.app;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PauseMenuTest extends ScreenLifecycleTest{
	@Override
    protected Screen getScreen(Conductor conductor, SpriteBatch spriteBatch) {
        return new PauseMenuScreen(conductor, spriteBatch);
    }
}
