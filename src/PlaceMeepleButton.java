import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by robinsat on 5/6/2015.
 *
 * This is a graphical class representing a button used to place meeples on a tile
 */

public class PlaceMeepleButton extends JLabel implements MouseListener{

    /** The color of this button */
    private Color color;
    /** The size of this button */
    private static final int BUTTON_SIZE = 30;

    /**
     * Constructor
     * @param color the color of this button
     */
    public PlaceMeepleButton(Color color, int x, int y) {
        this.color = color;
        this.setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
    }

    /* Mouse Listener methods */

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Not necessary
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Not necessary
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Not necessary
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Not necessary
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(this.getX(), this.getY(), BUTTON_SIZE, BUTTON_SIZE);
        g.setColor(this.color);
        g.fillOval(this.getX(), this.getY(), BUTTON_SIZE, BUTTON_SIZE);
    }
}
