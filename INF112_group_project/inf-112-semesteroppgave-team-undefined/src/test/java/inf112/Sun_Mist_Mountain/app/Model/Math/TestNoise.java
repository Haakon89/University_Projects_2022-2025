package inf112.Sun_Mist_Mountain.app.Model.Math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;

public class TestNoise extends RandomizingTest {

    @Test
    public void alwaysNormal() {
        var rng = new Random(SEED);
        var noise = new Noise(rng);

        for (int i = 0; i < ITERATIONS; i++) {
            int x = this.nextInteger();
            int y = this.nextInteger();

            var value = noise.at(x, y);
            assertTrue(0.0 <= value, value + " should be 0 or greater");
            assertTrue(value < 1.0, value + " should be less than 1");
        }
    }

    @Test
    public void sameForSameCoordinates() {
        var rng0 = new Random(SEED);
        var noise0 = new Noise(rng0);

        for (int i = 0; i < ITERATIONS; i++) {
            int x = this.nextInteger();
            int y = this.nextInteger();

            var value0 = noise0.at(x, y);
            var value1 = noise0.at(x, y);

            var rng1 = new Random(SEED);
            var noise1 = new Noise(rng1);

            var value2 = noise1.at(x, y);

            assertEquals(value0, value1);
            assertEquals(value1, value2);
        }
    }

}
