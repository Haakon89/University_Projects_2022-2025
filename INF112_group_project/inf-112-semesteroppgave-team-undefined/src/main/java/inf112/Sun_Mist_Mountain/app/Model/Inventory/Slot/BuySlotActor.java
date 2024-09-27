package inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import inf112.Sun_Mist_Mountain.app.Model.Inventory.Inventory;
import inf112.Sun_Mist_Mountain.app.Model.Player.Wallet;

public class BuySlotActor extends SlotActor {

    private Slot slot;
    
    /**
     * Make an actor of <code>Slot</code> that can be bought.
     */
    public BuySlotActor(Slot slot, Skin skin, Inventory inventory, Wallet wallet) {
        super(slot, skin);
        this.slot = slot;
        this.slot.addListener(this);
        this.addListener(new BuySlotListener(slot, inventory, wallet));
    }
}
