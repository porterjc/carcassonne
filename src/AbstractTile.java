import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

/**
 * Created by johnsoaa on 3/27/2015.
 */

public abstract class AbstractTile extends JLabel {

    public static final int TILE_PIXEL_SIZE = 160;
    public static final int TILE_INNER_MARGIN = 10;

    private AbstractTile left, right, top, bottom;
    protected Map<GlobalVariables.Direction, GlobalVariables.Feature> featuresMap;
    private Set<GlobalVariables.Internal> internals;
    private TileGrid grid;

    public AbstractTile() {
        featuresMap = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        internals = new HashSet<>();
    }

    public AbstractTile(AbstractTile l, AbstractTile r, AbstractTile t, AbstractTile b, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {

        setLeft(l);
        setRight(r);
        setTop(t);
        setBottom(b);
        featuresMap = features;
        internals = new HashSet<GlobalVariables.Internal>();
    }

    public AbstractTile(AbstractTile l, AbstractTile r, AbstractTile t, AbstractTile b) {
        setLeft(l);
        setRight(r);
        setTop(t);
        setBottom(b);
        featuresMap = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        internals = new HashSet<GlobalVariables.Internal>();
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
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
        featuresMap = features;
        this.internals = internals;
    }

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
        if (right != null)
            right.left = this;
    }

    public AbstractTile getTop() {
        return top;
    }

    public void setTop(AbstractTile top) {
        this.top = top;
        if (top != null)
            top.bottom = this;
    }

    public AbstractTile getBottom() {
        return bottom;
    }

    public void setBottom(AbstractTile bottom) {
        this.bottom = bottom;
        if (bottom != null)
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

    public int getValue() {
        return 0;
    }

    public abstract GlobalVariables.Direction updateAdjacent();

    public Image getImage() {
        return null;
    }

    public void moveTile(int x, int y) {
        this.setBounds(x, y, TILE_PIXEL_SIZE, TILE_PIXEL_SIZE);
    }

    public abstract void drawSelf();

    // Scoring algorithms
    public Pair<HashSet<Meeple>, Integer> scoreRoad(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples) {
        return new Pair(meeples, -1);
    }

    public Pair<HashSet<Meeple>, Integer> scoreCity(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples, boolean completion) {
        return new Pair(meeples, -1);
    }
    public boolean findFarmer(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from) {
        return false;
    }

    protected boolean checkFromBottom(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from) {
        return false;
    }

    protected boolean checkFromTop(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from) {
        return false;
    }

    protected boolean checkFromLeft(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from) {
        return false;
    }

    protected boolean checkFromRight(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from) {
        return false;
    }

}
