package inf112.Sun_Mist_Mountain.app.View.Sound;

public interface TrackLoader {

    /**
     * @return a streaming audio track loaded from the given path.
     */
    public Track load(String path);

}
