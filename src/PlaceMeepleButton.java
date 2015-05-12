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

    /** The feature on which a meeple is to be placed */
    private GlobalVariables.Feature feature;
    /** The internal feature on which a meeple is to be placed */
    private GlobalVariables.Internal internal;
    /** The size of this button */
    public static final int BUTTON_SIZE = 30;

    /**
     * Constructor
     * @param image the image of this button
     * @param x the x coordinate of this button
     * @param y the y coordinate of this button
     */
    public PlaceMeepleButton(GlobalVariables.Feature feature, GlobalVariables.Internal internal, Image image, int x, int y) {
        this.feature = feature;
        this.internal = internal;
        this.setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
        this.addMouseListener(this);
        this.setIcon(new ImageIcon(image.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_FAST)));
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

}
