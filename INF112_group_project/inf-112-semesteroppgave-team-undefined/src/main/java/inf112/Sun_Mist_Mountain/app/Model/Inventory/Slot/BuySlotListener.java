package inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import inf112.Sun_Mist_Mountain.app.Model.Inventory.Inventory;
import inf112.Sun_Mist_Mountain.app.Model.Player.Wallet;

public class BuySlotListener extends ClickListener {

    private Slot slot;
    private Inventory inventory;
    private Wallet wallet;

    public BuySlotListener(Slot itemToBuy, Inventory inventory, Wallet wallet) {
        super();
        this.slot = itemToBuy;
        this.inventory = inventory;
        this.wallet = wallet;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if (this.slot.getItem() != null) {
            if (this.slot.getItem().getPrice() <= this.wallet.getMoney()) {
                this.wallet.changeMoney(-this.slot.getItem().getPrice());
                this.inventory.placeItemInInventory(this.slot.getItem(), 1);
            }
        }
    }

}
