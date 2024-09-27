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

public class CommonSpritesheet implements Spritesheet {

    private final SpriteGenerator generator;
    private final Map<Around, TextureRegion> bases;
    private final ArrayList<TextureRegion> earthBases;
    private final PositionRandom rng;

    public CommonSpritesheet(PositionRandom rng) {
        this.generator = new SpriteGenerator();
        this.bases = new HashMap<>();
        this.rng = rng;

        var earthBases = new HashSet<TextureRegion>();

        CommonSpritesheet.putDirtSprites(this.generator, this.bases);
        CommonSpritesheet.putStoneTile(this.generator, this.bases);
        CommonSpritesheet.putEarthSprites(this.generator, earthBases);

        this.earthBases = new ArrayList<>(earthBases);
    }

    @Override
    public TextureRegion getBase(Around around, int x, int y) {
        if (around.base().equals(Sprite.Base.Earth)) {
            int index = this.rng.intAtBelow(x, y, this.earthBases.size());
            return this.earthBases.get(index);
        }

        return this.bases.get(around);
    }

    @Override
    public TextureRegion getEntity(inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite sprite) {
        return switch (sprite) {
            case Carrots -> this.generator.getSprite(SpriteName.CARROTS);
            case Rock -> this.generator.getSprite(SpriteName.ROCK);
            case Seeds -> this.generator.getSprite(SpriteName.SEEDS);
            case Tree -> this.generator.getSprite(SpriteName.TREE);
            case TreeStump -> this.generator.getSprite(SpriteName.TREE_STUMP);
            case Sprout -> this.generator.getSprite(SpriteName.SPROUTS);
            case House -> this.generator.getSprite(SpriteName.HOUSE);
        };
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
     * Add the dirt sprite variations to the sprite map.
     */
    private static void putDirtSprites(SpriteGenerator generator, Map<Around, TextureRegion> sprites) {
        sprites.put(new Around(Sprite.Base.Dirt, false, false, false, false), generator.getSprite(SpriteName.TILLED_MIDDLE));
        sprites.put(new Around(Sprite.Base.Dirt, false, false, false, true), generator.getSprite(SpriteName.TILLED_LEFT));
        sprites.put(new Around(Sprite.Base.Dirt, false, false, true, false), generator.getSprite(SpriteName.TILLED_BOTTOM));
        sprites.put(new Around(Sprite.Base.Dirt, false, false, true, true), generator.getSprite(SpriteName.TILLED_CORNER_BL));
        sprites.put(new Around(Sprite.Base.Dirt, false, true, false, false), generator.getSprite(SpriteName.TILLED_RIGHT));
        sprites.put(new Around(Sprite.Base.Dirt, false, true, false, true), generator.getSprite(SpriteName.TILLED_SOLO_MID_VERTICAL));
        sprites.put(new Around(Sprite.Base.Dirt, false, true, true, false), generator.getSprite(SpriteName.TILLED_CORNER_BR));
        sprites.put(new Around(Sprite.Base.Dirt, false, true, true, true), generator.getSprite(SpriteName.TILLED_SOLO_LOWER));
        sprites.put(new Around(Sprite.Base.Dirt, true, false, false, false), generator.getSprite(SpriteName.TILLED_TOP));
        sprites.put(new Around(Sprite.Base.Dirt, true, false, false, true), generator.getSprite(SpriteName.TILLED_CORNER_TL));
        sprites.put(new Around(Sprite.Base.Dirt, true, false, true, false), generator.getSprite(SpriteName.TILLED_SOLO_MID_HORIZONTAL));
        sprites.put(new Around(Sprite.Base.Dirt, true, false, true, true), generator.getSprite(SpriteName.TILLED_SOLO_RIGHT));
        sprites.put(new Around(Sprite.Base.Dirt, true, true, false, false), generator.getSprite(SpriteName.TILLED_CORNER_TR));
        sprites.put(new Around(Sprite.Base.Dirt, true, true, false, true), generator.getSprite(SpriteName.TILLED_SOLO_UPPER));
        sprites.put(new Around(Sprite.Base.Dirt, true, true, true, false), generator.getSprite(SpriteName.TILLED_SOLO_LEFT));
        sprites.put(new Around(Sprite.Base.Dirt, true, true, true, true), generator.getSprite(SpriteName.TILLED_SOLO));
    }

    /**
     * Add the dirt sprite variations to the sprite map.
     */
    private static void putStoneTile(SpriteGenerator generator, Map<Around, TextureRegion> sprites) {
        sprites.put(new Around(Sprite.Base.Stone, false, false, false, false), generator.getSprite(SpriteName.ROCK_MIDDLE));
        sprites.put(new Around(Sprite.Base.Stone, false, false, false, true), generator.getSprite(SpriteName.ROCK_LEFT));
        sprites.put(new Around(Sprite.Base.Stone, false, false, true, false), generator.getSprite(SpriteName.ROCK_BOTTOM));
        sprites.put(new Around(Sprite.Base.Stone, false, false, true, true), generator.getSprite(SpriteName.ROCK_CORNER_BL));
        sprites.put(new Around(Sprite.Base.Stone, false, true, false, false), generator.getSprite(SpriteName.ROCK_RIGHT));
        sprites.put(new Around(Sprite.Base.Stone, false, true, false, true), generator.getSprite(SpriteName.ROCK_SOLO_MID_VERTICAL));
        sprites.put(new Around(Sprite.Base.Stone, false, true, true, false), generator.getSprite(SpriteName.ROCK_CORNER_BR));
        sprites.put(new Around(Sprite.Base.Stone, false, true, true, true), generator.getSprite(SpriteName.ROCK_SOLO_LOWER));
        sprites.put(new Around(Sprite.Base.Stone, true, false, false, false), generator.getSprite(SpriteName.ROCK_TOP));
        sprites.put(new Around(Sprite.Base.Stone, true, false, false, true), generator.getSprite(SpriteName.ROCK_CORNER_TL));
        sprites.put(new Around(Sprite.Base.Stone, true, false, true, false), generator.getSprite(SpriteName.ROCK_SOLO_MID_HORIZONTAL));
        sprites.put(new Around(Sprite.Base.Stone, true, false, true, true), generator.getSprite(SpriteName.ROCK_SOLO_RIGHT));
        sprites.put(new Around(Sprite.Base.Stone, true, true, false, false), generator.getSprite(SpriteName.ROCK_CORNER_TR));
        sprites.put(new Around(Sprite.Base.Stone, true, true, false, true), generator.getSprite(SpriteName.ROCK_SOLO_UPPER));
        sprites.put(new Around(Sprite.Base.Stone, true, true, true, false), generator.getSprite(SpriteName.ROCK_SOLO_LEFT));
        sprites.put(new Around(Sprite.Base.Stone, true, true, true, true), generator.getSprite(SpriteName.ROCK_SOLO));
    }

    /**
     * Add the earth sprite variants to the sprite map.
     */
    private static void putEarthSprites(SpriteGenerator generator, Set<TextureRegion> sprites) {
        sprites.add(generator.getSprite(SpriteName.EARTH_1));
        sprites.add(generator.getSprite(SpriteName.EARTH_2));
        sprites.add(generator.getSprite(SpriteName.EARTH_3));
        sprites.add(generator.getSprite(SpriteName.EARTH_4));
        sprites.add(generator.getSprite(SpriteName.EARTH_5));
    }

}
