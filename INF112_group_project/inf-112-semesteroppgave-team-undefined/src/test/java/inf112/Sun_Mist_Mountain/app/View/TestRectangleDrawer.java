package inf112.Sun_Mist_Mountain.app.View;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

import inf112.Sun_Mist_Mountain.app.GameTest;

public class TestRectangleDrawer extends GameTest {

    @Test
    public void create1x1Texture() {
        var expected = new Color(0x12345678);

        // Create a texture
        Texture texture = RectangleDrawer.create1x1Texture(expected);

        // Get its pixel data
        TextureData data = texture.getTextureData();
        Pixmap pixmap = data.consumePixmap();

        var actual = new Color(pixmap.getPixel(0, 0));

        assertEquals(expected, actual);
    }

}
