package inf112.Sun_Mist_Mountain.app.Model.Actions;

import inf112.Sun_Mist_Mountain.app.Model.Item.ItemStack;

/**
 * {@code UseActions} contains methods that items, tiles, and entities can call
 * when they are interacted with, for example to give the player an item or
 * change the entity on the placed tile.
 */
public interface UseActions extends TileActions {

    /**
     * Indicates that the used item should be removed from the player's
     * inventory, but only if something else actually happens during this
     * action.
     */
    public void destroyItem();

    /**
     * Decrease the player's stamina, but only if something else actually
     * happens during this action.
     */
    public void exert(int amount);

    /**
     * Add {@code amount} to the player's stamina.
     */
    public void changeStamina(int amount);

    /**
     * Give the player the item stack.
     */
    public void dropItems(ItemStack items);

}
