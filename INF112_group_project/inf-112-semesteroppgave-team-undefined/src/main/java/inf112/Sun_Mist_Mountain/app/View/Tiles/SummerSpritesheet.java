package inf112.Sun_Mist_Mountain.app.View.Tiles;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;
import inf112.Sun_Mist_Mountain.app.View.SpriteGenerator;
import inf112.Sun_Mist_Mountain.app.View.SpriteName;

public class SummerSpritesheet implements Spritesheet {

    private final Map<Around, TextureRegion> bases;
    private final Spritesheet cascade;

    public SummerSpritesheet(Spritesheet cascade) {
        this.cascade = cascade;
        this.bases = new HashMap<>();

        var generator = new SpriteGenerator();
        SummerSpritesheet.putGrassSprites(generator, this.bases);
    }

    @Override
    public TextureRegion getBase(Around around, int x, int y) {
        var here = this.bases.get(around);
        if (here == null) {
            return this.cascade.getBase(around, x, y);
        }
        return here;
    }

    @Override
    public TextureRegion getEntity(inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite entitySprite) {
        return this.cascade.getEntity(entitySprite);
    }

    @Override
    public Iterable<TextureRegion> getBaseTextures() {
        return this.bases.values();
    }

    /**
     * Add the grass sprite variations to the sprite map.
     */
    private static void putGrassSprites(SpriteGenerator generator, Map<Around, TextureRegion> sprites) {
        sprites.put(new Around(Sprite.Base.Grass, false, false, false, false), generator.getSprite(SpriteName.GRASS_MIDDLE));
        sprites.put(new Around(Sprite.Base.Grass, false, false, false, true), generator.getSprite(SpriteName.GRASS_LEFT));
        sprites.put(new Around(Sprite.Base.Grass, false, false, true, false), generator.getSprite(SpriteName.GRASS_BOTTOM));
        sprites.put(new Around(Sprite.Base.Grass, false, false, true, true), generator.getSprite(SpriteName.GRASS_CORNER_BL));
        sprites.put(new Around(Sprite.Base.Grass, false, true, false, false), generator.getSprite(SpriteName.GRASS_RIGHT));
        sprites.put(new Around(Sprite.Base.Grass, false, true, false, true), generator.getSprite(SpriteName.GRASS_SOLO_MID_VERTICAL));
        sprites.put(new Around(Sprite.Base.Grass, false, true, true, false), generator.getSprite(SpriteName.GRASS_CORNER_BR));
        sprites.put(new Around(Sprite.Base.Grass, false, true, true, true), generator.getSprite(SpriteName.GRASS_SOLO_LOWER));
        sprites.put(new Around(Sprite.Base.Grass, true, false, false, false), generator.getSprite(SpriteName.GRASS_TOP));
        sprites.put(new Around(Sprite.Base.Grass, true, false, false, true), generator.getSprite(SpriteName.GRASS_CORNER_TL));
        sprites.put(new Around(Sprite.Base.Grass, true, false, true, false), generator.getSprite(SpriteName.GRASS_SOLO_MID_HORIZONTAL));
        sprites.put(new Around(Sprite.Base.Grass, true, false, true, true), generator.getSprite(SpriteName.GRASS_SOLO_RIGHT));
        sprites.put(new Around(Sprite.Base.Grass, true, true, false, false), generator.getSprite(SpriteName.GRASS_CORNER_TR));
        sprites.put(new Around(Sprite.Base.Grass, true, true, false, true), generator.getSprite(SpriteName.GRASS_SOLO_UPPER));
        sprites.put(new Around(Sprite.Base.Grass, true, true, true, false), generator.getSprite(SpriteName.GRASS_SOLO_LEFT));
        sprites.put(new Around(Sprite.Base.Grass, true, true, true, true), generator.getSprite(SpriteName.GRASS_MIDDLE));
    }

}
