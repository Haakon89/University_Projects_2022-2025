package inf112.Sun_Mist_Mountain.app.Controller;

import inf112.Sun_Mist_Mountain.app.Model.Math.Position;

public interface ControllableCamera {

    /**
     * @return the position of the focus of the camera.
     */
    public Position getTarget();

}
