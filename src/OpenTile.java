import javax.swing.*;
import java.awt.*;
import java.util.Map;
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
        return checkEast(tileToPlace) && checkWest(tileToPlace) && checkNorth(tileToPlace) && checkSouth(tileToPlace);
    }

    public boolean checkNorth(PlayableTile tile) {
        Map<GlobalVariables.Direction, GlobalVariables.Feature> tileeFeatures = tile.getFeatures();
        if (getTop().getFeatures() == null) return true;
        else
            return tileeFeatures.get(GlobalVariables.Direction.NORTH) == getTop().getFeatures().get(GlobalVariables.Direction.SOUTH);

    }

    public boolean checkSouth(PlayableTile tile) {
        Map<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = tile.getFeatures();
        if (getBottom().getFeatures() == null) return true;
        else
            return tileFeatures.get(GlobalVariables.Direction.SOUTH) == getBottom().getFeatures().get(GlobalVariables.Direction.NORTH);
    }

    public boolean checkWest(PlayableTile tile) {
        Map<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = tile.getFeatures();
        if (getLeft().getFeatures() == null) return true;
        else
            return tileFeatures.get(GlobalVariables.Direction.WEST) == getLeft().getFeatures().get(GlobalVariables.Direction.EAST);

    }

    public boolean checkEast(PlayableTile tile) {
        Map<GlobalVariables.Direction, GlobalVariables.Feature> tileeFeatures = tile.getFeatures();
        if (getRight().getFeatures() == null) return true;
        else
            return tileeFeatures.get(GlobalVariables.Direction.EAST) == getRight().getFeatures().get(GlobalVariables.Direction.WEST);

    }

    @Override
    public int scoreRoad(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples) {
        return -1;
    }

    @Override
    public int scoreCity(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples) {
        return -1;
    }

    @Override
    public int scoreGrass(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples) {
        return -1;
    }
}
