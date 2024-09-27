package inf112.Sun_Mist_Mountain.app.View.World;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Entity;
import inf112.Sun_Mist_Mountain.app.Model.Player.Player;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Grass;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.WateredDirt;
import inf112.Sun_Mist_Mountain.app.Model.World.Generator;
import inf112.Sun_Mist_Mountain.app.Model.World.World;
import inf112.Sun_Mist_Mountain.app.View.Animator;
import inf112.Sun_Mist_Mountain.app.View.Player.PlayerView;
import inf112.Sun_Mist_Mountain.app.View.Tiles.Around;
import inf112.Sun_Mist_Mountain.app.View.Tiles.Spritesheet;

public class TestWorldView extends GameTest {

    @BeforeEach
    public void setup() {
        Generator.unregisterAll();
        Generator.registerTile('G', new Grass());
        Generator.registerTile('P', new WateredDirt());
    }

    @Test
    public void drawsTile() {
        var tile = mock(Tile.class);
        when(tile.getSprite()).thenReturn(new Sprite(Sprite.Base.Dirt, Sprite.State.Watered));

        var world = Generator.fromString("P");
        var view = WorldView.create(world, TestWorldView.createPlayerView(world));
        var batch = mock(SpriteBatch.class);

        view.drawWorld(batch);
        verify(batch).draw(ArgumentMatchers.<TextureRegion>any(), eq(0f), eq(0f));
    }

    @Test
    public void allSeasons() {
        var world = Generator.fromString("G");
        var view = WorldView.create(world, TestWorldView.createPlayerView(world));

        var batch = mock(SpriteBatch.class);

        for (int i = 0; i < 8; i++) {
            assertDoesNotThrow(() -> {
                view.drawWorld(batch);
                view.drawUi(batch);
            });

            for (int day = 0; day < World.LENGTH_OF_YEAR / 4; day++) {
                world.newDay();
            }
        }
    }

    @Test
    public void allTimesAndSeasons() {
        var world = Generator.empty(1, 1);
        var view = WorldView.create(world, TestWorldView.createPlayerView(world));

        var size = new Rectangle(0f, 0f, 100f, 100f);
        view.resize(size);

        for (int i = 0; i < World.TICKS_PER_DAY * World.LENGTH_OF_YEAR + 1; i++) {
            world.tick();

            var batch = mock(SpriteBatch.class);

            view.drawWorld(batch);
            view.drawUi(batch);

            verify(batch).draw(
                ArgumentMatchers.<TextureRegion>any(),
                eq(size.x),
                eq(size.y),
                eq(size.width),
                eq(size.height));
        }
    }

    @Test
    public void drawsEntityLast() {
        TextureRegion tileTexture = mock(TextureRegion.class);
        TextureRegion entityTexture = mock(TextureRegion.class);
        Spritesheet spritesheet = new Spritesheet() {
            @Override
            public TextureRegion getBase(Around around, int x, int y) {
                return tileTexture;
            }

            @Override
            public TextureRegion getEntity(inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite entitySprite) {
                return entityTexture;
            }

            @Override
            public Iterable<TextureRegion> getBaseTextures() {
                return null;
            }
        };

        var batch = mock(SpriteBatch.class);
        var world = Generator.fromString("GG");
        world.tileAt(0, 0).setEntity(new Entity(inf112.Sun_Mist_Mountain.app.Model.Entities.Sprite.Carrots) {
            @Override
            public boolean isGround() {
                return false;
            }
        });

        var view = new WorldView(world, TestWorldView.createPlayerView(world), spritesheet, spritesheet, spritesheet);

        view.drawWorld(batch);

        var verifier = inOrder(batch);
        verifier.verify(batch, times(2)).draw(eq(tileTexture), anyFloat(), anyFloat());
        verifier.verify(batch).draw(eq(entityTexture), anyFloat(), anyFloat());
    }

    private static PlayerView createPlayerView(World world) {
        var animator = new Animator();
        var player = new Player(world);
        return new PlayerView(animator, player);
    }

}
