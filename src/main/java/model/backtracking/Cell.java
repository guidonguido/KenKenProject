package model.backtracking;

import java.util.Objects;

public class Cell {

    private  final Integer row, column;
    private final Cage cage;

    private Integer number = 0;

    public Cell(Integer row, Integer column, Cage cage) {
        this.row = row;
        this.column = column;
        this.cage = cage;
    }

    public Cell(Cell copy)
    {
        this.row = copy.row;
        this.column = copy.column;
        this.cage = copy.cage;
        this.number = new Integer(copy.number);
    }

    public Cell next(Cell[][] grid)
    {   int righe = grid.length;
        int colonne = grid[0].length;

        int nextRig = this.row;
        int nextCol = (this.column + 1) % colonne;

        if(nextCol < this.column)
            nextRig = (this.row +1) % righe;

        return grid[nextRig][nextCol];
    }

    public Cell previous(Cell[][] grid)
    {   int righe = grid.length;
        int colonne = grid[0].length;

        int previousRig = this.row;
        int previousCol = (this.column -1 +colonne) % colonne;

        if(previousCol > this.column)
            previousRig = (this.row -1 +righe) % righe;


        return grid[previousRig][previousCol];

    }


    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    public Cage getCage() {
        return cage;
    }

    public void setNumber (Integer number)
    {   if( number < 0 )
          throw new IllegalArgumentException();

        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Cage = " +cage.toString() +" (" +row +"," +column +") = " +number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return Objects.equals(row, cell.row) &&
                Objects.equals(column, cell.column) &&
                Objects.equals(cage, cell.cage) &&
                Objects.equals(number, cell.number);
    }
}
