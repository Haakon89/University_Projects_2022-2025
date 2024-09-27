package inf112.Sun_Mist_Mountain.app.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import inf112.Sun_Mist_Mountain.app.Conductor;

public class ShopController  extends InputAdapter{
	private final Conductor conductor;

    public ShopController(Conductor conductor) {
        this.conductor = conductor;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.R) {
            this.conductor.viewShop();
            return true;
        }

        return false;
    }

}
