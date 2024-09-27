package inf112.Sun_Mist_Mountain.app.View.Sound;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;

import inf112.Sun_Mist_Mountain.app.Model.Math.Shuffle;

public class BackgroundMusic {

    private final RandomGenerator rng;
    private final Shuffle<String> paths;
    private final TrackLoader loader;

    private Track currentTrack = null;
    private Instant lastCompletion = Instant.now();

    private final Consumer<Track> onTrackCompletion = track -> {
        this.lastCompletion = Instant.now();
        this.currentTrack = null;
        track.dispose();
    };

    public BackgroundMusic(RandomGenerator rng, TrackLoader loader) {
        this.rng = rng;
        this.paths = BackgroundMusic.fromPrefix("Sound/music", "ogg", 4);
        this.loader = loader;
    }

    /**
     * Play a background music track, if one is not already playing.
     */
    public void play() {
        if (this.currentTrack == null) {
            var path = this.paths.next(this.rng);

            this.currentTrack = this.loader.load(path);
            this.currentTrack.setOnCompletionListener(this.onTrackCompletion);
            this.currentTrack.play();
            this.lastCompletion = null;
        }
    }

    /**
     * @return the number of seconds since the last track finished, or 0 if a
     *         track is currently playing.
     */
    public long secondsSinceLastTrack() {
        if (this.lastCompletion == null) {
            return 0;
        }

        var now = Instant.now();
        return Duration.between(lastCompletion, now).toSeconds();
    }

    /**
     * Dispose of the currently playing background music, if any.
     */
    public void dispose() {
        if (this.currentTrack != null) {
            this.currentTrack.dispose();
        }
    }

    /**
     * @return variants of a music path from a file prefix and a count.
     */
    private static Shuffle<String> fromPrefix(String prefix, String extension, int count) {
        var paths = new String[count];
        for (int i = 0; i < count; i++) {
            paths[i] = String.format("%s-%d.%s", prefix, i + 1, extension);
        }

        return new Shuffle<>(Arrays.asList(paths));
    }

}
