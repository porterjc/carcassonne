import java.awt.*;

/**
 * Created by johnsoaa on 3/27/2015.
 */
public abstract class AbstractTile {
    private static final int TILE_PIXEL_SIZE = 300;


    private AbstractTile left, right, top, bottom;
    private Rectangle display;

    public AbstractTile(){
        display = new Rectangle(0, 0, TILE_PIXEL_SIZE, TILE_PIXEL_SIZE);
    }


    //public String getEdges() {
      //  return edges;
    //}

    /**
     * TODO add string owner, color meepleColor, snum feature placedON, Places edge (direction)
     */

    public abstract void draw();

    // Gets the displayable rectangle
    public Rectangle geDisplay(){ return display; }


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
