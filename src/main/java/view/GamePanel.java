package view;


import controller.NumbersController;
import model.backtracking.Cage;
import model.backtracking.Cell;
import model.backtracking.Gioco;
import model.backtracking.GiocoObservable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class GamePanel implements Observer {
    private Panel panel;
    private Panel[][] cells;
    private TargetLabel[][] target;
    private NumberLabel[][] numbers;

    public GamePanel(Gioco gioco)
    {   int dim = gioco.getGriglia().length;
        panel = new Panel(dim);

        cells = new Panel[dim][dim];
        numbers = new NumberLabel[dim][dim];
        target = new TargetLabel[dim][dim];
        for(int i = 0; i < dim; i++)
            for(int j = 0; j < dim; j++){
                cells[i][j] = new Panel(9, new Point2D.Double(i,j));
                cells[i][j].setBorder();
                cells[i][j].setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                target[i][j] = new TargetLabel();

                numbers[i][j] = new NumberLabel();

                panel.addComp(cells[i][j]);

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.NORTHWEST;
                gbc.insets = new Insets(0, 0, 20, 30);
                cells[i][j].addComp(target[i][j],gbc);
                gbc.gridx = 1;
                gbc.gridy = 1;
                //gbc.insets = new Insets(0, 0, 10, 10);
                gbc.anchor = GridBagConstraints.CENTER;
                cells[i][j].addComp(numbers[i][j],gbc);

                setNumberBorder(cells[i][j], i, j, gioco);

                target[i][j].setParentContainer(cells[i][j]);
                numbers[i][j].setParentContainer(cells[i][j]);
            }

        panel.setBorder();
        aggiornaTarget(gioco);
        aggiornaNumeri(gioco);
    }

    public Panel getPanel() {
        return panel;
    }


    private void setNumberBorder(Panel panel, int row, int col, Gioco gioco)
    {   Cell[][] grid = gioco.getGriglia();
        int dim = grid.length;

        int sup = 1;
        int inf = 1;
        int des = 1;
        int sin = 1;

        //Bordo destro
        if(row < dim - 1 && !grid[row][col].getCage().equals(grid[row + 1][col].getCage()))
        {
            inf = 3;
        }

        //Bordo sinistro
        if(row > 0 && !grid[row][col].getCage().equals(grid[row - 1][col].getCage()))
            sup = 3;

        //Bordo inferiore
        if(col < dim - 1 && !grid[row][col].getCage().equals(grid[row][col + 1].getCage()))
            des = 3;

        //Bordo superiore
        if(col > 0 && !grid[row][col].getCage().equals(grid[row][col - 1].getCage()))
            sin = 3;

        panel.setBorder(sup, sin, inf, des);
    }


    @Override
    public void update(Observable o, Object arg) {
        GiocoObservable gioco = (GiocoObservable) o;

        aggiornaNumeri(gioco.getGioco());
        aggiornaTarget(gioco.getGioco());
    }


    public void aggiornaTarget(Gioco gioco)
    {   //Si vuole visualizzare il target per una gabbia in una sola cella

        Cell[][] griglia = gioco.getGriglia();
        Set<Cage> cages = new HashSet<>();
        for(int i = 0; i < griglia.length; i++)
            for(int j = 0; j < griglia.length; j++)
            {
                cages.add(griglia[i][j].getCage());
            }

        //Per ogni gabbia si visualizza il target nella prima cella contenuta
        for(Cage cage : cages)
        {   int row = cage.getCells().getFirst().getRow();
            int column = cage.getCells().getFirst().getColumn();
            String targetNum = cage.getTarget().toString();
            String operation = "";

            switch (cage.getOperation())
            {
                case ADDITION:
                    operation = "+";
                    break;
                case MULTIPLICATION:
                    operation = "*";
                    break;
                case DIVISION:
                    operation = "÷";
                    break;
                case SUBTRACTION:
                    operation = "-";
                    break;
            }

            target[row][column].setText(targetNum +operation);

        }

    }


    public void aggiornaNumeri(Gioco gioco)
    {   Cell[][] griglia = gioco.getGriglia();

        int dim = griglia.length;

        System.out.println("dim griglia = " +dim +"dim numbers = " +numbers.length +(dim == numbers.length));

        for(int i = 0; i < dim; i++)
            for(int j = 0; j < dim; j++)
                numbers[i][j].setText(griglia[i][j].getNumber() == 0 ? "":griglia[i][j].getNumber().toString());

    }

    public void setController(NumbersController numbersController)
    {   for(int i = 0; i < cells.length; i++)
            for(int j = 0; j < cells.length; j++)
                cells[i][j].addMouseListener(numbersController);
    }
}
