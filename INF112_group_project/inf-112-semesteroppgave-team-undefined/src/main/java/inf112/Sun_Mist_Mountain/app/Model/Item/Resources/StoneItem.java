package inf112.Sun_Mist_Mountain.app.Model.Item.Resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.Rock;
import inf112.Sun_Mist_Mountain.app.Model.Item.Item;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.View.SpriteGenerator;
import inf112.Sun_Mist_Mountain.app.View.SpriteName;

public class StoneItem implements Item {

    private final SpriteGenerator sprites = new SpriteGenerator();
    private String name = "stone";

    @Override
    public TextureRegion getTexture() {
        return sprites.getSprite(SpriteName.ROCK);
    }

    @Override
    public void use(UseActions actions, PlacedTile tile) {
        actions.placeEntity(new Rock.Factory());
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
        return 5;
    }

    @Override
    public int getPrice() {
        return 30;
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
