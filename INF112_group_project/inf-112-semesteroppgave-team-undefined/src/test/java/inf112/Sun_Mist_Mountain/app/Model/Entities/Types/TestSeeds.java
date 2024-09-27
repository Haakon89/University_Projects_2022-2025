package inf112.Sun_Mist_Mountain.app.Model.Entities.Types;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Random;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;

public class TestSeeds extends GameTest {

    @Test
    public void grow() {
        var actions = mock(UseActions.class);
        var seeds = new Seeds.Factory().create(new Random(RandomizingTest.SEED));

        for (int i = 0; i < Seeds.GROWTH_MAXIMUM; i++) {
            seeds.grow(actions);
        }

        verify(actions).replaceEntity(any());
    }

    @Test
    public void isGround() {
        var seeds = new Seeds.Factory().create(new Random(RandomizingTest.SEED));
        assertTrue(seeds.isGround());
    }

}
