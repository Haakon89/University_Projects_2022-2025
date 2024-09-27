package inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.Item.Item;
import inf112.Sun_Mist_Mountain.app.Model.Item.Resources.StoneItem;

public class TestSlot extends RandomizingTest {

    private Slot slot;
    private Item item;

    @BeforeEach
    public void setup() {
        this.item = mock(Item.class);
        this.slot = new Slot(this.item, 1);
    }

    @Test
    public void getAmountTest() {
        assertNotEquals(0, this.slot.getAmount());
        assertEquals(1, this.slot.getAmount());
    }

    @Test
    public void takeTest1() {
        when(this.item.isReuseable()).thenReturn(false);

        assertTrue(this.slot.take(1));
    }

    @Test
    public void takeTest2() {
        when(this.item.isReuseable()).thenReturn(false);

        assertFalse(this.slot.take(10));
    }

    @Test
    public void takeTest3() {
        when(this.item.isReuseable()).thenReturn(true);

        assertTrue(this.slot.take(10));
    }

    @Test
    public void addWhenFull() {
        var slot = new Slot(this.item, Slot.MAX_STACK);

        int remaining = slot.add(item, 5);
        assertEquals(5, remaining);
    }

    @Test
    public void addFills() {
        var slot = new Slot(this.item, Slot.MAX_STACK - 12);

        assertEquals(0, slot.add(item, 12));
        assertEquals(9, slot.add(item, 9));
    }

    @Test
    public void addTakeInverse() {
        for (int i = 0; i < ITERATIONS; i++) {
            int amount = this.nextInteger() % (Slot.MAX_STACK / 2);
            var slot = new Slot(this.item, 1);

            assertEquals(0, slot.add(item, amount));
            assertTrue(slot.take(amount));
            assertEquals(1, slot.getAmount());
        }
    }

    @Test
    public void addDifferentItem() {
        var slot = new Slot(this.item, 5);
        var item2 = mock(StoneItem.class);

        assertEquals(23, slot.add(item2, 23));
    }

    @Test
    public void notifyOnAdd() {
        var listener = mock(SlotListener.class);
        this.slot.addListener(listener);
        this.slot.add(this.item, 5);

        verify(listener).event();
    }

    @Test
    public void notEmptyAfterAdd() {
        for (int i = 0; i < ITERATIONS; i++) {
            var slot = new Slot();
            int amount = this.nextInteger();

            if (amount > 0) {
                slot.add(this.item, amount);
                assertFalse(slot.isEmpty());
            }
        }
    }

    @Test
    public void emptyAfterTake() {
        this.slot.take(this.slot.getAmount());
        assertTrue(this.slot.isEmpty());
    }

}
