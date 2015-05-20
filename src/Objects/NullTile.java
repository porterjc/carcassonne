package Objects;

import Main.GlobalVariables;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A tile representing an empty space in the grid.
 * Created by robinsat on 4/14/2015.
 */
public class NullTile extends AbstractTile {

    /**
     * Constructor
     */
    public NullTile() {
        super();
        this.setSize(AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE);
        this.drawSelf();
        this.setVisible(true);
    }


    /**
     * Draws an empty square outlined in light blue to represent this tile
     */
    @Override
    public void drawSelf() {
        this.setBorder(BorderFactory.createLineBorder(new Color(80, 90, 115)));
    }

}
