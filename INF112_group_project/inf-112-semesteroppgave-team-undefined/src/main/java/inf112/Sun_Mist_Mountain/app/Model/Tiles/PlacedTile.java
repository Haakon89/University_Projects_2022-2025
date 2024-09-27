package inf112.Sun_Mist_Mountain.app.Model.Tiles;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Entity;
import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;

/**
 * A placed tile is a tile that may change but has a fixed position.
 */
public class PlacedTile {

    private final PixelPosition position;
    private Tile tile;
    private Entity entity;

    public PlacedTile(PixelPosition position, Tile tile) {
        this.position = position;
        this.tile = tile;
    }

    /**
     * @return the position of the tile.
     */
    public PixelPosition getPosition() {
        return this.position;
    }

    /**
     * @return the tile type and texture.
     */
    public Tile getTile() {
        return this.tile;
    }

    /**
     * Change this tile.
     */
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Entity getEntity() {
        if (this.hasEntity())
            return this.entity;
        return null;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * @return {@code true} if this tile can be walked on.
     */
    public boolean isGround() {
        if (this.hasEntity())
            return this.entity.isGround();
        else 
            return this.tile.isGround();
    }

    /**
     * @return {@code true} if this tile has any entities.
     */
    public boolean hasEntity() {
        if (this.entity == null)
            return false;
        return true;
    }

    /**
     * Till this tile, changing it in place.
     */
    public void till(TileActions actions) {
        if (this.entity != null) {
            this.entity.till(actions);
        } else {
            this.tile.till(actions);
        }
    }

    /**
     * Water this tile, changing it in place.
     */
    public void water(TileActions actions) {
        this.tile.water(actions);
    }

    /**
     * dry this tile, changing it in place.
     */
    public void dry(TileActions actions) {
        this.tile.dry(actions);
    }

    /**
     * Mine this tile, changing it in place.
     */
    public void mine(TileActions actions) {
        if (this.entity != null) {
            this.entity.mine(actions);
        } else {
            this.tile.mine(actions);
        }
    }

    /**
     * The tick method is invoked at random intervals, and can be used to move
     * forward.
     */
    public void tick(TileActions actions) {
        if (this.entity != null) {
            if (this.tile.getSprite().state().equals(Sprite.State.Watered)) {
                this.entity.grow(actions);
            }
        }
    }

}
