package controller;

import command.CommandHandler;
import command.HistoryCommandHaldler;
import command.RisolviCommand;
import model.backtracking.GiocoObservable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class GameActionController extends JToolBar implements Observer {

    private JButton undoButton;
    private JButton redoButton;
    private JButton risolviButton;

    public GameActionController(CommandHandler commandHandler)
    {   setLayout(new BorderLayout(20,20));

        Panel histPanel = new Panel();
        histPanel.setLayout(new BorderLayout(4,4));


        undoButton = new JButton("Undo");
        histPanel.add(undoButton,BorderLayout.WEST);

        redoButton = new JButton("Redo");
        histPanel.add(redoButton,BorderLayout.EAST);

        add(histPanel,BorderLayout.WEST);

        risolviButton = new JButton("Risolvi Gioco");
        add(risolviButton, BorderLayout.CENTER);

        undoButton.setEnabled(false);
        redoButton.setEnabled(false);

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(commandHandler.handle(new HistoryCommandHaldler().UNDO))
                    redoButton.setEnabled(true);
                else
                    undoButton.setEnabled(false);

            }
        });

        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(commandHandler.handle(new HistoryCommandHaldler().REDO))
                    undoButton.setEnabled(true);
                else
                    redoButton.setEnabled(false);

            }
        });

        risolviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoButton.setEnabled(false);
                redoButton.setEnabled(false);
                risolviButton.setEnabled(false);

                commandHandler.handle(new RisolviCommand());



            }
        });


    }

    @Override
    public void update(Observable o, Object arg) {
        if((boolean) arg)
            undoButton.setEnabled(true);
    }
}
