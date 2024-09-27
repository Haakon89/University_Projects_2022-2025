package inf112.Sun_Mist_Mountain.app;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestInstructionsScreen extends ScreenLifecycleTest {

    @Override
    protected Screen getScreen(Conductor conductor, SpriteBatch spriteBatch) {
        return new InstructionsScreen(conductor, spriteBatch);
    }

}
