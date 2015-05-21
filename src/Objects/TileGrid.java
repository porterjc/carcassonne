package Objects;

import Main.GlobalVariables;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by robinsat on 3/31/2015.
 * <p/>
 * This keeps track of all of the tiles in the game and displays them on a panel
 */
public class TileGrid extends JPanel {

    // The tile on the top left corner of this grid
    private AbstractTile topLeft;
    // The tile on the top right corner of this grid
    private AbstractTile topRight;
    // The tile on the bottom left corner of this grid
    private AbstractTile bottomLeft;
    // bottomRight is the only one we don't need

    private ArrayList<OpenTile> slots;
    // List of open "slots" that playable tiles can be placed

    //Provides easy storage for the panel's preferred width
    private int panelWidth;
    //Provides easy storage for the panel's preferred width
    private int panelHeight;

    // The game that this grid is keeping track of.
    private Game game;

    //Default, for testing purposes only
    public TileGrid() {
        this(900, 900);
        this.slots = new ArrayList<OpenTile>();
    }

    /**
     * Constructor
     *
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
       // addTile(new OpenTile(), 1, 1);

        this.slots = new ArrayList<OpenTile>();
    }

    /**
     * Initializes this grid to display basic information
     *
     * @param startingTile the tile at the center at the beginning of the game.
     */
    public void initialize(PlayableTile startingTile) {

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
        for (int i = 1; i < numTilesY; i++) {
            AbstractTile newTile = new NullTile();
            newTile.moveTile(xMargin, yMargin + (i * AbstractTile.TILE_PIXEL_SIZE));
            this.add(newTile);
            newTile.setTop(upperTile);

            if (i == flagY)
                flaggedTile = newTile;

            upperTile = newTile;
        }

        bottomLeft = upperTile;

        //Expand the grid right
        for (int i = 1; i < numTilesX; i++) {
            addRightColumn();
            if (i <= flagX)
                flaggedTile = flaggedTile.getRight();
        }

        flaggedTile.addTile(startingTile);
        panelWidth = fixedWidth;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

    }

    /**
     * Getter method for the game associated with this component
     * @return the game associated with this component
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Setter method for the game associated with this component
     * @param game the game associated with this component
     */
    public void setGame(Game game) {
        this.game = game;
    }

    public void addNullRow(GlobalVariables.Direction dir) {
        if (dir == GlobalVariables.Direction.NORTH)
            addTopRow();
        else if (dir == GlobalVariables.Direction.EAST)
            addRightColumn();
        else if (dir == GlobalVariables.Direction.WEST)
            addLeftColumn();
        else if (dir == GlobalVariables.Direction.SOUTH)
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

        while (existingToLeft != null) {
            NullTile newTile = new NullTile();
            newTile.setTop(existingAbove);
            newTile.setLeft(existingToLeft);
            newTile.moveTile(existingAbove.getX(), existingToLeft.getY());
            this.add(newTile);
            existingAbove = newTile;
            existingToLeft = existingToLeft.getBottom();
        }

        panelWidth += AbstractTile.TILE_PIXEL_SIZE;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
    }

    /**
     * Adds a column of null tiles to the left of the grid
     */
    private void addLeftColumn() {

        panelWidth += AbstractTile.TILE_PIXEL_SIZE;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

        for (Component component : this.getComponents()) {
            component.setBounds(component.getX() + AbstractTile.TILE_PIXEL_SIZE, component.getY(), AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE);
        }

        AbstractTile existingToRight = topLeft;
        AbstractTile existingAbove = new NullTile();
        existingAbove.setRight(existingToRight);
        existingAbove.moveTile(existingToRight.getX() - AbstractTile.TILE_PIXEL_SIZE, existingToRight.getY());
        this.add(existingAbove);
        existingToRight = existingToRight.getBottom();
        topLeft = existingAbove;

        while (existingToRight != null) {
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
        AbstractTile existingAbove = bottomLeft;
        AbstractTile existingToLeft = new NullTile();
        existingToLeft.setTop(existingAbove);
        existingToLeft.moveTile(existingAbove.getX(), existingAbove.getY() + AbstractTile.TILE_PIXEL_SIZE);
        this.add(existingToLeft);
        existingAbove = existingAbove.getRight();
        bottomLeft = existingToLeft;

        while (existingAbove != null) {
            NullTile newTile = new NullTile();
            newTile.setTop(existingAbove);
            newTile.setLeft(existingToLeft);
            newTile.moveTile(existingAbove.getX(), existingToLeft.getY());
            this.add(newTile);
            existingToLeft = newTile;
            existingAbove = existingAbove.getRight();
        }

        panelHeight += AbstractTile.TILE_PIXEL_SIZE;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
    }

    /**
     * Adds a row of null tiles to the top of the grid
     */
    private void addTopRow() {

        panelHeight += AbstractTile.TILE_PIXEL_SIZE;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

        for (Component component : this.getComponents()) {
            component.setBounds(component.getX(), component.getY() + AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE);
        }

        AbstractTile existingBelow = topLeft;
        AbstractTile existingToLeft = new NullTile();
        existingToLeft.setBottom(existingBelow);
        existingToLeft.moveTile(existingBelow.getX(), existingBelow.getY() - AbstractTile.TILE_PIXEL_SIZE);
        this.add(existingToLeft);
        existingBelow = existingBelow.getRight();
        topLeft = existingToLeft;

        while (existingBelow != null) {
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

    /**
     * Returns the arraylist of opentiles on the board
     */
    public ArrayList<OpenTile> getSlots(){
        return slots;
    }

    /**
     * adds a opentile to the arraylists of opentiles currently in the game
     * @param tile
     */
    public  void addSlot(OpenTile tile){
        slots.add(tile);
    }

    /**
     * adds a opentile to the arraylists of opentiles currently in the game
     * @param tile
     */
    public  void removeSlot(OpenTile tile){
        slots.remove(slots.indexOf(tile));
    }

    /**
     * Takes a playable tile and checks that it can be placed where one of the OpeTile's currently exist.
     *
     * @param tile
     * @return
     */
    public boolean areValidMoves(PlayableTile tile) {
        for (OpenTile temp : slots) {
            if (temp.canPlace(tile))
                return true;
            tile.rotateTile();
            if (temp.canPlace(tile))
                return true;
            tile.rotateTile();
            if (temp.canPlace(tile))
                return true;
            tile.rotateTile();
            if (temp.canPlace(tile))
                return true;
            tile.rotateTile();
        }
        return false;
    }
}
