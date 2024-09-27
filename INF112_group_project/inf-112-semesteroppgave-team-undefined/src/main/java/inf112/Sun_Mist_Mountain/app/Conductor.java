package inf112.Sun_Mist_Mountain.app;

/**
 * The conductor is responsible for moving between different states in the game.
 */
public interface Conductor {

    /**
     * Initially start the game.
     */
    public void startGame();

    /**
     * Put the game in a paused state.
     */
    public void pauseGame();

    /**
     * Resume the game from a paused state. Does nothing if @{code pauseGame()}
     * was not previously invoked.
     */
    public void resumeGame();
    
    /**
     * View the screen for instructions for the game.
     */
    public void viewInstructions();
    
    /**
     *  View the screen last used.
     */
    public void viewLastScreen();
    
    /**
     * View the shop.
     */
    public void viewShop();


}
