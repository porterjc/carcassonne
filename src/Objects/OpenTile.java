package Objects;

import Main.GlobalVariables;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by johnsoaa on 3/31/2015.
 */
public class OpenTile extends AbstractTile implements MouseListener {

    public OpenTile() {
        super();
        this.addMouseListener(this);
    }

    public OpenTile(AbstractTile left, AbstractTile right, AbstractTile top, AbstractTile bottom) {
        super(left, right, top, bottom);
        this.drawSelf();
        this.addMouseListener(this);
    }

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

    @Override
    public void drawSelf() {
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    public boolean canPlace(PlayableTile tileToPlace, boolean riverMode) {
        //boolean can = checkEast(tileToPlace) && checkWest(tileToPlace) && checkNorth(tileToPlace) && checkSouth(tileToPlace);
        boolean e = checkEast(tileToPlace);
        boolean w = checkWest(tileToPlace);
        boolean n = checkNorth(tileToPlace);
        boolean s = checkSouth(tileToPlace);

        return e && w && n && s;
    }

    public boolean checkNorth(PlayableTile tile) {
        //Map<Main.GlobalVariables.Direction, Main.GlobalVariables.Feature> tileFeatures = tile.getFeatures();
        GlobalVariables.Feature tileFeature = tile.getTargetFeature(GlobalVariables.Direction.NORTH);
        if (getTop().getFeatures() == null) {
            System.out.println("Top was null");
            return true;
        }
        else {
            boolean val = tileFeature == getTop().getTargetFeature(GlobalVariables.Direction.SOUTH);
           // if (!val)
                System.out.println("This top: " + tileFeature + " Bottom on tile above: " + getTop().getTargetFeature(GlobalVariables.Direction.SOUTH));
            //return tileFeatures.get(Main.GlobalVariables.Direction.NORTH) == getTop().getFeatures().get(Main.GlobalVariables.Direction.SOUTH);
            return val;
        }

    }

    public boolean checkSouth(PlayableTile tile) {
        //Map<Main.GlobalVariables.Direction, Main.GlobalVariables.Feature> tileFeatures = tile.getFeatures();
        GlobalVariables.Feature tileFeature = tile.getTargetFeature(GlobalVariables.Direction.SOUTH);
        if (getBottom().getFeatures() == null) {
            System.out.println("Bottom was null");
            return true;
        }
        else {
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
        }
        else {
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
        }
        else {
            boolean val = tileFeature == getRight().getTargetFeature(GlobalVariables.Direction.WEST);
            //return tileeFeatures.get(Main.GlobalVariables.Direction.EAST) == getRight().getFeatures().get(Main.GlobalVariables.Direction.WEST);
            //if (!val)
                System.out.println("This right: " + tileFeature + " Left on tile to right: " + getRight().getTargetFeature(GlobalVariables.Direction.WEST));
            System.out.println(getRight());
            return val;
        }

    }

    @Override
    public Pair<HashSet<Meeple>, Integer> scoreCity(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples, boolean completion) {
        return new Pair(meeples, -1);
    }

    @Override
    public boolean traceField(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from, Set<Meeple> farmers, Set<Integer> cities, boolean gameOver) {
        return false;
    }


    @Override
    public GlobalVariables.Direction addTile(AbstractTile tile) {
        GlobalVariables.Direction direction = super.addTile(tile);
        //TODO: remove self from list
        return direction;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TileGrid grid = (TileGrid) this.getParent();
        if(!grid.getGame().canAdjustTile())
            return;
        PlayableTile current = grid.getGame().getCurrentTile();

        if (this.canPlace(current, false)) {
            GlobalVariables.Direction direction = this.addTile(current);
            if (direction != null) {
                grid.addNullRow(direction);
            }
            current.addMeepleButtons(grid.getGame().getCurrentTurnPlayer(), grid.getGame().isAbbotMode());
            grid.revalidate();
            grid.repaint();

            grid.getGame().moveToNextState();

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
}
