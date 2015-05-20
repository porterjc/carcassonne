package Objects;

import Main.GlobalVariables;
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
        this.setSize(AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE);
        this.drawSelf();
        //this.setOpaque(true);
        this.setVisible(true);
    }

    @Override
    public void drawSelf() {
        this.setBorder(BorderFactory.createLineBorder(new Color(80, 90, 115)));
    }

    @Override
    public GlobalVariables.Direction addTile(AbstractTile newTile) {
        GlobalVariables.Direction dir = super.addTile(newTile);
        return dir;
    }
}
