package inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import inf112.Sun_Mist_Mountain.app.GameTest;

public class TestSlotActor extends GameTest {

    @Test
    public void skinIsNotNullOnEvent() {
        var skin = mock(Skin.class);
        var slot = new Slot();
        var actor = new SlotActor(slot, skin);

        assertDoesNotThrow(actor::event);
    }

}
