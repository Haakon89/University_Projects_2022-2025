package no.uib.inf101.sem2.grid;

/**
 * @param cp stores a Cellposition in the grid
 * @param value stores the value that is stored at the CellPosition in the grid
 */
public record GridCell<E>(CellPosition cp, E value) {

    /**
     * @return a Cellposition
     */
    public Object pos() {
        return cp;
    }
	}
