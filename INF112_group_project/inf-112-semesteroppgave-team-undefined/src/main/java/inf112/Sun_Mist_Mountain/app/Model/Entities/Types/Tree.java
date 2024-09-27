package inf112.Sun_Mist_Mountain.app.Model.Entities.Types;

import java.util.random.RandomGenerator;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Entity;
import inf112.Sun_Mist_Mountain.app.Model.Entities.EntityFactory;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite;

public class Tree extends Entity {

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
            actions.replaceEntity(new TreeStump.Factory());
            return;
        }

        actions.markHappened();
    }

    private Tree(int health) {
        super(Sprite.Tree);
        this.health = health;
    }

    public static class Factory implements EntityFactory {
        @Override
        public Entity create(RandomGenerator rng) {
            int health = rng.nextInt(HEALTH_MINIMUM, HEALTH_MAXIMUM + 1);
            return new Tree(health);
        }
    }

}
