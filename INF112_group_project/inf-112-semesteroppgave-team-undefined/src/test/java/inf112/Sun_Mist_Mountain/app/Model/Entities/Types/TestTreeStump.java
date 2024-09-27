package inf112.Sun_Mist_Mountain.app.Model.Entities.Types;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Random;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;

public class TestTreeStump extends GameTest {

    @Test
    public void mineDestroysEntity() {
        var actions = mock(UseActions.class);
        var stump = new TreeStump.Factory().create(new Random(RandomizingTest.SEED));

        for (int i = 0; i < TreeStump.HEALTH_MAXIMUM; i++) {
            stump.mine(actions);
        }

        verify(actions, atLeastOnce()).destroyEntity();
    }

    @Test
    public void isNotGround() {
        var stump = new TreeStump.Factory().create(new Random(RandomizingTest.SEED));
        assertFalse(stump.isGround());
    }

}
