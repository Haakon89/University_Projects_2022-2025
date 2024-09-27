package inf112.Sun_Mist_Mountain.app.Model.Math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;

public class TestVariants extends RandomizingTest {

    @Test
    public void empty() {
        var rng = this.nextRandom();
        var variants = new Variants<Integer>(List.of());

        var leak = new Leak<>();
        variants.any(rng, leak);

        assertEquals(List.of(), leak.met);
    }

    @Test
    public void anyChoosesOne() {
        for (int i = 0; i < ITERATIONS; i++) {
            var ints = this.nextIntegerList();
            var rng = this.nextRandom();
            var variants = new Variants<>(ints);

            var leak = new Leak<>();
            variants.any(rng, leak);

            if (ints.isEmpty()) {
                assertEquals(0, leak.met.size());
            } else {
                assertEquals(1, leak.met.size());
            }
        }
    }

    @Test
    public void allChoosesAll() {
        for (int i = 0; i < ITERATIONS; i++) {
            var ints = this.nextIntegerList();
            var variants = new Variants<>(ints);

            var leak = new Leak<>();
            variants.all(leak);

            assertEquals(ints.size(), leak.met.size());
        }
    }

    @Test
    public void anyIsSomewhatUniform() {
        var rng = this.nextRandom();
        var variants = new Variants<>(List.of(1, 2, 3, 4, 5));
        var counts = new HashMap<Integer, Integer>();

        var leak = new Leak<Integer>();

        int iterations = 10_000;
        for (int i = 0; i < iterations; i++) {
            variants.any(rng, leak);
        }

        for (var met : leak.met) {
            counts.merge(met, 1, (x, y) -> x + y);
        }

        int total = leak.met.size();
        double ones = (double) counts.get(1) / total;
        double twos = (double) counts.get(2) / total;
        double threes = (double) counts.get(3) / total;
        double fours = (double) counts.get(4) / total;
        double fives = (double) counts.get(5) / total;

        double delta = 1 / Math.sqrt(iterations);

        assertEquals(ones, twos, delta);
        assertEquals(ones, threes, delta);
        assertEquals(ones, fours, delta);
        assertEquals(ones, fives, delta);
    }

    private static class Leak<T> implements Consumer<T> {
        public final List<T> met = new ArrayList<>();

        @Override
        public void accept(T value) {
            this.met.add(value);
        }
    }

}
