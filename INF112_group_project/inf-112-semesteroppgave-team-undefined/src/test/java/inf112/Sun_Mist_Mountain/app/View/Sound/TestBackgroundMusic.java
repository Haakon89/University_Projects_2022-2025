package inf112.Sun_Mist_Mountain.app.View.Sound;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;

public class TestBackgroundMusic {

    @Test
    public void disposeBeforePlaying() {
        var rng = new Random(RandomizingTest.SEED);
        var music = new BackgroundMusic(rng, new MockTrackLoader());

        assertDoesNotThrow(music::dispose);
    }

    @Test
    public void disposeAfterPlaying() {
        var rng = new Random(RandomizingTest.SEED);
        var music = new BackgroundMusic(rng, new MockTrackLoader());

        music.play();
        assertDoesNotThrow(music::dispose);
    }

    @Test
    public void secondsSinceLastTrackWhenPlaying() {
        for (var i = 0; i < RandomizingTest.ITERATIONS; i++) {
            var rng = new Random(i);
            var music = new BackgroundMusic(rng, new MockTrackLoader());

            music.play();
            assertEquals(0.0, music.secondsSinceLastTrack());
        }
    }

    @Test
    public void atLeastFourDistinctTracks() {
        for (var i = 0; i < RandomizingTest.ITERATIONS; i++) {
            var rng = new Random(i);
            var loader = new MockTrackLoader();
            var music = new BackgroundMusic(rng, loader);

            for (var j = 0; j < 4; j++) {
                music.play();
                loader.tracks[0].finish();
            }

            assertEquals(4, loader.loadedPaths.size());
        }
    }

    @Test
    public void doesNotPlayIfAlreadyPlaying() {
        for (var i = 0; i < RandomizingTest.ITERATIONS; i++) {
            var rng = new Random(i);
            var loader = new MockTrackLoader();
            var music = new BackgroundMusic(rng, loader);

            music.play();
            assertTrue(loader.tracks[0].isPlaying());
            assertDoesNotThrow(music::play);
        }
    }

}
