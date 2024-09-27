package inf112.Sun_Mist_Mountain.app.Model.Entities.Types;

import java.util.random.RandomGenerator;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Entity;
import inf112.Sun_Mist_Mountain.app.Model.Entities.EntityFactory;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite;

public class Seeds extends Entity {

    public static final int GROWTH_MINIMUM = 2;
    public static final int GROWTH_MAXIMUM = 5;

    private int growth;

    @Override
    public boolean isGround() {
        return true;
    }

    @Override
    public void grow(TileActions actions) {
        this.growth -= 1;
        actions.useWater();

        if (this.growth <= 0) {
            actions.replaceEntity(new Sprouts.Factory());
        }

        actions.markHappened();
    }

    private Seeds(int growth) {
        super(Sprite.Seeds);
        this.growth = growth;
    }

    public static class Factory implements EntityFactory {
        @Override
        public Entity create(RandomGenerator rng) {
            int growth = rng.nextInt(GROWTH_MINIMUM, GROWTH_MAXIMUM + 1);
            return new Seeds(growth);
        }
    }

}
