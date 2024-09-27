package inf112.Sun_Mist_Mountain.app;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShopTest extends ScreenLifecycleTest{
	
	 @Override
	    protected Screen getScreen(Conductor conductor, SpriteBatch spriteBatch) {
		  GameScreen game = new GameScreen(spriteBatch, conductor);
	        return new ShopScreen(conductor, game, spriteBatch);
	    }

}

