package inf112.Sun_Mist_Mountain.app.Model.Math;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class Shuffle<T> {

    private final ArrayList<T> items;
    private int current;

    public Shuffle(Iterable<T> items) {
        this.items = new ArrayList<>();
        for (var item : items) {
            this.items.add(item);
        }

        this.current = this.items.size();
    }

    /**
     * @return the next item in the current list of items, or {@code null} if
     *         the list is empty.
     */
    public T next(RandomGenerator rng) {
        if (this.items.isEmpty()) {
            return null;
        }
 
        if (this.current >= this.items.size()) {
            this.current = 0;
            this.shuffle(rng);
        }

        var result = this.items.get(this.current);
        this.current += 1;
        return result;
    }

    /**
     * Shuffle the list of items.
     */
    private void shuffle(RandomGenerator rng) {
        // Fisher-Yates shuffle (https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle)
        for (int i = 0; i < this.items.size() - 1; i++) {
            int j = rng.nextInt(i, this.items.size());

            var tmp = this.items.get(i);
            this.items.set(i, this.items.get(j));
            this.items.set(j, tmp);
        }
    }

}
