package inf112.Sun_Mist_Mountain.app.Model.Entities;

import java.util.random.RandomGenerator;

public interface EntityFactory {

    /**
     * @return an instance of the entity created by this class using the
     *         supplied random number generator.
     */
    public Entity create(RandomGenerator rng);

}
