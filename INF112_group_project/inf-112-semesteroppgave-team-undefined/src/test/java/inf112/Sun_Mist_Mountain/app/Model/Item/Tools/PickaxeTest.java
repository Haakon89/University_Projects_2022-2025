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

public class PickaxeTest extends SetupTiles {

    Item pickaxe;

    @BeforeEach
    public void setup() {
        this.pickaxe = new Pickaxe();
    }

    @Test
    public void usePickaxeTest() {
        var tile = mock(PlacedTile.class);
        var actions = mock(UseActions.class);
        this.pickaxe.use(actions, tile);
        verify(tile).mine(actions);
    }

    @Test
    public void isReuseableTest() {
        assertTrue(this.pickaxe.isReuseable());
    }

}
