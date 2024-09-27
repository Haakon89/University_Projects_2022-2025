package inf112.Sun_Mist_Mountain.app.Model.Entities.Types;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Random;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;

public class TestCarrots extends GameTest {

    @Test
    public void till() {
        var actions = mock(UseActions.class);
        var carrots = new Carrots.Factory().create(new Random(RandomizingTest.SEED));

        carrots.till(actions);
        verify(actions, atLeastOnce()).dropItems(any());
        verify(actions, atLeastOnce()).destroyEntity();;
    }

    @Test
    public void isGround() {
        var carrots = new Carrots.Factory().create(new Random(RandomizingTest.SEED));
        assertTrue(carrots.isGround());
    }

}
