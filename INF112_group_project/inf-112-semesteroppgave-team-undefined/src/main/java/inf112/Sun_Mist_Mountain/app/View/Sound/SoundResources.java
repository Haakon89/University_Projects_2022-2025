package inf112.Sun_Mist_Mountain.app.View.Sound;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import inf112.Sun_Mist_Mountain.app.Model.Math.Variants;

public class SoundResources {

    /**
     * @return variants of a sound from a file prefix, extension, and the number
     *         of files in the sequence.
     */
    public static Variants<Sound> fromPrefix(String prefix, String extension, int count) {
        var sounds = new Sound[count];
        for (int i = 0; i < count; i++) {
            var path = String.format("%s-%d.%s", prefix, i + 1, extension);
            sounds[i] = Gdx.audio.newSound(Gdx.files.internal(path));
        }

        return new Variants<>(Arrays.asList(sounds));
    }

}
