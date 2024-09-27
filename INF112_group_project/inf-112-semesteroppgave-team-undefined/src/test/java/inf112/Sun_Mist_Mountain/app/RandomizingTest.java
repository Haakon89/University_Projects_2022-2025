package inf112.Sun_Mist_Mountain.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;

public class RandomizingTest {

    public static final int SEED = 123456789;
    public static final int ITERATIONS = 10_000;
    public static final int HEAVY_ITERATIONS = 100;

    /**
     * The margin of error allowed when checking equality
     */
    public static final double DELTA = 1e-5;

    private Random random;

    @BeforeEach
    public void setUpRng() {
        this.random = new Random(SEED);
    }

    /**
     * @return a uniformly random double within the range of an integer.
     */
    public double nextDouble() {
        return this.random.nextDouble(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * @return a uniformly random integer within its entire range.
     */
    public int nextInteger() {
        return this.random.nextInt();
    }

    /**
     * @return an array of inverse exponential length with integers chosen from
     *         {@link RandomizingTest#nextInteger}.
     */
    public List<Integer> nextIntegerList() {
        int length = 0;
        while (true) {
            if (this.random.nextBoolean()) {
                break;
            }

            length++;
        }

        var result = new ArrayList<Integer>(length);
        for (int i = 0; i < length; i++) {
            result.add(this.nextInteger());
        }

        return result;
    }

    /**
     * @return a randomly seeded RNG.
     */
    public Random nextRandom() {
        return new Random(this.random.nextLong());
    }

}
