package inf112.Sun_Mist_Mountain.app.Model.Tiles.Types;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;

public class Dirt extends Tile {

    public Dirt() {
        super(new Sprite(Sprite.Base.Dirt, Sprite.State.Dry));
    }

    @Override
    public boolean isGround() {
        return true;
    }

    @Override
    public void water(TileActions actions) {
        actions.changeTile(new WateredDirt());
    }

    @Override
    public void mine(TileActions actions) {
        actions.changeTile(new Grass());
    }

}
