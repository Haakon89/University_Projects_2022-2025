package inf112.Sun_Mist_Mountain.app.View.World;

import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;

/**
 * Contains a tile ({@code this.center()}) together with the four tiles directly
 * adjacent to it. {@code this.center()} is never {@code null}. The others may
 * be {@code null} if the center tile is at the edge of the world.
 */
public record Adjacent(PlacedTile above, PlacedTile right, PlacedTile below, PlacedTile left, PlacedTile center) {
}
