package inf112.Sun_Mist_Mountain.app.View.Sound;

import java.util.random.RandomGenerator;

import com.badlogic.gdx.audio.Sound;

import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;

/**
 * A {@code PlayerSounds} is responsible for playing sounds when the player
 * interacts with the world.
 */
public class PlayerSounds {

    private final StepSounds steps;
    private final UseSounds uses;
    private final RandomGenerator random;

    private boolean ready = true;

    public PlayerSounds(RandomGenerator rng, StepSounds sounds, UseSounds uses) {
        this.random = rng;
        this.steps = sounds;
        this.uses = uses;
    }

    /**
     * Play a stepping sound related to the given sprite, if this is ready to
     * play more.
     */
    public void move(Sprite sprite) {
        if (this.ready) {
            var sounds = this.steps.on(sprite);
            sounds.any(this.random, this::play);
            this.ready = false;
        }
    }

    /**
     * Play an interaction sound related to the given sprite, if this is ready
     * to play more.
     */
    public void interact(Sprite sprite) {
        if (this.ready) {
            var sounds = this.uses.interact(sprite);
            sounds.any(this.random, this::play);
            this.ready = false;
        }
    }

    /**
     * Mark this as ready to play more sounds.
     */
    public void ready() {
        this.ready = true;
    }

    /**
     * Pause all currently playing sounds.
     */
    public void pause() {
        this.steps.all(Sound::pause);
        this.uses.all(Sound::pause);
    }

    /**
     * Resume any paused stepping sounds.
     */
    public void resume() {
        this.steps.all(Sound::resume);
        this.uses.all(Sound::resume);
    }

    /**
     * @param sound will be played with a random volume, pitch, and pan.
     */
    private void play(Sound sound) {
        var volume = this.random.nextFloat(0.1f, 0.2f);
        var pitch = this.random.nextFloat(0.9f, 1.1f);
        var pan = this.random.nextFloat(-0.1f, 0.1f);

        sound.play(volume, pitch, pan);
    }

}
