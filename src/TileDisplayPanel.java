import javax.swing.*;

/**
 * Created by robinsat on 3/31/2015.
 */
public class TileDisplayPanel extends JPanel {

    private final int SIZE = 300;

    //The tile that is currently being displayed in this panel
    //private AbstractTile tile;
    //The display panel to the right of this one
    private TileDisplayPanel right;
    //The display panel below this one
    private TileDisplayPanel down;

    public TileDisplayPanel() {
        super();

    }
}
