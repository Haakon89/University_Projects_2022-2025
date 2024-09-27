package inf112.Sun_Mist_Mountain.app.Controller;

import inf112.Sun_Mist_Mountain.app.TaskHandle;
import inf112.Sun_Mist_Mountain.app.Timer;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.View.Sound.PlayerSounds;

public class SoundController {

    /**
     * The approximate amount of time between each step sound.
     */
    private static final float WALKING_STEP_INTERVAL = 0.3f;

    private final Timer timer;
    private final PlayerSounds sounds;
    private final Runnable readySoundTask;

    private TaskHandle readySoundHandle = null;

    public SoundController(Timer timer, PlayerSounds sounds) {
        this.timer = timer;
        this.sounds = sounds;
        this.readySoundTask = this.sounds::ready;
    }

    /**
     * Start this controller.
     */
    public void start() {
        this.readySoundHandle = this.timer.schedule(this.readySoundTask, 0, WALKING_STEP_INTERVAL);
    }

    /**
     * Stop this controller.
     */
    public void stop() {
        this.readySoundHandle.cancel();
    }

    /**
     * Play a sound when the player moves on a tile {@code to}.
     */
    public void move(PlacedTile to) {
        this.sounds.move(to.getTile().getSprite());
    }

    /**
     * Play a sound when the player interacts with a tile {@code to}.
     */
    public void interact(PlacedTile with) {
        this.sounds.interact(with.getTile().getSprite());
    }

}
