package inf112.Sun_Mist_Mountain.app.Model.Tiles;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;

/**
 * Represents the various kinds of tiles.
 */
public abstract class Tile {

    /**
     * The width in pixels of every tile.
     */
    public static final int WIDTH = 32;

    /**
     * The height in pixels of every tile.
     */
    public static final int HEIGHT = 32;

    private Sprite sprite;

    /**
     * Construct a tile from a sprite texture.
     */
    protected Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * @return <code>true</code> if the player can walk on this tile.
     */
    public abstract boolean isGround();

    /**
     * Called when this tile is tilled.
     */
    public void till(TileActions actions) {
        // do nothing
    }

    /**
     * Called when this tile is watered.
     */
    public void water(TileActions actions) {
        // do nothing
    }

    /**
     * Called when this tile is dried.
     */
    public void dry(TileActions actions) {
        // do nothing
    }

    /**
     * Called when this tile is mined.
     */
    public void mine(TileActions actions) {
        // do nothing
    }

    /**
     * @return the sprite for this tile.
     */
    public Sprite getSprite() {
        return this.sprite;
    }

}
