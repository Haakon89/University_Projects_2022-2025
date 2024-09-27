package inf112.Sun_Mist_Mountain.app.Model;

import inf112.Sun_Mist_Mountain.app.Model.Math.Position;

/**
 * A position object is one that has a position in the world.
 */
public interface Positioned {

    /**
     * @return the position of this object in the world.
     */
    public Position getPosition();

}
