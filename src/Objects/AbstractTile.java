package Objects;

import Main.GlobalVariables;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * The superclass representing all tiles in the game
 * Created by johnsoaa on 3/27/2015.
 */

public abstract class AbstractTile extends JLabel {

    public static final int TILE_PIXEL_SIZE = 160;
    public  boolean isPlayable = false;
    private AbstractTile left, right, top, bottom;
    protected Map<GlobalVariables.Direction, GlobalVariables.Feature> featuresMap;
    private Set<GlobalVariables.Internal> internals;

    public AbstractTile() {
        featuresMap = new HashMap<>();
        internals = new HashSet<>();
    }

    public AbstractTile(AbstractTile l, AbstractTile r, AbstractTile t, AbstractTile b, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {

        setLeft(l);
        setRight(r);
        setTop(t);
        setBottom(b);
        featuresMap = features;
        internals = new HashSet<>();
    }

    public AbstractTile(AbstractTile l, AbstractTile r, AbstractTile t, AbstractTile b) {
        setLeft(l);
        setRight(r);
        setTop(t);
        setBottom(b);
        featuresMap = new HashMap<>();
        internals = new HashSet<>();
    }


    public AbstractTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        featuresMap = features;
        internals = new HashSet<>();
    }

    public AbstractTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals) {
        featuresMap = features;
        this.internals = internals;
    }

    public AbstractTile(AbstractTile left, AbstractTile right, AbstractTile top, AbstractTile bottom, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals) {
        setLeft(left);
        setRight(right);
        setTop(top);
        setBottom(bottom);
        featuresMap = features;
        this.internals = internals;
    }

    /**
     * Gets the feature in the given direction
     * @param direction the direction of the feature to find
     * @return the feature on the given edge of the tile, or null if the tile is not playable
     */
    public GlobalVariables.Feature getTargetFeature(GlobalVariables.Direction direction) {
        return null;
    }

    public AbstractTile getLeft() {
        return left;
    }

    public void setLeft(AbstractTile left) {
        this.left = left;
        if (left != null)
            left.right = this;
    }

    public AbstractTile getRight() {
        return right;
    }

    public void setRight(AbstractTile right) {
        this.right = right;
        if(right != null)
            right.left = this;
    }

    public AbstractTile getTop() {
        return top;
    }

    public void setTop(AbstractTile top) {
        this.top = top;
        if(top != null)
            top.bottom = this;
    }

    public AbstractTile getBottom() {
        return bottom;
    }

    public void setBottom(AbstractTile bottom) {
        this.bottom = bottom;
        if(bottom != null)
            bottom.top = this;
    }

    public Map<GlobalVariables.Direction, GlobalVariables.Feature> getFeatures() {
        return null;
    }

    public Set<GlobalVariables.Internal> getInternals() {
        return internals;
    }

    /**
     * Replaces this tile in the grid with the new tile
     *
     * @param newTile the tile to replace this one
     * @return whether or not the grid needs to be expanded
     */
    public GlobalVariables.Direction addTile(AbstractTile newTile) {
        newTile.setTop(this.getTop());
        newTile.setBottom(this.getBottom());
        newTile.setLeft(this.getLeft());
        newTile.setRight(this.getRight());

        newTile.drawSelf();
        newTile.setBounds(this.getBounds());
        Container parent = this.getParent();
        parent.remove(this);
        parent.add(newTile);

        return newTile.updateAdjacent();
    }

    /**
     * Returns the value of this tile for scoring monasteries and gardens
     * @return 0 by default
     */
    public int getValue() {
        return 0;
    }

    /**
     * The direction in which the grid needs to be expanded
     * @return null by default
     */
    public GlobalVariables.Direction updateAdjacent() {
        return null;
    }

    /**
     * Sets the bounds of the tile without having to specify the size
     * @param x the x location of the tile
     * @param y the y location of the tile
     */
    public void moveTile(int x, int y) {
        this.setBounds(x, y, TILE_PIXEL_SIZE, TILE_PIXEL_SIZE);
    }

    /**
     * Specifies the appearance of the tile
     */
    public abstract void drawSelf();

    /**
     * Calculates the score for a road
     * @param alreadyVisited tiles that have already been visited
     * @param meeples meeples that are on this road
     * @param isEndofGame whether or not the game is complete
     * @param prevDirection the direction this tracing algorithm just came from
     * @return the meeples in a city and that city's value
     */
    public Pair<Set<Meeple>, Integer> scoreRoad(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples, boolean isEndofGame, GlobalVariables.Direction prevDirection) {
          return new Pair<>(meeples, -1);
    }

    /**
     * Calculates the score for a city
     * @param alreadyVisited the tiles that have already been visited
     * @param meeples meeples in this city
     * @param completion whether or not the game is complete
     * @return the meeples on a road and that road's value
     */
    public Pair<HashSet<Meeple>, Integer> scoreCity(Set<AbstractTile> alreadyVisited, HashSet<Meeple> meeples, boolean completion) {
        return new Pair<>(meeples, -1);
    }

    /**
     * Traces a field to find farmers and cities
     * @param alreadyVisited tiles that have already been visited
     * @param from the direction that this algorithm came from
     * @param farmers the farmers found on this field
     * @param cities the value of cities connected to this field
     * @param gameOver whether or not the game is over
     * @return the meeples on the field and the cities
     */
    public boolean traceField(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from, Set<Meeple> farmers, Set<Integer> cities, boolean gameOver) {
        return false;
    }

}
