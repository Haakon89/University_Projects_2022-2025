package inf112.Sun_Mist_Mountain.app.Model.World;

import java.util.HashMap;
import java.util.random.RandomGenerator;

import inf112.Sun_Mist_Mountain.app.Model.Entities.EntityFactory;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.Carrots;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.House;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.Rock;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.Sprouts;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.Tree;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.TreeStump;
import inf112.Sun_Mist_Mountain.app.Model.Math.Noise;
import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Dirt;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Grass;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Stone;

public class Generator {

    private static final double STONE_THRESHOLD = 0.6;
    private static final double GRASS_THRESHOLD = 0.4;

    private static final EntityPair[] ENTITIES = new EntityPair[] {
        new EntityPair(0.008, new Rock.Factory()),
        new EntityPair(0.004, new Tree.Factory()),
        new EntityPair(0.003, new TreeStump.Factory()),
        new EntityPair(0.002, new Carrots.Factory()),
        new EntityPair(0.002, new Sprouts.Factory()),
    };

    private static HashMap<Character, Tile> tiles = new HashMap<>();

    /**
     * The approximate radius of the circle around the origin guaranteed to be
     * ground.
     */
    private static final double CENTER_CIRCLE_SIZE = 4 * Tile.WIDTH;

    private final RandomGenerator rng;

    private final Noise noise;
    private final int width, height;
    private final Position origin;

    /**
     * @return a world consisting only of grass with the given width and height.
     */
    public static World empty(int width, int height) {
        var tiles = new PlacedTile[width * height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = Generator.gridCoordsToIndex(width, x, y);
                var position = new PixelPosition(y * Tile.HEIGHT, x * Tile.WIDTH);
                var placed = new PlacedTile(position, new Grass());
                tiles[index] = placed;
            }
        }

        return new World(width, height, tiles);
    }

    /**
     * Create a world from the given map.
     * @param map is a string where each line is a row of tiles
     * @throws IllegalArgumentException if the lines have different lengths, or
     *                                  if the map contains unknown tile
     *                                  characters.
     */
    public static World fromString(String map) throws IllegalArgumentException {
        Tile[][] tiles = map.lines()
            .map(line -> line.codePoints().mapToObj(c -> {
                var tile = Generator.tiles.get((char) c);
                if (tile == null) {
                    throw new IllegalArgumentException("unknown tile " + (char) c);
                }
                return tile;
            }).toArray(Tile[]::new))
            .toArray(Tile[][]::new);

        var height = tiles.length;
        var width = height == 0 ? 0 : tiles[0].length;
        var placed = new PlacedTile[width * height];

        for (int i = 0; i < height; i++) {
            if (tiles[i].length != width)
                throw new IllegalArgumentException();

            int row = height - i - 1;
            for (int col = 0; col < width; col++) {
                // Since y=0 is at the bottom of the screen, use height - i - 1
                // to compute `y` so the first lines are at the top of the map
                var tile = tiles[i][col];
                int x = col * Tile.WIDTH;
                int y = row * Tile.HEIGHT;
                int index = Generator.gridCoordsToIndex(width, col, row);
                placed[index] = new PlacedTile(new PixelPosition(y, x), tile);
            }
        }

        return new World(width, height, placed);
    }

    /**
     * Remove all currently registered tiles.
     */
    public static void unregisterAll() {
        Generator.tiles.clear();
    }

    /**
     * Register a tile and a character that can be used with
     * {@code Generator.fromString}.
     *
     * @throws IllegalArgumentException if {@code name} has already been
     *                                  registered.
     */
    public static void registerTile(char name, Tile tile) throws IllegalArgumentException {
        if (Generator.tiles.containsKey(name)) {
            throw new IllegalArgumentException(name + " is already used for " + Generator.tiles.get(name));
        }

        Generator.tiles.put(name, tile);
    }

    public Generator(RandomGenerator rng, int width, int height) {
        this.rng = rng;
        this.noise = new Noise(this.rng);
        this.width = width;
        this.height = height;
        this.origin = new Position(Tile.WIDTH * this.width / 2, Tile.HEIGHT * this.height / 2);
    }

    /**
     * @return a world with randomly generated tiles.
     */
    public World generate() {
        var tiles = new PlacedTile[this.width * this.height];
        var circleSizeSquare = Generator.CENTER_CIRCLE_SIZE * Generator.CENTER_CIRCLE_SIZE;

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                var position = new Position(x * Tile.HEIGHT, y * Tile.WIDTH);

                // This value is in the range [0, 1] when (x, y) has a distance
                // less than CENTER_CIRCLE_SIZE away from the origin
                var distanceSquare = origin.distanceSquaredTo(position);
                distanceSquare = distanceSquare / circleSizeSquare;

                // This makes it 0 at the origin and 0.5 at CENTER_CIRCLE_SIZE
                // and it will approach 1 the further away it gets
                var centerFactor = distanceSquare / (distanceSquare + 1.0);

                var value = this.noise.at(x, y) * centerFactor;

                Tile tile;
                if (STONE_THRESHOLD <= value) {
                    tile = new Stone();
                } else if (GRASS_THRESHOLD <= value) {
                    tile = new Grass();
                } else {
                    tile = new Dirt();
                }

                int index = x + this.width * y;
                var placed = new PlacedTile(position.toPixelPosition(), tile);

                if (placed.isGround() && 0.5 < centerFactor) {
                    var factory = this.entityAt(x, y);
                    if (factory != null) {
                        placed.setEntity(factory.create(this.rng));
                    }
                }

                tiles[index] = placed;
            }
        }

        return new World(this.width, this.height, tiles);
    }

    /**
     * @return a random entity for the given position.
     */
    private EntityFactory entityAt(int x, int y) {
        // Scale up by a lot and take the fraction so it is more independent of
        // the tile type
        double value = 1000.0 * this.noise.at(x, y);
        double frac = value - Math.floor(value);

        double cumulative = 0.0;

        for (var pair : Generator.ENTITIES) {
            cumulative += pair.probability();
            if (frac < cumulative) {
                return pair.factory;
            }
        }

        return null;
    }

    /**
     * @return the index of the tile in the tile array at the grid coordinates
     */
    private static int gridCoordsToIndex(int width, int x, int y) {
        return y * width + x;
    }

    /**
     * Represents an entity factory with some probability of being generated.
     */
    private static record EntityPair(double probability, EntityFactory factory) {}

}
