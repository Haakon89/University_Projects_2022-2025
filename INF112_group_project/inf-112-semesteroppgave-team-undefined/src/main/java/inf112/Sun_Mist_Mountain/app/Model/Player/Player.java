package inf112.Sun_Mist_Mountain.app.Model.Player;

import inf112.Sun_Mist_Mountain.app.Controller.ControllablePlayerModel;
import inf112.Sun_Mist_Mountain.app.Model.Positioned;
import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Math.Vector;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.Model.World.World;
import inf112.Sun_Mist_Mountain.app.View.Player.ViewablePlayer;

public class Player implements ControllablePlayerModel, Positioned, ViewablePlayer, Wallet {

    /**
     * The walking speed of the player in pixels per second.
     */
    public static final double WALKING_SPEED = 140.0;

    public static final int COLLISION_WIDTH = 26;

    /**
     * The width of the player texture in pixels.
     */
    private static final int TEXTURE_WIDTH = 32;

    /**
     * The radius of the player's tile selection.
     */
    private static final double SELECT_RADIUS = Math.min(Tile.WIDTH, Tile.HEIGHT);

    /**
     * The vector difference between the player center and its position.
     */
    private static final Vector POSITION_TO_CENTER = new Vector(TEXTURE_WIDTH / 2, 0);

    private Position target;
	private Position position;
	private World world; 

    public  int money;
    private int stamina;

	public Player(World world) {
        this.target = null;
		this.world = world;
        this.position = world.getOrigin().toPosition();
        this.money = 500;
        this.stamina = 100;
	}

    /**
     * @return the tile that the player is currently standing on.
     */
    public PlacedTile getTileBelow() {
        return this.world.tileAt(this.getCenter());
    }

    @Override
    public PlacedTile move(Vector by) {
        var newPosition = this.position.add(by);
        var tile = this.canMoveTo(newPosition);

    	if (tile != null) {
            this.setPosition(newPosition);
            return tile;
    	}

        return null;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public PlacedTile getTarget() {
        if (this.target != null) {
            return this.world.tileAt(this.target);
        }

        return null;
    }

    @Override
    public void setTarget(Position pos) {
        if (pos == null) {
            this.target = null;
            return;
        }

        Position center = this.getCenter();
        Vector difference = center.to(pos);

        double length = difference.length();

        var direction = switch (difference.direction()) {
            case West -> new Vector(-Tile.WIDTH, 0);
            case SouthWest -> new Vector(-Tile.WIDTH, -Tile.HEIGHT);
            case South -> new Vector(0, -Tile.HEIGHT);
            case SouthEast -> new Vector(Tile.WIDTH, -Tile.HEIGHT);
            case East -> new Vector(Tile.WIDTH, 0);
            case NorthEast -> new Vector(Tile.WIDTH, Tile.HEIGHT);
            case North -> new Vector(0, Tile.HEIGHT);
            case NorthWest -> new Vector(-Tile.WIDTH, Tile.HEIGHT);
        };

        pos = center;
        if (SELECT_RADIUS <= length) {
            pos = pos.add(direction);
        }

        if (this.isInBounds(pos)) {
            this.target = pos;
        }
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public void changeMoney(int change) {
        if ((this.money + change) >= 0) {
            this.money = this.money + change;
        }
    }

    /**
     * set the amount of money the player has, as long as the amount is non negative.
     * @param newTotal
     */
    public void setMoney(int newTotal) {
        if (newTotal >= 0) {
            this.money = newTotal;
        }
    }

    /**
     * get the player's stamina
     * @return
     */
    public int getStamina() {
        return this.stamina;
    }

    @Override
    public void changeStamina(int change) {
        if ((this.stamina + change) > 100) {
            this.stamina = 100;
        } else if ((this.stamina + change) < 0) {
            this.stamina = 0;
        } else {
            this.stamina = this.stamina + change;
        }
    }

    @Override
    public void setStamina(int newStamina) {
        if (newStamina > 100) {
            this.stamina = 100;
        } else if (newStamina < 0) {
            this.stamina = 0;
        } else {
            this.stamina = newStamina;
        }
    }

    /**
     * Set the position of the player.
     */
    private void setPosition(Position pos) {
        this.position = pos;
        this.target = null;
    }

    /**
     * @return the center of the player character
     */
    private Position getCenter() {
        return this.position.add(POSITION_TO_CENTER);
    }

    /**
     * @return the placed tile that the player will be on if it can move to the
     *         given position, or {@code null} if the player can not move there.
     */
    private PlacedTile canMoveTo(Position pos) {
    	try {
            int offset = (TEXTURE_WIDTH - COLLISION_WIDTH) / 2;
            PlacedTile left = this.world.tileAt(new Position(pos.x() + offset, pos.y()));
            PlacedTile right = this.world.tileAt(new Position(pos.x() + offset + COLLISION_WIDTH, pos.y()));

            if (left.isGround() && right.isGround()) {
                return left;
            }

            return null;
    	}
    	catch (IndexOutOfBoundsException e) {
    		return null;
    	}
    }

    /**
     * @return {@code true} if the given position is a valid position for the
     *         world.
     */
    private boolean isInBounds(Position pos) {
        try {
            this.world.tileAt(pos);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public int getCurrentStamina() {
        return this.stamina;
    }

}
