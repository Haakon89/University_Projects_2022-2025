package inf112.Sun_Mist_Mountain.app.View.Tiles;

import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;
import inf112.Sun_Mist_Mountain.app.View.World.Adjacent;

/**
 * Represents the base of a sprite and whether any of the adjacent tiles have a
 * different base.
 */
public record Around(Sprite.Base base, boolean aboveDiffers, boolean rightDiffers, boolean belowDiffers, boolean leftDiffers) {

    /**
     * @return an {@link Around} for {@code adjacent.center()}.  If an adjacent
     *         tile is {@code null}, it is considered the same as the center.
     */
    public static Around of(Adjacent adjacent) {
        var base = adjacent.center().getTile().getSprite().base();
        var aboveDiffers = differs(base, adjacent.above());
        var rightDiffers = differs(base, adjacent.right());
        var belowDiffers = differs(base, adjacent.below());
        var leftDiffers = differs(base, adjacent.left());
        return new Around(base, aboveDiffers, rightDiffers, belowDiffers, leftDiffers);
    }

    /**
     * @param neighbor may be {@code null}, in which case {@code false} is
     *                 returned.
     * @return whether the given adjacent tile has a sprite base that differs
     *         from {@code from}
     */
    private static boolean differs(Sprite.Base from, PlacedTile neighbor) {
        return neighbor != null && neighbor.getTile().getSprite().base() != from;
    }

}
