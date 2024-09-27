package inf112.Sun_Mist_Mountain.app.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Input;

import inf112.Sun_Mist_Mountain.app.GameTest;
import inf112.Sun_Mist_Mountain.app.Model.Coordinates;
import inf112.Sun_Mist_Mountain.app.Model.Inventory.InventoryActor;
import inf112.Sun_Mist_Mountain.app.Model.Item.Tools.Hoe;
import inf112.Sun_Mist_Mountain.app.Model.Inventory.Inventory;
import inf112.Sun_Mist_Mountain.app.Model.Math.Vector;
import inf112.Sun_Mist_Mountain.app.Model.Player.Player;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Dirt;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Types.Grass;
import inf112.Sun_Mist_Mountain.app.Model.World.Generator;
import inf112.Sun_Mist_Mountain.app.Model.World.TimeOfDay;
import inf112.Sun_Mist_Mountain.app.Model.World.World;
import inf112.Sun_Mist_Mountain.app.View.Animator;
import inf112.Sun_Mist_Mountain.app.View.Sound.PlayerSounds;


public class TestController extends GameTest {

    private Conntroller controller;
    private SoundController soundController;
    private TimerController timerController;
    private ToolbarController toolbarController;
    private Coordinates coordinates;
    private MockTimer timer;
    private Player testPlayer;
    private World testWorld;
    private Animator animator;
    private ControllableToolbar inventory;
    private ControllableInventoryActor actor;

    @BeforeEach
    public void setUp() {
        this.animator = mock(Animator.class);
        this.actor = mock(InventoryActor.class);

        this.testWorld = Generator.empty(500, 500);
        this.coordinates = mock(Coordinates.class);
        this.testPlayer = new Player(this.testWorld);
        this.inventory = new Inventory();
        this.timer = new MockTimer();
        this.soundController = new SoundController(this.timer, mock(PlayerSounds.class));
        this.controller = new Conntroller(this.coordinates, this.testPlayer, this.animator, this.soundController);
        this.timerController = new TimerController(this.timer, this.testWorld, this.testPlayer);
        this.toolbarController = new ToolbarController(this.inventory, this.testPlayer, this.actor, this.soundController);

        this.inventory.placeItemInInventory(new Hoe(), 1);
    }

    @Test
    public void testPlayerMovesUp() {
        var initialPosition = this.testPlayer.getPosition();

        this.controller.keyDown(Input.Keys.W);
        this.controller.update(1.0f);

        var expectedPosition = initialPosition.add(new Vector(0, Player.WALKING_SPEED));
        assertEquals(expectedPosition, this.testPlayer.getPosition());
    }

    @Test
    public void testPlayerMovesDown() {
        var initialPosition = this.testPlayer.getPosition();

        this.controller.keyDown(Input.Keys.S);
        this.controller.update(1.0f);

        var expectedPosition = initialPosition.add(new Vector(0, -Player.WALKING_SPEED));
        assertEquals(expectedPosition, this.testPlayer.getPosition());
    }

    @Test
    public void testPlayerMovesLeft() {
        var initialPosition = this.testPlayer.getPosition();

        this.controller.keyDown(Input.Keys.A);
        this.controller.update(1.0f);

        var expectedPosition = initialPosition.add(new Vector(-Player.WALKING_SPEED, 0));
        assertEquals(expectedPosition, this.testPlayer.getPosition());
    }

    @Test
    public void testPlayerMovesRight() {
        var initialPosition = this.testPlayer.getPosition();

        this.controller.keyDown(Input.Keys.D);
        this.controller.update(1.0f);

        var expectedPosition = initialPosition.add(new Vector(Player.WALKING_SPEED, 0));
        assertEquals(expectedPosition, this.testPlayer.getPosition());
    }

    @Test
    public void testChangesTileOnMouseClick() {
        Tile actualTile = this.testWorld.tileAt(this.testPlayer.getPosition()).getTile();
        assertInstanceOf(Grass.class, actualTile);
        this.testPlayer.setTarget(this.testPlayer.getPosition());
        this.toolbarController.touchDown(10, 10, 0, Input.Buttons.LEFT);
        actualTile = this.testWorld.tileAt(this.testPlayer.getPosition()).getTile();
        assertInstanceOf(Dirt.class, actualTile);
    }

