package inf112.Sun_Mist_Mountain.app.Model.Math;

import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;

public record Position(double x, double y) {

    /**
     * The position with coordinates {@code (0, 0)}.
     */
    public static final Position ZERO = new Position(0, 0);

    /**
     * @return the square of the distance in pixels to the other position.
     */
    public double distanceSquaredTo(Position b) {
        double dx = b.x - this.x;
        double dy = b.y - this.y;
        return dx * dx + dy * dy;
    }

    /**
     * @return the vector pointing from this position to the other position.
     */
    public Vector to(Position other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        return new Vector(dx, dy);
    }

    /**
     * @return return a mix of {@code this} and {@code other} determined by the
     *         {@code factor} parameter.
     */
    public Position blend(Position other, double factor) {
        return this.add(this.to(other).scale(factor));
    }

    /**
     * @return the position obtained by following the given
     */
    public Position add(Vector vector) {
        return new Position(this.x + vector.dx(), this.y + vector.dy());
    }

    /**
     * @return the closes pixel-perfect position to this exact position.
     */
    public PixelPosition toPixelPosition() {
        int x = (int) Math.round(this.x);
        int y = (int) Math.round(this.y);
        return new PixelPosition(y, x);
    }

}
