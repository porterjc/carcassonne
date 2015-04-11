import java.awt.*;
import java.util.Map;

/**
 * Created by johnsoaa on 3/31/2015.
 */
public class OpenTile extends AbstractTile {

    public OpenTile() {
        super();
    }

    public OpenTile(AbstractTile left, AbstractTile right, AbstractTile top, AbstractTile bottom) {
        super(left, right, top, bottom);
    }

    @Override
    public Image getImage() {
        return null;
    }


    public boolean canPlace(PlayableTile tileToPlace) {
        return checkEast(tileToPlace) && checkWest(tileToPlace) && checkNorth(tileToPlace) && checkSouth(tileToPlace);
    }

    public boolean checkNorth(PlayableTile tile) {
        Map<GlobalVariables.Direction, GlobalVariables.Feature> tileeFeatures = tile.getFeatures();
        if (getTop() == null) return true;
        else
            return tileeFeatures.get(GlobalVariables.Direction.NORTH) == getTop().getFeatures().get(GlobalVariables.Direction.SOUTH);

    }

    public boolean checkSouth(PlayableTile tile) {
        Map<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = tile.getFeatures();
        if (getBottom() == null) return true;
        else
            return tileFeatures.get(GlobalVariables.Direction.SOUTH) == getBottom().getFeatures().get(GlobalVariables.Direction.NORTH);
    }

    public boolean checkWest(PlayableTile tile) {
        Map<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = tile.getFeatures();
        if (getLeft() == null) return true;
        else
            return tileFeatures.get(GlobalVariables.Direction.WEST) == getLeft().getFeatures().get(GlobalVariables.Direction.EAST);

    }

    public boolean checkEast(PlayableTile tile) {
        Map<GlobalVariables.Direction, GlobalVariables.Feature> tileeFeatures = tile.getFeatures();
        if (getRight() == null) return true;
        else
            return tileeFeatures.get(GlobalVariables.Direction.EAST) == getRight().getFeatures().get(GlobalVariables.Direction.WEST);

    }
}
