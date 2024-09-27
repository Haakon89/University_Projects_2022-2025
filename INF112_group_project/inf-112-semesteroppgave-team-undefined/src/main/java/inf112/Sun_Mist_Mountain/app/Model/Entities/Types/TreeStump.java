package inf112.Sun_Mist_Mountain.app.Model.Entities.Types;

import java.util.random.RandomGenerator;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Entity;
import inf112.Sun_Mist_Mountain.app.Model.Entities.EntityFactory;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite;
import inf112.Sun_Mist_Mountain.app.Model.Item.ItemStack;
import inf112.Sun_Mist_Mountain.app.Model.Item.Seeds.GenericSeed;
import inf112.Sun_Mist_Mountain.app.Model.Item.Tools.Hoe;

public class TreeStump extends Entity {

    public static final int HEALTH_MINIMUM = 3;
    public static final int HEALTH_MAXIMUM = 10;

    private int health;

    @Override
    public boolean isGround() {
        return false;
    }

    @Override
    public void mine(TileActions actions) {
        this.health -= 1;

        if (this.health <= 0) {
            actions.destroyEntity();

            if (actions instanceof UseActions useActions) {
                useActions.dropItems(new ItemStack(new Hoe(), 1));
                useActions.dropItems(new ItemStack(new GenericSeed(), 1));
            }

            return;
        }

        actions.markHappened();
    }

    private TreeStump(int health) {
        super(Sprite.TreeStump);
        this.health = health;
    }

    public static class Factory implements EntityFactory {
        @Override
        public Entity create(RandomGenerator rng) {
            int health = rng.nextInt(HEALTH_MINIMUM, HEALTH_MAXIMUM + 1);
            return new TreeStump(health);
        }
    }

}
