package inf112.Sun_Mist_Mountain.app.Model.Item;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestItemStack {
    
    private Item mockItem;
    private ItemStack itemStack;

    @BeforeEach
    public void setup() {
        this.mockItem = mock(Item.class);
        this.itemStack = new ItemStack(mockItem);
    }

    @Test
    public void getItemTest() {
        Assertions.assertEquals(mockItem, itemStack.getItem());
    }

    @Test
    public void getAmountTest() {
        Assertions.assertEquals(1, itemStack.getAmount());
    }

}
