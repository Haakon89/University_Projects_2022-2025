package inf112.Sun_Mist_Mountain.app.Model.Entities.Types;

import java.util.random.RandomGenerator;

import inf112.Sun_Mist_Mountain.app.Model.Entities.Entity;
import inf112.Sun_Mist_Mountain.app.Model.Entities.EntityFactory;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite;

public class House extends Entity {

    private House() {
        super(Sprite.House);
    }

    @Override
    public boolean isGround() {
        return false;
    }
    
    public static class Factory implements EntityFactory {
        @Override
        public Entity create(RandomGenerator rng) {
            return new House();
        }
    }
}
