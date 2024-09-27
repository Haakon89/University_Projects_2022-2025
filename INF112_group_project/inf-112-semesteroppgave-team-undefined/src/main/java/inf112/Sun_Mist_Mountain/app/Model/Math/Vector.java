package inf112.Sun_Mist_Mountain.app.Model.Math;

public record Vector(double dx, double dy) {

    /**
     * @return the angle of this vector in radians. A vector pointing right has
     *         an angle around {@code 0} while a vector pointing up has an angle
     *         around {@code PI/2}. The zero vector has an angle of {@code 0}.
     */
    public double angle() {
        return Math.atan2(dy, dx);
    }

    /**
     * @return the eight-way direction of this vector. The zero vector has a
     *         direction of {@code East}.
     */
    public Direction direction() {
        var angle = this.angle();
        if (angle < -7 * Math.PI / 8) {
            return Direction.West;
        } else if (angle < -5 * Math.PI / 8) {
            return Direction.SouthWest;
        } else if (angle < -3 * Math.PI / 8) {
            return Direction.South;
        } else if (angle < -1 * Math.PI / 8) {
            return Direction.SouthEast;
        } else if (angle < 1 * Math.PI / 8) {
            return Direction.East;
        } else if (angle < 3 * Math.PI / 8) {
            return Direction.NorthEast;
        } else if (angle < 5 * Math.PI / 8) {
            return Direction.North;
        } else if (angle < 7 * Math.PI / 8) {
            return Direction.NorthWest;
        } else {
            return Direction.West;
        }
    }

    /**
     * @return the square of the length of this vector
     */
    public double lengthSquared() {
        return this.dx * this.dx + this.dy * this.dy;
    }

    /**
     * @return the length of this vector.
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * @return a new vector with the same direction as this, but length of 1. If
     *         this has a length of 0, the returned vector will as well.
     */
    public Vector normalize() {
        double lengthSquared = this.lengthSquared();

        if (lengthSquared == 0) {
            return this;
        }

        double length = Math.sqrt(lengthSquared);
        double normalX = this.dx / length;
        double normalY = this.dy / length;
        return new Vector(normalX, normalY);
    }

    /**
     * @return this vector scaled by the given factor.
     */
    public Vector scale(double factor) {
        return new Vector(this.dx * factor, this.dy * factor);
    }

    /**
     * @return a vector with thethe given length and the same direction as
     *         {@code this}, unless it is a zero vector.
     */
    public Vector withLength(double length) {
        return this.normalize().scale(length);
    }

    /**
     * @return a vector with the smallest of the length of {@code this} and
     *         {@code maxLength} and the direction of {@code this}.
     */
    public Vector withLengthAtMost(double maxLength) {
        if (this.lengthSquared() > maxLength * maxLength) {
            return this.withLength(maxLength);
        }

        return this;
    }

    /**
     * @return the sum of {@code this} and the {@code other} vector
     */
    public Vector add(Vector other) {
        return new Vector(this.dx + other.dx, this.dy + other.dy);
    }

}
