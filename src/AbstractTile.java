import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by johnsoaa on 3/27/2015.
 */

public abstract class AbstractTile {


    public static final int SIZE = 300;
    private static final int TILE_PIXEL_SIZE = 300;

    private AbstractTile left, right, top, bottom;
    private Map<GlobalVariables.Direction, GlobalVariables.Feature> featuresMap;

    public AbstractTile() {
        featuresMap = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
    }

    public AbstractTile(AbstractTile l, AbstractTile r, AbstractTile t, AbstractTile b, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {

        setLeft(l);
        setRight(r);
        setTop(t);
        setBottom(b);
        featuresMap = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
    }

    public AbstractTile(AbstractTile l, AbstractTile r, AbstractTile t, AbstractTile b) {
        setLeft(l);
        setRight(r);
        setTop(t);
        setBottom(b);
        featuresMap = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
    }


    public AbstractTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        featuresMap = features;
    }


    /**
     * TODO add string owner, color meepleColor, snum feature placedON, Places edge (direction)
     */


    public AbstractTile getLeft() {
        return left;
    }

    public void setLeft(AbstractTile left) {
        this.left = left;
        left.right = this;
    }

    public AbstractTile getRight() {
        return right;
    }

    public void setRight(AbstractTile right) {
        this.right = right;
        right.left = this;
    }

    public AbstractTile getTop() {
        return top;
    }

    public void setTop(AbstractTile top) {
        this.top = top;
        top.bottom = this;
    }

    public AbstractTile getBottom() {
        return bottom;
    }

    public void setBottom(AbstractTile bottom) {
        this.bottom = bottom;
        bottom.top = this;
    }

    public Map<GlobalVariables.Direction, GlobalVariables.Feature> getFeatures() {
        return featuresMap;
    }

    public abstract Image getImage();

}
