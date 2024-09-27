package inf112.Sun_Mist_Mountain.app.Model.World;

import java.util.random.RandomGenerator;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Entities.EntityFactory;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;

public class Actions implements TileActions {

    private final RandomGenerator rng;
    private final PlacedTile currentTile;

    public Actions(RandomGenerator rng, PlacedTile currentTile) {
        this.rng = rng;
        this.currentTile = currentTile;
    }

    @Override
    public void destroyEntity() {
        this.currentTile.setEntity(null);
    }

    @Override
    public void placeEntity(EntityFactory factory) {
        if (!this.currentTile.hasEntity()) {
            this.currentTile.setEntity(factory.create(this.rng));
        }
    }

	@Override
	public void replaceEntity(EntityFactory factory) {
        this.currentTile.setEntity(factory.create(this.rng));
	}

	@Override
	public void changeTile(Tile to) {
        this.currentTile.setTile(to);
	}

    @Override
    public void useWater() {
        this.currentTile.dry(this);
    }

    @Override
    public void markHappened() {
        // do nothing, since a player did not cause this action
    }

}
