import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by robinsat on 4/3/2015.
 */
public class TileLabel extends JLabel {

    // Stores a tile that this graphical object represents
    private AbstractTile tile;

    // The size in pixels that these labels should be rendered
    private static final int TILE_PIXEL_SIZE = 300;

    // The coordinates of this tile
    private int x;
    private int y;

    //Constructor
    public TileLabel(int x, int y) {
        this.x = x;
        this.y = y;
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
