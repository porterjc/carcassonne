import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    private AbstractTile bottomRight;//TODO initialize & use this variable

    //Provides easy storage for the panel's preferred width
    private int panelWidth;
    //Provides easy storage for the panel's preferred width
    private int panelHeight;

    private JLabel currentTileLabel;
    private JLabel tilesLeftLabel;

    // The game that this grid is keeping track of.
    // TODO: this should be private
    private Game game;

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
        this.setBackground(GlobalVariables.DARK_BLUE);
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
      /*  minX = 0;
        minY = 0;
        maxX = 2;
        maxY = 2; */

        game = new Game();

        // Calculate the number of tiles to start with, make sure it's an odd, positive number so it looks pretty
        int numTilesX = panelWidth / AbstractTile.TILE_PIXEL_SIZE;
        if (numTilesX < 5) {
            panelWidth += (5 - numTilesX) * AbstractTile.TILE_PIXEL_SIZE;
            numTilesX = 5;
        }
        if (numTilesX % 2 == 0)
            numTilesX--;

        int numTilesY = panelHeight / AbstractTile.TILE_PIXEL_SIZE;
        if (numTilesY < 5) {
            panelHeight += (5 - numTilesY) * AbstractTile.TILE_PIXEL_SIZE;
            numTilesY = 5;
        }
        if (numTilesY % 2 == 0)
            numTilesY--;

        int xMargin = Math.abs((panelWidth - (numTilesX * AbstractTile.TILE_PIXEL_SIZE)) / 2);
        int yMargin = Math.abs((panelHeight - (numTilesY * AbstractTile.TILE_PIXEL_SIZE)) / 2);

        int fixedWidth = panelWidth;
        int flagX = numTilesX / 2;
        int flagY = numTilesY / 2;

        //Add tile in top left corner
        AbstractTile upperTile = new NullTile();
        upperTile.moveTile(xMargin, yMargin);
        this.add(upperTile);
        topLeft = upperTile;
        topRight = upperTile;

        // The tile to flag to place the starting tile
        AbstractTile flaggedTile = upperTile;

        // Populate the leftmost column
        for(int i = 1; i < numTilesY; i++) {
            AbstractTile newTile = new NullTile();
            newTile.moveTile(xMargin, yMargin + (i * AbstractTile.TILE_PIXEL_SIZE));
            this.add(newTile);
            newTile.setTop(upperTile);

            if(i == flagY)
                flaggedTile = newTile;

            upperTile = newTile;
        }

        bottomLeft = upperTile;
        bottomRight = upperTile;

        //Expand the grid right
        for(int i = 1; i < numTilesX; i++) {
            addRightColumn();
            if(i <= flagX)
                flaggedTile = flaggedTile.getRight();
        }

        flaggedTile.addTile(startingTile);
        panelWidth = fixedWidth;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

        //this.add(startingTile);
        //startingTile.setBounds(AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE);
        //startingTile.setVisible(true);

       /* AbstractTile tile1 = new NullTile();
        this.add(tile1);
        tile1.moveTile(AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE);

        AbstractTile tile2 = new NullTile();
        this.add(tile2);
        tile2.moveTile(AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE * 2);

        AbstractTile tile3 = new NullTile();
        this.add(tile3);
        tile3.moveTile(AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE * 3);

        tile1.setBottom(tile2);
        tile2.setBottom(tile3);*/

        /*topRight = tile1;
        topLeft = tile1;
        bottomLeft = tile3;
        bottomRight = tile3;*/

        //addRightColumn();
        //addRightColumn();

       // addLeftColumn();
        //addRightColumn();
        //addBottomRow();
        //addTopRow();

    }

    public Game getGame() { return this.game; }


    public void addNullRow(GlobalVariables.Direction dir) {
        if(dir == GlobalVariables.Direction.NORTH)
            addTopRow();
        else if(dir == GlobalVariables.Direction.EAST)
            addRightColumn();
        else if(dir == GlobalVariables.Direction.WEST)
            addLeftColumn();
        else if(dir == GlobalVariables.Direction.SOUTH)
            addBottomRow();
    }

    /**
     * Adds a column of null tiles to the right of the grid
     */
    private void addRightColumn() {
        AbstractTile existingToLeft = topRight;
        AbstractTile existingAbove = new NullTile();
        existingAbove.setLeft(existingToLeft);
        existingAbove.moveTile(existingToLeft.getX() + AbstractTile.TILE_PIXEL_SIZE, existingToLeft.getY());
        this.add(existingAbove);
        existingToLeft = existingToLeft.getBottom();
        topRight = existingAbove;

        while(existingToLeft != null) {
            NullTile newTile = new NullTile();
            newTile.setTop(existingAbove);
            newTile.setLeft(existingToLeft);
            newTile.moveTile(existingAbove.getX(), existingToLeft.getY());
            this.add(newTile);
            existingAbove = newTile;
            existingToLeft = existingToLeft.getBottom();
        }

        bottomRight = existingAbove;
        panelWidth +=  AbstractTile.TILE_PIXEL_SIZE;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
    }

    /**
     * Adds a column of null tiles to the left of the grid
     */
    private void addLeftColumn() {

        panelWidth += AbstractTile.TILE_PIXEL_SIZE;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

        for(Component component : this.getComponents()) {
            component.setBounds(component.getX() + AbstractTile.TILE_PIXEL_SIZE, component.getY(), AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE);
        }

        AbstractTile existingToRight = topLeft;
        AbstractTile existingAbove= new NullTile();
        existingAbove.setRight(existingToRight);
        existingAbove.moveTile(existingToRight.getX() - AbstractTile.TILE_PIXEL_SIZE, existingToRight.getY());
        this.add(existingAbove);
        existingToRight = existingToRight.getBottom();
        topLeft = existingAbove;

        while(existingToRight != null) {
            NullTile newTile = new NullTile();
            newTile.setTop(existingAbove);
            newTile.setRight(existingToRight);
            newTile.moveTile(existingAbove.getX(), existingToRight.getY());
            this.add(newTile);
            existingAbove = newTile;
            existingToRight = existingToRight.getBottom();
        }

        bottomLeft = existingAbove;
    }

    /**
     * Adds a row of null tiles to the bottom of the grid
     */
    private void addBottomRow() {
        System.out.println("Heya");
        AbstractTile existingAbove = bottomLeft;
        AbstractTile existingToLeft = new NullTile();
        existingToLeft.setTop(existingAbove);
        existingToLeft.moveTile(existingAbove.getX(), existingAbove.getY() + AbstractTile.TILE_PIXEL_SIZE);
        this.add(existingToLeft);
        existingAbove = existingAbove.getRight();
        bottomLeft = existingToLeft;

        while(existingAbove != null) {
            NullTile newTile = new NullTile();
            newTile.setTop(existingAbove);
            newTile.setLeft(existingToLeft);
            newTile.moveTile(existingAbove.getX(), existingToLeft.getY());
            this.add(newTile);
            existingToLeft = newTile;
            existingAbove = existingAbove.getRight();
        }

        bottomRight = existingToLeft;
        System.out.println("ph: " + panelHeight + " h: " + this.getHeight());
        panelHeight += AbstractTile.TILE_PIXEL_SIZE;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        System.out.println("newph: " + panelHeight + " newh: " + this.getHeight());
    }

    /**
     * Adds a row of null tiles to the top of the grid
     */
    private void addTopRow() {

        panelHeight += AbstractTile.TILE_PIXEL_SIZE;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

        for(Component component : this.getComponents()) {
            component.setBounds(component.getX(), component.getY() + AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE);
        }

        AbstractTile existingBelow = topLeft;
        AbstractTile existingToLeft = new NullTile();
        existingToLeft.setBottom(existingBelow);
        existingToLeft.moveTile(existingBelow.getX(), existingBelow.getY() - AbstractTile.TILE_PIXEL_SIZE );
        this.add(existingToLeft);
        existingBelow = existingBelow.getRight();
        topLeft = existingToLeft;

        while(existingBelow != null) {
            NullTile newTile = new NullTile();
            newTile.setBottom(existingBelow);
            newTile.setLeft(existingToLeft);
            newTile.moveTile(existingBelow.getX(), existingToLeft.getY());
            this.add(newTile);
            existingToLeft = newTile;
            existingBelow = existingBelow.getRight();
        }

        topRight = existingToLeft;

        revalidate();
        repaint();
    }

    public void setTileLabels(JLabel tileLabel, JLabel tilesLeftLabel, GraphicButton moveOnButton) {
        this.currentTileLabel = tileLabel;
        this.tilesLeftLabel = tilesLeftLabel;
    }

    public void updateCurrentTileUI() {
        this.currentTileLabel.setIcon(game.getCurrentTile().getIcon());
        this.tilesLeftLabel.setText(game.getNumberOfTilesLeft() + "");

    }
    public void updateTurnLabel() {
        this.turnLabel.setBackground(game.getCurrentTurnPlayer().getColor());
    }
    public boolean areValidMoves(ArrayList<OpenTile> slots, PlayableTile tile){
        for(int i = 0; i < slots.size(); i++){
            OpenTile temp = slots.get(i);
            if(temp.canPlace(tile))
                return true;
            tile.rotateTile();
            if(temp.canPlace(tile))
                return true;
            tile.rotateTile();
            if(temp.canPlace(tile))
                return true;
            tile.rotateTile();
            if(temp.canPlace(tile))
                return true;
            tile.rotateTile();
        }
        return false;
    }
}
