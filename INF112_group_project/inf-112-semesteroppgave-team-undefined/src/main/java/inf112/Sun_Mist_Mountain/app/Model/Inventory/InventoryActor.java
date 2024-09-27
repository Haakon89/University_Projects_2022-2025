package inf112.Sun_Mist_Mountain.app.Model.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import inf112.Sun_Mist_Mountain.app.Controller.ControllableInventoryActor;
import inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot.Slot;
import inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot.SlotActor;

public class InventoryActor extends Window implements ControllableInventoryActor {

    private Inventory inventory;

    /**
     * Makes a <code>InventoryActor</code> that can be used as <code>Actor</code>.
     */
    public InventoryActor(Inventory inventory, Skin skin) {
        super("Inventory", skin);
        this.inventory = inventory;

        this.row();
        
        for (Slot[] toolbar : this.inventory.getInventory()) {
            for (Slot slot : toolbar) {
                SlotActor actor = new SlotActor(slot, skin);
                this.add(actor);
            }
            this.row();
        }
        this.pack();
        this.setVisible(false);

        this.setPosition(Gdx.graphics.getWidth() / 2 - this.getWidth() / 2, 
                    Gdx.graphics.getHeight() / 2 - this.getHeight() / 2);
    }

    @Override
    public void changeVisibility() {
        this.setVisible(!this.isVisible());
    }
    
}
