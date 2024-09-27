package inf112.Sun_Mist_Mountain.app.View.Sound;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.audio.Sound;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.Math.Variants;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;

public class TestPlayerSounds extends RandomizingTest {

    @Test
    public void onlyPlaysOnceBeforeReadying() {
        var rng = this.nextRandom();
        var steps = new MockStepSounds();
        var uses = new MockUseSounds();

        var player = new PlayerSounds(rng, steps, uses);
        player.ready();
        player.move(null);
        player.move(null);

        verify(steps.sound, times(1)).play(anyFloat(), anyFloat(), anyFloat());
        verifyNoInteractions(uses.sound);
    }

    @Test
    public void startsOutReady() {
        var rng = this.nextRandom();
        var steps = new MockStepSounds();
        var uses = new MockUseSounds();

        var player = new PlayerSounds(rng, steps, uses);
        player.move(null);

        verify(steps.sound).play(anyFloat(), anyFloat(), anyFloat());
        verifyNoInteractions(uses.sound);
    }

    @Test
    public void resetByReadying() {
        var rng = this.nextRandom();
        var steps = new MockStepSounds();
        var uses = new MockUseSounds();

        var player = new PlayerSounds(rng, steps, uses);
        player.ready();
        player.move(null);
        player.move(null);
        player.interact(null);
        player.ready();
        player.move(null);
        player.interact(null);
        player.ready();
        player.move(null);
        player.ready();
        player.interact(null);
        player.interact(null);
        player.ready();
        player.interact(null);
        player.ready();

        verify(steps.sound, times(3)).play(anyFloat(), anyFloat(), anyFloat());
        verify(uses.sound, times(2)).play(anyFloat(), anyFloat(), anyFloat());
    }

    @Test
    public void pause() {
        var rng = this.nextRandom();
        var steps = new MockStepSounds();
        var uses = new MockUseSounds();

        var player = new PlayerSounds(rng, steps, uses);
        player.pause();

        verify(steps.sound).pause();
        verify(uses.sound).pause();;
    }

    @Test
    public void resume() {
        var rng = this.nextRandom();
        var steps = new MockStepSounds();
        var uses = new MockUseSounds();

        var player = new PlayerSounds(rng, steps, uses);
        player.resume();

        verify(steps.sound).resume();
        verify(uses.sound).resume();
    }

    private static class MockStepSounds implements StepSounds {

        public final Sound sound = mock(Sound.class);
        private final Variants<Sound> variants = new Variants<>(List.of(sound));

        @Override
        public Variants<Sound> on(Sprite sprite) {
            return this.variants;
        }

        @Override
        public void all(Consumer<Sound> fn) {
            this.variants.all(fn);
        }

    }

    private static class MockUseSounds implements UseSounds {

        public final Sound sound = mock(Sound.class);
        private final Variants<Sound> variants = new Variants<>(List.of(sound));

        @Override
        public Variants<Sound> interact(Sprite sprite) {
            return this.variants;
        }

        @Override
        public void all(Consumer<Sound> fn) {
            this.variants.all(fn);
        }

    }


}
