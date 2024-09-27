package inf112.Sun_Mist_Mountain.app.View.World;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Math.PositionRandom;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.World.World;
import inf112.Sun_Mist_Mountain.app.View.RectangleDrawer;
import inf112.Sun_Mist_Mountain.app.View.Viewer;
import inf112.Sun_Mist_Mountain.app.View.Player.PlayerView;
import inf112.Sun_Mist_Mountain.app.View.Tiles.CommonSpritesheet;
import inf112.Sun_Mist_Mountain.app.View.Tiles.FallSpritesheet;
import inf112.Sun_Mist_Mountain.app.View.Tiles.Spritesheet;
import inf112.Sun_Mist_Mountain.app.View.Tiles.SummerSpritesheet;
import inf112.Sun_Mist_Mountain.app.View.Tiles.TileView;
import inf112.Sun_Mist_Mountain.app.View.Tiles.WinterSpritesheet;

public class WorldView implements Viewer {

    private static final Color MORNING_TINT = new Color(0.8f, 0.7f, 0.0f, 0.1f);
    private static final Color DAY_TINT     = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    private static final Color EVENING_TINT = new Color(0.5f, 0.3f, 0.6f, 0.1f);
    private static final Color NIGHT_TINT   = new Color(0.0f, 0.0f, 0.7f, 0.2f);

    private final Adjacents adjacents;
    private final ViewableWorld world;
    private final TileView tileView;
    private final RectangleDrawer morningTint, dayTint, eveningTint, nightTint;
    private final Spritesheet summerSheet, fallSheet, winterSheet;
    private final PlayerView playerView;

    /**
     * @return a new world view using the default spritesheets.
     */
    public static WorldView create(ViewableWorld world, PlayerView playerView) {
        var rng = new PositionRandom(new Random());
        var common = new CommonSpritesheet(rng);
        var summerSheet = new SummerSpritesheet(common);
        var fallSheet = new FallSpritesheet(common);
        var winterSheet = new WinterSpritesheet(common, rng);
        return new WorldView(world, playerView, summerSheet, fallSheet, winterSheet);
    }

    public WorldView(ViewableWorld world, PlayerView playerView, Spritesheet summerSheet, Spritesheet fallSheet, Spritesheet winterSheet) {
        this.world = world;
        this.adjacents = new Adjacents(this.world);

        this.tileView = new TileView();

        this.morningTint = new RectangleDrawer(MORNING_TINT);
        this.dayTint     = new RectangleDrawer(DAY_TINT);
        this.eveningTint = new RectangleDrawer(EVENING_TINT);
        this.nightTint   = new RectangleDrawer(NIGHT_TINT);

        this.summerSheet = summerSheet;
        this.fallSheet = fallSheet;
        this.winterSheet = winterSheet;

        this.playerView = playerView;
    }

    /**
     * Set the visible part of the world to the rectangle between the
     * {@code topLeft} and {@code bottomRight}.
     */
    public void setViewport(Position topLeft, Position bottomRight) {
        this.adjacents.setViewport(topLeft, bottomRight);
    }

    @Override
    public void drawWorld(SpriteBatch batch) {
        var entityTilesAbovePlayer = new ArrayList<PlacedTile>();
        var entityTilesBelowPlayer = new ArrayList<PlacedTile>();
        var playerY = World.nearestTilePosition(playerView.getPlayer().getPosition().toPixelPosition()).row();

        var sheet = this.getSpritesheet();

        // Draw the world
        for (var tile : this.adjacents) {
            this.tileView.draw(batch, tile, sheet);

            var placed = tile.center();
            if (placed.hasEntity()) {
                if (placed.getPosition().row() < playerY) {
                    entityTilesBelowPlayer.add(placed);
                } else {
                    entityTilesAbovePlayer.add(placed);
                }
            }
        }

        // Draw entities above player
        for (var tile : entityTilesAbovePlayer) {
            this.tileView.drawEntity(batch, tile, sheet);
        }

        // Draw player
        this.playerView.drawWorld(batch);

        // Draw entities below player
        for (var tile : entityTilesBelowPlayer) {
            this.tileView.drawEntity(batch, tile, sheet);
        }
    }

    @Override
    public void drawUi(SpriteBatch batch) {
        this.playerView.drawUi(batch);
        this.getTint().draw(batch);
    }

    @Override
    public void resize(Rectangle screen) {
        this.playerView.resize(screen);
        this.morningTint.setRectangle(screen);
        this.dayTint.setRectangle(screen);
        this.eveningTint.setRectangle(screen);
        this.nightTint.setRectangle(screen);
    }

    @Override
    public void show() {
        this.playerView.show();
    }

    @Override
    public void hide() {
        this.playerView.hide();
    }

    @Override
    public void dispose() {
        this.playerView.dispose();
        this.morningTint.dispose();
        this.dayTint.dispose();
        this.eveningTint.dispose();
        this.nightTint.dispose();
    }

    /**
     * @return the spritesheet to use to draw the tiles.
     */
    private Spritesheet getSpritesheet() {
        return switch (this.world.getSeason()) {
            case SPRING -> this.fallSheet;
            case SUMMER -> this.summerSheet;
            case AUTUMN -> this.fallSheet;
            case WINTER -> this.winterSheet;
        };
    }

    /**
     * @return the color to tint the screen with.
     */
    private RectangleDrawer getTint() {
        return switch (this.world.getTimeOfDay()) {
            case MORNING -> this.morningTint;
            case DAY -> this.dayTint;
            case EVENING -> this.eveningTint;
            case NIGHT -> this.nightTint;
        };
    }

}
