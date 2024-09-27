package inf112.Sun_Mist_Mountain.app.Model.World;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Dirt;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.WateredDirt;

public class TestWorld extends GameTest {

    // GGP
    // DPP
    // DDG
    private World world;

    @BeforeEach
    public void setUp() {
        this.world = Generator.empty(3, 3);

        this.world.tileAt(0, 2).setTile(new WateredDirt());
        this.world.tileAt(1, 0).setTile(new Dirt());
        this.world.tileAt(1, 1).setTile(new WateredDirt());
        this.world.tileAt(1, 2).setTile(new WateredDirt());
        this.world.tileAt(2, 0).setTile(new Dirt());
        this.world.tileAt(2, 1).setTile(new Dirt());
    }

    @Test
    public void tileAtPixelPosition() {
        int x = 2 * Tile.WIDTH;
        int y = 1 * Tile.HEIGHT;
        Tile actual = this.world.tileAt(new PixelPosition(x, y)).getTile();
        assertInstanceOf(WateredDirt.class, actual);
    }

    @Test
    public void negativeOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.world.tileAt(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> this.world.tileAt(0, -1));
    }

    @Test
    public void columnOutOfBounds() {
        var position = new PixelPosition(0, 3 * Tile.WIDTH);
        assertThrows(IndexOutOfBoundsException.class, () -> this.world.tileAt(3, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> this.world.tileAt(position));
    }

    @Test
    public void rowOutOfBounds() {
        var position = new PixelPosition(3 * Tile.HEIGHT, 0);
        assertThrows(IndexOutOfBoundsException.class, () -> this.world.tileAt(0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> this.world.tileAt(position));
    }

    @Test
    public void originIsAPositionInTheMap() {
        PixelPosition origin = this.world.getOrigin();
        assertDoesNotThrow(() -> this.world.tileAt(origin));
    }

    @Test
    public void getTilesDoesNotConjureUpNewTiles() {
        var tiles = this.world.getTiles();

        for (var tile : tiles) {
            var position = tile.getPosition();
            var expected = this.world.tileAt(position);

            assertEquals(expected, tile);
        }
    }

    @Test
    public void nearestTilePosition() {
        var rng = new Random(RandomizingTest.SEED);

        for (int i = 0; i < RandomizingTest.ITERATIONS; i++) {
            var position = new PixelPosition(rng.nextInt(), rng.nextInt());
            PixelPosition rounded = World.nearestTilePosition(position);

            int dx = position.col() - rounded.col();
            int dy = position.row() - rounded.row();

            double length = Math.sqrt(dx * dx + dy * dy);
            double max = Math.sqrt(Tile.WIDTH * Tile.WIDTH + Tile.HEIGHT * Tile.HEIGHT);

            assertTrue(length <= max, "nearest tile " + rounded + " is " + length + " away from " + position);
        }
    }

    @Test
    public void changeSeasonListener() {
        var seasons = new HashSet<Seasons>();
        this.world.addSeasonChangeListener(seasons::add);

        // + 1 to change back to spring
        for (int i = 0; i < World.LENGTH_OF_YEAR + 1; i++) {
            this.world.newDay();
        }

        var expected = Set.of(Seasons.SPRING, Seasons.SUMMER, Seasons.AUTUMN, Seasons.WINTER);
        assertEquals(expected, seasons);
    }

    @Test
    public void tickUpdatesTimeOfDay() {
        var times = new HashSet<TimeOfDay>();
        for (int i = 0; i < 2 * World.TICKS_PER_DAY; i++) {
            times.add(this.world.getTimeOfDay());
            this.world.tick();
        }

        var expected = Set.of(TimeOfDay.MORNING, TimeOfDay.DAY, TimeOfDay.EVENING, TimeOfDay.NIGHT);
        assertEquals(expected, times);
    }

    @Test
    public void tickUpdatesDay() {
        var days = new HashSet<Integer>();

        for (int i = 0; i < World.TICKS_PER_DAY * World.LENGTH_OF_YEAR; i++) {
            days.add(this.world.getDaysPassed());
            this.world.tick();
        }

        var expected = new HashSet<Integer>();
        for (int i = 0; i < World.LENGTH_OF_YEAR; i++) {
            expected.add(i);
        }

        assertEquals(expected, days);
    }

}
