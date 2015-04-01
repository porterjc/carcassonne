import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by robinsat on 3/31/2015.
 */
public class TileGrid extends JPanel {

    // The number of rows this grid currently has
    private int numRows;
    // The number of columns this grid currently has
    private int numCols;
    // This is the current smallest x value of the grid coordinates
    private int offsetX;
    // This is the current smallest y value of the grid coordinates
    private int offsetY;
    // A list of all tiles that need to be rendered on the page. For graphical purposes only
    private ArrayList<AbstractTile> tileList;


    public TileGrid() {
        super();
        this.setBackground(Color.blue);
        numRows = 3;
        numCols = 3;

        offsetX = -1;
        offsetY = -1;
        tileList = new ArrayList<AbstractTile>();

    }

    public void addTile(AbstractTile newTile) {
        tileList.add(newTile);
        if(newTile.getxVal() < offsetX)
            offsetX = newTile.getxVal();
    }

    // Draws all of the tiles curretnly on the grid
    @Override
    public void paintComponent(Graphics g) {

        for (AbstractTile tile : tileList) {
            tile.draw(g, offsetX, offsetY);
        }
    }

}
