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

public class TestGrass extends GameTest {

    UseActions actions;
    Tile grass;

    @BeforeEach
    public void setup() {
        this.actions = mock(UseActions.class);
        this.grass = new Grass();
    }

    @Test
    public void tillToDirt() {
        this.grass.till(this.actions);
        verify(this.actions).changeTile(isA(Dirt.class));
    }

    @Test
    public void waterDoeNothing() {
        this.grass.water(this.actions);
        verifyNoInteractions(this.actions);
    }

    @Test
    public void mineDoesNothing() {
        this.grass.mine(this.actions);
        verifyNoInteractions(this.actions);
    }

    @Test
    public void isGround() {
        assertTrue(this.grass.isGround());
    }

}
