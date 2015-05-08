import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by robinsat on 4/14/2015.
 */
public class NullTile extends AbstractTile {

    public NullTile() {
        super();
        this.setSize(TILE_PIXEL_SIZE, TILE_PIXEL_SIZE);
        this.drawSelf();
        //this.setOpaque(true);
        this.setVisible(true);
    }


    @Override
    public void drawSelf() {
        this.setBorder(BorderFactory.createLineBorder(new Color(80, 90, 115)));
    }


    @Override
    public Map<GlobalVariables.Direction, GlobalVariables.Feature> getFeatures() {
        return null;
    }

    @Override
    public GlobalVariables.Direction addTile(AbstractTile newTile) {
        GlobalVariables.Direction dir = super.addTile(newTile);
        return dir;
    }
    // TODO either delete or refactor
    @Override
    public GlobalVariables.Direction updateAdjacent() {
        // This should never be called
        return null;
    }
}
