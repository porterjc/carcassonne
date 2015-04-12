import javax.swing.*;
import java.awt.*;

/**
 * Created by robinsat on 3/31/2015.
 */
public class TileGrid extends JPanel {

    // The minimum x value of the tiles on this grid
    private int minX;
    // The minimum y value of the tiles on this grid
    private int minY;
    // The maximum x value of the tiles on this grid
    private int maxX;
    // The maximum y value of the tiles on this grid
    private int maxY;

    //Provides easy storage for the panel's preferred width
    private int panelWidth;
    //Provides easy storage for the panel's preferred width
    private int panelHeight;

    // The game that this grid is keeping track of.
    // TODO: this should be private
    public Game game;


    public TileGrid() {
        super();
        this.setBackground(new Color(39, 40, 49));
        this.setLayout(new GridLayout());
        panelWidth = 900;
        panelHeight = 900;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

        minX = 0;
        minY = 0;
        maxX = 2;
        maxY = 2;

        // Tiles will be manually placed on this grid
        this.setLayout(null);
        //Add starting tile
        //addTile(new OpenTile(), 1, 1);


    }

    public void addTile(AbstractTile newTile, int x, int y, boolean addAround) {
        if(x > maxX) {
            growRight(x);
        }
        else if (x < 0) {
            growLeft(x);
            x = 0;
        }

        if(y > maxY) {
            growDown(y);
        }
        else if (y < minY) {
            growUp(y);
            y = 0;
        }

        this.add(new TileLabel(newTile, x, y));

        if(addAround) {
            OpenTile top = new OpenTile();
            addTile(top, x, y - 1, false);
            newTile.setTop(top);

            OpenTile bottom = new OpenTile();
            addTile(bottom, x, y + 1, false);
            newTile.setBottom(bottom);

            OpenTile left = new OpenTile();
            addTile(left, x - 1, y, false);
            newTile.setLeft(left);

            OpenTile right = new OpenTile();
            addTile(right, x + 1, y, false);
            newTile.setRight(right);

            game.drawTile();
        }

       // tileList.add(newTile);
        //if(newTile.getxVal() < offsetX)
          //  growLeft();
    }

    private void growRight(int x) {
        panelWidth += (x - maxX) * TileLabel.TILE_PIXEL_SIZE;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        maxX++;
    }

    private void growLeft(int x) {
        panelWidth += Math.abs(x) * TileLabel.TILE_PIXEL_SIZE;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

        for(Component component : this.getComponents()) {
            component.setBounds(component.getX() + TileLabel.TILE_PIXEL_SIZE, component.getY(), TileLabel.TILE_PIXEL_SIZE, TileLabel.TILE_PIXEL_SIZE);
        }
    }

    private void growDown(int y) {
        panelHeight += (y - maxY) * TileLabel.TILE_PIXEL_SIZE;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        maxY++;
    }

    private void growUp(int y) {
        panelHeight += Math.abs(y) * TileLabel.TILE_PIXEL_SIZE;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

        for(Component component : this.getComponents()) {
            component.setBounds(component.getX(), component.getY() + TileLabel.TILE_PIXEL_SIZE, TileLabel.TILE_PIXEL_SIZE, TileLabel.TILE_PIXEL_SIZE);

        }
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
