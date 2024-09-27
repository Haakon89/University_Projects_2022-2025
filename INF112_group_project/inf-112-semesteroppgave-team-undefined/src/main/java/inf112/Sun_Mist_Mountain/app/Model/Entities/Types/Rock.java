package inf112.Sun_Mist_Mountain.app.Model.Entities.Types;

import java.util.random.RandomGenerator;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Entity;
import inf112.Sun_Mist_Mountain.app.Model.Entities.EntityFactory;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite;
import inf112.Sun_Mist_Mountain.app.Model.Item.ItemStack;
import inf112.Sun_Mist_Mountain.app.Model.Item.Resources.StoneItem;

public class Rock extends Entity {

    private static final int HEALTH_MINIMUM = 2;
    private static final int HEALTH_MAXIMUM = 3;
    private static final int DROP_MINIMUM = 3;
    private static final int DROP_MAXIMUM = 5;

    private int health;
    private int drop;

    @Override
    public boolean isGround() {
        return false;
    }

    @Override
    public void mine(TileActions actions) {
        if (actions instanceof UseActions useActions) {
            this.health--;
            if (health <= 0) {
                useActions.dropItems(new ItemStack(new StoneItem(), drop));
                useActions.destroyEntity();
            }
        }

        actions.markHappened();
    }

    private Rock(int health, int drop) {
        super(Sprite.Rock);
        this.health = health;
        this.drop = drop;
    }

    public static class Factory implements EntityFactory {
        @Override
        public Entity create(RandomGenerator rng) {
            int health = rng.nextInt(HEALTH_MINIMUM, HEALTH_MAXIMUM + 1);
            int drop = rng.nextInt(DROP_MINIMUM, DROP_MAXIMUM);
            return new Rock(health, drop);
        }
    }

}
