package UIComponents;

import UIComponents.SelectableButton;

import javax.swing.*;

/**
 * This class stores selectable buttons, allowing the user to select them individually
 * Created by robinsat on 4/12/2015.
 */
public class SelectPanel extends JPanel {

   // The currently selected button
    private SelectableButton selected;

    /**
     * Constructor
     */
    public SelectPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setOpaque(false);
    }

    /**
     * Gets the currently selected button
     * @return the currently selected button
     */
    public SelectableButton getSelected() {
        return selected;
    }

    /**
     * Selects a new button
     * @param selected the button to select
     */
    public void setSelected(SelectableButton selected) {
        if(this.selected != null) {
            this.selected.deselect();
        }
        this.selected = selected;
        this.selected.select();
    }

}
