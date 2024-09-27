package inf112.Sun_Mist_Mountain.app.Model.Inventory;

import inf112.Sun_Mist_Mountain.app.Controller.ControllableToolbar;
import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot.Slot;
import inf112.Sun_Mist_Mountain.app.Model.Item.Item;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;

public class Inventory implements ControllableToolbar {

    public static final int TOOLBAR_COUNT = 3;
    public static final int TOOLBAR_SIZE = 10;
    public static final int TOOLBAR_SLOT_SIZE = 32;

    private int activeIndex;
    private int activeToolbarIndex;

    private final Slot[][] inventory;
    private Slot[] toolbar;

    /**
     * Creates the default Toolbar that 'equips' the first index.
     */
    public Inventory() {
        this.inventory = new Slot[TOOLBAR_COUNT][TOOLBAR_SIZE];

        for (int i = 0; i < TOOLBAR_COUNT; i++) {
            for (int j = 0; j < TOOLBAR_SIZE; j++) {
                this.inventory[i][j] = new Slot(null, 0);
            }
        }

        this.activeToolbarIndex = 0;
        this.toolbar = inventory[this.activeToolbarIndex];

        this.activeIndex = 0;
    }

    /**
     * @return the rows of slots in this inventory.
     */
    public Slot[][] getInventory() {
        return this.inventory;
    }

    /**
     * @return the row of slots that is currently active.
     */
    public Slot[] getActiveToolbar() {
        return this.toolbar;
    }

    @Override
    public Integer getActiveIndex() { 
        return this.activeIndex;
    }

    @Override
    public void changeToolbar() {
        try {
            this.activeToolbarIndex += 1;
            this.toolbar = this.inventory[this.activeToolbarIndex];
            setActiveIndex(this.getActiveIndex());
        } catch (Exception e) {
            this.activeToolbarIndex = 0;
            this.toolbar = this.inventory[this.activeToolbarIndex];
            setActiveIndex(this.getActiveIndex());
        }
    }

    @Override
    public void setActiveIndex(Integer index) { 
        if (index == -1)
            this.activeIndex = Inventory.TOOLBAR_SIZE - 1;
        else if (index == 10)
            this.activeIndex = 0;
        else {
            this.checkIndexOutOfBounds(index);
            this.activeIndex = index;
        }
    }

    @Override
    public boolean useActiveItem(UseActions actions, PlacedTile tile) {
        var slot = this.toolbar[this.activeIndex];
        if (!slot.isEmpty()) {
            slot.getItem().use(actions, tile);
            return true;
        }

        return false;
    }

    @Override
    public Item getActiveItem() {
        return this.toolbar[this.activeIndex].getItem();
    }
    
    @Override
    public boolean placeItemInInventory(Item item, int amount) {
        int remaining = amount;

        for (var slot : this.toolbar) {
            remaining = slot.add(item, remaining);

            if (remaining == 0) {
                return true;
            }
        }

        for (var slots : this.inventory) {
            for (var slot : slots) {
                remaining = slot.add(item, remaining);
                if (remaining == 0) {
                    return true;
                }
            }
        }

        return remaining == 0;
    }

    @Override
    public boolean takeActiveItem(int amount) {
        return this.toolbar[this.activeIndex].take(amount);
    }

    private void checkIndexOutOfBounds(Integer index) {
        if (index >= Inventory.TOOLBAR_SIZE || index < 0)
            throw new IndexOutOfBoundsException();
    }

}
