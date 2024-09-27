package inf112.Sun_Mist_Mountain.app.Model.Tiles.Types;

import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;

public class Grass extends Tile {

    public Grass() {
        super(new Sprite(Sprite.Base.Grass, Sprite.State.Dry));
    }

    @Override
    public boolean isGround() {
        return true;
    }

    @Override
    public void till(TileActions actions) {
        actions.changeTile(new Dirt());
    }

}
