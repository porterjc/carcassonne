import java.awt.*;
import java.util.Map;

/**
 * Created by johnsoaa on 3/31/2015.
 */
public class OpenTile extends AbstractTile {

    public OpenTile(int x, int y) {
        super(x, y);
    }

    public OpenTile(int i, int i1, AbstractTile left, AbstractTile right, AbstractTile top, AbstractTile bottom) {
        super(i, i1, left, right, top, bottom);
    }

    @Override
    public void draw(Graphics g, int offsetX, int offsetY) {
        int x = (getxVal() - offsetX) * 300;
        int y = (getyVal() - offsetY) * 300;
        g.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        g.drawRect(x, y, SIZE, SIZE);
        g.setColor(Color.WHITE);
        g.fillRect(x, y, SIZE, SIZE);
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
        if (getTop() == null) return true;
        else
            return tileFeatures.get(GlobalVariables.Direction.SOUTH) == getBottom().getFeatures().get(GlobalVariables.Direction.NORTH);
    }

    public boolean checkWest(PlayableTile tile) {
        Map<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = tile.getFeatures();
        if (getTop() == null) return true;
        else
            return tileFeatures.get(GlobalVariables.Direction.WEST) == getLeft().getFeatures().get(GlobalVariables.Direction.EAST);

    }

    public boolean checkEast(PlayableTile tile) {
        Map<GlobalVariables.Direction, GlobalVariables.Feature> tileeFeatures = tile.getFeatures();
        if (getTop() == null) return true;
        else
            return tileeFeatures.get(GlobalVariables.Direction.EAST) == getTop().getFeatures().get(GlobalVariables.Direction.WEST);

    }
}
