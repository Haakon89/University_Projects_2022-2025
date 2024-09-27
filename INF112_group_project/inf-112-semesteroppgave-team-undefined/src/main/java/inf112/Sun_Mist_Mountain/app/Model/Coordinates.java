package inf112.Sun_Mist_Mountain.app.Model;

import com.badlogic.gdx.math.Rectangle;

import inf112.Sun_Mist_Mountain.app.Model.Camera.Camera;
import inf112.Sun_Mist_Mountain.app.Model.Math.Position;

/**
 * This class is used to convert between screen coordinates (such as on mouse
 * events) and world coordinates.
 */
public class Coordinates {

    private final Camera camera;
    private final Rectangle screen;

    public Coordinates(Camera camera) {
        this.camera = camera;
        this.screen = new Rectangle();
    }

    /**
     * Change the size of the screen.
     */
    public void resizeScreen(int newWidth, int newHeight) {
        this.screen.width = newWidth;
        this.screen.height = newHeight;
    }

    /**
     * @return the world position corresponding to the given {@code (x, y)}
     *         coordinate pair.
     */
    public Position screenToWorld(int screenX, int screenY) {
        // The screenY coordinate is 0 at the _top_ of the screen, while the
        // world coordinates place 0 at the _bottom_ of the screen. Further,
        // this.camera.getTarget() returns the world coordinates of the _center_
        // of the screen.
        var cameraTarget = this.camera.getTarget();

        double screenWidth = this.screen.width;
        double screenHeight = this.screen.height;

        double invertedScreenY = screenHeight - screenY;

        double leftOfScreenInWorld = cameraTarget.x() - screenWidth / 2;
        double bottomOfScreenInWorld = cameraTarget.y() - screenHeight / 2;

        double worldX = leftOfScreenInWorld + screenX;
        double worldY = bottomOfScreenInWorld + invertedScreenY;

        return new Position(worldX, worldY);
    }

}
