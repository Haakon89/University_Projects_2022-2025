package inf112.Sun_Mist_Mountain.app.Model.Shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import inf112.Sun_Mist_Mountain.app.Model.Inventory.Inventory;
import inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot.BuySlotActor;
import inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot.SellSlotActor;
import inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot.Slot;
import inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot.SlotActor;
import inf112.Sun_Mist_Mountain.app.Model.Player.Wallet;

public class ShopActor extends Window {

    private Inventory inventory;
    private Shop shop;
    private Wallet wallet;

    private Label balance;

    public ShopActor(Skin skin, Shop shop, Inventory inventory, Wallet wallet) {
        super("Store", skin);
        this.inventory = inventory;
        this.shop = shop;
        this.wallet = wallet;

        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                updateBalanceText(wallet.getMoney());
            }
        });

        Table inventoryTable = this.createInventoryTable(skin);
        Table shopTable = this.createShopTable(skin);
        this.balance = new Label("Balance: " + Integer.toString(this.wallet.getMoney()), skin);

        this.add(this.balance);
        this.row();

        this.add(shopTable);
        this.row().expandY().pad(10);
        this.add(inventoryTable);
        
        this.pack();
        this.setVisible(true);
        this.setMovable(false);

        this.setPosition(Gdx.graphics.getWidth() / 2 - this.getWidth() / 2, 
                    Gdx.graphics.getHeight() / 2 - this.getHeight() / 2);

    }

    /**
     * Updates the <code>balance</code> label to "Balance: <code>m</code>"
     * @param m - New Integer label will update to.
     */
    public void updateBalanceText(int m) {
        this.balance.setText("Balance: " + Integer.toString(m));
    }

    private Table createInventoryTable(Skin skin) {
        Table table = new Table();
        table.add(new Label("Inventory", skin));

        table.row();

        for (Slot[] toolbar : this.inventory.getInventory()) {
            for (Slot slot : toolbar) {
                SlotActor actor = new SellSlotActor(slot, skin, this.wallet);
                table.add(actor);
            }
            table.row();
        }
        
        return table;
    }

    private Table createShopTable(Skin skin) {
        Table table = new Table();
        table.add(new Label("Shop", skin));

        table.row();

        for (Slot[] row : this.shop.getShop()) {
            for (Slot slot : row) {
                BuySlotActor actor = new BuySlotActor(slot, skin, this.inventory, this.wallet);
                table.add(actor);
            }
            table.row();
        }

        return table;
    }

    
}
