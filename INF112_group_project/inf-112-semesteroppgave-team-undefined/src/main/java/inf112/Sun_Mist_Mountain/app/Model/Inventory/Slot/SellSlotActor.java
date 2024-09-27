package inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import inf112.Sun_Mist_Mountain.app.Model.Player.Wallet;

public class SellSlotActor extends SlotActor {

    private Slot slot;

    /**
     * Make an actor of a <code>Slot</code> that can (depending on <code>Item.isSellable()</code>) be sold
     */
    public SellSlotActor(Slot slot, Skin skin, Wallet wallet) {
        super(slot, skin);
        this.slot = slot;
        this.slot.addListener(this);
        this.addListener(new SellSlotListener(slot, wallet));
    }


    
}
