package inf112.Sun_Mist_Mountain.app.Model.Player;

public interface Wallet {
    
    /**
     * @return the amount of money the player has.
     */
    int getMoney();

    /**
     * change the amount of money the player has as long as the new total is non-negative
     * @param change
     */
    void changeMoney(int amount);
}
