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
    public static final int BUTTON_SIZE = 30;

    /**
     * Constructor
     * @param color the color of this button
     */
    public PlaceMeepleButton(Color color, int x, int y) {
        this.color = color;
        this.setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
        this.addMouseListener(this);
    }

    /* Mouse Listener methods */

    @Override
    public void mouseClicked(MouseEvent e) {
        // Keep only this one
        Container parent = getParent();
        parent.removeAll();
        parent.add(this);
        parent.repaint();
        //TODO: Do it a much better way than this
        ((TileGrid) parent.getParent()).getGame().moveToNextState();
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
        int rad = getRadius();
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, rad, rad);
        g.setColor(this.color);
        g.fillOval(0, 0, rad, rad);
    }

    /**
     * Returns the radius of this button
     * @return the radius of this button
     */
    public static int getRadius() {
        return BUTTON_SIZE / 2;
    }
}
