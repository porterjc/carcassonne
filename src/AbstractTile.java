import java.awt.*;

/**
 * Created by johnsoaa on 3/27/2015.
 */
public abstract class AbstractTile {
    public static final int SIZE = 300;
    private static String oxwner;

    private int xVal;
    private int yVal;

    public AbstractTile(int x, int y){
        xVal = x;
        yVal = y;
    }
    public static String getOxwner() {
        return oxwner;
    }

    //public String getEdges() {
      //  return edges;
    //}

    /**
     * TODO add string owner, color meepleColor, snum feature placedON, Places edge (direction)
     */

    public abstract void draw(Graphics g, int offsetX, int offsetY);

    // Gets the x coordinate on the grid
    public int getxVal() { return xVal; }

    // Gets the x coordinate on the grid
    public int getyVal() { return yVal; }


}
