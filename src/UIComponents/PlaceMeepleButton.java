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
    /** Whether or not this button is active. If not, it is for display purposes only and clicking does nothing */
    private boolean isActive;

    /** The size of this button */
    public static final int BUTTON_SIZE = 30;

    /**
     * Default constructor. The subclass will only need to use the following fields:
     * @param internal the internal feature that the meeple will placed on
     * @param player the player that owns the placed meeple
     */
    protected PlaceMeepleButton(GlobalVariables.Internal internal, Player player) {
        this.internal = internal;
        this.player = player;
        this.activate();
        this.addMouseListener(this);
    }

    /**
     * Constructor
     * @param player the image of this button
     * @param x the x coordinate of this button
     * @param y the y coordinate of this button
     */
    public PlaceMeepleButton(GlobalVariables.Feature feature, GlobalVariables.Internal internal, Player player, GlobalVariables.Location location, int x, int y) {
        this(internal, player);
        this.feature = feature;
        this.location = location;
        this.setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
        this.setIcon(new ImageIcon(player.getPlayerColor().getMeepleImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_FAST)));
    }

    /**
     * Sets the isActive field to true, enabling the button to be clicked
     */
    protected void activate() {
        this.isActive = true;
    }

    /**
     * Sets the isActive field to false, disabling the button from being clicked
     */
    protected void deactivate() {
        this.isActive = false;
    }

    /**
     * Sets the "feature" field to the specified feature
     * @param feature the feature to set for the meeple
     */
    protected void setFeature(GlobalVariables.Feature feature) {
        this.feature = feature;
    }

    /**
     * Sets the "internal" field to the specified internal feature
     * @param internal the internal feature to set for the meeple
     */
    protected void setInternal(GlobalVariables.Internal internal) {
        this.internal = internal;
    }

    /**
     * Sets the player field to the player that will own the placed meeple
     * @param player the player that will own the placed meeple
     */
    protected void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Getter method for the owner of the placed meeple
     * @return the owner of the placed meeple
     */
    protected Player getPlayer() {
        return this.player;
    }

    /* Mouse Listener methods */

    @Override
    public void mouseClicked(MouseEvent e) {
        // If this button is inactive, do nothing
        if(!isActive)
            return;
        if(player.getMeeples().isEmpty())
            return;

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
        deactivate();
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