    @Test
    public void testScrollDownChangesInventorySlotUpOne() {
        this.inventory.setActiveIndex(0);
        this.toolbarController.scrolled(0, 1);
        Integer currentIndex = this.inventory.getActiveIndex();
        Integer expectedIndex = 1;
        assertEquals(currentIndex, expectedIndex);
    }

    @Test
    public void testScrollUpChangesInventorySlotDownOne() {
        this.inventory.setActiveIndex(1);
        this.toolbarController.scrolled(0, -1);
        Integer currentIndex = this.inventory.getActiveIndex();
        Integer expectedIndex = 0;
        assertEquals(currentIndex, expectedIndex);
    }

    @Test
    public void testMovingToolbarIndexUsingNumPad() {
        this.toolbarController.keyDown(Input.Keys.NUM_1);
        int currentIndex = this.inventory.getActiveIndex();
        int expectedIndex = 0;
        assertEquals(expectedIndex, currentIndex);
        this.toolbarController.keyDown(Input.Keys.NUM_2);
        currentIndex = this.inventory.getActiveIndex();
        expectedIndex = 1;
        assertEquals(expectedIndex, currentIndex);
        this.toolbarController.keyDown(Input.Keys.NUM_3);
        currentIndex = this.inventory.getActiveIndex();
        expectedIndex = 2;
        assertEquals(expectedIndex, currentIndex);
        this.toolbarController.keyDown(Input.Keys.NUM_4);
        currentIndex = this.inventory.getActiveIndex();
        expectedIndex = 3;
        assertEquals(expectedIndex, currentIndex);
        this.toolbarController.keyDown(Input.Keys.NUM_5);
        currentIndex = this.inventory.getActiveIndex();
        expectedIndex = 4;
        assertEquals(expectedIndex, currentIndex);
        this.toolbarController.keyDown(Input.Keys.NUM_6);
        currentIndex = this.inventory.getActiveIndex();
        expectedIndex = 5;
        assertEquals(expectedIndex, currentIndex);
        this.toolbarController.keyDown(Input.Keys.NUM_7);
        currentIndex = this.inventory.getActiveIndex();
        expectedIndex = 6;
        assertEquals(expectedIndex, currentIndex);
        this.toolbarController.keyDown(Input.Keys.NUM_8);
        currentIndex = this.inventory.getActiveIndex();
        expectedIndex = 7;
        assertEquals(expectedIndex, currentIndex);
        this.toolbarController.keyDown(Input.Keys.NUM_9);
        currentIndex = this.inventory.getActiveIndex();
        expectedIndex = 8;
        assertEquals(expectedIndex, currentIndex);
        this.inventory.setActiveIndex(1);
        this.toolbarController.keyDown(Input.Keys.NUM_0);
        currentIndex = this.inventory.getActiveIndex();
        expectedIndex = 9;
        assertEquals(expectedIndex, currentIndex);
    }

    @Test
    public void timeMovesWhenStarted() {
        this.timerController.start();

        var actual = new HashSet<TimeOfDay>();
        for (int i = 0; i < World.TICKS_PER_DAY; i++) {
            this.timer.run();
            actual.add(this.testWorld.getTimeOfDay());
        }

        var expected = Set.of(TimeOfDay.MORNING, TimeOfDay.DAY, TimeOfDay.EVENING, TimeOfDay.NIGHT);
        assertEquals(expected, actual);
    }

    @Test
    public void timeDoesNotMoveWhenStopped() {
        this.timerController.start();
        this.timerController.stop();

        var actual = new HashSet<Integer>();
        for (int i = 0; i < World.TICKS_PER_DAY; i++) {
            this.timer.run();
            actual.add(this.testWorld.getTicksPassed());
        }

        var expected = Set.of(0);
        assertEquals(expected, actual);
    }

    @Test
    public void incrementsDaysAtTheEndOfDay() {
        this.timerController.start();

        for (int i = 0; i < World.TICKS_PER_DAY; i++) {
            this.timer.run();
        }

        int currentDaysPassed = this.testWorld.getDaysPassed();
        int expectedDaysPassed = 1;
        assertEquals(expectedDaysPassed, currentDaysPassed);
    }


}
