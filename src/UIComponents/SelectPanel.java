package UIComponents;

import UIComponents.SelectableButton;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class stores selectable buttons, allowing the user to select them individually
 * Created by robinsat on 4/12/2015.
 */
public class SelectPanel extends JPanel {

   // The currently selected button
    private ArrayList<SelectableButton> selected;

    /**
     * Constructor
     */
    public SelectPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setOpaque(false);
        this.selected = new ArrayList<>();
    }

    /**
     * Gets the currently selected button
     * @return the currently selected button
     */
    public ArrayList<SelectableButton> getAllSelected() {
        return selected;
    }

    /**
     * Selects a new button
     * @param selected the button to select
     */
    public void setSelected(SelectableButton selected){
        if(this.selected.contains(selected)){
            selected.deselect();
            this.selected.remove(selected);
        }else {
            this.selected.add(selected);
            selected.select();
        }
    }

}
