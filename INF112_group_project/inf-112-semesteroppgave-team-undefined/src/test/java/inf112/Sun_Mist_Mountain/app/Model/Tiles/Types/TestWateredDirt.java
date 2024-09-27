package inf112.Sun_Mist_Mountain.app.Model.Tiles.Types;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;

public class TestWateredDirt extends GameTest {

    UseActions actions;
    Tile wateredDirt;

    @BeforeEach
    public void setup() {
        this.actions = mock(UseActions.class);
        this.wateredDirt = new WateredDirt();
    }

    @Test
    public void tillDoesNothing() {
        this.wateredDirt.till(this.actions);
        verifyNoInteractions(this.actions);
    }

    @Test
    public void waterDoesNothing() {
        this.wateredDirt.water(this.actions);
        verifyNoInteractions(this.actions);
    }

    @Test
    public void mineToGrass() {
        this.wateredDirt.mine(this.actions);
        verify(this.actions).changeTile(isA(Grass.class));
    }

    @Test
    public void isGround() {
        assertTrue(this.wateredDirt.isGround());
    }

}
