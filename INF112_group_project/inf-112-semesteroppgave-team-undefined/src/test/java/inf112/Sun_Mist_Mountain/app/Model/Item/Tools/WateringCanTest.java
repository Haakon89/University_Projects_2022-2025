package inf112.Sun_Mist_Mountain.app.Model.Item.Tools;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Item.Item;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.SetupTiles;

public class WateringCanTest extends SetupTiles {

    private Item wateringCan;

    @BeforeEach
    public void setup() {
        this.wateringCan = new WateringCan();
    }

    @Test
    public void useWateringCanTest() {
        var tile = mock(PlacedTile.class);
        var actions = mock(UseActions.class);
        this.wateringCan.use(actions, tile);
        verify(tile).water(actions);
    }

    @Test
    public void isReuseableTest() {
        assertTrue(this.wateringCan.isReuseable());
    }

}
