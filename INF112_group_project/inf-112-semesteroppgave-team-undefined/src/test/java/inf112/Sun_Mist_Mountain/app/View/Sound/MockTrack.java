package inf112.Sun_Mist_Mountain.app.View.Sound;

import java.util.function.Consumer;

public class MockTrack implements Track {
    private boolean isDisposed = false;
    private boolean isPlaying = false;
    private int pauses = 0;

    private Consumer<? super Track> listener = null;

    public void finish() {
        this.isPlaying = false;
        if (this.listener != null) {
            this.listener.accept(this);
        }
    }

    public void reset() {
        this.isDisposed = false;
        this.isPlaying = false;
        this.listener = null;
    }

    public int getPauseCount() {
        return this.pauses;
    }

    @Override
    public void play() {
        if (this.isDisposed) {
            throw new UsedAfterDisposing();
        }

        this.isPlaying = true;
    }

    @Override
    public void pause() {
        if (this.isDisposed) {
            throw new UsedAfterDisposing();
        }

        if (!this.isPlaying) {
            throw new PlayedTwice();
        }

        this.pauses += 1;
        this.isPlaying = false;
    }

    @Override
    public boolean isPlaying() {
        if (this.isDisposed) {
            throw new UsedAfterDisposing();
        }

        return this.isPlaying;
    }

    @Override
    public void setOnCompletionListener(Consumer<? super Track> listener) {
        if (this.isDisposed) {
            throw new UsedAfterDisposing();
        }

        this.listener = listener;
    }

    @Override
    public void setLooping(boolean shouldLoop) {
        if (this.isDisposed) {
            throw new UsedAfterDisposing();
        }
    }

    @Override
    public void setVolume(float volume) {
        if (this.isDisposed) {
            throw new UsedAfterDisposing();
        }
    }

    @Override
    public void dispose() {
        if (this.isDisposed) {
            throw new UsedAfterDisposing();
        }

        this.isDisposed = true;
    }

    private static class UsedAfterDisposing extends RuntimeException {}

    private static class PlayedTwice extends RuntimeException {}

}
