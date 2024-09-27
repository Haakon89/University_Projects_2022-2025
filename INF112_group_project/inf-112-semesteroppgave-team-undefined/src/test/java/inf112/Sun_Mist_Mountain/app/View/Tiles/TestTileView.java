package inf112.Sun_Mist_Mountain.app.View.Tiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;
import java.util.random.RandomGenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.Seeds;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Grass;
import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;
import inf112.Sun_Mist_Mountain.app.View.World.Adjacent;

public class TestTileView extends GameTest {

    private RandomGenerator rng;

    @BeforeEach
    public void setup() {
        this.rng = new Random(RandomizingTest.SEED);
    }

    @Test
    public void worksWithUnexpectedTile() {
        var batch = mock(SpriteBatch.class);

        var sheet = new EmptySpritesheet();
        var view = new TileView();

        assertDoesNotThrow(() -> {
            view.draw(batch, TestTileView.getDummyAdjacent(), sheet);
        });
    }

    @Test
    public void drawEntityMissingTexture() {
        var batch = mock(SpriteBatch.class);
        var sheet = new EmptySpritesheet();
        var view = new TileView();

        var adjacent = TestTileView.getDummyAdjacent();
        var center = adjacent.center();
        center.setEntity(new Seeds.Factory().create(this.rng));

        var pos = center.getPosition();

        view.drawEntity(batch, adjacent.center(), sheet);
        verify(batch).draw(
            ArgumentMatchers.<TextureRegion>notNull(),
            eq((float) pos.col()),
            eq((float) pos.row()),
            eq((float) Tile.WIDTH),
            eq((float) Tile.HEIGHT));
    }

    @Test
    public void drawEntityGoodTexture() {
        var batch = mock(SpriteBatch.class);
        var sheet = mock(Spritesheet.class);
        var texture = mock(TextureRegion.class);

        when(sheet.getEntity(any())).thenReturn(texture);

        var adjacent = TestTileView.getDummyAdjacent();
        adjacent.center().setEntity(new Seeds.Factory().create(this.rng));

        new TileView().drawEntity(batch, adjacent.center(), sheet);

        verify(batch).draw(eq(texture), anyFloat(), anyFloat());
    }

    /**
     * @return a fully initialized {@code Adjacent} with arbitrary values.
     */
    private static Adjacent getDummyAdjacent() {
        var position = new PixelPosition(0, 0);
        var tile = new Grass();
        var placed = new PlacedTile(position, tile);

        return new Adjacent(placed, placed, placed, placed, placed);
    }

}
