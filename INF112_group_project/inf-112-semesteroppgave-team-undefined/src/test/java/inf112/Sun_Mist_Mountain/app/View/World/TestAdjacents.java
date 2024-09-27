package inf112.Sun_Mist_Mountain.app.View.World;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Dirt;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Grass;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Stone;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.WateredDirt;
import inf112.Sun_Mist_Mountain.app.Model.World.Generator;
import inf112.Sun_Mist_Mountain.app.Model.World.World;

public class TestAdjacents extends GameTest {

    @BeforeEach
    public void setup() {
        Generator.unregisterAll();
        Generator.registerTile('G', new Grass());
        Generator.registerTile('D', new Dirt());
        Generator.registerTile('P', new WateredDirt());
        Generator.registerTile('S', new Stone());
    }

    @Test
    public void emptyWorld() {
        var world = Generator.fromString("");
        List<Adjacent> expected = List.of();
        List<Adjacent> actual = getAdjacents(world);

        assertEquals(expected, actual);
    }

    @Test
    public void singleTile() {
        var world = Generator.fromString("G");
        var actual = getAdjacents(world);

        assertEquals(1, actual.size());

        var adjacent = actual.get(0);

        assertNull(adjacent.above());
        assertNull(adjacent.right());
        assertNull(adjacent.below());
        assertNull(adjacent.left());
        assertInstanceOf(Grass.class, adjacent.center().getTile());
    }

    @Test
    public void two() {
        var world = Generator.fromString("GD");
        var actual = getAdjacents(world);

        assertEquals(2, actual.size());

        var first = actual.get(0);

        assertNull(first.above());
        assertNull(first.below());
        assertNull(first.left());
        assertInstanceOf(Dirt.class, first.right().getTile());
        assertInstanceOf(Grass.class, first.center().getTile());

        var second = actual.get(1);

        assertNull(second.above());
        assertNull(second.below());
        assertNull(second.right());
        assertInstanceOf(Grass.class, second.left().getTile());
        assertInstanceOf(Dirt.class, second.center().getTile());
    }

    @Test
    public void middle3x3() {
        var world = Generator.fromString("GGG\nPPD\nDPD");
        var actual = getAdjacents(world);

        assertEquals(9, actual.size());

        var middle = actual.get(4);

        assertInstanceOf(Grass.class, middle.above().getTile());
        assertInstanceOf(Dirt.class, middle.right().getTile());
        assertInstanceOf(WateredDirt.class, middle.below().getTile());
        assertInstanceOf(WateredDirt.class, middle.left().getTile());
        assertInstanceOf(WateredDirt.class, middle.center().getTile());
    }

    @Test
    public void onlyThoseInViewport() {
        var world = Generator.fromString(String.join("\n",
            "GGGDDD",
            "GGDDDG",
            "GDDDGG",
            "DDDGGG",
            "DDGGGD",
            "DGGGDD"
        ));

        // Visible:
        // ------
        // ------
        // ------
        // -DD---
        // -DG---
        // ------

        var topLeft = new Position(1 * Tile.WIDTH, 3 * Tile.HEIGHT);
        var bottomRight = new Position(3 * Tile.WIDTH, 1 * Tile.HEIGHT);

        var adjacents = new Adjacents(world);
        adjacents.setViewport(topLeft, bottomRight);

        var actual = new ArrayList<Tile>();
        for (var adjacent : adjacents) {
            actual.add(adjacent.center().getTile());
        }

        var expected = List.of(Dirt.class, Dirt.class, Dirt.class, Grass.class);

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertInstanceOf(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void topToBottom() {
        var world = Generator.fromString(String.join("\n",
            "GS",
            "DP"));

        var adjacents = new Adjacents(world);

        var actual = new ArrayList<Tile>();
        for (var adjacent : adjacents) {
            actual.add(adjacent.center().getTile());
        }

        var expected = List.of(Grass.class, Stone.class, Dirt.class, WateredDirt.class);

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertInstanceOf(expected.get(i), actual.get(i));
        }
    }

    private static List<Adjacent> getAdjacents(World world) {
        var adjacents = new Adjacents(world);
        var result = new ArrayList<Adjacent>();
        for (var element : adjacents) {
            result.add(element);
        }
        return result;
    }

}
