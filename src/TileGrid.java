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

    // The tile on the top left corner of this grid
    private AbstractTile topLeft;
    // The tile on the top right corner of this grid
    private AbstractTile topRight;
    // The tile on the bottom left corner of this grid
    private AbstractTile bottomLeft;
    // The tile on the bottom right corner of this grid
    private AbstractTile bottomRight;

    //Provides easy storage for the panel's preferred width
    private int panelWidth;
    //Provides easy storage for the panel's preferred width
    private int panelHeight;

    // The game that this grid is keeping track of.
    // TODO: this should be private
    public Game game;

    //Default, for testing purposes only
    public TileGrid() {
        this(900, 900);
    }

    /**
     * Constructor
     * @param x the width of this panel
     * @param y the height of this panel
     */
    public TileGrid(int x, int y) {
        super();
        this.setBackground(new Color(39, 40, 49));
        this.setLayout(new GridLayout());
        panelWidth = x;
        panelHeight = y;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

        // Tiles will be manually placed on this grid
        this.setLayout(null);
        //Add starting tile
        //addTile(new OpenTile(), 1, 1);


    }

    /**
     * Initializes this grid to display basic information
     * @param startingTile
     */
    public void initialize(PlayableTile startingTile) {
        minX = 0;
        minY = 0;
        maxX = 2;
        maxY = 2;

        topRight = startingTile;
        topLeft = startingTile;
        bottomLeft = startingTile;
        bottomRight = startingTile;
        addLeftColumn();
        addRightColumn();
        addBottomRow();
        addTopRow();

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

    /**
     * Adds a column of null tiles to the right of the grid
     */
    private void addRightColumn() {
        AbstractTile existingToLeft = topRight;
        AbstractTile existingAbove = new NullTile();
        existingAbove.setLeft(existingToLeft);
        topRight = existingAbove;

        while(existingToLeft != null) {
            NullTile newTile = new NullTile();
            newTile.setTop(existingAbove);
            newTile.setLeft(existingToLeft);
            existingAbove = newTile;
            existingToLeft = existingToLeft.getBottom();
        }
        bottomRight = existingAbove;
    }

    /**
     * Adds a column of null tiles to the left of the grid
     */
    private void addLeftColumn() {
        AbstractTile existingToRight = topLeft;
        AbstractTile existingAbove= new NullTile();
        existingAbove.setRight(existingToRight);
        topLeft = existingAbove;

        while(existingToRight != null) {
            NullTile newTile = new NullTile();
            newTile.setTop(existingAbove);
            newTile.setRight(existingToRight);
            existingAbove = newTile;
            existingToRight = existingToRight.getBottom();
        }

        bottomLeft = existingAbove;
    }

    /**
     * Adds a row of null tiles to the bottom of the grid
     */
    private void addBottomRow() {
        AbstractTile existingAbove = bottomLeft;
        AbstractTile existingToLeft = new NullTile();
        existingToLeft.setTop(existingAbove);
        bottomLeft = existingToLeft;

        while(existingAbove != null) {
            NullTile newTile = new NullTile();
            newTile.setTop(existingAbove);
            newTile.setLeft(existingToLeft);
            existingToLeft = newTile;
            existingAbove = existingAbove.getRight();
        }

        bottomRight = existingToLeft;
    }

    /**
     * Adds a row of null tiles to the top of the grid
     */
    private void addTopRow() {
        AbstractTile existingBelow = topLeft;
        AbstractTile existingToLeft = new NullTile();
        existingToLeft.setBottom(existingBelow);
        topLeft = existingToLeft;

        while(existingBelow != null) {
            NullTile newTile = new NullTile();
            newTile.setBottom(existingBelow);
            newTile.setLeft(existingToLeft);
            existingToLeft = newTile;
            existingBelow = existingBelow.getRight();
        }

       topRight = existingToLeft;
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
