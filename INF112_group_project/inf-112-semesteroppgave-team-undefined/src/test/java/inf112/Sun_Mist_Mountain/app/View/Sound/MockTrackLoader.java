package inf112.Sun_Mist_Mountain.app.View.Sound;

import java.util.HashSet;

public class MockTrackLoader implements TrackLoader {
    public MockTrack[] tracks;
    public HashSet<String> loadedPaths;
    private int i = 0;

    public MockTrackLoader() {
        this.tracks = new MockTrack[] { new MockTrack() };
        this.loadedPaths = new HashSet<>();
    }

    public MockTrackLoader(MockTrack... tracks) {
        this.tracks = tracks;
        this.loadedPaths = new HashSet<>();
    }

    @Override
    public Track load(String path) {
        if (this.tracks.length <= this.i) {
            this.i = 0;
        }

        this.loadedPaths.add(path);

        var track = this.tracks[this.i];
        this.i += 1;

        track.reset();
        return track;
    }
}
