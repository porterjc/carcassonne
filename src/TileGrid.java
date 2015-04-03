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
    //private int offsetX;
    // This is the current smallest y value of the grid coordinates
    //private int offsetY;
    // A list of all tiles that need to be rendered on the page. For graphical purposes only
   // private ArrayList<AbstractTile> tileList;

    //Provides easy storage for the panel's preferred width
    private int panelWidth;
    //Provides easy storage for the panel's preferred width
    private int panelHeight;


    public TileGrid() {
        super();
        this.setBackground(Color.blue);
        this.setLayout(new GridLayout());
        panelWidth = 900;
        panelHeight = 900;

        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        numRows = 3;
        numCols = 3;

        // Tiles will be manually placed on this grid
        this.setLayout(null);
        addTile(new OpenTile(), 1, 1);

       // tileList = new ArrayList<AbstractTile>();

    }

    public void addTile(AbstractTile newTile, int x, int y) {
        if(x >= numCols) {
            panelWidth += (x - numCols + 1) * TileLabel.TILE_PIXEL_SIZE;
            this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        }
        if(y >= numRows) {
            panelHeight += (y - numRows + 1) * TileLabel.TILE_PIXEL_SIZE;
            this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        }
        this.add(new TileLabel(newTile, x, y));

       // tileList.add(newTile);
        //if(newTile.getxVal() < offsetX)
          //  growLeft();
    }

    private void growLeft() {
       // offsetX--;
       // this.setPreferredSize(new Dimension(this.getWidth() + AbstractTile.SIZE, this.getHeight()));
    }


    // Draws all of the tiles curretnly on the grid
   /* @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (AbstractTile tile : tileList) {
            tile.draw(g, offsetX, offsetY);
        }
    }*/

}
