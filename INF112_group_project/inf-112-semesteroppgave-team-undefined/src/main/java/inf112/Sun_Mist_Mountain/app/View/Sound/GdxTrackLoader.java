package inf112.Sun_Mist_Mountain.app.View.Sound;

import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GdxTrackLoader implements TrackLoader {

    @Override
    public Track load(String path) {
        return new GdxTrack(Gdx.audio.newMusic(Gdx.files.internal(path)));
    }


    private static record GdxTrack(Music music) implements Track {
        @Override
        public void play() {
            this.music.play();
        }

        @Override
        public void pause() {
            this.music.pause();
        }

        @Override
        public boolean isPlaying() {
            return this.music.isPlaying();
        }

        @Override
        public void setOnCompletionListener(Consumer<? super Track> listener) {
            this.music.setOnCompletionListener(music -> listener.accept(this));
        }

        @Override
        public void setLooping(boolean shouldLoop) {
            this.music.setLooping(shouldLoop);
        }

        @Override
        public void setVolume(float volume) {
            this.music.setVolume(volume);
        }

        @Override
        public void dispose() {
            this.music.dispose();
        }
    }

}
