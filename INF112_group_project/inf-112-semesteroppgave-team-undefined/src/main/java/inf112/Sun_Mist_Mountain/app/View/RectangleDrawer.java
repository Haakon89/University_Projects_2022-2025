package inf112.Sun_Mist_Mountain.app.View;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class RectangleDrawer {

    private final TextureRegion texture;
    private Rectangle rectangle;

    /**
     * Create an object which will draw the given color to a rectangle on the
     * screen.
     */
    public RectangleDrawer(Color color) {
        this.texture = new TextureRegion(RectangleDrawer.create1x1Texture(color));
        this.rectangle = new Rectangle();
    }

    /**
     * Update the rectangle that this draws to.
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Draw the rectangle with the given color.
     */
    public void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
    }

    /**
     * Dispose of the data in this drawer.
     */
    public void dispose() {
        this.texture.getTexture().dispose();
    }

    /**
     * @return a 1 pixel wide by 1 pixel high texture with the given color.
     */
    public static Texture create1x1Texture(Color color) {
        var data = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        data.drawPixel(0, 0, Color.rgba8888(color));
        return new Texture(data);
    }

}
