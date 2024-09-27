package inf112.Sun_Mist_Mountain.app.Model.Math;

import java.nio.ByteBuffer;
import java.util.random.RandomGenerator;

public class Noise {

    private static final double SCALE = 10.0;
    private static final double PERSISTENCE = 0.5;
    private static final int OCTAVES = 3;

    private final long seed;
    private final ByteBuffer buffer;

    public Noise(RandomGenerator rng) {
        this.seed = rng.nextLong();
        this.buffer = ByteBuffer.allocate(Long.BYTES + 2 * Integer.BYTES);
    }

    /**
     * @return a noise value in the range `[0, 1)` which varies smoothly as `x`
     *         and `y` varies.
     */
    public double at(int x, int y) {
        double x0 = (double) x / SCALE;
        double y0 = (double) y / SCALE;

        double frequency = 1.0;
        double amplitude = 1.0;

        double sum = 0.0;
        double max = 0.0;

        for (int i = 0; i < OCTAVES; i++) {
            sum += this.value(x0 * frequency, y0 * frequency) * amplitude;
            max += amplitude;

            frequency *= 2;
            amplitude *= PERSISTENCE;
        }

        return sum / max;
    }

    private double value(double x, double y) {
        // Round to nearest integer coordinates
        int x0 = (int) Math.floor(x);
        int y0 = (int) Math.floor(y);
        int x1 = x0 + 1;
        int y1 = y0 + 1;

        // The fractional part of the `(x, y)` pair
        double dx = x - x0;
        double dy = y - y0;

        // The raw random values
        double x0y0 = this.raw(x0, y0);
        double x0y1 = this.raw(x0, y1);
        double x1y0 = this.raw(x1, y0);
        double x1y1 = this.raw(x1, y1);

        // Interpolate between them
        double xy0 = x0y0 + dx * (x1y0 - x0y0);
        double xy1 = x0y1 + dx * (x1y1 - x0y1);

        return xy0 + dy * (xy1 - xy0);
    }

    /**
     * @return a value uniformly chosen from the range `[0, 1)` based on the
     *         `(x, y)` coordinates.
     */
    private double raw(int x, int y) {
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

        // A `double` can only represents 53-bit integers precisely, so we
        // remove the top `64-53` bits to avoid introducing bias when scaling
        // down (as in
        // https://marc-b-reynolds.github.io/math/2020/06/16/UniformFloat.html)
        // Note that the logical shift `>>>` will also always shift away the
        // sign bit, so this number should always be positive.
        hash = hash >>> (64 - 53);

        // Convert to double and scale down by multiplying with `1/1^53 = 1^-53`
        return (double) hash * 0x1p-53;
    }

}
