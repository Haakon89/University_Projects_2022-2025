package inf112.Sun_Mist_Mountain.app.Model.Tiles.Types;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;

public class WateredDirt extends Tile {

    public WateredDirt() {
        super(new Sprite(Sprite.Base.Dirt, Sprite.State.Watered));
    }

    @Override
    public boolean isGround() {
        return true;
    }

    @Override
    public void mine(TileActions actions) {
        actions.changeTile(new Grass());
    }

    @Override
    public void dry(TileActions actions) {
        actions.changeTile(new Dirt());
    }

}
