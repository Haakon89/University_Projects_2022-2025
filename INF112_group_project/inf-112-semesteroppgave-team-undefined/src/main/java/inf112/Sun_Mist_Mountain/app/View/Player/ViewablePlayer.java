package inf112.Sun_Mist_Mountain.app.View.Player;

import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;

public interface ViewablePlayer {

    /**
     * @return the position of the bottom left of the player.
     */
    public Position getPosition();

    /**
     * @return the tile currently being targeted, or {@code null} if nothing
     *         is.
     */
    public PlacedTile getTarget();

}
