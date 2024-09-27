package inf112.Sun_Mist_Mountain.app.Model.Tiles;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Dirt;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Grass;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Stone;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.WateredDirt;
import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;

public class SetupTiles extends GameTest {

    /**
     * Creates a PlacedTiles: "GDSP"
     * @return
     */
    public PlacedTile[] setupTiles() {
        PlacedTile[] tiles = new PlacedTile[] {
            new PlacedTile(new PixelPosition(0, 1), new Grass()),
            new PlacedTile(new PixelPosition(0, 2), new Dirt()),
            new PlacedTile(new PixelPosition(0, 3), new Stone()),
            new PlacedTile(new PixelPosition(0, 4), new WateredDirt())
        };

        return tiles;
    }
}
