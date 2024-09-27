package inf112.Sun_Mist_Mountain.app.Model.Math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;

public class TestShuffle extends RandomizingTest {

    @Test
    public void empty() {
        var rng = this.nextRandom();
        var shuffle = new Shuffle<>(List.of());

        for (int i = 0; i < ITERATIONS; i++) {
            assertNull(shuffle.next(rng));
        }
    }

    @Test
    public void one() {
        var rng = this.nextRandom();
        var shuffle = new Shuffle<>(List.of(1));

        for (int i = 0; i < ITERATIONS; i++) {
            assertEquals(1, shuffle.next(rng));
        }
    }

    @Test
    public void allDistinct() {
        for (int i = 0; i < HEAVY_ITERATIONS; i++) {
            var rng = this.nextRandom();
            var items = this.nextIntegerList();
            var shuffle = new Shuffle<>(items);

            for (int j = 0; j < ITERATIONS; j++) {
                var set = new HashSet<Integer>();
                for (int k = 0; k < items.size(); k++) {
                    set.add(shuffle.next(rng));
                }

                assertEquals(items.size(), set.size());
            }
        }
    }

}
