package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TargetLabel extends JLabel implements PanelComponent {

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

    public TargetLabel() {
        super("", CENTER);

        //setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        setBackground(Color.white);
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
