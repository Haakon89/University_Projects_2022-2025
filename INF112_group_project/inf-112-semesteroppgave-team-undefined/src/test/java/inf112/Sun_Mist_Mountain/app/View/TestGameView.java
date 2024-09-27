package inf112.Sun_Mist_Mountain.app.View;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.Model.Coordinates;
import inf112.Sun_Mist_Mountain.app.Model.Camera.Camera;
import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.World.Generator;
import inf112.Sun_Mist_Mountain.app.Model.World.World;
import inf112.Sun_Mist_Mountain.app.View.Player.ViewablePlayer;

public class TestGameView extends GameTest {

    private World world;
    private ViewablePlayer player;

    private Coordinates coordinates;
    private Camera camera;
    private SpriteBatch batch;
    private Animator animator;
    private ViewableModel ui;
    private GameView view;

    @BeforeEach
    public void setup() {
        this.player = new ViewablePlayer() {
            @Override
            public Position getPosition() {
                return new Position(15, 15);
            }

            @Override
            public PlacedTile getTarget() {
                return null;
            }
        };

        this.camera = mock(Camera.class);
        when(this.camera.getTarget()).thenReturn(new Position(0, 0));

        this.coordinates = new Coordinates(this.camera);
        this.world = Generator.empty(1, 1);
        this.batch = mock(SpriteBatch.class);
        this.animator = mock(Animator.class);
        this.ui = mock(ViewableModel.class);

        this.view = new GameView(this.coordinates, this.batch, this.camera, this.world, this.player, this.animator, this.ui);
    }

    @Test
    public void camera() {
        this.view.draw();

        verify(this.batch, atLeastOnce()).setProjectionMatrix(ArgumentMatchers.any());
    }

    @Test
    public void doesNotDisposeBatch() {
        this.view.dispose();

        verifyNoInteractions(this.batch);
    }

    @Test
    public void resize() {
        this.view.resize(120, 130);

        var actual = this.view.getScreen();
        var expected = new Rectangle(0, 0, 120, 130);
        assertEquals(expected, actual);
    }

    @Test
    public void followsCamera() {
        this.view.draw();

        verify(this.camera, atLeastOnce()).getTarget();
    }

}
