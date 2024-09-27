package inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import inf112.Sun_Mist_Mountain.app.Model.Player.Wallet;

public class SellSlotListener extends ClickListener {

    private Slot slot;
    private Wallet wallet;

    public SellSlotListener(Slot itemToSell, Wallet wallet) {
        super();
        this.slot = itemToSell;
        this.wallet = wallet;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if (this.slot.getItem() != null) {
            if (this.slot.getItem().isSellable()) {
                this.wallet.changeMoney(this.slot.getItem().getSellValue());
                this.slot.take(1);
            }
        }
    }
    
}
