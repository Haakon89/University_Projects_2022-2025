package inf112.Sun_Mist_Mountain.app.Model.Tiles.Types;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;

public class Stone extends Tile {

    public Stone() {
        super(new Sprite(Sprite.Base.Stone, Sprite.State.Dry));
    }

    @Override
    public boolean isGround() {
        return false;
    }

    @Override
    public void mine(TileActions actions) {
        actions.changeTile(new Earth());
    }

}
