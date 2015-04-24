import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by robinsat on 4/1/2015.
 */
public class PlayableTile extends AbstractTile {

    private BufferedImage image;
    private int rotation = 0; //represents how much much this tile has been rotated. 0 is the default value before rotations happen;
    private Meeple meeple;

    public PlayableTile() {
        super();
    }

    public PlayableTile(AbstractTile o, AbstractTile o1, AbstractTile o2, AbstractTile o3, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        super(o, o1, o2, o3, features);

    }

    public PlayableTile(AbstractTile o, AbstractTile o1, AbstractTile o2, AbstractTile o3, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals) {
        super(o, o1, o2, o3, features, internals);
    }


    public PlayableTile(BufferedImage image, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        super(features);
        this.image = image;
        Image scaled = image.getScaledInstance(TILE_PIXEL_SIZE, TILE_PIXEL_SIZE, Image.SCALE_DEFAULT);
        this.setIcon(new ImageIcon(scaled));
    }

    public PlayableTile(BufferedImage image, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals) {
        super(features, internals);
        this.image = image;
    }

    public PlayableTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        super(features);
    }

    public PlayableTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals) {
        super(features, internals);
    }

    public Map<GlobalVariables.Direction, GlobalVariables.Feature> getFeatures() {
        return featuresMap;
    }

    @Override
    public GlobalVariables.Direction updateAdjacent() {
        GlobalVariables.Direction topdir = this.getTop().addTile(new OpenTile());
        GlobalVariables.Direction leftdir = this.getLeft().addTile(new OpenTile());
        GlobalVariables.Direction rightdir = this.getRight().addTile(new OpenTile());
        GlobalVariables.Direction bottomdir = this.getBottom().addTile(new OpenTile());

        if (topdir != null)
            return topdir;
        else if (leftdir != null)
            return leftdir;
        else if (rightdir != null)
            return rightdir;
        else
            return bottomdir;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Image getAdjustedImage() {
        BufferedImage raw = getImage();
        return raw.getScaledInstance(TILE_PIXEL_SIZE, TILE_PIXEL_SIZE, Image.SCALE_DEFAULT);
        // return raw;
        //int scaleFactor = TILE_PIXEL_SIZE /getImage().getWidth();
        //AffineTransform transform = new AffineTransform();
        //transform.rotate(rotation * .5 * Math.PI);

        //BufferedImageOp bio = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
       /* GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage rotated = gc.createCompatibleImage(raw.getWidth(), raw.getHeight());
        Graphics2D g2d = rotated.createGraphics();
        g2d.rotate(Math.PI * .5);
        g2d.drawRenderedImage(raw, null);
        g2d.dispose();
        return rotated;*/
        //return bio.filter(raw, null);
    }


    public void drawSelf() {
        // Image resized = getImage().getScaledInstance(TILE_PIXEL_SIZE, TILE_PIXEL_SIZE, Image.SCALE_DEFAULT);
        //  AffineTransform transform = AffineTransform.getScaleInstance(TILE_PIXEL_SIZE, TILE_PIXEL_SIZE).getQuadrantRotateInstance(rotation);
        //AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
        //Image rotated = op.filter(resized, null);
        // BufferedImageOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
        this.setIcon(getIcon());
    }

    @Override
    public int scoreRoad(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples) {
        int currentTileScore = 1;
        Map<GlobalVariables.Direction, GlobalVariables.Feature> features = this.getFeatures();
        if (getInternals().contains(GlobalVariables.Internal.ROADSTOP) && alreadyVisited.size() > 1) //hit the end of the road
            return currentTileScore;
//        if (alreadyVisited.contains(this) && alreadyVisited.size() > 1) { // small circular road
//            return currentTileScore;
//        }
        alreadyVisited.add(this);

        if ((!alreadyVisited.contains(this.getLeft())) && features.get(GlobalVariables.Direction.WEST) == GlobalVariables.Feature.ROAD) {
            AbstractTile l = this.getLeft();
            int temp = l.scoreRoad(alreadyVisited, meeples);
            if (temp == -1) return -1;
            return 1 + temp;
        }
        if ((!alreadyVisited.contains(this.getRight())) && features.get(GlobalVariables.Direction.EAST) == GlobalVariables.Feature.ROAD) {
            AbstractTile r = this.getRight();
            int temp = r.scoreRoad(alreadyVisited, meeples);
            if (temp == -1) return -1;
            return 1 + temp;
        } if ((!alreadyVisited.contains(this.getTop())) && features.get(GlobalVariables.Direction.NORTH) == GlobalVariables.Feature.ROAD) {
            AbstractTile t = this.getTop();
            int temp = t.scoreRoad(alreadyVisited, meeples);
            if (temp == -1) return -1;
            return 1 + temp;
        }
        if ((!alreadyVisited.contains(this.getBottom())) && features.get(GlobalVariables.Direction.SOUTH) == GlobalVariables.Feature.ROAD) {
            AbstractTile b = this.getBottom();
            int temp = b.scoreRoad(alreadyVisited, meeples);
            if (temp == -1) return -1;
            return 1 + temp;
        }
        return -1;
    }

    @Override
    public int scoreCity(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples) {
        int cityScore = 2;

        alreadyVisited.add(this);
        if ((!alreadyVisited.contains(this.getBottom())) && getTargetFeature(GlobalVariables.Direction.SOUTH) == GlobalVariables.Feature.CITY) {
            AbstractTile b = this.getBottom();
            int temp = b.scoreCity(alreadyVisited, meeples);
            cityScore += temp;
        }
        if ((!alreadyVisited.contains(this.getRight())) && getTargetFeature(GlobalVariables.Direction.EAST) == GlobalVariables.Feature.CITY) {
            AbstractTile r = this.getRight();
            int temp = r.scoreCity(alreadyVisited, meeples);
            cityScore += temp;
        }
        return cityScore;
    }

    @Override
    public boolean findFarmer(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples) {
        return false;
    }

    /**
     * Takes a boolean and rotates
     */
    public void rotateTile() {
        if (rotation == 3)
            rotation = 0;
        else
            rotation++;
    }

    @Override
    public GlobalVariables.Feature getTargetFeature(GlobalVariables.Direction direction) {
        GlobalVariables.Feature feature = null;
        if (rotation == 0) {
            return this.getFeatures().get(direction);
        } else {
            if (direction.equals(GlobalVariables.Direction.NORTH)) {
                feature = getFeatureInPosition0(feature, GlobalVariables.Direction.WEST, GlobalVariables.Direction.SOUTH, GlobalVariables.Direction.EAST);
            } else if (direction.equals(GlobalVariables.Direction.SOUTH)) {
                feature = getFeatureInPosition2(feature, GlobalVariables.Direction.EAST, GlobalVariables.Direction.NORTH, GlobalVariables.Direction.WEST);
            } else if (direction.equals(GlobalVariables.Direction.EAST)) {
                feature = getFeatureInPosition1(feature, GlobalVariables.Direction.NORTH, GlobalVariables.Direction.WEST, GlobalVariables.Direction.SOUTH);
            } else if (direction.equals(GlobalVariables.Direction.WEST)) {
                feature = getFeatureInPosition3(feature, GlobalVariables.Direction.SOUTH, GlobalVariables.Direction.EAST, GlobalVariables.Direction.NORTH);
            }
            return feature;
        }
    }


    private GlobalVariables.Feature getFeatureInPosition0(GlobalVariables.Feature feature, GlobalVariables.Direction west, GlobalVariables.Direction south, GlobalVariables.Direction east) {
        if (rotation == 1) {
            feature = this.getFeatures().get(west);
        } else if (rotation == 2) {
            feature = this.getFeatures().get(south);
        } else if (rotation == 3) {
            feature = this.getFeatures().get(east);
        }
        return feature;
    }

    private GlobalVariables.Feature getFeatureInPosition1(GlobalVariables.Feature feature, GlobalVariables.Direction north, GlobalVariables.Direction west, GlobalVariables.Direction south) {
        if (rotation == 1) {
            feature = this.getFeatures().get(north);
        } else if (rotation == 2) {
            feature = this.getFeatures().get(west);
        } else if (rotation == 3) {
            feature = this.getFeatures().get(south);
        }
        return feature;
    }

    private GlobalVariables.Feature getFeatureInPosition2(GlobalVariables.Feature feature, GlobalVariables.Direction east, GlobalVariables.Direction north, GlobalVariables.Direction west) {
        if (rotation == 1) {
            feature = this.getFeatures().get(east);
        } else if (rotation == 2) {
            feature = this.getFeatures().get(north);
        } else if (rotation == 3) {
            feature = this.getFeatures().get(west);
        }
        return feature;
    }

    private GlobalVariables.Feature getFeatureInPosition3(GlobalVariables.Feature feature, GlobalVariables.Direction south, GlobalVariables.Direction east, GlobalVariables.Direction north) {
        if (rotation == 1) {
            feature = this.getFeatures().get(south);
        } else if (rotation == 2) {
            feature = this.getFeatures().get(east);
        } else if (rotation == 3) {
            feature = this.getFeatures().get(north);
        }
        return feature;
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

    @Override
    public GlobalVariables.Direction addTile(AbstractTile tile) {
        return null;
    }

    /**
     * This method is soley for testing purposes.
     *
     * @param x
     */
    public void setRotation(int x) {
        this.rotation = x;
    }

    public Meeple getMeeple() {
        return meeple;
    }

    public void setMeeple(Meeple meeple) {
        this.meeple = meeple;
    }

    /**
     * Allows the icon to be rotated
     */
    private class AdjustableIcon extends ImageIcon {

        private int rotation;

        public AdjustableIcon(Image image, int rotation) {
            super(image);
            this.rotation = rotation;
        }

        @Override
        public void paintIcon(Component component, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();
            int w1 = this.getIconWidth() / 2;
            int w2 = (this.getIconWidth() % 2) == 0 ? 0 : -1;
            g2d.translate(x + w1, y + w1);
            g2d.rotate(rotation * Math.PI * .5);
            super.paintIcon(component, g2d, -w1, -w1);
        }
    }

    public AdjustableIcon getIcon() {
        return new AdjustableIcon(this.getAdjustedImage(), this.rotation);
    }
}
