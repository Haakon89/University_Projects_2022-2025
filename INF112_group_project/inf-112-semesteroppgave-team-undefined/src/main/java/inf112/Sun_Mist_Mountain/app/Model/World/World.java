package inf112.Sun_Mist_Mountain.app.Model.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;
import java.util.Random;

import inf112.Sun_Mist_Mountain.app.Controller.ControllableWorldModel;
import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.View.World.ViewableWorld;

public class World implements ControllableWorldModel, ViewableWorld {

    public static final int LENGTH_OF_YEAR = 12;

    public static final int TICKS_PER_DAY_PART = 100;
    public static final int TICKS_PER_DAY = 4 * TICKS_PER_DAY_PART;

    /**
     * Number of tiles to update every tick.
     */
    private static final int UPDATED_TILES_PER_TICK = 1000;

    private final int width, height;
    private final PlacedTile[] tiles;
    private final PixelPosition origin;

    private final RandomGenerator rng = new Random();
    private final ArrayList<Consumer<Seasons>> seasonChangeListeners;

    private int ticksToday;
    private int daysPassed;
    private Seasons currentSeason;
    private boolean raining;

    /**
     * @return the pixel position of the tile nearest to the given position.
     */
    public static PixelPosition nearestTilePosition(PixelPosition position) {
        int x = Math.floorDiv(position.col(), Tile.WIDTH) * Tile.WIDTH;
        int y = Math.floorDiv(position.row(), Tile.HEIGHT) * Tile.HEIGHT;
        return new PixelPosition(y, x);
    }

    /**
     * Create a new world from the given tile array with the origin in the
     * center.
     */
    World(int width, int height, PlacedTile[] tiles) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        this.origin = new PixelPosition(this.height * Tile.HEIGHT / 2, this.width * Tile.WIDTH / 2);

        this.seasonChangeListeners = new ArrayList<>();

        this.ticksToday = 0;
        this.daysPassed = 0;
        this.currentSeason = Seasons.SPRING;
        this.raining = true;
    }

    /**
     * @return the mutable placed tile at the given pixel position.
     * @throws IndexOutOfBoundsException if the position is out of bounds.
     */
    public PlacedTile tileAt(Position position) throws IndexOutOfBoundsException {
        return this.tileAt(position.toPixelPosition());
    }

    /**
     * @return the number of days passed since the start of the year.
     */
    public int getDaysPassed() {
        return this.daysPassed;
    }

    /**
     * @return the number of ticks passed since the start of the day.
     */
    public int getTicksPassed() {
        return this.ticksToday;
    }

    /**
     * Change to a new day.
     */
    public void newDay() {
        this.ticksToday = 0;
        this.daysPassed += 1;

        int summerChange = LENGTH_OF_YEAR/4;
        int autumnChange = (LENGTH_OF_YEAR/4)*2;
        int winterChange = (LENGTH_OF_YEAR/4)*3;

        if (this.daysPassed == summerChange) {
            this.setSeason(Seasons.SUMMER);
        } else if (this.daysPassed == autumnChange) {
            this.setSeason(Seasons.AUTUMN);
        } else if (this.daysPassed == winterChange) {
            this.setSeason(Seasons.WINTER);
        } else if (this.daysPassed > LENGTH_OF_YEAR) {
            this.daysPassed = 0;
            this.setSeason(Seasons.SPRING);
        }

        rainCheck();
    }

    /**
     * @return the mutable placed tile at the given grid coordinates.
     * @throws IndexOutOfBoundsException if the coordinates are out of bounds.
     */
    @Override
    public PlacedTile tileAt(int col, int row) throws IndexOutOfBoundsException {
        if (col < 0 || col >= this.width || row < 0 || row >= this.height)
            throw new IndexOutOfBoundsException();
        int i = this.gridCoordsToIndex(col, row);
        return this.tiles[i];
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public PlacedTile tileAt(PixelPosition position) throws IndexOutOfBoundsException {
        PixelPosition nearest = World.nearestTilePosition(position);
        int col = nearest.col() / Tile.WIDTH;
        int row = nearest.row() / Tile.HEIGHT;
        return this.tileAt(col, row);
    }

    @Override
    public PixelPosition getOrigin() {
        return this.origin;
    }

    @Override
    public Iterable<PlacedTile> getTiles() {
        return List.of(this.tiles);
    }

    @Override
    public TimeOfDay getTimeOfDay() {
        return switch (this.ticksToday / World.TICKS_PER_DAY_PART) {
            case 0 -> TimeOfDay.MORNING;
            case 1 -> TimeOfDay.DAY;
            case 2 -> TimeOfDay.EVENING;
            case 3 -> TimeOfDay.NIGHT;
            default -> throw new IllegalStateException(ticksToday + " should always be less than " + TICKS_PER_DAY);
        };
    }

    @Override
    public Seasons getSeason() {
        return this.currentSeason;
    }

    @Override
    public void addSeasonChangeListener(Consumer<Seasons> listener) {
        this.seasonChangeListeners.add(listener);
    }

    @Override
    public void tick() {
        this.tickTiles();
        this.moveTime();
    }

    @Override
    public boolean isRaining() {
        return this.raining;
    }

    /**
     * @return the index of the tile in the tile array at the grid coordinates
     */
    private int gridCoordsToIndex(int col, int row) {
        return row * this.width + col;
    }

    /**
     * Set the season and notify all listeners.
     */
    private void setSeason(Seasons newSeason) {
        this.currentSeason = newSeason;
        for (var listener : this.seasonChangeListeners) {
            listener.accept(this.currentSeason);
        }
    }

    /**
     * Set whether or not the day is a rain day based on the current season.
     */
    private void rainCheck() {
        Seasons season = this.currentSeason;

        if (season.equals(Seasons.SUMMER)) {
            int randomNumber = this.rng.nextInt(10);
            if (randomNumber > 8) {
                this.raining = true;
            } else {
                this.raining = false;
            }
        } else if (season.equals(Seasons.SPRING)) {
            int randomNumber = this.rng.nextInt(8);
            if (randomNumber > 6) {
                this.raining = true;
            } else {
                this.raining = false;
            }
        } else if (season.equals(Seasons.AUTUMN)) {
            int randomNumber = this.rng.nextInt(6);
            if (randomNumber > 4) {
                this.raining = true;
            } else {
                this.raining = false;
            }
        } else {
            this.raining = false;
        }
    }

    /**
     * Calls {@code tick()} on a randomly chosen subset of tiles
     */
    private void tickTiles() {
        // Choose `UPDATED_TILES_PER_TICK` distinct tile
        var tiles = new PlacedTile[World.UPDATED_TILES_PER_TICK];
        for (int i = 0; i < tiles.length; i++) {
            int tileIndex = this.rng.nextInt(this.tiles.length);
            tiles[i] = this.tiles[tileIndex];
        }

        for (PlacedTile tile : tiles) {
            var actions = new Actions(this.rng, tile);
            tile.tick(actions);

            if (this.isRaining()) {
                tile.water(actions);
            } else {
                tile.dry(actions);
            }
        }
    }

    /**
     * Move the time forward by one tick.
     */
    private void moveTime() {
        this.ticksToday += 1;
        if (World.TICKS_PER_DAY <= this.ticksToday) {
            this.ticksToday = 0;
            this.newDay();
        }
    }

}
