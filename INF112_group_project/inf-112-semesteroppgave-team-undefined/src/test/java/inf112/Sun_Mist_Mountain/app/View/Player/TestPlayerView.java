package inf112.Sun_Mist_Mountain.app.View.Player;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Dirt;
import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;
import inf112.Sun_Mist_Mountain.app.View.Animator;

public class TestPlayerView extends GameTest {

    @Test
    public void drawTarget() {
        var animator = new Animator();

        var player = mock(ViewablePlayer.class);
        when(player.getPosition()).thenReturn(new Position(60.0, 75.0));
        when(player.getTarget()).thenReturn(new PlacedTile(new PixelPosition(50, 40), new Dirt()));

        var batch = mock(SpriteBatch.class);

        var view = new PlayerView(animator, player);

        view.drawWorld(batch);
        view.drawUi(batch);

        verify(batch).draw(
            ArgumentMatchers.<TextureRegion>any(),
            eq(40.0f),
            eq(50.0f),
            eq((float) Tile.WIDTH),
            eq((float) Tile.HEIGHT));
    }

    @Test
    public void drawPlayer() {
        var animator = new Animator();

        var player = mock(ViewablePlayer.class);
        when(player.getPosition()).thenReturn(new Position(64.0, 102.0));

        var batch = mock(SpriteBatch.class);
        var view = new PlayerView(animator, player);
        view.drawWorld(batch);
        view.drawUi(batch);

        verify(batch).draw(animator.getCurrentFrame(), 64.0f, 102.0f);
    }

}
