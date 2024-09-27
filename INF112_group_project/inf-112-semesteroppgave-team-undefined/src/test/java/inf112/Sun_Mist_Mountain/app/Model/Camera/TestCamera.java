package inf112.Sun_Mist_Mountain.app.Model.Camera;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.Model.Positioned;
import inf112.Sun_Mist_Mountain.app.Model.Math.Position;

public class TestCamera {

    @Test
    public void onlyMovesWhenPlayerIsOutsideThreshold() {
        var player = new Placed();
        var camera = new Camera(player);

        Position initialTarget = camera.getTarget();

        for (int i = 0; i < 1000; i++) {
            player.position = new Position(0, i);
            camera.update(0.1f);

            Position actual = camera.getTarget();

            if (i <= Camera.TOLERANCE) {
                assertEquals(initialTarget, actual);
            } else {
                assertNotEquals(initialTarget, actual);
            }
        }
    }

    @Test
    public void smoothlyMovesWhenPlayerMoves() {
        var player = new Placed();
        var camera = new Camera(player);

        Position target1 = camera.getTarget();
        player.position = new Position(0, (int) Camera.TOLERANCE - 1);
        camera.update(0.02f);
        Position target2 = camera.getTarget();
        player.position = new Position(0, (int) Camera.TOLERANCE + 1);
        camera.update(0.02f);
        Position target3 = camera.getTarget();

        double d1 = target1.distanceSquaredTo(target2);
        double d2 = target1.distanceSquaredTo(target3);

        assertEquals(0, d1);
        assertTrue(d2 < 10 * 10, "camera jumped by " + Math.sqrt(d2) + " pixels");
    }

    private static class Placed implements Positioned {
        public Position position = Position.ZERO;

        @Override
        public Position getPosition() {
            return this.position;
        }
    }

}
