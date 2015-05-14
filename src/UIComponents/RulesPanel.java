package UIComponents;

import javax.swing.*;
import java.awt.*;

/**
 * This panel displays the rules of the game and provides the necessary visuals.
 * Created by robinsat on 5/11/2015.
 */
public class RulesPanel extends JPanel {

    /**
     * Constructor
     * @param width the width of this UI component
     */
    public RulesPanel(int width) {
        super();
        this.setPreferredSize(new Dimension(width, 1000));
        this.setBackground(Color.WHITE);
        this.displayRules();
    }

    /**
     * This adds the necessary text and images to this panel to clarify all the rules of the game.
     */
    public void displayRules() {
        //TODO: implement this
    }
}
