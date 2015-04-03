import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Map;

/**
 * Created by robinsat on 4/3/2015.
 */
public class TileLabel extends JLabel {

    // Stores a tile that this graphical object represents
    private AbstractTile tile;

    // The size in pixels that these labels should be rendered
    public static final int TILE_PIXEL_SIZE = 300;

    // The coordinates of this tile
    private int x;
    private int y;

    //Constructor
    public TileLabel(AbstractTile tile, int x, int y) {
        this.x = x;
        this.y = y;
        this.tile = tile;

        this.setOpaque(true);
        this.setBounds(x * TILE_PIXEL_SIZE, y * TILE_PIXEL_SIZE, TILE_PIXEL_SIZE, TILE_PIXEL_SIZE);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //TODO: This is just for testing. Eventually, remove this text and replace with a picture
        Map<GlobalVariables.Direction, GlobalVariables.Feature> features = this.tile.getFeatures();

        String labeltext = "N: " + (features.get(GlobalVariables.Direction.NORTH) == null? null : features.get(GlobalVariables.Direction.NORTH)) + "\n";
        labeltext += "E; " + (features.get(GlobalVariables.Direction.EAST) == null? null : features.get(GlobalVariables.Direction.EAST)) + "\n";
        labeltext += "W; " + (features.get(GlobalVariables.Direction.WEST) == null? null : features.get(GlobalVariables.Direction.WEST)) + "\n";
        labeltext += "S; " + (features.get(GlobalVariables.Direction.SOUTH) == null? null : features.get(GlobalVariables.Direction.SOUTH)) + "\n";

        this.setText(labeltext);
    }
}
