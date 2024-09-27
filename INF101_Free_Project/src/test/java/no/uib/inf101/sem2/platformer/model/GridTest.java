package no.uib.inf101.sem2.platformer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.platformer.model.PlatformerBoard;

public class GridTest {
  
  @Test
  void gridTestGetRowsAndCols() throws IOException {
    PlatformerBoard board = new PlatformerBoard();
    //IGrid<Integer> board = new Grid<>();
    assertEquals(500, board.rows());
    assertEquals(1000, board.cols());
  }
  
  @Test
  void gridSanityTest() throws IOException {
    
    PlatformerBoard board = new PlatformerBoard();
    
    assertEquals('-', board.get(new CellPosition(310, 144)));
    assertEquals('-', board.get(new CellPosition(280, 870)));
    
    board.set(new CellPosition(310, 144), 'R');
    
    assertEquals('R', board.get(new CellPosition(310, 144)));
    assertEquals('-', board.get(new CellPosition(309, 143)));
    assertEquals('-', board.get(new CellPosition(311, 145)));
  }
  
  @Test
  void gridCanHoldNull() throws IOException {
    PlatformerBoard board = new PlatformerBoard();
    
    assertEquals('W', board.get(new CellPosition(0, 0)));
    assertEquals('W', board.get(new CellPosition(2, 1)));
    
    board.set(new CellPosition(1, 1), null);
    
    assertEquals(null, board.get(new CellPosition(1, 1)));
    assertEquals('W', board.get(new CellPosition(0, 1)));
    assertEquals('W', board.get(new CellPosition(1, 0)));
    assertEquals('W', board.get(new CellPosition(2, 1)));
  }
  
  @Test
  void coordinateIsOnGridTest() throws IOException {
    PlatformerBoard board = new PlatformerBoard();
    
    assertTrue(board.positionIsOnGrid(new CellPosition(2, 1)));
    assertTrue(board.positionIsOnGrid(new CellPosition(3, 1)));
    assertTrue(board.positionIsOnGrid(new CellPosition(2, 2)));
    
    assertTrue(board.positionIsOnGrid(new CellPosition(0, 0)));
    assertFalse(board.positionIsOnGrid(new CellPosition(-1, 0)));
    assertFalse(board.positionIsOnGrid(new CellPosition(0, -1)));
  }
  
  @Test
  void throwsExceptionWhenCoordinateOffGrid() throws IOException {
    PlatformerBoard board = new PlatformerBoard();
    
    try {
      @SuppressWarnings("unused")
      Character x = board.get(new CellPosition(8000, 5000));
      fail();
    } catch (IndexOutOfBoundsException e) {
      // Test passed
    }
  }
  
  @Test
  void testIterator() throws IOException {
    PlatformerBoard board = new PlatformerBoard();
    board.set(new CellPosition(0, 0), 'R');
    board.set(new CellPosition(1, 1), 'O');
    board.set(new CellPosition(2, 1), '-');
    
    List<GridCell<Character>> items = new ArrayList<>();
    for (GridCell<Character> coordinateItem : board) {
      items.add(coordinateItem);
    }
    
    assertEquals(1000 * 500, items.size());
    assertTrue(items.contains(new GridCell<Character>(new CellPosition(0, 0), 'R')));
    assertTrue(items.contains(new GridCell<Character>(new CellPosition(1, 1), 'O')));
    assertTrue(items.contains(new GridCell<Character>(new CellPosition(2, 1), '-')));
    assertTrue(items.contains(new GridCell<Character>(new CellPosition(0, 1), 'W')));
  }
}
