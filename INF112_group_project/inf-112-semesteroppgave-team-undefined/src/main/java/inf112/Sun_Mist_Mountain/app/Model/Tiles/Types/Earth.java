package inf112.Sun_Mist_Mountain.app.Model.Tiles.Types;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;

public class Earth extends Tile {

    public Earth() {
        super(new Sprite(Sprite.Base.Earth, Sprite.State.Dry));
    }

    @Override
    public boolean isGround() {
        return true;
    }

    @Override
    public void mine(TileActions actions) {
        actions.changeTile(new Dirt());
    }

}
