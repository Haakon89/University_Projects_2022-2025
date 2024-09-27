package inf112.Sun_Mist_Mountain.app.Controller;

import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Item.Item;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;

public interface ControllableToolbar {

    /**
     * used to set the current index of the toolbar
     */
    void setActiveIndex(Integer Index);

    /**
     * get the current index of the toolbar
     */
    Integer getActiveIndex();

    /**
     * Uses item on given tile.
     * Check Item.use() and given Tile method to see effect.
     *
     * @return true if you hold an item, false otherwise
     */
    boolean useActiveItem(UseActions actions, PlacedTile tile);

    /**
     * Subtracts {@code amount} from the current active item slot.
     *
     * @return {@code true} if that amount was removed, {@code false} if the
     *         amount was larger than the number of items in the active slot.
     */
    boolean takeActiveItem(int amount);

    /**
     * Places {@code item} to nearest slot with same item, with the
     * {@code amount} added. If there are no slots with the same item, then item
     * will be placed at nearest empty slot.
     *
     * @return {@code true} if item was placed, {@code false} otherwise
     */
    public boolean placeItemInInventory(Item item, int amount);

    /**
     * checks what item is in the active slot
     * @return that Item
     */
    public Item getActiveItem();

    /**
     * Change which row in the inventory is the active toolbar.
     */
    void changeToolbar(); 

}
