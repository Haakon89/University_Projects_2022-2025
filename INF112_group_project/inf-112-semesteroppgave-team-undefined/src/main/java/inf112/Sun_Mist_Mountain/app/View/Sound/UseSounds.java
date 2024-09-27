package inf112.Sun_Mist_Mountain.app.View.Sound;

import java.util.function.Consumer;

import com.badlogic.gdx.audio.Sound;

import inf112.Sun_Mist_Mountain.app.Model.Math.Variants;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;

public interface UseSounds {

    /**
     * @return the sound variants used when interacting with the given sprite.
     */
    public Variants<Sound> interact(Sprite sprite);

    /**
     * Invoke a function on all of the sounds used here.
     */
    public void all(Consumer<Sound> fn);

}
