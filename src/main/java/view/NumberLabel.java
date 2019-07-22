package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NumberLabel extends JLabel implements PanelComponent {
    //LEAF CLASS

    private Panel parentContainer;
    @Override
    public boolean addComp(PanelComponent component) {
        return false;
    }

    @Override
    public boolean remove(PanelComponent component) {
        return false;
    }

    @Override
    public List<PanelComponent> getChildren() {
        return null;
    }

    public NumberLabel() {
        super("", CENTER);

        //setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setFont(new Font(Font.DIALOG, Font.PLAIN, 50));
        setBackground(Color.WHITE);
    }

    public void setText(String text) {
        super.setText(text);
    }

    public String getText() {
        return super.getText();
    }

    public void setForeground(Color color) {
        super.setForeground(color);
    }

    public void setCompBackground(Color color) {
        super.setBackground(color);

    }

    public void setParentContainer(Panel parent)
    {   parentContainer = parent;
    }


}
