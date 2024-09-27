package inf112.Sun_Mist_Mountain.app.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import inf112.Sun_Mist_Mountain.app.Conductor;

/**
 * A simple controller which will only pause or unpause the game.
 */
public class PauseController extends InputAdapter {

    private final Conductor conductor;

    public PauseController(Conductor conductor) {
        this.conductor = conductor;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.E) {
            this.conductor.pauseGame();
            return true;
        }

        return false;
    }

}
