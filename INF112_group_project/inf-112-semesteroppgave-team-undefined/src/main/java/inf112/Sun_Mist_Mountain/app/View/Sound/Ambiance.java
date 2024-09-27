package inf112.Sun_Mist_Mountain.app.View.Sound;

import inf112.Sun_Mist_Mountain.app.Model.World.Seasons;

public class Ambiance {

    private static final float VOLUME = 0.1f;

    private final Track birds;
    private final Track drips;
    private Track current;

    public Ambiance(TrackLoader loader) {
        this.birds = loader.load("Sound/ambiance-birds.ogg");
        this.drips = loader.load("Sound/ambiance-drips.ogg");

        this.birds.setLooping(true);
        this.drips.setLooping(true);

        this.birds.setVolume(Ambiance.VOLUME);
        this.drips.setVolume(Ambiance.VOLUME);

        this.current = this.birds;
    }

    /**
     * Start playing the background ambiance.
     */
    public void start() {
        this.current.play();
    }

    /**
     * Pause the currently playing background ambiance.
     */
    public void pause() {
        this.current.pause();
    }

    /**
     * Resume any currently playing background ambiance.
     */
    public void resume() {
        this.current.play();
    }

    /**
     * Change to an appropriate ambiance for the given season.
     */
    public void changeSeason(Seasons season) {
        var newSound = switch (season) {
            case WINTER -> this.drips;
            default -> this.birds;
        };

        if (newSound != this.current) {
            this.current.pause();
            this.current = newSound;
            this.current.play();
        }
    }

}
