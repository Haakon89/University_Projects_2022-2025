package inf112.Sun_Mist_Mountain.app.View.Sound;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.audio.Sound;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;

public class TestStepSounds extends GameTest {

    private static final Sprite GRASS = new Sprite(Sprite.Base.Grass, Sprite.State.Dry);
    private static final Sprite DIRT = new Sprite(Sprite.Base.Dirt, Sprite.State.Dry);

    @Test
    public void multipleGrassVariants() {
        var grasses = new HashSet<>();

        var sounds = new OggStepSounds();
        sounds.on(GRASS).all(grasses::add);

        assertFalse(grasses.isEmpty());
        assertNotEquals(1, grasses.size());
    }

    @Test
    public void multipleDirtVariants() {
        var dirts = new HashSet<>();

        var sounds = new OggStepSounds();
        sounds.on(DIRT).all(dirts::add);

        assertFalse(dirts.isEmpty());
        assertNotEquals(1, dirts.size());
    }

    @Test
    public void pauseAll() {
        var sprites = new ArrayList<Sprite>();
        for (var base : Sprite.Base.values()) {
            for (var state : Sprite.State.values()) {
                sprites.add(new Sprite(base, state));
            }
        }

        // Play every sprite sound
        var sounds = new OggStepSounds();
        for (var sprite : sprites) {
            sounds.on(sprite).all(Sound::play);
        }

        assertDoesNotThrow(() -> sounds.all(Sound::pause));
    }

}
