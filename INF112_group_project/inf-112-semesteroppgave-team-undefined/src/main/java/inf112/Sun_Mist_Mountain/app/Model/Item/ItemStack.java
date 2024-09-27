package inf112.Sun_Mist_Mountain.app.Model.Item;

public class ItemStack {

    private final Item item;
    private int amount;

    public ItemStack(Item item) {
        this.item = item;
        this.amount = 1;
    }

    public ItemStack(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    /**
     * @return Item in ItemStack
     */
    public Item getItem() {
        return this.item;
    }

    /**
     * @return Amount of Item in ItemStack
     */
    public int getAmount() {
        return this.amount;
    }
}
