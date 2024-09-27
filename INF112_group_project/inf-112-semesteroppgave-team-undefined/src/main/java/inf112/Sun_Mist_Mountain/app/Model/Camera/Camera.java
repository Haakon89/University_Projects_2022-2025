package inf112.Sun_Mist_Mountain.app.Model.Camera;

import inf112.Sun_Mist_Mountain.app.Controller.ControllableCamera;
import inf112.Sun_Mist_Mountain.app.Model.Positioned;
import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Math.Vector;
import inf112.Sun_Mist_Mountain.app.View.ViewableCamera;

public class Camera implements ControllableCamera, ViewableCamera {

    /**
     * How much the player can move before the camera follows it.
     */
    public static final long TOLERANCE = 70;

    private static final double FOLLOW = 0.9;

    private final Positioned follow;

    private Position target;

    public Camera(Positioned follow) {
        this.follow = follow;
        this.target = follow.getPosition();
    }

    /**
     * Update the cameras position.
     * @param delta is the time in seconds since the update, or 0 if this was
     *              is the first update.
     */
    public void update(float deltaTime) {
        Position playerPosition = this.follow.getPosition();
        Vector delta = this.target.to(playerPosition);
        double difference = Math.sqrt(delta.lengthSquared()) - TOLERANCE;

        if (difference > 0) {
            Position idealTarget = this.target.add(delta.withLength(difference));
            this.target = this.target.blend(idealTarget, FOLLOW * deltaTime);
        }
    }

    @Override
    public Position getTarget() {
        return this.target;
    }

}
