package inf112.Sun_Mist_Mountain.app.Model.Item;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;

/**
 * An Item is anything that the player can pick up in their inventory.
 */
public interface Item {

    /**
     * @return TextureRegion of Item with ItemSpritesheet.
     */
    public TextureRegion getTexture();

    /**
     * What happens if we use item.
     */
    public void use(UseActions actions, PlacedTile tile);

    /**
     * @return {@code true} if this item can be used multiple times.
     */
    public boolean isReuseable();

    /**
     * @return {@code true} if this item can be sold.
     */
    public boolean isSellable();

    /**
     * @return the amount of money the player should receive when selling this
     *         item.
     */
    public int getSellValue();

    /**
     * @return the amount of money it costs to buy this item.
     */
    public int getPrice();

    /**
     * gets the name of the Item
     * @return
     */
    public String getName();

    /**
     * checks if the name of the item is equal to another name
     * @param name
     * @return
     */
    public boolean isEqual(String name);
}
