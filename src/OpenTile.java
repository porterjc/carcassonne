import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by johnsoaa on 3/31/2015.
 */
public class OpenTile extends AbstractTile {

    public OpenTile() {
        super();
    }

    public OpenTile(AbstractTile left, AbstractTile right, AbstractTile top, AbstractTile bottom) {
        super(left, right, top, bottom);
        this.drawSelf();
    }

    @Override
    public GlobalVariables.Direction updateAdjacent() {
        if (getTop() == null)
            return GlobalVariables.Direction.NORTH;

        else if (getLeft() == null)
            return GlobalVariables.Direction.WEST;

        else if (getRight() == null)
            return GlobalVariables.Direction.EAST;

        else if (getLeft() == null)
            return GlobalVariables.Direction.SOUTH;

        return null;
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public void drawSelf() {
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }


    public boolean canPlace(PlayableTile tileToPlace) {
        //boolean can = checkEast(tileToPlace) && checkWest(tileToPlace) && checkNorth(tileToPlace) && checkSouth(tileToPlace);
        boolean e = checkEast(tileToPlace);
        boolean w = checkWest(tileToPlace);
        boolean n = checkNorth(tileToPlace);
        boolean s = checkSouth(tileToPlace);

        System.out.println("E: " + e + "W: " + w + "N: " + n + "S: " + s);
        return e && w && n && s;
    }

    public boolean checkNorth(PlayableTile tile) {
        //Map<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = tile.getFeatures();
        GlobalVariables.Feature tileFeature = tile.getTargetFeature(GlobalVariables.Direction.NORTH);
        if (getTop().getFeatures() == null) return true;
        else {
            boolean val = tileFeature == getTop().getTargetFeature(GlobalVariables.Direction.SOUTH);
            if (!val)
                System.out.println(tileFeature + " " + getTop().getTargetFeature(GlobalVariables.Direction.SOUTH));
            //return tileFeatures.get(GlobalVariables.Direction.NORTH) == getTop().getFeatures().get(GlobalVariables.Direction.SOUTH);
            return val;
        }

    }

    public boolean checkSouth(PlayableTile tile) {
        //Map<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = tile.getFeatures();
        GlobalVariables.Feature tileFeature = tile.getTargetFeature(GlobalVariables.Direction.SOUTH);
        if (getBottom().getFeatures() == null) return true;
        else {
            boolean val = tileFeature == getBottom().getTargetFeature(GlobalVariables.Direction.NORTH);
            if (!val)
                System.out.println(tileFeature + " " + getBottom().getTargetFeature(GlobalVariables.Direction.NORTH));
            //return tileFeatures.get(GlobalVariables.Direction.SOUTH) == getBottom().getFeatures().get(GlobalVariables.Direction.NORTH);
            return val;
        }
    }

    public boolean checkWest(PlayableTile tile) {
       // Map<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = tile.getFeatures();
        GlobalVariables.Feature tileFeature = tile.getTargetFeature(GlobalVariables.Direction.WEST);
        if (getLeft().getFeatures() == null) return true;
        else {
            boolean val = tileFeature == getLeft().getTargetFeature(GlobalVariables.Direction.EAST);
            //return tileFeatures.get(GlobalVariables.Direction.WEST) == getLeft().getFeatures().get(GlobalVariables.Direction.EAST);
            if (!val)
                System.out.println(tileFeature + " " + getLeft().getTargetFeature(GlobalVariables.Direction.EAST));
            return val;
        }

    }

    public boolean checkEast(PlayableTile tile) {
        //Map<GlobalVariables.Direction, GlobalVariables.Feature> tileeFeatures = tile.getFeatures();
        GlobalVariables.Feature tileFeature = tile.getTargetFeature(GlobalVariables.Direction.EAST);
        if (getRight().getFeatures() == null) return true;
        else {
            boolean val = tileFeature == getRight().getTargetFeature(GlobalVariables.Direction.WEST);
            //return tileeFeatures.get(GlobalVariables.Direction.EAST) == getRight().getFeatures().get(GlobalVariables.Direction.WEST);
            if (!val)
                System.out.println(tileFeature + " " + getRight().getTargetFeature(GlobalVariables.Direction.WEST));
            System.out.println(getRight());
            return val;
        }

    }

    @Override
    public Pair<ArrayList<Meeple>, Integer> scoreRoad(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples) {
        return new Pair(meeples, -1);
    }

    @Override
    public int scoreCity(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples, boolean completion) {
        return -1;
    }

    @Override
    public boolean findFarmer(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from) {
        return false;
    }
}
