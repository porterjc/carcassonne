import java.awt.*;
import java.util.HashMap;

/**
 * Created by robinsat on 4/1/2015.
 */
public class PlayableTile extends AbstractTile {

    public PlayableTile(int x, int y) {
        super(x, y);
    }

    public PlayableTile(int i, int i1, AbstractTile o, AbstractTile o1, AbstractTile o2, AbstractTile o3, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        super(i, i1,o,o1,o2,o3,features);
    }

    public PlayableTile(int i, int i1, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        super(i, i1, features);
    }

    @Override
    public void draw(Graphics g, int offsetX, int offsetY) {

    }

}
