package no.uib.inf101.sem2.grid;

import java.util.ArrayList;
import java.util.Iterator;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Grid<E> implements IGrid<E> {

    private int rows;
    private int cols;
    private E defaultValue;
    private E forground;
    private ArrayList<ArrayList<E>> grid;
    
    public Grid() {
        this(null, null, null, null, null, null, null);
    }

    public Grid(E defaultValue, E forground, E doubleJump, E wallJump, E death, E door, BufferedImage levelImage) {
        this.rows = levelImage.getHeight();
        this.cols = levelImage.getWidth();
        this.defaultValue = defaultValue;
        this.forground = forground;
        this.grid = new ArrayList<ArrayList<E>>();
        for (int x = 0; x < this.rows; x++) {
            ArrayList<E> collumns = new ArrayList<>();
            for (int y = 0; y < this.cols; y++) {
                int pixelColor = levelImage.getRGB(y, x);
                    
                if (pixelColor == Color.WHITE.getRGB()) {
                    collumns.add(this.forground);
                } else if (pixelColor == -1237980) {
                    collumns.add(doubleJump);
                } else if (pixelColor == -14503604) {
                    collumns.add(wallJump);
                } else if (pixelColor == -8421505) {
                    collumns.add(door);
                } else if (pixelColor == -32985) {
                    collumns.add(death);
                } else {
                    collumns.add(this.defaultValue);
                }
            }
            this.grid.add(collumns);
        }   
    }

    @Override
    public int rows() {
        return this.rows;
    }

    @Override
    public int cols() {
        return this.cols;
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        ArrayList<GridCell<E>> iteration = new ArrayList<>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                CellPosition pos = new CellPosition(i, j);
                iteration.add(new GridCell<E>(pos, this.get(pos)));
            }
        }
        return iteration.iterator();
    }

    @Override
    public void set(CellPosition pos, E value) {
        try {
            int row = pos.row();
            int col = pos.col();
            this.grid.get(row).set(col, value);
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Cellposition not on grid.");
        }
    }

    @Override
    public E get(CellPosition pos) {
        try {
            return this.grid.get(pos.row()).get(pos.col());
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Cellposition not on grid.");
        }
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        try {
        if (pos.row() < 0 || pos.col() < 0) { 
            return false;
        }
        if (pos.row()+1 > this.rows || pos.col()+1 > this.cols) {
            return false;
        }
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Cellposition not on grid.");
        }
        return true;
    }
}