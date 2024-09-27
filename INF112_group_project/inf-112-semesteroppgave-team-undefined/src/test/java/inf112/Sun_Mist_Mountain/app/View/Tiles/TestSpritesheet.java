package inf112.Sun_Mist_Mountain.app.View.Tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.Math.PositionRandom;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;

public class TestSpritesheet extends GameTest {

    @Test
    public void summerOnlyDuplicatesMiddle() {
        var spritesheet = new SummerSpritesheet(new EmptySpritesheet());
        var expected = Set.of(spritesheet.getBase(new Around(Sprite.Base.Grass, true, true, true, true), 0, 0));
        assertNoUnexpectedDuplicates(spritesheet, expected);
    }

    @Test
    public void fallOnlyDuplicatesMiddle() {
        var spritesheet = new FallSpritesheet(new EmptySpritesheet());
        var expected = Set.of(spritesheet.getBase(new Around(Sprite.Base.Grass, true, true, true, true), 0, 0));
        assertNoUnexpectedDuplicates(spritesheet, expected);
    }

    @Test
    public void winterOnlyDuplicatesMiddle() {
        var rng = new PositionRandom(new Random(RandomizingTest.SEED));
        var spritesheet = new WinterSpritesheet(new EmptySpritesheet(), rng);
        var expected = Set.of(spritesheet.getBase(new Around(Sprite.Base.Grass, true, true, true, true), 0, 0));
        assertNoUnexpectedDuplicates(spritesheet, expected);
    }

    @Test
    public void commonNoDuplicates() {
        var rng = new PositionRandom(new Random(RandomizingTest.SEED));
        var spritesheet = new CommonSpritesheet(rng);
        var expected = Set.<TextureRegion>of();
        assertNoUnexpectedDuplicates(spritesheet, expected);
    }

    @Test
    public void commonEarthVarieties() {
        var rng = new Random(RandomizingTest.SEED);
        var spritesheet = new CommonSpritesheet(new PositionRandom(rng));
        var earths = new HashSet<TextureRegion>();

        for (var i = 0; i < RandomizingTest.ITERATIONS; i++) {
            earths.add(spritesheet.getBase(new Around(Sprite.Base.Earth, false, false, false, false), rng.nextInt(), rng.nextInt()));
        }

        assertTrue(1 < earths.size(), "there should be more than 1 unique earth varieties");
    }

    @Test
    public void winterEarthVarieties() {
        var rng = new Random(RandomizingTest.SEED);
        var spritesheet = new WinterSpritesheet(new EmptySpritesheet(), new PositionRandom(rng));
        var earths = new HashSet<TextureRegion>();

        for (var i = 0; i < RandomizingTest.ITERATIONS; i++) {
            earths.add(spritesheet.getBase(new Around(Sprite.Base.Earth, false, false, false, false), rng.nextInt(), rng.nextInt()));
        }

        assertTrue(1 < earths.size(), "there should be more than 1 unique earth varieties");
    }

    @Test
    public void allEntitySprites() {
        var rng = new PositionRandom(new Random(RandomizingTest.SEED));
        var common = new CommonSpritesheet(rng);

        var sheets = new Spritesheet[] {
            common,
            new SummerSpritesheet(common),
            new FallSpritesheet(common),
            new WinterSpritesheet(common, rng),
        };

        for (var sheet : sheets) {
            for (var entity : inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite.values()) {
                assertNotNull(sheet.getEntity(entity));
            }
        }
    }

    /**
     * Check that only the textures provided in {@code expected} are actually
     * duplicated.
     */
    private void assertNoUnexpectedDuplicates(Spritesheet to, Set<TextureRegion> expected) {
        var encountered = new HashSet<TextureRegion>();
        var actual = new HashSet<TextureRegion>();

        for (var texture : to.getBaseTextures()) {
            if (!encountered.add(texture)) {
                actual.add(texture);
            }
        }

        assertEquals(expected, actual);
    }

}
