package inf112.Sun_Mist_Mountain.app.Model.Entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.Actions.TileActions;
import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.Carrots;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.Rock;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.Seeds;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.Tree;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.TreeStump;

public class TestEntity extends RandomizingTest {

    private UseActions actions;
    private Entity entity;

    @BeforeEach
    public void setup() {
        this.actions = mock(UseActions.class);
        this.entity = new Entity(null) {
            @Override
            public boolean isGround() { return false; }
        };
    }

    @Test
    public void tillTest() {
        this.entity.till(actions);
        verifyNoInteractions(actions);
    }

    @Test
    public void growTest() {
        this.entity.grow(actions);
        verifyNoInteractions(actions);
    }

    @Test
    public void mineTest() {
        this.entity.mine(actions);
        verifyNoInteractions(actions);
    }

    @Test
    public void allInteractionsWorkWithTileActions() {
        var actions = mock(TileActions.class);
        var factories = new EntityFactory[] {
            new Carrots.Factory(),
            new Rock.Factory(),
            new Seeds.Factory(),
            new Tree.Factory(),
            new TreeStump.Factory(),
        };

        for (var factory : factories) {
            var rng = this.nextRandom();
            var entity = factory.create(rng);

            assertDoesNotThrow(() -> {
                entity.grow(actions);
                entity.mine(actions);
                entity.till(actions);
            });
        }
    }

}
