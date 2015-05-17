package UIComponents;

import Main.GlobalVariables;
import Objects.Player;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a graphical button used to place an abbot, which is a special type of meeple
 * Created by robinsat on 5/17/2015.
 */
public class PlaceAbbotButton extends PlaceMeepleButton {

    public PlaceAbbotButton(GlobalVariables.Feature feature, GlobalVariables.Internal internal, Player player, GlobalVariables.Location location, int x, int y) {
        super(feature, internal, player, location, x, y);
    }
}
