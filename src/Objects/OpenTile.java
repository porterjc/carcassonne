package Objects;

import Main.GlobalVariables;
import UIComponents.OpenTileMouseListener;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

/**
 * A tile representing an open space on the grid where a tile may be placed
 * Created by johnsoaa on 3/31/2015.
 */
public class OpenTile extends AbstractTile {

    /**
     * Constructor
     */
    public OpenTile() {
        super();
        this.addMouseListener(new OpenTileMouseListener(this));
    }

    /**
     * Constructor
     *
     * @param left   the tile to the left of this
     * @param right  the tile to the right of this
     * @param top    the tile above this
     * @param bottom the tile below this
     */
    public OpenTile(AbstractTile left, AbstractTile right, AbstractTile top, AbstractTile bottom) {
        super(left, right, top, bottom);
        this.drawSelf();
        this.addMouseListener(new OpenTileMouseListener(this));
    }

    /**
     * Called after addTile is called. If this is linked to nothing in any direction, expand the grid
     *
     * @return the direction to expand the grid
     */
    @Override
    public GlobalVariables.Direction updateAdjacent() {
        if (getTop() == null)
            return GlobalVariables.Direction.NORTH;

        else if (getLeft() == null)
            return GlobalVariables.Direction.WEST;

        else if (getRight() == null)
            return GlobalVariables.Direction.EAST;

        else if (getBottom() == null)
            return GlobalVariables.Direction.SOUTH;

        return null;
    }

    /**
     * Draws an empty square with a white border to represent this tile
     */
    @Override
    public void drawSelf() {
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    /**
     * Determines whether a tile may be placed over this one
     *
     * @param tileToPlace the tile to be placed over this one
     * @return whether or not the playable tile may be added over this one
     */
    public boolean canPlace(PlayableTile tileToPlace) {
        boolean e = checkEast(tileToPlace);
        boolean w = checkWest(tileToPlace);
        boolean n = checkNorth(tileToPlace);
        boolean s = checkSouth(tileToPlace);

        return e && w && n && s;
    }

    public boolean checkNorth(PlayableTile tile) {
        GlobalVariables.Feature tileFeature = tile.getTargetFeature(GlobalVariables.Direction.NORTH);
        if (getTop().getFeatures() == null) {
            System.out.println("Top was null");
            return true;
        } else {
            boolean val = tileFeature == getTop().getTargetFeature(GlobalVariables.Direction.SOUTH);
            System.out.println("This top: " + tileFeature + " Bottom on tile above: " + getTop().getTargetFeature(GlobalVariables.Direction.SOUTH));
            return val;
        }

    }

    public boolean checkSouth(PlayableTile tile) {
        //Map<Main.GlobalVariables.Direction, Main.GlobalVariables.Feature> tileFeatures = tile.getFeatures();
        GlobalVariables.Feature tileFeature = tile.getTargetFeature(GlobalVariables.Direction.SOUTH);
        if (getBottom().getFeatures() == null) {
            System.out.println("Bottom was null");
            return true;
        } else {
            boolean val = tileFeature == getBottom().getTargetFeature(GlobalVariables.Direction.NORTH);
            //  if (!val)
            System.out.println("This bottom: " + tileFeature + " Top on tile below: " + getBottom().getTargetFeature(GlobalVariables.Direction.NORTH));
            //return tileFeatures.get(Main.GlobalVariables.Direction.SOUTH) == getBottom().getFeatures().get(Main.GlobalVariables.Direction.NORTH);
            return val;
        }
    }

    public boolean checkWest(PlayableTile tile) {
        // Map<Main.GlobalVariables.Direction, Main.GlobalVariables.Feature> tileFeatures = tile.getFeatures();
        GlobalVariables.Feature tileFeature = tile.getTargetFeature(GlobalVariables.Direction.WEST);
        if (getLeft().getFeatures() == null) {
            System.out.println("Left was null");
            return true;
        } else {
            boolean val = tileFeature == getLeft().getTargetFeature(GlobalVariables.Direction.EAST);
            //return tileFeatures.get(Main.GlobalVariables.Direction.WEST) == getLeft().getFeatures().get(Main.GlobalVariables.Direction.EAST);
            //  if (!val)
            System.out.println("This left: " + tileFeature + " Right on tile to left: " + getLeft().getTargetFeature(GlobalVariables.Direction.EAST));
            return val;
        }

    }

    public boolean checkEast(PlayableTile tile) {
        //Map<Main.GlobalVariables.Direction, Main.GlobalVariables.Feature> tileeFeatures = tile.getFeatures();
        GlobalVariables.Feature tileFeature = tile.getTargetFeature(GlobalVariables.Direction.EAST);
        if (getRight().getFeatures() == null) {
            System.out.println("Right was null");
            return true;
        } else {
            boolean val = tileFeature == getRight().getTargetFeature(GlobalVariables.Direction.WEST);
            //return tileeFeatures.get(Main.GlobalVariables.Direction.EAST) == getRight().getFeatures().get(Main.GlobalVariables.Direction.WEST);
            //if (!val)
            System.out.println("This right: " + tileFeature + " Left on tile to right: " + getRight().getTargetFeature(GlobalVariables.Direction.WEST));
            return val;
        }

    }

    @Override
    public Pair<HashSet<Meeple>, Integer> scoreCity(Set<AbstractTile> alreadyVisited, HashSet<Meeple> meeples, boolean completion) {
        return new Pair(meeples, -1);
    }


    @Override
    public GlobalVariables.Direction addTile(AbstractTile tile) {
        GlobalVariables.Direction direction = super.addTile(tile);
        //TODO: remove self from list
        return direction;
    }

}
