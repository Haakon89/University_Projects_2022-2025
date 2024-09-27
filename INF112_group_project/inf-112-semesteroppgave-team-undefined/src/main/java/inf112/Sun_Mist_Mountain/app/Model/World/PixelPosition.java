package inf112.Sun_Mist_Mountain.app.Model.World;

import inf112.Sun_Mist_Mountain.app.Model.Math.Position;

public record PixelPosition(int row, int col) {

    /**
     * The position with coordinates {@code (0, 0)}
     */
    public static final PixelPosition ZERO = new PixelPosition(0, 0);

    /**
     * @return a double-based {@link Position} with the same coordinates as this
     *         one.
     */
    public Position toPosition() {
        return new Position(this.col, this.row);
    }

}
