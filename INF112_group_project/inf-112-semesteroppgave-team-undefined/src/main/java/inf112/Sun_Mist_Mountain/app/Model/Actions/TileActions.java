package inf112.Sun_Mist_Mountain.app.Model.Actions;

import inf112.Sun_Mist_Mountain.app.Model.Entities.EntityFactory;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;

public interface TileActions {

    /**
     * Destroy the current entity.
     */
    public void destroyEntity();

    /**
     * Place an entity generated from the given factory on this tile, if there
     * is no entity there already.
     */
    public void placeEntity(EntityFactory factory);

    /**
     * Replace the entity on the targeted tile.
     */
    public void replaceEntity(EntityFactory factory);

    /**
     * Change the tile to a new tile.
     */
    public void changeTile(Tile to);

    /**
     * Dry up this tile ("eating up its water").
     */
    public void useWater();

    /**
     * Mark that something has happened. If this is a {@code UseAction}, then
     * this will cause {@code exert} and {@code destroyItem} to drain stamina
     * and destroy the used item, if they are invoked.
     */
    public void markHappened();

}
