import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

/**
 * Created by robinsat on 4/3/2015.
 */
public class TileLabel extends JLabel implements MouseListener{

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
        addMouseListener(this);

        resetView();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        PlayableTile current = ((TileGrid) this.getParent()).game.getCurrentTile();
        if(this.tile instanceof OpenTile && ((OpenTile)this.tile).canPlace(current)) {
            this.tile = current;
            resetView();
            ((TileGrid) this.getParent()).game.drawTile();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void resetView() {
        //TODO: This is just for testing. Eventually, remove this text and replace with a picture
        Map<GlobalVariables.Direction, GlobalVariables.Feature> features = this.tile.getFeatures();

        String labeltext = "N: " + (features.get(GlobalVariables.Direction.NORTH) == null? null : features.get(GlobalVariables.Direction.NORTH)) + " ";
        labeltext += "E; " + (features.get(GlobalVariables.Direction.EAST) == null? null : features.get(GlobalVariables.Direction.EAST)) + " ";
        labeltext += "W; " + (features.get(GlobalVariables.Direction.WEST) == null? null : features.get(GlobalVariables.Direction.WEST)) + " ";
        labeltext += "S; " + (features.get(GlobalVariables.Direction.SOUTH) == null? null : features.get(GlobalVariables.Direction.SOUTH)) + " ";

        this.setText(labeltext);
    }
}
