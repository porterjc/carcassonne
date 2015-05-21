package UIComponents;

import Main.GlobalVariables;
import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This class represents a graphical button used to place an abbot, which is a special type of meeple
 * Created by robinsat on 5/17/2015.
 */
public class PlaceAbbotButton extends PlaceMeepleButton {

    /**
     * Constructor
     * @param internal the internal feature that this is on, which can be a garden or monastery
     * @param player the player that owns this abbot
     * @param tileRotation the roration of the tile, which is used to determine where this abbot should appear
     */
    public PlaceAbbotButton(GlobalVariables.Internal internal, Player player, int tileRotation) {
        super(internal, player);
        this.setIcon(new ImageIcon(player.getPlayerColor().getAbbotImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_FAST)));
        this.drawAbbot(tileRotation);
    }

    /**
     * Sets the isActive field to false, disabling the button from being clicked
     */
    protected void deactivate() {
        // Do nothing, this should be active at all times
    }

    /**
     * Draws the abbot in the appropriate location based on the tile rotation
     * @param tileRotation the tile's current position
     */
    private void drawAbbot(int tileRotation) {
        int margin = 15;
        int half = AbstractTile.TILE_PIXEL_SIZE / 2 - BUTTON_SIZE / 2;
        if(getInternal() == GlobalVariables.Internal.MONASTERY)
            this.setBounds(half + margin , half, BUTTON_SIZE, BUTTON_SIZE);
        else {

            if (tileRotation == 0) {
                this.setBounds(margin, half, BUTTON_SIZE, BUTTON_SIZE);
            } else if (tileRotation == 1) {
                this.setBounds(half - 10, 8, BUTTON_SIZE, BUTTON_SIZE);
            } else if (tileRotation == 2) {
                this.setBounds(AbstractTile.TILE_PIXEL_SIZE - margin - BUTTON_SIZE, half - margin, BUTTON_SIZE, BUTTON_SIZE);
            } else if (tileRotation == 3) {
                this.setBounds(half + 10, AbstractTile.TILE_PIXEL_SIZE - 20 - BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
            } else
                this.setBounds(half, half, BUTTON_SIZE, BUTTON_SIZE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        PlayableTile parentTile = (PlayableTile) getParent();
        Game game = ((TileGrid) parentTile.getParent()).getGame();
        if(!game.canPlaceMeeple())
            return;

        if(game.getCurrentTile() == parentTile && getPlayer().getAbbot().getInternal() == null) {
            System.out.println("Placing abbot");

            // Keep only this one
            parentTile.removeAll();
            parentTile.add(this);
            parentTile.repaint();

            getPlayer().getAbbot().place(parentTile, getInternal(), getMeepleLocation());
            //If this is on a garden or monastery, the game needs to know about it
            game.addMonk(getPlayer().getAbbot());
            game.moveToNextState();
        }
        else if(game.getCurrentTurnPlayer() == getPlayer()){
            System.out.println("Removing abbot");
            getPlayer().updateScore(parentTile.scoreSurrounding(false));
            getPlayer().getAbbot().remove();
            parentTile.removeAll();
            parentTile.repaint();
            game.removeMonk(getPlayer().getAbbot());
            game.getCurrentTile().removeAll();
            game.getCurrentTile().repaint();
            game.moveToNextState();
        }
    }
}
