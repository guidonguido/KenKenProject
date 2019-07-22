package command;

import model.backtracking.GiocoObservable;
import view.Panel;

import java.awt.*;
import java.awt.geom.Point2D;

public class NumberCommand implements Command{

    private String number, oldNumber;
    private GiocoObservable gioco = GiocoObservable.getInstance();
    private boolean isAReDo = false;

    private Panel cell;

    public NumberCommand(Panel cell, String number)
    {   this.cell = cell;
        this.number = number;
        Point2D position = cell.getPosition();
        int row = (int) position.getX();
        int col = (int) position.getY();

        oldNumber = gioco.getGriglia()[row][col].getNumber().toString();


    }

    @Override
    public boolean doIt() {
        Point2D position = cell.getPosition();
        int row = (int) position.getX();
        int col = (int) position.getY();

        if( gioco.impostaNumero(row, col, number.equals("Cancella") ? 0:Integer.parseInt(number))) {
            if(!isAReDo) {
                cell.setBackground(Color.GREEN);
                isAReDo = true;
            }
            return true;
        }
        else{
            if(!isAReDo) {
                cell.setBackground(Color.RED);
                isAReDo = true;
            }
            return false;
        }

    }

    @Override
    public boolean undoIt() {

        Point2D position = cell.getPosition();
        int row = (int) position.getX();
        int col = (int) position.getY();

        cell.setBackground(Color.WHITE);

        return gioco.impostaNumero(row, col, Integer.parseInt(oldNumber));

    }
}
