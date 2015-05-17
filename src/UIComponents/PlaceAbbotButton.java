package UIComponents;

import Main.GlobalVariables;
import Objects.AbstractTile;
import Objects.Player;

import javax.swing.*;
import java.awt.*;

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
     * Draws the abbot in the appropriate location based on the tile rotation
     * @param tileRotation the tile's current position
     */
    private void drawAbbot(int tileRotation) {
        int margin = 10;
        int half = AbstractTile.TILE_PIXEL_SIZE / 2 - BUTTON_SIZE / 2;
        if(tileRotation == 0) {
            this.setBounds(10, half, BUTTON_SIZE, BUTTON_SIZE);
        }
        else if(tileRotation == 1) {
            this.setBounds(half, 10, BUTTON_SIZE, BUTTON_SIZE);
        }
        else if(tileRotation == 2) {
            this.setBounds(AbstractTile.TILE_PIXEL_SIZE - margin - BUTTON_SIZE, half, BUTTON_SIZE, BUTTON_SIZE);
        }
        else if(tileRotation == 3) {
            this.setBounds(half, AbstractTile.TILE_PIXEL_SIZE - margin - BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
        }
        else
            this.setBounds(half, half, BUTTON_SIZE, BUTTON_SIZE);
    }
}
