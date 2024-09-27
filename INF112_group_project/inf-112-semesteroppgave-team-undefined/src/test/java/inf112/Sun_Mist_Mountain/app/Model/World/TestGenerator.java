package inf112.Sun_Mist_Mountain.app.Model.World;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Dirt;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Grass;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.WateredDirt;

public class TestGenerator extends RandomizingTest {

    @Test
    public void validWorld() {
        var rng = this.nextRandom();

        for (int i = 0; i < HEAVY_ITERATIONS; i++) {
            int width = rng.nextInt(500);
            int height = rng.nextInt(500);

            var generator = new Generator(rng, width, height);
            var world = generator.generate();

            assertEquals(width, world.getWidth());
            assertEquals(height, world.getHeight());

            // Every tile in this world should have a non-null tile with a
            // correct position
            for (int row = 0; row < world.getHeight(); row++) {
                for (int col = 0; col < world.getWidth(); col++) {
                    var tile = world.tileAt(col, row);

                    var expectedPosition = new PixelPosition(row * Tile.HEIGHT, col * Tile.HEIGHT);
                    assertEquals(expectedPosition, tile.getPosition());
                }
            }
        }
    }

    @Test
    public void emptyWorldIsAllGrass() {
        int width = 6;
        int height = 4;
        var world = Generator.empty(width, height);

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Tile actual = world.tileAt(col, row).getTile();
                assertInstanceOf(Grass.class, actual);
            }
        }
    }

    @Test
    public void fromString() {
        Generator.unregisterAll();
        Generator.registerTile('G', new Grass());
        Generator.registerTile('D', new Dirt());
        Generator.registerTile('P', new WateredDirt());

        var world = Generator.fromString(String.join("\n",
            "GDP",
            "PGD",
            "DPG"));

        assertInstanceOf(Dirt.class, world.tileAt(0, 0).getTile());
        assertInstanceOf(WateredDirt.class, world.tileAt(1, 0).getTile());
        assertInstanceOf(Grass.class, world.tileAt(2, 0).getTile());
        assertInstanceOf(WateredDirt.class, world.tileAt(0, 1).getTile());
        assertInstanceOf(Grass.class, world.tileAt(1, 1).getTile());
        assertInstanceOf(Dirt.class, world.tileAt(2, 1).getTile());
        assertInstanceOf(Grass.class, world.tileAt(0, 2).getTile());
        assertInstanceOf(Dirt.class, world.tileAt(1, 2).getTile());
        assertInstanceOf(WateredDirt.class, world.tileAt(2, 2).getTile());
    }

    @Test
    public void emptyMapString() {
        var world = Generator.fromString("");

        assertEquals(0, world.getHeight());
        assertEquals(0, world.getWidth());
        assertThrows(IndexOutOfBoundsException.class, () -> world.tileAt(0, 0));
    }

    @Test
    public void badMapString() {
        assertThrows(IllegalArgumentException.class, () -> Generator.fromString("ab\ncde"));
    }

    @Test
    public void originIsAlwaysOnGround() {
        int height = 50;
        int width = 50;

        for (int i = 0; i < HEAVY_ITERATIONS; i++) {
            var world = new Generator(this.nextRandom(), width, height).generate();
            var origin = world.tileAt(world.getOrigin());

            assertTrue(origin.getTile().isGround());
            if (origin.hasEntity()) {
                assertTrue(origin.getEntity().isGround());
            }
            assertTrue(origin.isGround());
        }
    }

}
