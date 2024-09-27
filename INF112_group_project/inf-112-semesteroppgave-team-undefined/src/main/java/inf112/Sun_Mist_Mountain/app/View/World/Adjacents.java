package inf112.Sun_Mist_Mountain.app.View.World;

import java.util.Iterator;

import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;

/**
 * {@code Adjacents} can be used to iterate over every tile and its adjacent
 * neighbours in a given {@link ViewableWorld} within the bounds of a viewport.
 */
public class Adjacents implements Iterable<Adjacent> {

    private final ViewableWorld world;

    private int bottom;
    private int left;
    private int right;
    private int top;

    public Adjacents(ViewableWorld world) {
        this.world = world;

        bottom = 0;
        left = 0;
        right = this.world.getWidth();
        top = this.world.getHeight();
    }

    /**
     * Set the viewport to a rectangle between two positions in the world.
     */
    public void setViewport(Position topLeft, Position bottomRight) {
        var left = (int) Math.floor(topLeft.x() / Tile.WIDTH);
        var top = (int) Math.ceil(topLeft.y() / Tile.HEIGHT);
        var right = (int) Math.ceil(bottomRight.x() / Tile.WIDTH);
        var bottom = (int) Math.floor(bottomRight.y() / Tile.HEIGHT);

        this.bottom = Math.max(bottom, 0);
        this.left = Math.max(left, 0);
        this.right = Math.min(right, this.world.getWidth());
        this.top = Math.min(top, this.world.getHeight());
    }

    @Override
    public Iterator<Adjacent> iterator() {
        return new AdjacentsIterator();
    }

    private class AdjacentsIterator implements Iterator<Adjacent> {
        private final int viewportRight = right;
        private final int viewportLeft = left;
        private final int viewportBottom = bottom;
        private final int worldWidth = world.getWidth();
        private final int worldHeight = world.getHeight();

        // Current grid position
        private int col = left;
        private int row = top - 1;

        @Override
        public boolean hasNext() {
            return this.viewportBottom <= this.row;
        }

        @Override
        public Adjacent next() {
            var above = this.tileAt(this.col, this.row + 1);
            var right = this.tileAt(this.col + 1, this.row);
            var below = this.tileAt(this.col, this.row - 1);
            var left = this.tileAt(this.col - 1, this.row);
            var center = this.tileAt(this.col, this.row);

            this.col += 1;
            if (this.col == this.viewportRight) {
                this.col = viewportLeft;
                this.row -= 1;
            }

            return new Adjacent(above, right, below, left, center);
        }

        /**
         * @return the tile at {@code (col, row)}, or {@code null} if this is out
         *         of bounds.
         */
        private PlacedTile tileAt(int col, int row) {
            if (col < 0 || this.worldWidth <= col || row < 0 || this.worldHeight <= row)
                return null;

            return Adjacents.this.world.tileAt(col, row);
        }
    }

}
