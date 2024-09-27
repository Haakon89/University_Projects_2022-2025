package inf112.Sun_Mist_Mountain.app.Model.Math;

import java.nio.ByteBuffer;
import java.util.random.RandomGenerator;

/**
 * Generates noisy values which are always the same for the same coordinates.
 */
public class PositionRandom {

    private final long seed;
    private final ByteBuffer buffer;

    public PositionRandom(RandomGenerator rng) {
        this.seed = rng.nextLong();
        this.buffer = ByteBuffer.allocate(Long.BYTES + 2 * Integer.BYTES);
    }

    /**
     * @return a random double uniformly sampled from the range {@code [0, 1)}
     *         based on the two given coordinates.
     */
    public double doubleAt(int x, int y) {
        // A `double` can only represents 53-bit integers precisely, so we
        // remove the top `64-53` bits to avoid introducing bias when scaling
        // down (as in
        // https://marc-b-reynolds.github.io/math/2020/06/16/UniformFloat.html)
        // Note that the logical shift `>>>` will also always shift away the
        // sign bit, so this number should always be positive.
        long hash = this.longAt(x, y) >>> (64 - 53);

        // Convert to double and scale down by multiplying with `1/1^53 = 1^-53`
        return (double) hash * 0x1p-53;
    }

    /**
     * @return an integer uniformly sampled in the range {@code [0, max)} based
     *         on the two given coordinates.
     */
    public int intAtBelow(int x, int y, int max) {
        // Make sure `max` is positive
        if (max == Integer.MIN_VALUE) {
            return (int) this.longAt(x, y);
        }

        if (max == 0) {
            return (int) this.longAt(x, y);
        }

        max = Math.abs(max);

        // Use a do-while loop to avoid bias when taking the modulo:
        // https://stackoverflow.com/questions/10984974/why-do-people-say-there-is-modulo-bias-when-using-a-random-number-generator
        long random = this.longAt(x, y);

        int value;
        long factor = 1;

        do {
            value = (int) ((factor * random) & Integer.MAX_VALUE);
            factor += 1;
        } while (value < 0 || (Long.MAX_VALUE - Long.MAX_VALUE % max) <= value);

        return value % max;
    }

    /**
     * @return a long uniformly sampled from its entire range based on the two
     *         given coordinates.
     */
    public long longAt(int x, int y) {
        // Hash the two integers using the FNV-1a hashing algorithm:
        // https://en.wikipedia.org/wiki/Fowler%E2%80%93Noll%E2%80%93Vo_hash_function#FNV-1a_hash
        long hash = 0xcbf29ce484222325l;

        this.buffer.clear();
        this.buffer.putInt(x);
        this.buffer.putInt(y);
        this.buffer.putLong(this.seed);

        for (var b : this.buffer.array()) {
            hash ^= b;
            hash *= 0x00000100000001b3l;
        }

        return hash;
    }

}
