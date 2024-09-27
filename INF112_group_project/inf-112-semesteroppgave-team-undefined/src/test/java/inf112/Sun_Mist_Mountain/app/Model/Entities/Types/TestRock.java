package inf112.Sun_Mist_Mountain.app.Model.Entities.Types;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Entity;
import inf112.Sun_Mist_Mountain.app.Model.Item.ItemStack;

public class TestRock extends GameTest {

    private UseActions actions;
    private Entity rock;

    @BeforeEach
    public void setup() {
        this.actions = mock(UseActions.class);
        this.rock = new Rock.Factory().create(new Random(RandomizingTest.SEED));
    }

    @Test
    public void mineTest() {
        this.rock.mine(actions);
        verify(actions).markHappened();

        this.rock.mine(actions);
        this.rock.mine(actions);
        verify(actions, atLeastOnce()).dropItems(isA(ItemStack.class));
        verify(actions, atLeastOnce()).destroyEntity();;
    }

    @Test
    public void isGroundTest() {
        assertFalse(this.rock.isGround());
    }

}
