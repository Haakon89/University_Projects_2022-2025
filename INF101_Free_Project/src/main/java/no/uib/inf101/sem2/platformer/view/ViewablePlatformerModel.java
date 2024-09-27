package no.uib.inf101.sem2.platformer.view;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.platformer.model.DoubleJump;
import no.uib.inf101.sem2.platformer.model.GameState;
import no.uib.inf101.sem2.platformer.model.MovementState;
import no.uib.inf101.sem2.platformer.model.PlayerDirection;
import no.uib.inf101.sem2.platformer.model.WallJump;

public interface ViewablePlatformerModel {

    /**
     * iterates over the tiles in the Player collision objekt
     * @return an object that gives the position and value of the tiles in the Player as it iterates.
     */
    public Iterable<GridCell<Character>> getPlayerTilesOnBoard();

    /**
     * @return the state of the game
     */
    public GameState getGameState();

    /**
     * Checks if the double jump upgrade is active or not
     * @return ACTIVE or INACTIVE
     */
    public DoubleJump getDoubleJumpState();

    /**
     * Checks if the wall jump upgrade is active or not
     * @return ACTIVE or INACTIVE
     */
    public WallJump getWallJumpState();

    /**
     * gets the dimension of an object
     * @return a GridDimension object
     */
    public GridDimension getDimension();

    /**
     * get the direction in which way the player is facing
     * @return LEFT or RIGHT
     */
    public PlayerDirection getPlayerDirection();

    /**
     * get what the state of movement the player is in
     * @return IDLE, RUNNING, JUMPING or FALLING 
     */
    public MovementState getMovementState();

    /**
     * get the name of the animation frame that should be used
     * @return a string that is the name of the image file that should be used.
     */
    public String getPlayerFrame();
}
