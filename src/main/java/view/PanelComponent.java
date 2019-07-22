package view;


import java.awt.*;
import java.util.List;

public interface PanelComponent {


    boolean addComp (PanelComponent component);
    boolean remove(PanelComponent component);
    List<PanelComponent> getChildren();

     void setCompBackground(Color color);

}
