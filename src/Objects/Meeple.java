package Objects;

import Main.GlobalVariables;
import javafx.util.Pair;

import java.awt.Color;

/**
 * Meeples may be placed on any feature of a tile, and they belong to the player of their designated color.
 * This keeps track of who "owns" the feature and can claim points for it
 * Created by johnsoaa on 3/29/2015.
 */
public class Meeple {


    private GlobalVariables.PlayerColor mColor;
    private Player meepleOwner;
    private GlobalVariables.Feature placedOn;
    private GlobalVariables.Feature placedOnInternal;
    private GlobalVariables.Location location;
    private PlayableTile tile;


    public Meeple(Player p, GlobalVariables.PlayerColor color) {
        mColor = color;
        meepleOwner = p;
    }

    public GlobalVariables.PlayerColor getColor() {
        return mColor;
    }

    public Player getPlayer() {
        return meepleOwner;
    }


    public void place(PlayableTile pt, GlobalVariables.Feature place, GlobalVariables.Location loc) {
        this.placedOn = place;
        this.tile = pt;
        pt.setMeeple(this);
        this.location = loc;
    }

    public Pair<AbstractTile, GlobalVariables.Feature> getPlacedOn(){
        return  new Pair(tile, placedOn);
    }

    public GlobalVariables.Feature getFeature() {
        return placedOn;
    }

    public GlobalVariables.Location getLocation() { return location; }

    public PlayableTile getTile() {
        return this.tile;
    }

    public void remove() {
        this.tile = null;
    }


}
