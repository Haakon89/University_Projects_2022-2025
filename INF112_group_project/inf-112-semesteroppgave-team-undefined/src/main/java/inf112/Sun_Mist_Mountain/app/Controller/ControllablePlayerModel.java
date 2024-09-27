package inf112.Sun_Mist_Mountain.app.Controller;

import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Math.Vector;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;

public interface ControllablePlayerModel {

    /**
     * Move the player by the given movement vector.
     *
     * @return the tile that the player is now on, or {@code null} if the player
     *         did not move.
     */
    public PlacedTile move(Vector by);

    /**
     * @return the tile currently being targeted by the player, or {@code null}
     *         if nothing is.
     */
    public PlacedTile getTarget();

    /**
     * Set the position currently being targeted. If {@code pos} is {@code null}
     * then the target will be unset.
     */
    public void setTarget(Position pos);

    /**
     * change the amount of stamina the player has, but it won't go above 100
     * or bellow 0.
     * @param change
     */
    public void changeStamina(int change);

    /**
     * sets the amount of stamina the player has, but it won't go above 100
     * or bellow 0.
     * @param newStamina
     */
    public void setStamina(int newStamina);

    /**
     * 
     * @return the amount of stamina the player has
     */
    public int getCurrentStamina();

}
