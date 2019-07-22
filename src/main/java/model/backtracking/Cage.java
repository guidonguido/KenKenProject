package model.backtracking;

import java.util.LinkedList;
import java.util.Objects;

public class Cage {



    public enum Operation{
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
    }

    private LinkedList<Cell> cells;
    private Integer target;
    private int dim;


    private Operation operation;

    public Cage(Integer target, Operation operation, int dim )
    {   this.dim = dim;
        this.cells = new LinkedList<>();
        this.target = target;
        this.operation = operation;
    }

    public Cage(Integer target, String operation, int dim )
    {   this.dim = dim;
        this.cells = new LinkedList<>();
        this.target = target;

        this.operation = Operation.valueOf(operation.toUpperCase());


    }

    public void addCell(Cell cell)
    {   cells.add(cell);
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        target = target;
    }

    public LinkedList<Cell> getCells() {
        return new LinkedList<>(cells);
    }

    public int getDim() {
        return dim;
    }

    public Operation getOperation() {
        return operation;
    }
    @Override
    public String toString() {
        return "" +target.toString() +" " +operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cage cage = (Cage) o;
        return cells.equals(cage.cells) &&
                target.equals(cage.target) &&
                operation == cage.operation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells, target, operation);
    }


}
