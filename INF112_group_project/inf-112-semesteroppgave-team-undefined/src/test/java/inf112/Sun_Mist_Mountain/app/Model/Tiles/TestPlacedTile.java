package inf112.Sun_Mist_Mountain.app.Model.Tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Entity;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Dirt;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Grass;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.WateredDirt;
import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;

public class TestPlacedTile extends GameTest {

    private PlacedTile[] tiles;
    private Entity entity;
    private UseActions actions;

    @BeforeEach
    public void setUp() {
        this.tiles = new PlacedTile[] {
            new PlacedTile(new PixelPosition(32, 32), new Grass()),
            new PlacedTile(new PixelPosition(100, 12), new Dirt()),
            new PlacedTile(new PixelPosition(5, 62), new WateredDirt()),
        };

        this.entity = mock(Entity.class);
        this.actions = mock(UseActions.class);
    }

    @Test
    public void tillDoesNotChangePosition() {
        for (var placedTile : this.tiles) {
            PixelPosition expected = placedTile.getPosition();
            placedTile.till(actions);
            PixelPosition actual = placedTile.getPosition();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void isGround() {
        for (var placedTile : this.tiles) {
            boolean expected = placedTile.getTile().isGround();
            boolean actual = placedTile.isGround();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void wontChangeWithEntity() {
        Entity entity = mock(Entity.class);

        for (PlacedTile placedTile : this.tiles) {
            placedTile.setEntity(entity);
            placedTile.till(actions);
            placedTile.mine(actions);

            verifyNoInteractions(actions);
        }
    }

    @Test
    public void entityIsGroundPriority() {
        when(this.entity.isGround()).thenReturn(false);
        for (PlacedTile tile : this.tiles) {
            tile.setEntity(entity);
            assertFalse(tile.isGround());
        }
    }

    @Test
    public void getEntityReturnsNull() {
        for (PlacedTile tile : this.tiles) {
            assertNull(tile.getEntity());
        }
    }

}
