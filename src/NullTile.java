import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by robinsat on 4/14/2015.
 */
public class NullTile extends AbstractTile {

    public NullTile() {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.setVisible(true);
    }

    @Override
    public Image getImage() {
        return null;
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

    @Override
    public GlobalVariables.Direction updateAdjacent() {
        // This should never be called
        return null;
    }
}
