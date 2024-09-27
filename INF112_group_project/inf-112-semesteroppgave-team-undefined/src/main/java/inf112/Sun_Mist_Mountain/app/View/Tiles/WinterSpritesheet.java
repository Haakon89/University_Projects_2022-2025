package inf112.Sun_Mist_Mountain.app.View.Tiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.Model.Math.PositionRandom;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;
import inf112.Sun_Mist_Mountain.app.View.SpriteGenerator;
import inf112.Sun_Mist_Mountain.app.View.SpriteName;

public class WinterSpritesheet implements Spritesheet {

    private final Map<Around, TextureRegion> bases;
    private final ArrayList<TextureRegion> earthBases;

    private final PositionRandom rng;
    private final Spritesheet cascade;

    public WinterSpritesheet(Spritesheet cascade, PositionRandom rng) {
        this.bases = new HashMap<>();
        this.cascade = cascade;
        this.rng = rng;

        var earhBases = new HashSet<TextureRegion>();

        var generator = new SpriteGenerator();
        WinterSpritesheet.putGrassSprites(generator, this.bases);
        WinterSpritesheet.putDirtSprites(generator, this.bases);
        WinterSpritesheet.putEarthSprites(generator, earhBases);

        this.earthBases = new ArrayList<>(earhBases);
    }

    @Override
    public TextureRegion getBase(Around around, int x, int y) {
        if (around.base().equals(Sprite.Base.Earth)) {
            int index = this.rng.intAtBelow(x, y, this.earthBases.size());
            return this.earthBases.get(index);
        }

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
        var textures = new ArrayList<TextureRegion>();

        for (var texture : this.bases.values()) {
            textures.add(texture);
        }

        for (var texture : this.earthBases) {
            textures.add(texture);
        }

        return textures;
    }

    /**
     * Add the grass sprite variations to the sprite map.
     */
    private static void putGrassSprites(SpriteGenerator generator, Map<Around, TextureRegion> sprites) {
        sprites.put(new Around(Sprite.Base.Grass, false, false, false, false), generator.getSprite(SpriteName.SNOW_MIDDLE));
        sprites.put(new Around(Sprite.Base.Grass, false, false, false, true), generator.getSprite(SpriteName.SNOW_LEFT));
        sprites.put(new Around(Sprite.Base.Grass, false, false, true, false), generator.getSprite(SpriteName.SNOW_BOTTOM));
        sprites.put(new Around(Sprite.Base.Grass, false, false, true, true), generator.getSprite(SpriteName.SNOW_CORNER_BL));
        sprites.put(new Around(Sprite.Base.Grass, false, true, false, false), generator.getSprite(SpriteName.SNOW_RIGHT));
        sprites.put(new Around(Sprite.Base.Grass, false, true, false, true), generator.getSprite(SpriteName.SNOW_SOLO_MID_VERTICAL));
        sprites.put(new Around(Sprite.Base.Grass, false, true, true, false), generator.getSprite(SpriteName.SNOW_CORNER_BR));
        sprites.put(new Around(Sprite.Base.Grass, false, true, true, true), generator.getSprite(SpriteName.SNOW_SOLO_LOWER));
        sprites.put(new Around(Sprite.Base.Grass, true, false, false, false), generator.getSprite(SpriteName.SNOW_TOP));
        sprites.put(new Around(Sprite.Base.Grass, true, false, false, true), generator.getSprite(SpriteName.SNOW_CORNER_TL));
        sprites.put(new Around(Sprite.Base.Grass, true, false, true, false), generator.getSprite(SpriteName.SNOW_SOLO_MID_HORIZONTAL));
        sprites.put(new Around(Sprite.Base.Grass, true, false, true, true), generator.getSprite(SpriteName.SNOW_SOLO_RIGHT));
        sprites.put(new Around(Sprite.Base.Grass, true, true, false, false), generator.getSprite(SpriteName.SNOW_CORNER_TR));
        sprites.put(new Around(Sprite.Base.Grass, true, true, false, true), generator.getSprite(SpriteName.SNOW_SOLO_UPPER));
        sprites.put(new Around(Sprite.Base.Grass, true, true, true, false), generator.getSprite(SpriteName.SNOW_SOLO_LEFT));
        sprites.put(new Around(Sprite.Base.Grass, true, true, true, true), generator.getSprite(SpriteName.SNOW_MIDDLE));
    }

