package inf112.Sun_Mist_Mountain.app.View.Sound;

import java.util.function.Consumer;

public interface Track {

    /**
     * Start the track, or resume it if it was paused.
     */
    public void play();

    /**
     * Temporarily pause this track. Call {@code this.play()} to resume it
     * again.
     */
    public void pause();

    /**
     * @return {@code true} if this track is currently playing.
     */
    public boolean isPlaying();

    /**
     * @param listener will be invoked with {@code this} when this is done
     *                 playing.
     */
    public void setOnCompletionListener(Consumer<? super Track> listener);

    /**
     * @param shouldLoop is {@code true} if this track should restart when it
     *                   reaches its end.
     */
    public void setLooping(boolean shouldLoop);

    /**
     * @param volume is in the range {@code [0, 1]}, where {@code 0} is
     *               completely silent and {@code 1} is full volume.
     */
    public void setVolume(float volume);

    /**
     * Dispose of this track.
     */
    public void dispose();

}
