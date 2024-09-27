package inf112.Sun_Mist_Mountain.app.Model.Entities.Types;

import java.util.random.RandomGenerator;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Entity;
import inf112.Sun_Mist_Mountain.app.Model.Entities.EntityFactory;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite;
import inf112.Sun_Mist_Mountain.app.Model.Item.ItemStack;
import inf112.Sun_Mist_Mountain.app.Model.Item.Seeds.Carrot;
import inf112.Sun_Mist_Mountain.app.Model.Item.Seeds.GenericSeed;

public class Carrots extends Entity {

    private static final int CARROT_MINIMUM = 1;
    private static final int CARROT_MAXIMUM = 1;
    private static final int SEED_MINIMUM = 3;
    private static final int SEED_MAXIMUM = 5;

    private final int carrots;
    private final int seeds;

    @Override
    public boolean isGround() {
        return true;
    }

    @Override
    public void till(TileActions actions) {
        actions.destroyEntity();

        if (actions instanceof UseActions useActions) {
            useActions.dropItems(new ItemStack(new Carrot(), this.carrots));
            useActions.dropItems(new ItemStack(new GenericSeed(), this.seeds));
        }
    }

    private Carrots(int droppedCarrots, int droppedSeeds) {
        super(Sprite.Carrots);
        this.carrots = droppedCarrots;
        this.seeds = droppedSeeds;
    }

    public static class Factory implements EntityFactory {
        @Override
        public Entity create(RandomGenerator rng) {
            int carrots = rng.nextInt(CARROT_MINIMUM, CARROT_MAXIMUM + 1);
            int seeds = rng.nextInt(SEED_MINIMUM, SEED_MAXIMUM + 1);
            return new Carrots(carrots, seeds);
        }
    }

}
