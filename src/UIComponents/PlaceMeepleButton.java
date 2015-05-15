package UIComponents;

import Main.GlobalVariables;
import Objects.*;

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
    /** The player that will control the placed meeple */
    private Player player;
    /** The location at which to place the meeple */
    private GlobalVariables.Location location;
    /** The size of this button */
    public static final int BUTTON_SIZE = 30;

    /**
     * Constructor
     * @param player the image of this button
     * @param x the x coordinate of this button
     * @param y the y coordinate of this button
     */
    public PlaceMeepleButton(GlobalVariables.Feature feature, GlobalVariables.Internal internal, Player player, GlobalVariables.Location location, int x, int y) {
        this.feature = feature;
        this.internal = internal;
        this.player = player;
        this.location = location;
        this.setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
        this.addMouseListener(this);
        this.setIcon(new ImageIcon(player.getPlayerColor().getMeepleImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_FAST)));
    }

    /* Mouse Listener methods */

    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO: Do it a much better way than this if we can. this is gross
        // Keep only this one
        PlayableTile parentTile = (PlayableTile) getParent();
        parentTile.removeAll();
        parentTile.add(this);
        parentTile.repaint();

        Meeple toPlace = player.removeMeeple();
        if(feature != null)
            toPlace.place(parentTile, feature, location);
        else
            toPlace.place(parentTile, internal, location);
        Game game = ((TileGrid) parentTile.getParent()).getGame();
        //If this is on a garden or monastery, the game needs to know about it
        if(internal == GlobalVariables.Internal.MONASTERY || internal == GlobalVariables.Internal.GARDEN)
            game.addMonk(toPlace);

        game.moveToNextState();
        game.moveToNextState();
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
