package inf112.Sun_Mist_Mountain.app.View.World;

import java.util.function.Consumer;

import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.World.Seasons;
import inf112.Sun_Mist_Mountain.app.Model.World.TimeOfDay;

public interface ViewableWorld {

    /**
     * @return every visible tile.
     */
    public Iterable<PlacedTile> getTiles();

    /**
     * @return the current season.
     */
    public Seasons getSeason();

    /**
     * @param listener will be invoked whenever the season changes.
     */
    public void addSeasonChangeListener(Consumer<Seasons> listener);

    /**
     * @return the current time of day.
     */
    public TimeOfDay getTimeOfDay();

    /**
     * checks if it is raining
     * @return
     */
    public boolean isRaining();

    /**
     * @return the tile with the given {@code (col, row)} grid position.
     */
    public PlacedTile tileAt(int col, int row);

    /**
     * @return the number of columns of tiles in this world.
     */
    public int getWidth();

    /**
     * @return the number of rows of tiles in this world.
     */
    public int getHeight();

}
