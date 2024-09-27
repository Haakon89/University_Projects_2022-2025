package inf112.Sun_Mist_Mountain.app.Model.Item.Tools;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Item.Item;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.SetupTiles;

public class HoeTest extends SetupTiles {

    Item hoe;

    @BeforeEach
    public void setup() {
        this.hoe = new Hoe();
    }

    @Test
    public void useHoeTest() {
        var tile = mock(PlacedTile.class);
        var actions = mock(UseActions.class);
        this.hoe.use(actions, tile);
        verify(tile).till(actions);
    }

    @Test
    public void isReuseableTest() {
        assertTrue(this.hoe.isReuseable());
    }
}
