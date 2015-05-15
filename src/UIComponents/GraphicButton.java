package UIComponents;

import Main.GlobalVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A versatile panel to read button presses and render in a variety of different styles
 * Created by porterjc on 3/26/2015.
 */
public abstract class GraphicButton extends JPanel implements MouseListener{

    /**
     * Constructor
     * @param width the width of the button
     * @param height the height of the button
     */
    public GraphicButton(int width, int height) {
        super();
        this.setBackground(GlobalVariables.LIGHT_BLUE);
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.addMouseListener(this);
    }

    /**
     * Constructor
     * @param width the width of the button
     * @param height the height of the button
     * @param text the text to display on this button
     */
    public GraphicButton(int width, int height, String text) {
        this(width, height);
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.add(new GameLabel(text));
    }

    /**
     * Sets the text of the button
     * @param text the text to set for this button
     */
    public void setText(String text) {
        this.removeAll();
        this.add(new GameLabel(text));
    }

    /**
     * Clears out any text inside this component
     */
    public void clear() {
        this.removeAll();
    }

    /**
     * This is abstract and will respond to clicks in a manner defined by subclasses
     * @param e the click event
     */
    @Override
    public abstract void mouseClicked(MouseEvent e);

    // No response is necessary for other events

    @Override
    public void mousePressed(MouseEvent e) {
        //does nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //does nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //does nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //does nothing
    }

}
