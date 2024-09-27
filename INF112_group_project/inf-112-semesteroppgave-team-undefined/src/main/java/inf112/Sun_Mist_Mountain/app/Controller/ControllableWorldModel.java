package inf112.Sun_Mist_Mountain.app.Controller;

import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;

public interface ControllableWorldModel {

    /**
     * @return the {@link PlacedTile} at the given position.
     * @throws IndexOutOfBoundsException if the position is out of bounds.
     */
    public PlacedTile tileAt(PixelPosition position) throws IndexOutOfBoundsException;

    /**
     * @return the starting position of this world.
     */
    public PixelPosition getOrigin();

    /**
     * Update some tiles.
     */
    public void tick();

}
