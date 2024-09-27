package inf112.Sun_Mist_Mountain.app.Model.Item.Seeds;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Item.Item;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.View.SpriteGenerator;
import inf112.Sun_Mist_Mountain.app.View.SpriteName;

public class Carrot implements Item {
    private SpriteGenerator sprite = new SpriteGenerator();
    private String name = "carrot";

    @Override
    public TextureRegion getTexture() {
        return sprite.getSprite(SpriteName.ITEM_CARROT);
    }

    @Override
    public void use(UseActions actions, PlacedTile tile) {
        actions.changeStamina(10);
        actions.destroyItem();
    }

    @Override
    public boolean isReuseable() {
        return false;
    }

    @Override
    public boolean isSellable() {
        return true;
    }

    @Override
    public int getSellValue() {
        return 80;
    }

    @Override
    public int getPrice() {
        return 100;
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
