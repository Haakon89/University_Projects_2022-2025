package inf112.Sun_Mist_Mountain.app.Model.Math;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;

public class Variants<T> {

    private final List<T> variants;

    public Variants(Iterable<T> variants) {
        this.variants = new ArrayList<>();
        for (var variant : variants) {
            this.variants.add(variant);
        }
    }

    /**
     * Choose one of the variants, and apply the given function to it. If this
     * is empty, {@code fn} is never invoked.
     */
    public void any(RandomGenerator rng, Consumer<? super T> fn) {
        if (!this.variants.isEmpty()) {
            int i = rng.nextInt(this.variants.size());
            fn.accept(this.variants.get(i));
        }
    }

    /**
     * Invoke {@code fn} for all variants in this.
     */
    public void all(Consumer<? super T> fn) {
        this.variants.forEach(fn);
    }

}
