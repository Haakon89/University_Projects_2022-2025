package inf112.Sun_Mist_Mountain.app.View;

import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteGenerator {
    
    private Texture objectSheet;
    private Texture environmentSheet;
    private Texture itemSheet;
    private HashMap<SpriteName, TextureRegion> sprites;

    int spriteWidth = 32;
    int spriteHeight = 32;

    public SpriteGenerator() {
        this.objectSheet = new Texture(Gdx.files.internal("World/world_objects_sheet.png"));
        this.environmentSheet = new Texture(Gdx.files.internal("World/spritesheet_ground.png"));
        this.itemSheet = new Texture(Gdx.files.internal("Item/items.png"));
    
        int spritesPerRow = environmentSheet.getWidth() / spriteWidth;

        TextureRegion house = new TextureRegion(objectSheet, 0, 0, 160, 192);
        TextureRegion tree = new TextureRegion(objectSheet, 192, 62, 32, 128);
        TextureRegion rock = new TextureRegion(objectSheet, 160, 96, 32, 32);
        TextureRegion sprouts = new TextureRegion(objectSheet, 160, 64, 32, 32);
        TextureRegion carrots = new TextureRegion(objectSheet, 160, 32, 32, 32);
        TextureRegion seed = new TextureRegion(objectSheet, 160, 128, 32, 32);
        TextureRegion treeStump = new TextureRegion(objectSheet, 160, 160, 32, 32);
        TextureRegion wateringcan = new TextureRegion(itemSheet, 0, 0, 32, 32);
        TextureRegion hoe = new TextureRegion(itemSheet, 32, 0, 32, 32);
        TextureRegion pickaxe = new TextureRegion(itemSheet, 64, 0, 32, 32);
        TextureRegion carrot = new TextureRegion(itemSheet, 0, 32, 32, 32);

        this.sprites = new HashMap<>();
        this.sprites.put(SpriteName.HOUSE, house);
        this.sprites.put(SpriteName.TREE, tree);
        this.sprites.put(SpriteName.ROCK, rock);
        this.sprites.put(SpriteName.SPROUTS, sprouts);
        this.sprites.put(SpriteName.CARROTS, carrots);
        this.sprites.put(SpriteName.SEEDS, seed);
        this.sprites.put(SpriteName.TREE_STUMP, treeStump);
        this.sprites.put(SpriteName.ITEM_WATERINGCAN, wateringcan);
        this.sprites.put(SpriteName.ITEM_HOE, hoe);
        this.sprites.put(SpriteName.ITEM_PICKAXE, pickaxe);
        this.sprites.put(SpriteName.ITEM_CARROT, carrot);

        for (SpriteName spriteName : SpriteName.values()) {
            if (spriteName.equals(SpriteName.TREE)) {
                break;
            }
            int index = spriteName.ordinal();
            int x = (index % spritesPerRow) * spriteWidth;
            int y = (index / spritesPerRow) * spriteHeight;
        
            TextureRegion sprite = new TextureRegion(environmentSheet, x, y, spriteWidth, spriteHeight);
            this.sprites.put(spriteName, sprite);
        }
    }

    /**
     * returns a TextureRegion depending on the SpriteName given.
     * @param name
     * @return
     */
    public TextureRegion getSprite(SpriteName name) {
        return sprites.get(name);
    }
}
