import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by robinsat on 4/1/2015.
 */
public class PlayableTile extends AbstractTile {

    private Image image;
    private int rotation = 0; //represents how much much this tile has been rotated. 0 is the default value before rotations happen;

    public PlayableTile() {
        super();
    }

    public PlayableTile(int i, int i1, AbstractTile o, AbstractTile o1, AbstractTile o2, AbstractTile o3, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        super(o,o1,o2,o3,features);
    }

    public PlayableTile(int i, int i1, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        super(features);
    }

    public PlayableTile(Image image, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        super(features);
        this.image = image;
        Image scaled = image.getScaledInstance(TILE_PIXEL_SIZE, TILE_PIXEL_SIZE, Image.SCALE_DEFAULT);
        this.setIcon(new ImageIcon(scaled));
    }

    public PlayableTile(Image image, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals) {
        super(features, internals);
        this.image = image;
    }

    public PlayableTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        super(features);
    }

    @Override
    public GlobalVariables.Direction updateAdjacent() {
        GlobalVariables.Direction topdir = this.getTop().addTile(new OpenTile());
        GlobalVariables.Direction leftdir = this.getLeft().addTile(new OpenTile());
        GlobalVariables.Direction rightdir = this.getRight().addTile(new OpenTile());
        GlobalVariables.Direction bottomdir = this.getBottom().addTile(new OpenTile());

        if(topdir != null)
            return topdir;
        else if(leftdir != null)
            return leftdir;
        else if(rightdir != null)
            return rightdir;
        else
            return bottomdir;
    }

    public Image getImage() {
        return image;
    }


    public void drawSelf() {
        Image resized = getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        this.setIcon(new ImageIcon(resized));
    }

    /*Unneccessary change for commit*/
    /**
     *  Takes a boolean and rotates
     */
    public void rotateTile() {
        if(rotation == 3)
            rotation = 0;
        else
            rotation++;
    }

    public GlobalVariables.Feature getTargetFeature(GlobalVariables.Direction direction){
        if(rotation == 1){
           return this.getFeatures().get(GlobalVariables.Direction.WEST);
        } else if(rotation == 2){
            return this.getFeatures().get(GlobalVariables.Direction.SOUTH);
        } else
            return this.getFeatures().get(direction);
    }

    public AbstractTile getTopLeft() {
        return getTop().getLeft();
    }

    public AbstractTile getTopRight() {
        return getTop().getRight();
    }

    public AbstractTile getBottomLeft() {
        return getBottom().getLeft();
    }

    public AbstractTile getBottomRight() {
        return getBottom().getRight();
    }
}
