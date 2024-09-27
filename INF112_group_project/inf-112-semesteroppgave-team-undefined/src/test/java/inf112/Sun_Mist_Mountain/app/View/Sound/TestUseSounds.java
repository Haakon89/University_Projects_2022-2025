package inf112.Sun_Mist_Mountain.app.View.Sound;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.audio.Sound;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;

public class TestUseSounds extends GameTest {

    @Test
    public void pauseAll() {
        var sprites = new ArrayList<Sprite>();
        for (var base : Sprite.Base.values()) {
            for (var state : Sprite.State.values()) {
                sprites.add(new Sprite(base, state));
            }
        }

        // Play every sprite sound
        var sounds = new OggUseSounds();
        for (var sprite : sprites) {
            sounds.interact(sprite).all(Sound::play);
        }

        assertDoesNotThrow(() -> sounds.all(Sound::pause));
    }

}