    /**
     * Add the dirt sprite variations to the sprite map.
     */
    private static void putDirtSprites(SpriteGenerator generator, Map<Around, TextureRegion> sprites) {
        sprites.put(new Around(Sprite.Base.Dirt, false, false, false, false), generator.getSprite(SpriteName.WINTER_TILLED_MIDDLE));
        sprites.put(new Around(Sprite.Base.Dirt, false, false, false, true), generator.getSprite(SpriteName.WINTER_TILLED_LEFT));
        sprites.put(new Around(Sprite.Base.Dirt, false, false, true, false), generator.getSprite(SpriteName.WINTER_TILLED_BOTTOM));
        sprites.put(new Around(Sprite.Base.Dirt, false, false, true, true), generator.getSprite(SpriteName.WINTER_TILLED_CORNER_BL));
        sprites.put(new Around(Sprite.Base.Dirt, false, true, false, false), generator.getSprite(SpriteName.WINTER_TILLED_RIGHT));
        sprites.put(new Around(Sprite.Base.Dirt, false, true, false, true), generator.getSprite(SpriteName.WINTER_TILLED_SOLO_MID_VERTICAL));
        sprites.put(new Around(Sprite.Base.Dirt, false, true, true, false), generator.getSprite(SpriteName.WINTER_TILLED_CORNER_BR));
        sprites.put(new Around(Sprite.Base.Dirt, false, true, true, true), generator.getSprite(SpriteName.WINTER_TILLED_SOLO_LOWER));
        sprites.put(new Around(Sprite.Base.Dirt, true, false, false, false), generator.getSprite(SpriteName.WINTER_TILLED_TOP));
        sprites.put(new Around(Sprite.Base.Dirt, true, false, false, true), generator.getSprite(SpriteName.WINTER_TILLED_CORNER_TL));
        sprites.put(new Around(Sprite.Base.Dirt, true, false, true, false), generator.getSprite(SpriteName.WINTER_TILLED_SOLO_MID_HORIZONTAL));
        sprites.put(new Around(Sprite.Base.Dirt, true, false, true, true), generator.getSprite(SpriteName.WINTER_TILLED_SOLO_RIGHT));
        sprites.put(new Around(Sprite.Base.Dirt, true, true, false, false), generator.getSprite(SpriteName.WINTER_TILLED_CORNER_TR));
        sprites.put(new Around(Sprite.Base.Dirt, true, true, false, true), generator.getSprite(SpriteName.WINTER_TILLED_SOLO_UPPER));
        sprites.put(new Around(Sprite.Base.Dirt, true, true, true, false), generator.getSprite(SpriteName.WINTER_TILLED_SOLO_LEFT));
        sprites.put(new Around(Sprite.Base.Dirt, true, true, true, true), generator.getSprite(SpriteName.WINTER_TILLED_SOLO));
    }

    /**
     * Add the earth sprite variants to the sprite map.
     */
    private static void putEarthSprites(SpriteGenerator generator, Set<TextureRegion> sprites) {
        sprites.add(generator.getSprite(SpriteName.WINTER_EARTH_1));
        sprites.add(generator.getSprite(SpriteName.WINTER_EARTH_2));
        sprites.add(generator.getSprite(SpriteName.WINTER_EARTH_3));
        sprites.add(generator.getSprite(SpriteName.WINTER_EARTH_4));
        sprites.add(generator.getSprite(SpriteName.WINTER_EARTH_5));
    }


}
