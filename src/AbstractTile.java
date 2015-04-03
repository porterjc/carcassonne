import java.awt.*;

/**
 * Created by johnsoaa on 3/27/2015.
 */

public abstract class AbstractTile {
    public static final int SIZE = 300;
    private static final int TILE_PIXEL_SIZE = 300;

    private AbstractTile left, right, top, bottom;

    // Used for graphic purposes only
    private int xVal;
    private int yVal;

    public AbstractTile() {

    }

    public AbstractTile(int x, int y){
        xVal = x;
        yVal = y;
    }

    //public String getEdges() {
      //  return edges;
    //}

    /**
     * TODO add string owner, color meepleColor, snum feature placedON, Places edge (direction)
     */

   // public abstract void draw(Graphics g, int offsetX, int offsetY);

    // Gets the x coordinate on the grid
    public int getxVal() { return xVal; }

    // Gets the x coordinate on the grid
    public int getyVal() { return yVal; }


    public AbstractTile getLeft() {
        return left;
    }

    public void setLeft(AbstractTile left) {
        this.left = left;
    }

    public AbstractTile getRight() {
        return right;
    }

    public void setRight(AbstractTile right) {
        this.right = right;
    }

    public AbstractTile getTop() {
        return top;
    }

    public void setTop(AbstractTile top) {
        this.top = top;
    }

    public AbstractTile getBottom() {
        return bottom;
    }

    public void setBottom(AbstractTile bottom) {
        this.bottom = bottom;
    }
}
