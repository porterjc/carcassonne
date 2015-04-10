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
    // Used for graphic purposes only
    private int xVal;
    private int yVal;

    public AbstractTile() {
        featuresMap = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
    }

    public AbstractTile(int x, int y) {
        xVal = x;
        yVal = y;
        featuresMap = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
    }

    public AbstractTile(int x, int y, AbstractTile l, AbstractTile r, AbstractTile t, AbstractTile b, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        xVal = x;
        yVal = y;
        setLeft(l);
        setRight(r);
        setTop(t);
        setBottom(b);
        featuresMap = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
    }

    public AbstractTile(int x, int y, AbstractTile l, AbstractTile r, AbstractTile t, AbstractTile b) {
        xVal = x;
        yVal = y;
        setLeft(l);
        setRight(r);
        setTop(t);
        setBottom(b);
        featuresMap = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
    }

    public AbstractTile(int i, int i1, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        featuresMap = features;
    }

    public AbstractTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        featuresMap = features;
    }


    /**
     * TODO add string owner, color meepleColor, snum feature placedON, Places edge (direction)
     */

    public abstract void draw(Graphics g, int offsetX, int offsetY);


    // Gets the x coordinate on the grid
    public int getxVal() {
        return xVal;
    }

    // Gets the x coordinate on the grid
    public int getyVal() {
        return yVal;
    }


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
