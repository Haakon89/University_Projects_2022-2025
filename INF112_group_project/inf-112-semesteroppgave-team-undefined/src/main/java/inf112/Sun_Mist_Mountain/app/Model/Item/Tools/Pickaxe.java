package inf112.Sun_Mist_Mountain.app.Model.Item.Tools;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Item.Item;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.View.SpriteGenerator;
import inf112.Sun_Mist_Mountain.app.View.SpriteName;

public class Pickaxe implements Item {
    SpriteGenerator sprite = new SpriteGenerator();
    private String name = "pickaxe";
    @Override
    public TextureRegion getTexture() {
        return sprite.getSprite(SpriteName.ITEM_PICKAXE);
    }

    @Override
    public void use(UseActions actions, PlacedTile tile) {
        if (tile != null) {
            actions.exert(5);
            tile.mine(actions);
        }
    }

    @Override
    public boolean isReuseable() {
        return true;
    }

    @Override
    public boolean isSellable() {
        return false;
    }

    @Override
    public int getSellValue() {
        return 0;
    }

    @Override
    public int getPrice() {
        return 10;
    }
    @Override
    public boolean isEqual(String name) {
        if (name.equals(this.name)) {
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
    
}
