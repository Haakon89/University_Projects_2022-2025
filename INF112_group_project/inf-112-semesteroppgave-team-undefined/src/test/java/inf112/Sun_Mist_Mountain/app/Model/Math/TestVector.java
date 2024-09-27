package inf112.Sun_Mist_Mountain.app.Model.Math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;

public class TestVector extends RandomizingTest {

    @Test
    public void lengthIsNonNegative() {
        for (int i = 0; i < ITERATIONS; i++) {
            var vector = new Vector(this.nextDouble(), this.nextDouble());
            double length = vector.lengthSquared();
            assertTrue(length >= 0, "The length of " + vector + " should be non-negative, but is " + length);
        }
    }

    @Test
    public void normalizeIsOneOrZero() {
        for (int i = 0; i < ITERATIONS; i++) {
            var vector = new Vector(this.nextDouble(), this.nextDouble());
            Vector normal = vector.normalize();

            if (vector.lengthSquared() == 0) {
                assertEquals(vector, normal);
            } else {
                assertEquals(1, normal.lengthSquared(), DELTA);
            }
        }
    }

    @Test
    public void scaleByZero() {
        for (int i = 0; i < ITERATIONS; i++) {
            var vector = new Vector(this.nextDouble(), this.nextDouble());
            Vector scaled = vector.scale(0);
            assertEquals(0, scaled.lengthSquared());
        }
    }

    @Test
    public void scaleByOne() {
        for (int i = 0; i < ITERATIONS; i++) {
            var vector = new Vector(this.nextDouble(), this.nextDouble());
            Vector scaled = vector.scale(1);
            assertEquals(vector.dx(), scaled.dx(), DELTA);
            assertEquals(vector.dy(), scaled.dy(), DELTA);
        }
    }

    @Test
    public void addIsCommutativeAndAssociative() {
        for (int i = 0; i < ITERATIONS; i++) {
            var a = new Vector(this.nextDouble(), this.nextDouble());
            var b = new Vector(this.nextDouble(), this.nextDouble());
            var c = new Vector(this.nextDouble(), this.nextDouble());

            var expected = a.add(b.add(c));
            var actual = b.add(a.add(c));

            assertEquals(expected.dx(), actual.dx(), DELTA);
            assertEquals(expected.dy(), actual.dy(), DELTA);
        }
    }

    @Test
    public void withLength() {
        for (int i = 0; i < ITERATIONS; i++) {
            var a = new Vector(this.nextDouble(), this.nextDouble());
            double expectedLength = Math.abs(this.nextDouble());
            double actual = a.withLength(expectedLength).length();

            if (a.lengthSquared() > 0) {
                double delta = Math.abs(expectedLength * DELTA);
                assertEquals(expectedLength, actual, delta);
            }
        }
    }

    @Test
    public void zeroLength() {
        for (int i = 0; i < ITERATIONS; i++) {
            var a = new Vector(0, 0);
            var actual = a.withLength(this.nextDouble());
            assertEquals(0, actual.lengthSquared());
        }
    }

    @Test
    public void maxLength() {
        for (int i = 0; i < ITERATIONS; i++) {
            var a = new Vector(this.nextDouble(), this.nextDouble());
            double maxLength = Math.abs(this.nextDouble());
            double actual = a.withLengthAtMost(maxLength).length();

            double delta = Math.abs(maxLength * DELTA);
            assertTrue(actual <= maxLength + delta, "vector has length " + actual + " which is greater than " + maxLength);
        }
    }

    @Test
    public void angleInRadians() {
        for (int i = 0; i < ITERATIONS; i++) {
            var a = new Vector(this.nextDouble(), this.nextDouble());
            double angle = a.angle();

            assertTrue(-Math.PI < angle, angle + " should be greater than -π");
            assertTrue(angle <= Math.PI, angle + " should be less than or equal to π");
        }
    }

}
