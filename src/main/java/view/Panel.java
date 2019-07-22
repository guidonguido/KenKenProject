package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel implements PanelComponent {

    private ArrayList<PanelComponent> children = new ArrayList<>();

    private Point2D position;



    public Panel(int dim)
    {
        super(new GridLayout(dim,dim));
    }

    public Panel(int dim, Point2D position)
    {   super(new GridLayout(dim,dim));
        this.position = position;
        super.setBackground(Color.WHITE);
    }

    @Override
    public boolean addComp(PanelComponent component) {
        children.add(component);
        if( component instanceof Component) {
            super.add((Component)component);
        }

        return true;
    }


    public boolean addComp(PanelComponent component, GridBagConstraints gridBagConstraints) {
        children.add(component);
        if( component instanceof Component) {
            super.add((Component)component, gridBagConstraints);
        }

        return true;
    }



    @Override
    public boolean remove(PanelComponent component) {
        return false;
    }

    @Override
    public List<PanelComponent> getChildren() {
        return null;
    }

    public Point2D getPosition()
    {
        return new Point2D.Double(position.getX(), position.getY());
    }


    public void setBorder(int sup, int sin, int inf, int des)
    {
        setBorder(BorderFactory.createMatteBorder(sup, sin, inf, des, Color.BLACK));
    }

    public void setBorder()
    {
        setBorder((BorderFactory.createLineBorder(Color.black)));
    }

    public void setCompBackground(Color color) {
        super.setBackground(color);
        for(int i = 0; i < children.size(); i++)
            children.get(i).setCompBackground(color);


    }

}
