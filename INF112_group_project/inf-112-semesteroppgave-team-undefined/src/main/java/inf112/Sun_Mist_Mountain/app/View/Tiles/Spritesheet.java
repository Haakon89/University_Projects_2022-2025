package inf112.Sun_Mist_Mountain.app.View.Tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite;

public interface Spritesheet {

    /**
     * @param x the x-coordinate of the tile.
     * @param y the y-coordinate of the tile.
     * @return a texture region for the given sprite given its neighbours, or
     *         {@code null} if there is no appropriate texture.
     */
    public TextureRegion getBase(Around around, int x, int y);

    /**
     * @return a texture region for the given entity sprite, or {@code null} if
     *         there is no appropriate texture.
     */
    public TextureRegion getEntity(Sprite entitySprite);

    /**
     * @return an iterable over every base texture in this sprite sheet.
     */
    Iterable<TextureRegion> getBaseTextures();

}
