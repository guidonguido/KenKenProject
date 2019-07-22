package view;

import command.CommandHandler;
import command.HistoryCommandHaldler;
import command.ScegliFileCommand;
import controller.GameActionController;
import controller.NumbersController;
import model.backtracking.Cage;
import model.backtracking.Cell;
import model.backtracking.GiocoObservable;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KenKen {

    private JFrame frame;
    private GiocoObservable gioco = GiocoObservable.getInstance();

    public KenKen()
    {
        gioco.newGame(inizializzaGrigliaDefault());

        inizializzaNuovaPartita();

    }

    private void inizializzaNuovaPartita()
    {
        CommandHandler commandHandler = new HistoryCommandHaldler();

        GamePanel panel = new GamePanel(gioco.getGioco());

        NumbersController numbersController = new NumbersController(panel, commandHandler);
        GameActionController actionController = new GameActionController(commandHandler);
        panel.setController(numbersController); //aggiunge i MouseListener alle celle

        gioco.addObserver(panel);
        gioco.addObserver(actionController);



        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(900,920);

        frame.add(panel.getPanel(), BorderLayout.CENTER);
        frame.add(numbersController, BorderLayout.SOUTH);
        frame.add(actionController,BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JButton newPartitaButton = new JButton("Nuova Partita");
        actionController.add(newPartitaButton, BorderLayout.EAST);

        newPartitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                FileFilter filtroKen = new FileNameExtensionFilter("KenKen FIles", "ken");
                fileChooser.addChoosableFileFilter(filtroKen);
                fileChooser.setFileFilter(filtroKen);
                fileChooser.setPreferredSize(new Dimension(800,500));

                int n = fileChooser.showOpenDialog(frame);

                if(n == JFileChooser.APPROVE_OPTION) {
                    gioco.deleteObserver(panel);
                    commandHandler.handle(new ScegliFileCommand(fileChooser.getSelectedFile()));
                    frame.dispose();
                    inizializzaNuovaPartita();
                }
            }
        });
    }
    private Cell[][] inizializzaGrigliaDefault()
    {
        Cell[][] griglia = new Cell[6][6];
        Cage cage1 = new Cage(11,"addition",2);
        Cage cage2 = new Cage(2,"division",2);
        Cage cage3 = new Cage(20,"multiplication",2);
        Cage cage4 = new Cage(6,"multiplication",4);
        Cage cage5 = new Cage(3,"subtraction",2);
        Cage cage6 = new Cage(3,"division",2);
        Cage cage7 = new Cage(240,"multiplication",4);
        Cage cage8 = new Cage(6,"multiplication",2);
        Cage cage9 = new Cage(6,"multiplication",2);
        Cage cage10 = new Cage(7,"addition",3);
        Cage cage11 = new Cage(30,"multiplication",2);
        Cage cage12 = new Cage(6,"multiplication",2);
        Cage cage13 = new Cage(9,"addition",2);
        Cage cage14 = new Cage(8,"addition",3);
        Cage cage15 = new Cage(2,"division",2);

        griglia[0][0] = new Cell(0,0,cage1);
        griglia[0][1] = new Cell(0,1,cage2);
        griglia[0][2] = new Cell(0,2,cage2);
        griglia[0][3] = new Cell(0,3,cage3);
        griglia[0][4] = new Cell(0,4,cage4);
        griglia[0][5] = new Cell(0,5,cage4);

        griglia[1][0] = new Cell(1,0,cage1);
        griglia[1][1] = new Cell(1,1,cage5);
        griglia[1][2] = new Cell(1,2,cage5);
        griglia[1][3] = new Cell(1,3,cage3);
        griglia[1][4] = new Cell(1,4,cage6);
        griglia[1][5] = new Cell(1,5,cage4);

        griglia[2][0] = new Cell(2,0,cage7);
        griglia[2][1] = new Cell(2,1,cage7);
        griglia[2][2] = new Cell(2,2,cage8);
        griglia[2][3] = new Cell(2,3,cage8);
        griglia[2][4] = new Cell(2,4,cage6);
        griglia[2][5] = new Cell(2,5,cage4);

        griglia[3][0] = new Cell(3,0,cage7);
        griglia[3][1] = new Cell(3,1,cage7);
        griglia[3][2] = new Cell(3,2,cage9);
        griglia[3][3] = new Cell(3,3,cage10);
        griglia[3][4] = new Cell(3,4,cage11);
        griglia[3][5] = new Cell(3,5,cage11);

        griglia[4][0] = new Cell(4,0,cage12);
        griglia[4][1] = new Cell(4,1,cage12);
        griglia[4][2] = new Cell(4,2,cage9);
        griglia[4][3] = new Cell(4,3,cage10);
        griglia[4][4] = new Cell(4,4,cage10);
        griglia[4][5] = new Cell(4,5,cage13);

        griglia[5][0] = new Cell(5,0,cage14);
        griglia[5][1] = new Cell(5,1,cage14);
        griglia[5][2] = new Cell(5,2,cage14);
        griglia[5][3] = new Cell(5,3,cage15);
        griglia[5][4] = new Cell(5,4,cage15);
        griglia[5][5] = new Cell(5,5,cage13);

        cage1.addCell(griglia[0][0]);
        cage1.addCell(griglia[1][0]);

        cage2.addCell(griglia[0][1]);
        cage2.addCell(griglia[0][2]);

        cage3.addCell(griglia[0][3]);
        cage3.addCell(griglia[1][3]);

        cage4.addCell(griglia[0][4]);
        cage4.addCell(griglia[0][5]);
        cage4.addCell(griglia[1][5]);
        cage4.addCell(griglia[2][5]);

        cage5.addCell(griglia[1][1]);
        cage5.addCell(griglia[1][2]);

        cage6.addCell(griglia[1][4]);
        cage6.addCell(griglia[2][4]);

        cage7.addCell(griglia[2][0]);
        cage7.addCell(griglia[2][1]);
        cage7.addCell(griglia[3][0]);
        cage7.addCell(griglia[3][1]);

        cage8.addCell(griglia[2][2]);
        cage8.addCell(griglia[2][3]);

        cage9.addCell(griglia[3][2]);
        cage9.addCell(griglia[4][2]);

        cage10.addCell(griglia[3][3]);
        cage10.addCell(griglia[4][3]);
        cage10.addCell(griglia[4][4]);

        cage11.addCell(griglia[3][4]);
        cage11.addCell(griglia[3][5]);

        cage12.addCell(griglia[4][0]);
        cage12.addCell(griglia[4][1]);

        cage13.addCell(griglia[4][5]);
        cage13.addCell(griglia[5][5]);

        cage14.addCell(griglia[5][0]);
        cage14.addCell(griglia[5][1]);
        cage14.addCell(griglia[5][2]);

        cage15.addCell(griglia[5][3]);
        cage15.addCell(griglia[5][4]);

        return griglia;
    }
    public static void main(String[] args) {
        new KenKen();
    }
}
