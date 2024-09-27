package inf112.Sun_Mist_Mountain.app.Model.Entities;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;

public abstract class Entity {

    private Sprite sprite;

    protected Entity(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * @return {@code true} if this entity can be walked over.
     */
    public abstract boolean isGround();

    /**
     * What this entity does when tilled.
     */
    public void till(TileActions action) {
        // do nothing by default
    }

    /**
     * What this entity does when mined.
     */
    public void mine(TileActions action) {
        // do nothing by default
    }

    /**
     * Invoked whenever the tile is watered and this entity can grow.
     */
    public void grow(TileActions action) {
        // do nothing by default
    }

    /**
     * @return the sprite for this entity.
     */
    public Sprite getSprite() {
        return this.sprite;
    }

}
