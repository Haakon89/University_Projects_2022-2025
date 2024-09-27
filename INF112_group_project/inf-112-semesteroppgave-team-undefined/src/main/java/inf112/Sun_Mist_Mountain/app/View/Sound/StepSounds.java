package inf112.Sun_Mist_Mountain.app.View.Sound;

import java.util.function.Consumer;

import com.badlogic.gdx.audio.Sound;

import inf112.Sun_Mist_Mountain.app.Model.Math.Variants;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;

public interface StepSounds {

    /**
     * @return the sound variants used when stepping on {@code sprite}.
     */
    public Variants<Sound> on(Sprite sprite);

    /**
     * Invoke a function for all of the sounds used here.
     */
    public void all(Consumer<Sound> fn);

}
