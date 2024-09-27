package inf112.Sun_Mist_Mountain.app.Model.Math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;

public class TestPositionRandom extends RandomizingTest {

    @Test
    public void doublesAreNormal() {
        for (int i = 0; i < ITERATIONS; i++) {
            var rng = new PositionRandom(this.nextRandom());
            double value = rng.doubleAt(this.nextInteger(), this.nextInteger());

            assertTrue(0 <= value, value + " should be equal to or larger than 0");
            assertTrue(value < 1, value + " should be less than 1");
        }
    }

    @Test
    public void intIsBelowMax() {
        for (int i = 0; i < HEAVY_ITERATIONS; i++) {
            var rng = new PositionRandom(this.nextRandom());

            for (int j = 0; j < ITERATIONS; j++) {
                int max = this.nextInteger();
                int value = rng.intAtBelow(this.nextInteger(), this.nextInteger(), max);

                if (0 < max) {
                    assertTrue(value < max);
                } else if (max < 0 && max != Integer.MIN_VALUE) {
                    assertTrue(value < Math.abs(max));
                }
            }
        }
    }

    @Test
    public void sameForSameCoordinates() {
        for (int i = 0; i < ITERATIONS; i++) {
            var rng = new PositionRandom(this.nextRandom());
            int x = this.nextInteger();
            int y = this.nextInteger();

            long l1 = rng.longAt(x, y);
            int i1 = rng.intAtBelow(x, y, 0);
            double d1 = rng.doubleAt(x, y);

            long l2 = rng.longAt(x, y);
            int i2 = rng.intAtBelow(x, y, 0);
            double d2 = rng.doubleAt(x, y);

            assertEquals(l1, l2);
            assertEquals(i1, i2);
            assertEquals(d1, d2);
        }
    }

    @Test
    public void intDoesNotHang() {
        for (int i = 0; i < HEAVY_ITERATIONS; i++) {
            int max = i + 1;
            var rng = new PositionRandom(this.nextRandom());

            for (int j = 0; j < ITERATIONS; j++) {
                assertTimeout(Duration.ofSeconds(1), () -> {
                    rng.intAtBelow(this.nextInteger(), this.nextInteger(), max);
                });
            }
        }
    }

    @Test
    public void intBoundedByZero() {
        var rng = new PositionRandom(this.nextRandom());
        assertTimeout(Duration.ofSeconds(1), () -> rng.intAtBelow(0, 0, 0));
    }

    @Test
    public void intBoundedByIntMin() {
        var rng = new PositionRandom(this.nextRandom());
        assertTimeout(Duration.ofSeconds(1), () -> rng.intAtBelow(0, 0, Integer.MIN_VALUE));
    }

    @Test
    public void intBoundedByNegative() {
        var rng = new PositionRandom(this.nextRandom());
        assertTimeout(Duration.ofSeconds(1), () -> rng.intAtBelow(0, 0, -10));
    }

}
