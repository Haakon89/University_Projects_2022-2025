package inf112.Sun_Mist_Mountain.app.View.Player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;
import inf112.Sun_Mist_Mountain.app.View.Animator;
import inf112.Sun_Mist_Mountain.app.View.RectangleDrawer;
import inf112.Sun_Mist_Mountain.app.View.Viewer;

public class PlayerView implements Viewer {

    private static final Color TARGETED_TILE_COLOR = new Color(0.5f, 0.2f, 0.7f, 0.3f);

    private final Animator animator;
    private final ViewablePlayer player;
    private final RectangleDrawer targetedTileRectangle;

    public PlayerView(Animator animator, ViewablePlayer player) {
        this.animator = animator;
        this.player = player;
        this.targetedTileRectangle = new RectangleDrawer(TARGETED_TILE_COLOR);
    }

    /**
     * @return the player this view will display.
     */
    public ViewablePlayer getPlayer() {
        return this.player;
    }

    @Override
    public void drawWorld(SpriteBatch batch) {
        // Draw target below player by drawing it first
        var target = this.player.getTarget();

        if (target != null) {
            PixelPosition tile = target.getPosition();
            this.targetedTileRectangle.setRectangle(new Rectangle(tile.col(), tile.row(), Tile.WIDTH, Tile.HEIGHT));
            this.targetedTileRectangle.draw(batch);
        }

        var texture = this.animator.getCurrentFrame();
        var position = this.player.getPosition();
        batch.draw(texture, (float) position.x(), (float) position.y());
    }

    @Override
    public void drawUi(SpriteBatch batch) {
        // empty
    }

    @Override
    public void resize(Rectangle screen) {
        // empty
    }

    @Override
    public void dispose() {
        // empty
    }

}
