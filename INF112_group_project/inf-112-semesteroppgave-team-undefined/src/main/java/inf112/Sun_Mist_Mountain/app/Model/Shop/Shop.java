package inf112.Sun_Mist_Mountain.app.Model.Shop;

import inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot.Slot;
import inf112.Sun_Mist_Mountain.app.Model.Item.Resources.StoneItem;
import inf112.Sun_Mist_Mountain.app.Model.Item.Seeds.Carrot;
import inf112.Sun_Mist_Mountain.app.Model.Item.Seeds.GenericSeed;
import inf112.Sun_Mist_Mountain.app.Model.Item.Tools.Hoe;
import inf112.Sun_Mist_Mountain.app.Model.Item.Tools.Pickaxe;
import inf112.Sun_Mist_Mountain.app.Model.Item.Tools.WateringCan;

public class Shop {

    public final int SHOP_ROWS = 3;
    public final int SHOP_COLS = 10;

    private Slot[][] shop;

    public Shop() {
        this.shop = new Slot[SHOP_ROWS][SHOP_COLS];
        for (int i = 0; i < SHOP_ROWS; i++) {
            for (int j = 0; j < SHOP_COLS; j++) {
                this.shop[i][j] = new Slot(null, 0);
            }
        }

        this.setItemsInShop();
    }

    /**
     * @return the rows of slots in this shop.
     */
    public Slot[][] getShop() {
        return this.shop;
    }

    private void setItemsInShop() {
        this.shop[0][0] = new Slot(new Hoe(), 1);
        this.shop[0][1] = new Slot(new WateringCan(), 1);
        this.shop[0][2] = new Slot(new Pickaxe(), 1);
        this.shop[0][3] = new Slot(new GenericSeed(), 5);
        this.shop[0][4] = new Slot(new Carrot(), 10);
        this.shop[0][5] = new Slot(new StoneItem(), 5);
    }

}
