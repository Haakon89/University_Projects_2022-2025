package inf112.Sun_Mist_Mountain.app.View.Tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Dirt;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Grass;
import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;
import inf112.Sun_Mist_Mountain.app.View.World.Adjacent;

public class TestAround {

    @Test
    public void allSame() {
        var tile = new Grass();
        var adjacent = TestAround.fromTiles(tile, tile, tile, tile, tile);
        var expected = new Around(Sprite.Base.Grass, false, false, false, false);
        var actual = Around.of(adjacent);
        assertEquals(expected, actual);
    }

    @Test
    public void sameAsNull() {
        var tile = new Grass();
        var adjacent = TestAround.fromTiles(tile, null, tile, null, tile);
        var expected = new Around(Sprite.Base.Grass, false, false, false, false);
        var actual = Around.of(adjacent);
        assertEquals(expected, actual);
    }

    @Test
    public void someDifferent() {
        var grass = new Grass();
        var dirt = new Dirt();
        var adjacent = TestAround.fromTiles(dirt, grass, grass, dirt, dirt);
        var expected = new Around(Sprite.Base.Dirt, false, true, true, false);
        var actual = Around.of(adjacent);
        assertEquals(expected, actual);
    }

    @Test
    public void allDifferent() {
        var grass = new Grass();
        var dirt = new Dirt();
        var adjacent = TestAround.fromTiles(grass, grass, grass, grass, dirt);
        var expected = new Around(Sprite.Base.Dirt, true, true, true, true);
        var actual = Around.of(adjacent);
        assertEquals(expected, actual);
    }

    @Test
    public void allDifferentExceptNull() {
        var grass = new Grass();
        var dirt = new Dirt();
        var adjacent = TestAround.fromTiles(null, grass, grass, grass, dirt);
        var expected = new Around(Sprite.Base.Dirt, false, true, true, true);
        var actual = Around.of(adjacent);
        assertEquals(expected, actual);
    }

    private static Adjacent fromTiles(Tile above, Tile right, Tile below, Tile left, Tile center) {
        return new Adjacent(
            above == null ? null : new PlacedTile(new PixelPosition(0, 32), above),
            right == null ? null : new PlacedTile(new PixelPosition(32, 64), right),
            below == null ? null : new PlacedTile(new PixelPosition(64, 32), below),
            left == null ? null : new PlacedTile(new PixelPosition(32, 0), left),
            new PlacedTile(new PixelPosition(32, 32), center)
        );
    }

}
