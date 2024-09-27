package inf112.Sun_Mist_Mountain.app.Model.Math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import inf112.Sun_Mist_Mountain.app.RandomizingTest;

public class TestDirection extends RandomizingTest {

    @Test
    public void northVector() {
        assertEquals(Direction.North,     new Vector(0, 1).direction());
    }

    @Test
    public void northEastVector() {
        assertEquals(Direction.NorthEast, new Vector(1, 1).direction());
    }

    @Test
    public void eastVector() {
        assertEquals(Direction.East,      new Vector(1, 0).direction());
    }

    @Test
    public void southEastVector() {
        assertEquals(Direction.SouthEast, new Vector(1, -1).direction());
    }

    @Test
    public void southVector() {
        assertEquals(Direction.South,     new Vector(0, -1).direction());
    }

    @Test
    public void southWestVector() {
        assertEquals(Direction.SouthWest, new Vector(-1, -1).direction());
    }

    @Test
    public void westVector() {
        assertEquals(Direction.West,      new Vector(-1, 0).direction());
    }

    @Test
    public void northWestVector() {
        assertEquals(Direction.NorthWest, new Vector(-1, 1).direction());
    }

    @Test
    public void definedForAllVectors() {
        for (int i = 0; i < ITERATIONS; i++) {
            var vector = new Vector(this.nextDouble(), this.nextDouble());
            assertNotNull(vector.direction());
        }
    }

}
