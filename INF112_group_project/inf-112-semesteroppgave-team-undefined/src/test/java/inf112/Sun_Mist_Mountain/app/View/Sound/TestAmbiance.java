package inf112.Sun_Mist_Mountain.app.View.Sound;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.Model.World.Seasons;

public class TestAmbiance {

    @Test
    public void startPauseResumeOnAllSeasons() {
        var loader = new MockTrackLoader();
        var ambiance = new Ambiance(loader);

        for (var season : Seasons.values()) {
            ambiance.changeSeason(season);
            assertDoesNotThrow(ambiance::start);
            assertDoesNotThrow(ambiance::pause);
            assertDoesNotThrow(ambiance::resume);
        }
    }

    @Test
    public void changeSeasonWhilePlaying() {
        var birds = new MockTrack();
        var drips = new MockTrack();

        var loader = new MockTrackLoader(birds, drips);
        var ambiance = new Ambiance(loader);

        ambiance.start();
        ambiance.changeSeason(Seasons.WINTER);
        ambiance.changeSeason(Seasons.WINTER);
        ambiance.changeSeason(Seasons.SPRING);

        assertFalse(drips.isPlaying());
        assertTrue(birds.isPlaying());
        assertEquals(1, drips.getPauseCount());
    }

}
