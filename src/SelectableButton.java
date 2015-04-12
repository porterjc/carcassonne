import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A GraphicButton that appears as "selected" when clicked
 * Created by robinsat on 4/12/2015.
 */
public class SelectableButton extends GraphicButton {

    private SelectPanel panel;

    /**
     * Constructor
     * @param width the width of the button
     * @param height the height of the button
     */
    public SelectableButton(int width, int height, SelectPanel panel ) {
        super(width, height);
        this.panel = panel;
    }

    /**
     * Constructor
     * @param width the width of the button
     * @param height the height of the button
     */
    public SelectableButton(int width, int height, String text, SelectPanel panel ) {
        super(width, height, text);
        this.panel = panel;
    }

    /**
     * Selects the button when clicked
     * @param e the click event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        panel.setSelected(this);
    }

    /**
     * Display a border to show that this button is "selected"
     */
    public void select() {
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    }

    /**
     * Remove the border and display this button as "normal"
     */
    public void deselect() {
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }

}
