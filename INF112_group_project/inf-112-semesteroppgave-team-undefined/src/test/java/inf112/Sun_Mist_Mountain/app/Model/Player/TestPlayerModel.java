package inf112.Sun_Mist_Mountain.app.Model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.random.RandomGenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;
import inf112.Sun_Mist_Mountain.app.Model.Entities.Types.Rock;
import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Math.Vector;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.Model.World.Generator;
import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;
import inf112.Sun_Mist_Mountain.app.Model.World.World;

public class TestPlayerModel {

	private RandomGenerator rng;
	private Player player;
	private World world;

	@BeforeEach
	void setUp() {
		this.rng = new Random(RandomizingTest.SEED);
		this.world = Generator.empty(50, 50);
		this.player = new Player(this.world);
	}

	@Test
	public void allowedPlayerMove() {
		var initialPosition = this.player.getPosition();
		var movedTo = this.player.move(new Vector(10.0, 5.0));

		assertNotNull(movedTo);
		assertEquals(new PixelPosition(800, 800), movedTo.getPosition());
		assertNotEquals(initialPosition, this.player.getPosition());
	}

	@Test
	void moveOutOfBoundDisallowed() {
		var initialPosition = this.player.getPosition();
		var movedTo = this.player.move(new Vector(10000000, 10000000)); // Posisjon ute av brettet.

		assertNull(movedTo);
		assertEquals(initialPosition, this.player.getPosition());
	}

	@Test
	void moveToObstacleDisallowed() {
		var obstacled = this.world.tileAt(new PixelPosition(0, 0));
		obstacled.setEntity(new Rock.Factory().create(this.rng));

		var initialPosition = this.player.getPosition();
		var toObstacled = initialPosition.to(obstacled.getPosition().toPosition());

		var movedTo = this.player.move(toObstacled);

		assertNull(movedTo);
		assertEquals(initialPosition, this.player.getPosition());
	}

	@Test
	public void setTargetFarAway() {
		Position originalPos = this.player.getPosition();
		Position farAway = originalPos.add(new Vector(Tile.WIDTH * 1000, Tile.HEIGHT * 1000));

		this.player.setTarget(farAway);

		Position target = this.player.getTarget().getPosition().toPosition();

		double max = 2 * 2 * (Tile.WIDTH * Tile.WIDTH + Tile.HEIGHT * Tile.HEIGHT);
		double distance = this.player.getPosition().distanceSquaredTo(target);

		assertTrue(distance <= max, "target is " + Math.sqrt(distance) + " pixels from player");
	}

	@Test
	public void unsetTarget() {
		this.player.setTarget(null);
		assertNull(this.player.getTarget());
	}

}
