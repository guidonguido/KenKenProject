package model.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Gioco extends Problema<Cell,Integer> {

    private Cell[][] griglia;

    private ArrayList<Cell[][]> soluzioni = new ArrayList<>();

    private int dim;

    public Gioco( Cell[][] griglia )
    {
        //Il gioco riceve una griglia composta di celle precostruita
        //

        if(griglia.length != griglia[0].length)
            throw new IllegalGridException();

        this.griglia = griglia;
        dim = griglia.length;

    }


    @Override
    protected Cell primoPuntoDiScelta() {
        return griglia[0][0];
    }

    @Override
    protected Cell prossimoPuntoDiScelta(Cell ps, Integer integer) {
        return ps.next(griglia);
    }

    @Override
    protected Cell ultimoPuntoDiScelta() {
        return griglia[dim-1][dim-1];
    }

    @Override
    protected Integer primaScelta(Cell ps) {
        return 1;
    }

    @Override
    protected Integer prossimaScelta(Integer integer) {
        return integer+1;
    }

    @Override
    protected Integer ultimaScelta(Cell ps) {
        return dim;
    }

    @Override
    protected boolean assegnabile(Integer scelta, Cell puntoDiScelta) {
        //Una scelta è assegnabile se si rientra ancora nei limiti del target nella gabbia
        //e se il numero non è già presente nella riga o colonna della cella

        if( !validCage(scelta, puntoDiScelta))
            return false;

        return validNumber(scelta,puntoDiScelta);

    }

    public boolean validNumber(Integer scelta, Cell puntoDiScelta)
    {
        for(int i = 0; i < dim; i++ )
            if( griglia[i][puntoDiScelta.getColumn()].getNumber().equals(scelta))
                return false;
        for(int i = 0; i < dim; i++ )
            if( griglia[puntoDiScelta.getRow()][i].getNumber().equals(scelta))
               return false;

            return true;
    }

    private boolean validCage(Integer scelta, Cell puntoDiScelta) {
        //Una gabbia è valida se applicando il numero scelto alla cella, si rientra ancora nei valori del target o lo si raggiunge

        Cage cage = puntoDiScelta.getCage();

        int currentTarget = 0;

        switch (cage.getOperation()) {
            case ADDITION:
                int assegnati = 0;
                for (Cell c : cage.getCells()) {
                    currentTarget = (c.getNumber() > 0) ? currentTarget + c.getNumber() : currentTarget;
                    assegnati = (c.getNumber() > 0) ? ++assegnati : assegnati;
                }
                currentTarget += scelta;

                if(assegnati == cage.getDim() - 1  && currentTarget != cage.getTarget()) {
                    return false;
                }

                return currentTarget <= cage.getTarget();

            case MULTIPLICATION:
                currentTarget = 1;
                assegnati = 0;
                for (Cell c : cage.getCells()){
                    currentTarget = (c.getNumber() > 0) ? currentTarget * c.getNumber() : currentTarget;
                    if(c.getNumber() > 0)
                        assegnati++;
                }
                currentTarget *= scelta;

                if(assegnati == cage.getDim() - 1  && currentTarget != cage.getTarget()) {
                    return false;
                }

                return currentTarget <= cage.getTarget();


            case SUBTRACTION: {                                             //Ordino le celle presenti nella gabbia e sottraggo dal maggiore al minore
                List<Cell> cells = cage.getCells();
                LinkedList<Integer> numbersInCell = new LinkedList<>();

                for(Cell c: cells)
                    if(c.getNumber() != 0)
                        numbersInCell.add(c.getNumber());

                    numbersInCell.add(scelta);

                if(numbersInCell.size() == 2) {
                    Collections.sort(numbersInCell);
                    return (numbersInCell.get(1) - numbersInCell.get(0)) == cage.getTarget();
                }

                return true;

            }

            case DIVISION:                                                  //Ordino le celle presenti nella gabbia e divido dal maggiore al minore
                List<Cell> cells = cage.getCells();
                LinkedList<Integer> numbersInCell = new LinkedList<>();

                for(Cell c: cells)
                    if(c.getNumber() != 0)
                        numbersInCell.add(c.getNumber());

                numbersInCell.add(scelta);

                if(numbersInCell.size() == 2) {
                    Collections.sort(numbersInCell);
                    return (numbersInCell.get(1) / numbersInCell.get(0)) == cage.getTarget();
                }

                return true;
        }

        return false;
    }





    @Override
    protected void assegna(Integer scelta, Cell puntoDiScelta) {

        puntoDiScelta.setNumber(scelta);

    }

    @Override
    protected void deassegna(Integer scelta, Cell puntoDiScelta) {

        puntoDiScelta.setNumber(0);

    }

    @Override
    protected Cell precedentePuntoDiScelta(Cell puntoDiScelta) {
        return puntoDiScelta.previous(griglia);
    }

    @Override
    protected Integer ultimaSceltaAssegnata(Cell puntoDiScelta) {
        return puntoDiScelta.getNumber();
    }

    @Override
    public void risolvi() {
        for(int i = 0; i < dim; i++)
            for(int j = 0; j < dim; j++)
               griglia[i][j].setNumber(0);

        soluzioni.clear();
        super.risolvi();
    }

    @Override
    protected void scriviSoluzione(int nrsol) {
        System.out.println("soluzione numero: "+nrsol);

        Cell[][] sol = new Cell[dim][dim];

        for(int i = 0; i < dim; i++)
            for(int j = 0; j < dim; j++)
            {   sol[i][j] = new Cell(griglia[i][j]);
                System.out.println(griglia[i][j]);
            }

        soluzioni.add(sol);
        System.out.println();
        System.out.println("--- --- --- --- --- --- --- --- --- --- --- --- --- ---");
        System.out.println();



    }

    public Cell[][] getGriglia() {
        return griglia;
    }

    public void setGriglia(Cell[][] cells)
    {
        this.griglia = cells;
    }

    public ArrayList<Cell[][]> getSoluzioni(){
        return soluzioni;
    }
}
