package no.uib.inf101.sem2.platformer.controller;

import no.uib.inf101.sem2.platformer.model.GameState;
import no.uib.inf101.sem2.platformer.model.MovementState;
import no.uib.inf101.sem2.platformer.model.PlayerDirection;

public interface ControllablePlatformerModel {
    
    /**
     * used to move the player around on the level
     * @param deltaRow movement on the row
     * @param deltaCol movement on the collumns
     * @return true if the player moved
     */
    public boolean movePlayer(int deltaRow, int deltaCol);

    /**
     * @return the state of the game
     */
    public GameState getGameState();

    /**
     * @return the amount of milliseconds that pass between each time the clocktick methode moves of the player
     */
    public int getMillisecondsPerMove();

    /**
     *moves the player down if tickstate is set to falling and up if it is set to jumping each time the timer is called. 
     */
    public void clockTick(String state);

    /**
     * checks if the player is falling or jumping
     * @return a string either 'falling' or 'jumping'
     */
    public String getTickState();

    /**
     * tells the clocktick method that the player is jumping and how fast it should move upwards. 
     * If double jump is active the player can jump one extra time while in the air, otherwise they 
     * can only jump while standing on a collision object.
     */
    public void jumpPlayer();

    /**
     * checks if a position is legal without trying to move the player. 
     * @param deltaRow amount of rows away from the player to test
     * @param deltaCol  amount of collumns away from the player to test
     * @return true if the player could move into the new position and false otherwise
     */
    public boolean checkLegalPosition(int deltaRow, int deltaCol);

    /**
     * changes the state of the game,
     */
    public void setGameState(GameState gameState);

    /**
     * Sets the direction that the player is facing
     * @param playerDirection LEFT or RIGHT
     */
    public void setPlayerDirection(PlayerDirection playerDirection);

    /**
     * Sets the movement state for the Platformermodel
     * @param movementState IDLE, RUNNING, JUMPING or FALLING
     */
    public void setMovementState(MovementState movementState);

    /**
     * sets the index that is used to find which animation frame that should be used.
     * @param frameIndex
     */
    public void setCurrentRunningFrame(int frameIndex);
    
}
