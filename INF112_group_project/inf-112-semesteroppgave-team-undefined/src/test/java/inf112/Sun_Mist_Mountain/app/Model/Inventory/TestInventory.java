package inf112.Sun_Mist_Mountain.app.Model.Inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot.Slot;
import inf112.Sun_Mist_Mountain.app.Model.Item.Item;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Dirt;
import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;

public class TestInventory extends GameTest {
    
    private UseActions actions;
    private Inventory inventory;
    private PlacedTile tile;

    @BeforeEach
    public void setup() {
        this.actions = mock(UseActions.class);
        this.inventory = new Inventory();
        this.tile = new PlacedTile(new PixelPosition(0, 0), new Dirt());
    }

    @Test
    public void getActiveIndexTest() {
        Assertions.assertEquals(0, this.inventory.getActiveIndex());
    }

    
    @Test
    public void setActiveIndexTest() {
        this.inventory.setActiveIndex(5);
        Assertions.assertEquals(5, this.inventory.getActiveIndex());
    }
    
    @Test
    public void setActiveIndexTest2() {
        this.inventory.setActiveIndex(10);
        Assertions.assertEquals(0, this.inventory.getActiveIndex());
    }
    
    @Test
    public void setActiveIndexTest3() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> this.inventory.setActiveIndex(11));
    }

    @Test
    public void useActiveItemTest() {
        Assertions.assertFalse(this.inventory.useActiveItem(this.actions, this.tile));
    }

    @Test
    public void useActiveItemTest2() {
        this.inventory.setActiveIndex(9);
        Assertions.assertFalse(this.inventory.useActiveItem(this.actions, this.tile));
    }

    @Test
    public void getActiveToolbarTest() {
        Slot[] activeToolbar = this.inventory.getInventory()[0];
        assertEquals(activeToolbar, this.inventory.getActiveToolbar());
    }

    @Test
    public void changeToolbarTest1() {
        Slot[] toolbarToBe = this.inventory.getInventory()[1];
        this.inventory.changeToolbar();
        assertEquals(toolbarToBe, this.inventory.getActiveToolbar());
    }

    @Test
    public void changeToolbarTest2() {
        Slot[] toolbarToBe = this.inventory.getInventory()[0];
        this.inventory.changeToolbar(); // [1]
        this.inventory.changeToolbar(); // [2]
        this.inventory.changeToolbar(); // [3] -> [0]
        assertEquals(toolbarToBe, this.inventory.getActiveToolbar());
    }

    @Test
    public void placeItemInInventoryTest1() {
        // Checks if we find the same item in active toolbar.
        Slot slot = mock(Slot.class);
        Item item = mock(Item.class);
        when(slot.getItem()).thenReturn(item);
        when(slot.getAmount()).thenReturn(1);

        this.inventory.getActiveToolbar()[0] = slot;

        assertTrue(this.inventory.placeItemInInventory(item, 0));

    }

    @Test
    public void placeItemInInventoryTest2() {
        // Checks if we find an empty slot not in active toolbar.
        Slot slot = mock(Slot.class);
        Item item = mock(Item.class);
        when(slot.getItem()).thenReturn(item);

        for (int i = 0; i < Inventory.TOOLBAR_SIZE; i++) {
            this.inventory.getActiveToolbar()[i] = slot;
        }

        Item itemToBePlaced = mock(Item.class);

        assertTrue(this.inventory.placeItemInInventory(itemToBePlaced, 0));
    }

    @Test
    public void placeItemInInventoryTest3() {
        // Checks that we do not find any open slots or slots with the same item.
        Item item = mock(Item.class);
        Slot slot = new Slot(item, Slot.MAX_STACK);

        for (int i = 0; i < Inventory.TOOLBAR_COUNT; i++) {
            for (int j = 0; j < Inventory.TOOLBAR_SIZE; j++) {
                this.inventory.getInventory()[i][j] = slot;
            }
        }

        Item itemToBePlaced = mock(Item.class);

        assertFalse(this.inventory.placeItemInInventory(itemToBePlaced, 1));
    }

}
