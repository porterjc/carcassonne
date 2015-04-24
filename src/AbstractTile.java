import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by johnsoaa on 3/27/2015.
 */

public abstract class AbstractTile extends JLabel implements MouseListener{

    public static final int TILE_PIXEL_SIZE = 160;

    private AbstractTile left, right, top, bottom;
    private Map<GlobalVariables.Direction, GlobalVariables.Feature> featuresMap;
    private Set<GlobalVariables.Internal> internals;
    private TileGrid grid;

    public AbstractTile() {
        featuresMap = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        internals = new HashSet<>();
        this.addMouseListener(this);
    }

    public AbstractTile(AbstractTile l, AbstractTile r, AbstractTile t, AbstractTile b, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {

        setLeft(l);
        setRight(r);
        setTop(t);
        setBottom(b);
        featuresMap = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        internals = new HashSet<GlobalVariables.Internal>();
        this.addMouseListener(this);
    }

    public AbstractTile(AbstractTile l, AbstractTile r, AbstractTile t, AbstractTile b) {
        setLeft(l);
        setRight(r);
        setTop(t);
        setBottom(b);
        featuresMap = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        internals = new HashSet<GlobalVariables.Internal>();
        this.addMouseListener(this);
    }


    public AbstractTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        featuresMap = features;
        internals = new HashSet<>();
        this.addMouseListener(this);
    }

    public AbstractTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals) {
        featuresMap = features;
        this.internals = internals;
        this.addMouseListener(this);
    }

    public GlobalVariables.Feature getTargetFeature(GlobalVariables.Direction direction){
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
        return featuresMap;
    }

    public Set<GlobalVariables.Internal> getInternals() {
        return internals;
    }

    /**
     * Replaces this tile in the grid with the new tile
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

    public abstract GlobalVariables.Direction updateAdjacent();

    public abstract Image getImage();

    public void moveTile(int x, int y) {
        this.setBounds(x, y, TILE_PIXEL_SIZE, TILE_PIXEL_SIZE);
    }

    public abstract void drawSelf();

    @Override
    public void mouseClicked(MouseEvent e) {
        TileGrid grid = (TileGrid) this.getParent();
        PlayableTile current = grid.getGame().getCurrentTile();
        System.out.println(current);

        if(this instanceof OpenTile && ((OpenTile)this).canPlace(current)) {
            GlobalVariables.Direction direction = this.addTile(current);
            if(direction != null) {
                grid.addNullRow(direction);
               /* revalidate();
                repaint();
                grid.revalidate();
                grid.repaint();
                grid.getParent().revalidate();
                grid.getParent().repaint();*/
            }
            grid.revalidate();
            grid.repaint();

            grid.getGame().drawTile();

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Scoring algorithms

    public abstract int scoreRoad(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples);

    public abstract int scoreCity(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples);

    public abstract int scoreGrass(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples);
}
