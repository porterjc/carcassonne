import java.awt.Color;

/**
 * Created by johnsoaa on 3/29/2015.
 */
public class Meeple {


    private Color mColor;
    private Player meepleOwner;
    private GlobalVariables.Feature placedOn;
    private GlobalVariables.Location location;
    private PlayableTile tile;


    public Meeple(Player p, Color aqua) {
        mColor = aqua;
        meepleOwner = p;
    }

    public Color getColor() {
        return mColor;
    }

    public Player getPlayer() {
        return meepleOwner;
    }

    public void place(PlayableTile pt, GlobalVariables.Feature place) {
        this.placedOn = place;
        this.tile = pt;
    }

    public GlobalVariables.Feature getFeature() {
        return placedOn;
    }

    public PlayableTile getTile() {
        return this.tile;
    }

    public void remove() {
        this.tile = null;
    }
}
