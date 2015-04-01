import java.awt.*;

/**
 * Created by johnsoaa on 3/27/2015.
 */
public abstract class AbstractTile {
    private static final int SIZE = 300;
    private static String oxwner;

    private Rectangle display;

    public AbstractTile(){
        display = new Rectangle(0, 0, SIZE, SIZE);
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

    public abstract void draw(Graphics g);

    // Gets the displayable rectangle
    public Rectangle geDisplay(){ return display; }


}
