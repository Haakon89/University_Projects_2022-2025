package inf112.Sun_Mist_Mountain.app.View;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TestSpriteGenerator {

    @BeforeAll
    public static void setupGdx() {
        // Set up a headless application so libGDX can load properly
        var config = new HeadlessApplicationConfiguration();
        var listener = new ApplicationListener() {
            @Override
            public void create() { }

            @Override
            public void resize(int _width, int _height) { }

            @Override
            public void render() { }

            @Override
            public void pause() { }

            @Override
            public void resume() { }

            @Override
            public void dispose() { }
        };

        new HeadlessApplication(listener, config);
        Gdx.gl = Mockito.mock(GL20.class);
    }

    private SpriteGenerator spriteGenerator;
    private Texture objectSheet;
    private Texture environmentSheet;

    @BeforeEach
    public void setUp() {
        this.objectSheet = new Texture(Gdx.files.internal("World/world_objects_sheet.png"));
        this.environmentSheet = new Texture(Gdx.files.internal("World/spritesheet_ground.png"));
        this.spriteGenerator = new SpriteGenerator();
    }

    @Test
    public void testIfGetsRightSprites() {
        TextureRegion house = new TextureRegion(objectSheet, 0, 0, 160, 192);
        TextureRegion tilledEarthCornerTL = new TextureRegion(environmentSheet, 0, 0, 32, 32);

        assertTextureRegionsEqual(house, this.spriteGenerator.getSprite(SpriteName.HOUSE));
        assertTextureRegionsEqual(tilledEarthCornerTL, this.spriteGenerator.getSprite(SpriteName.TILLED_CORNER_TL));

    }

    private void assertTextureRegionsEqual(TextureRegion expected, TextureRegion actual) {
        assertNotNull(actual, "Actual TextureRegion is null");

        assertEquals(expected.getRegionX(), actual.getRegionX(), "X coordinates do not match");
        assertEquals(expected.getRegionY(), actual.getRegionY(), "Y coordinates do not match");
        assertEquals(expected.getRegionWidth(), actual.getRegionWidth(), "Widths do not match");
        assertEquals(expected.getRegionHeight(), actual.getRegionHeight(), "Heights do not match");
    }
}
