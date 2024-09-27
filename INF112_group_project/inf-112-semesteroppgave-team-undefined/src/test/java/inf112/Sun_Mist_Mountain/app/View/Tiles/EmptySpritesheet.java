package inf112.Sun_Mist_Mountain.app.View.Tiles;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite;

/**
 * A spritesheet without any sprites.
 */
public class EmptySpritesheet implements Spritesheet {

    @Override
    public TextureRegion getBase(Around around, int x, int y) {
        return null;
    }

    @Override
    public TextureRegion getEntity(Sprite entitySprite) {
        return null;
    }

    @Override
    public Iterable<TextureRegion> getBaseTextures() {
        return List.of();
    }

}
