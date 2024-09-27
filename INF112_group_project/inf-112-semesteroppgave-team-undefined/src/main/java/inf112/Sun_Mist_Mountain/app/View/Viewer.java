package inf112.Sun_Mist_Mountain.app.View;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface Viewer {

    /**
     * Draw to the world layer.
     */
    public void drawWorld(SpriteBatch batch);

    /**
     * Draw to the UI layer.
     */
    public void drawUi(SpriteBatch batch);

    /**
     * Resize the viewer.
     * @param newWidth
     * @param newHeight
     */
    public void resize(Rectangle screen);

    /**
     * Dispose of any resources associated with this viewer.
     */
    public void dispose();

    /**
     * Called when this viewer is visible.
     */
    public default void show() {}

    /**
     * Called when this viewer is no longer visible.
     */
    public default void hide() {}

}
