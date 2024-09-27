package inf112.Sun_Mist_Mountain.app.Model.Math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;

public class TestPosition extends RandomizingTest {

    @Test
    public void distanceSquared() {
        var a = new Position(1, 2);
        var b = new Position(5, 5);

        double expected = 5 * 5;
        double actual = a.distanceSquaredTo(b);

        assertEquals(expected, actual);
    }

    @Test
    public void distanceIsCommutative() {
        for (int i = 0; i < ITERATIONS; i++) {
            var a = new Position(this.nextDouble(), this.nextDouble());
            var b = new Position(this.nextDouble(), this.nextDouble());

            double aToB = a.distanceSquaredTo(b);
            double bToA = b.distanceSquaredTo(a);

            assertEquals(aToB, bToA);
        }
    }

    @Test
    public void distanceIsNonNegative() {
        for (int i = 0; i < ITERATIONS; i++) {
            var a = new Position(this.nextDouble(), this.nextDouble());
            var b = new Position(this.nextDouble(), this.nextDouble());

            double distance = a.distanceSquaredTo(b);

            assertTrue(distance >= 0, "The distance between " + a + " and " + b + " should be non-negative, but is " + distance);
        }
    }

    @Test
    public void distanceIsDifferenceVectorLength() {
        for (int i = 0; i < ITERATIONS; i++) {
            var a = new Position(this.nextDouble(), this.nextDouble());
            var b = new Position(this.nextDouble(), this.nextDouble());

            double distance = a.distanceSquaredTo(b);
            double differenceLength = a.to(b).lengthSquared();

            assertEquals(distance, differenceLength);
        }
    }

    @Test
    public void addingDifference() {
        for (int i = 0; i < ITERATIONS; i++) {
            var a = new Position(this.nextDouble(), this.nextDouble());
            var expected = new Position(this.nextDouble(), this.nextDouble());

            Vector difference = a.to(expected);
            Position actual = a.add(difference);

            assertEquals(expected.x(), actual.x(), DELTA);
            assertEquals(expected.y(), actual.y(), DELTA);
        }
    }

    @Test
    public void integerToPixelPosition() {
        for (int i = 0; i < ITERATIONS; i++) {
            var x = this.nextInteger();
            var y = this.nextInteger();

            var a = new Position(x, y);

            var expected = new PixelPosition(y, x);
            var actual = a.toPixelPosition();

            assertEquals(expected, actual);
        }
    }

    @Test
    public void toPixelPositionIsNearby() {
        for (int i = 0; i < ITERATIONS; i++) {
            var a = new Position(this.nextDouble(), this.nextDouble());

            var rounded = a.toPixelPosition();

            assertEquals(a.x(), rounded.col(), 1.0);
            assertEquals(a.y(), rounded.row(), 1.0);
        }
    }

    @Test
    public void pixelToExactAndBack() {
        for (int i = 0; i < ITERATIONS; i++) {
            var expected = new PixelPosition(this.nextInteger(), this.nextInteger());
            var actual = expected.toPosition().toPixelPosition();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void zeroOrOneBlend() {
        for (int i = 0; i < ITERATIONS; i++) {
            var zero = new Position(this.nextDouble(), this.nextDouble());
            var one = new Position(this.nextDouble(), this.nextDouble());

            var actual = zero.blend(one, 0);

            assertEquals(zero.x(), actual.x(), DELTA);
            assertEquals(zero.y(), actual.y(), DELTA);

            actual = zero.blend(one, 1);

            assertEquals(one.x(), actual.x(), DELTA);
            assertEquals(one.y(), actual.y(), DELTA);
        }
    }

    @Test
    public void blendEqualsOppositeBlend() {
        for (int i = 0; i < ITERATIONS; i++) {
            var a = new Position(this.nextDouble(), this.nextDouble());
            var b = new Position(this.nextDouble(), this.nextDouble());

            var factor = this.nextDouble();
            var expected = a.blend(b, factor);
            var actual = b.blend(a, 1 - factor);

            var deltaX = Math.abs(expected.x() * DELTA);
            var deltaY = Math.abs(expected.y() * DELTA);

            assertEquals(expected.x(), actual.x(), deltaX);
            assertEquals(expected.y(), actual.y(), deltaY);
        }
    }

}
