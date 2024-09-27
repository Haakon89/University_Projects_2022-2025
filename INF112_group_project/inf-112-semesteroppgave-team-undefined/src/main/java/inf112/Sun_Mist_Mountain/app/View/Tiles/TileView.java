package inf112.Sun_Mist_Mountain.app.View.Tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import inf112.Sun_Mist_Mountain.app.Model.Entities.Entity;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.View.RectangleDrawer;
import inf112.Sun_Mist_Mountain.app.View.World.Adjacent;

public class TileView {

    private static final Color WATER_OVERLAY = new Color(0.0f, 0.3f, 1.0f, 0.3f);
    private static final Color MISSING_TEXTURE = new Color(1.0f, 0.0f, 1.0f, 1.0f);
    private static final Color MISSING_ENTITY = new Color(1.0f, 0.0f, 1.0f, 0.5f);

    private final RectangleDrawer waterOverlay, missingTexture, missingEntity;

    public TileView() {
        this.waterOverlay = new RectangleDrawer(WATER_OVERLAY);
        this.missingTexture = new RectangleDrawer(MISSING_TEXTURE);
        this.missingEntity = new RectangleDrawer(MISSING_ENTITY);
    }

    /**
     * Draw the center tile of {@code adjacent} to the given sprite batch.
     */
    public void draw(SpriteBatch batch, Adjacent adjacent, Spritesheet spritesheet) {
        var center = adjacent.center();
        var sprite = center.getTile().getSprite();

        var position = center.getPosition();
        var base = spritesheet.getBase(Around.of(adjacent), position.col(), position.row());

        if (base != null) {
            batch.draw(base, position.col(), position.row());
        } else {
            this.missingTexture.setRectangle(new Rectangle(position.col(), position.row(), Tile.WIDTH, Tile.HEIGHT));
            this.missingTexture.draw(batch);
        }

        if (sprite.state() == Sprite.State.Watered) {
            var width = base.getRegionWidth();
            var height = base.getRegionHeight();
            this.waterOverlay.setRectangle(new Rectangle(position.col(), position.row(), width, height));
            this.waterOverlay.draw(batch);
        }
    }

    /**
     * Draw the entity of the placed tile.
     */
    public void drawEntity(SpriteBatch batch, PlacedTile tile, Spritesheet spritesheet) {
        var position = tile.getPosition();
        Entity entity = tile.getEntity();
        if (entity != null) {
            var texture = spritesheet.getEntity(entity.getSprite());
            if (texture != null) {
                batch.draw(texture, position.col(), position.row());
            } else {
                this.missingEntity.setRectangle(new Rectangle(position.col(), position.row(), Tile.WIDTH, Tile.HEIGHT));
                this.missingEntity.draw(batch);
            }
        }
    }

}
