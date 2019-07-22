package controller;

import command.CommandHandler;
import command.NumberCommand;
import model.backtracking.GiocoObservable;
import view.GamePanel;
import view.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class NumbersController extends JPanel implements MouseListener {

    private GiocoObservable gioco;

    private Panel selectedCell;     //La cella che si sta attualmente controllando
    private List<JButton> buttons = new ArrayList<>();


    public NumbersController(GamePanel gamePanel, CommandHandler commandHandler)
    {   super(new GridLayout());
        super.setPreferredSize(new Dimension(110,110));
        this.gioco = GiocoObservable.getInstance();

        for(int i = 0; i <= gioco.getGriglia().length ; i++)
        {
            JButton button = new JButton();
            buttons.add(button);

            if( i==0 )
                button.setText("Cancella");
            else
                button.setText("" +i);

            button.setBorder(BorderFactory.createEtchedBorder(1));
            add(button);
            button.setEnabled(false);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(selectedCell == null) return;

                    commandHandler.handle(new NumberCommand(selectedCell, button.getText()));

                }
            });
        }
    }


    private void enableButtons()
    {
        for(JButton b: buttons)
            b.setEnabled(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JPanel) {
            if(selectedCell != null) selectedCell.setBackground(Color.WHITE);
            selectedCell = (Panel) e.getSource();
            System.out.println("Mouse cliccato nel Panel in posizione" +e.getPoint());
        }

        selectedCell.setBackground(Color.ORANGE);
        enableButtons();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
