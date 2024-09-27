package no.uib.inf101.sem2.platformer.model.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public class Player implements Iterable<GridCell<Character>> {

    private char playerID;
    private boolean[][] playerShape;
    private CellPosition playerPOS;

    private Player(char ID, boolean[][] shape, CellPosition pos) {
        this.playerID = ID;
        this.playerShape = shape;
        this.playerPOS = pos;
    }

    /**
     * takes in an identifying character and chooses the correct shape.
     * @param ID the identifier of the Player shape
     * @return a new Player with the identifying character, the correct shape for the identifier and cellposition (0, 0)
     */
    static Player newPlayer(char ID) {
        boolean[][] playerShape = new boolean[40][27];
        for(int i = 0; i < playerShape.length; i++) {
            for(int j = 0; j < playerShape[i].length; j++) {
                playerShape[i][j] = true;
            }
        }
        HashMap<Character, boolean[][]> tetrominoShapeID = new HashMap<>();
        tetrominoShapeID.put('I', playerShape);
        
        if (tetrominoShapeID.containsKey(ID)) {
            return new Player(ID, tetrominoShapeID.get(ID), new CellPosition(0, 0));
        }
        else {
            throw new IllegalArgumentException("Not legal character!!!");
        }
    }

    /**
     * @param deltaRow change in row position
     * @param deltaCol change in collumn position
     * @return a copy of the Player that is shifted over by the paramater amounts.
     */
    public Player shiftedBy(int deltaRow, int deltaCol) {
        int newRow = this.playerPOS.row() + deltaRow;
        int newCol = this.playerPOS.col() + deltaCol;
        Player copy = new Player(this.playerID, this.playerShape, new CellPosition(newRow, newCol));
        return copy;
    }

    /**
     * takes in a Griddimension that it uses to calculate the top  center of the grid.
     * @param dimension the dimensions of the grid we are working with
     * @return a new Player with a new position at the top centre of the grid
     */
    public Player moveToStart(GridDimension dimension) {
        return new Player(this.playerID, this.playerShape, new CellPosition(dimension.rows()-100, 30));
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> iteration = new ArrayList<>();
        for (int i = 0; i < playerShape.length; i++) {
            for (int j = 0; j < playerShape[0].length; j++) {
                CellPosition pos = new CellPosition(i+this.playerPOS.row(), j+this.playerPOS.col());
                if (playerShape[i][j] == true) {
                    iteration.add(new GridCell<Character>(pos, playerID));
                }
            }
        }
        return iteration.iterator();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + playerID;
        result = prime * result + Arrays.deepHashCode(playerShape);
        result = prime * result + ((playerPOS == null) ? 0 : playerPOS.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        if (playerID != other.playerID)
            return false;
        if (!Arrays.deepEquals(playerShape, other.playerShape))
            return false;
        if (playerPOS == null) {
            if (other.playerPOS != null)
                return false;
        } else if (!playerPOS.equals(other.playerPOS))
            return false;
        return true;
    }
}