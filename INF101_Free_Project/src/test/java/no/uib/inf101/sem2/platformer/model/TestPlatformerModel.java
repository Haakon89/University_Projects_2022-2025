package no.uib.inf101.sem2.platformer.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.platformer.model.player.PlayerFactory;
import no.uib.inf101.sem2.platformer.model.player.RandomPlayerFactory;

public class TestPlatformerModel {

    @Test
    public void testIfMovePlayerReturnsTrue() throws IOException {
        PlatformerBoard board = new PlatformerBoard();
        PlayerFactory builder = new RandomPlayerFactory();
        PlatformerModel model = new PlatformerModel(board, builder);
        
        assertTrue(model.movePlayer(1, 0));     
    }   

    @Test
    public void testIfPlayerCanJumpWhileNotOnTheGround() throws IOException {
        PlatformerBoard board = new PlatformerBoard();
        PlayerFactory builder = new RandomPlayerFactory();
        PlatformerModel model = new PlatformerModel(board, builder);
        List<GridCell<Character>> playerCellsBeforeJump = new ArrayList<>();

        model.movePlayer(-1, 0);

        for (GridCell<Character> gc : model.getPlayerTilesOnBoard()) {
            playerCellsBeforeJump.add(gc);
        }
        
        model.clockTick("jumping");;
        
        List<GridCell<Character>> playerCellsAfterJump = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerTilesOnBoard()) {
            playerCellsAfterJump.add(gc);
        }

        assertTrue(playerCellsBeforeJump.equals(playerCellsAfterJump));     
    }  
    @Test
    public void testIfPlayerCanJumpWhileNotOnTheGroundAfterDoubleJumpUnlock() throws IOException {
        PlatformerBoard board = new PlatformerBoard();
        PlayerFactory builder = new RandomPlayerFactory();
        PlatformerModel model = new PlatformerModel(board, builder);

        model.movePlayer(-1, 0);

        
        model.setDoubleJump(DoubleJump.ACTIVE);

        List<GridCell<Character>> playerCellsBeforeJump = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerTilesOnBoard()) {
            playerCellsBeforeJump.add(gc);
        }
        
        model.jumpPlayer();
        model.clockTick("jumping");
        
        List<GridCell<Character>> playerCellsAfterJump = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerTilesOnBoard()) {
            playerCellsAfterJump.add(gc);
        }
        
        assertFalse(playerCellsBeforeJump.equals(playerCellsAfterJump));     
    }   
    
    @Test
    public void testIfPositionChangesAfterMovePlayer() throws IOException {
        PlatformerBoard board = new PlatformerBoard();
        PlayerFactory builder = new RandomPlayerFactory();
        PlatformerModel model = new PlatformerModel(board, builder);

        List<GridCell<Character>> playerCellsBeforeMove = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerTilesOnBoard()) {
            playerCellsBeforeMove.add(gc);
        }
        
        model.movePlayer(1, 0);

        List<GridCell<Character>> playerCellsAfterMove = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerTilesOnBoard()) {
            playerCellsAfterMove.add(gc);
        }
        
        assertFalse(playerCellsBeforeMove.equals(playerCellsAfterMove));
    }

    @Test
    public void testIfPossibleToMovePlayerOffGrid() throws IOException {
        PlatformerBoard board = new PlatformerBoard();
        PlayerFactory builder = new RandomPlayerFactory();
        PlatformerModel model = new PlatformerModel(board, builder);

        assertFalse(model.movePlayer(board.rows()+1, board.cols()+1));
    }

    @Test
    public void testIfPlayerCanMoveIntoAllreadyOccupiedSpace() throws IOException {
        PlatformerBoard board = new PlatformerBoard();
        PlayerFactory builder = new RandomPlayerFactory();
        PlatformerModel model = new PlatformerModel(board, builder);
        
        while (model.movePlayer(-1, 0)) {

        }

        assertFalse(model.movePlayer(-1, 0));
    }

    
    @Test
    public void testIfClockTickMovesplayer() throws IOException {
        PlatformerBoard board = new PlatformerBoard();
        PlayerFactory builder = new RandomPlayerFactory();
        PlatformerModel model = new PlatformerModel(board, builder);
        
        List<GridCell<Character>> cellsBeforeTick = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerTilesOnBoard()) {
            cellsBeforeTick.add(gc);
            System.out.println(gc.cp());
        }
        
        model.clockTick("falling");

        List<GridCell<Character>> cellsAfterTick = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerTilesOnBoard()) {
            cellsAfterTick.add(gc);
            System.out.println(gc.cp());
        }
        assertFalse(cellsBeforeTick.equals(cellsAfterTick));
    }
}
