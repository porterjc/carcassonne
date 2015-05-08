import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by robinsat on 4/1/2015.
 */
public class PlayableTile extends AbstractTile {

    private BufferedImage image;
    private int rotation = 0; //represents how much much this tile has been rotated. 0 is the default value before rotations happen;
    private Meeple meeple = null;

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

    public PlayableTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals, Meeple m) {
        super(features, internals);
        this.meeple = m;
    }

    public Map<GlobalVariables.Direction, GlobalVariables.Feature> getFeatures() {
        return featuresMap;
    }

    @Override
    public GlobalVariables.Direction updateAdjacent() {
        GlobalVariables.Direction topdir = this.getTop().getClass() != OpenTile.class ? this.getTop().addTile(new OpenTile()) : null;
        GlobalVariables.Direction leftdir = this.getLeft().getClass() != OpenTile.class ? this.getLeft().addTile(new OpenTile()) : null;
        GlobalVariables.Direction rightdir = this.getRight().getClass() != OpenTile.class ? this.getRight().addTile(new OpenTile()) : null;
        GlobalVariables.Direction bottomdir = this.getBottom().getClass() != OpenTile.class ? this.getBottom().addTile(new OpenTile()) : null;

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
    }

    /**
     * Draws the appropriately scaled and rotated icon image on the tile
     */
    @Override
    public void drawSelf() {
        this.setIcon(getIcon());
    }

    /**
     * Adds buttons to place a meeple over a tile
     */
    public void addMeepleButtons() {
        this.add(new PlaceMeepleButton(Color.RED, 10, 10));
    }


    @Override
    public Pair<HashSet<Meeple>, Integer> scoreRoad(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples) {
        int currentTileScore = 1;
        alreadyVisited.add(this);
        Map<GlobalVariables.Direction, GlobalVariables.Feature> features = this.getFeatures();
        if (getInternals().contains(GlobalVariables.Internal.ROADSTOP) && alreadyVisited.size() > 1) //hit the end of the road
            return new Pair(meeples, currentTileScore);
        Meeple tileM = this.getMeeple();//need a few more cases
        if (tileM != null) {
            if (tileM.getFeature() == GlobalVariables.Feature.ROAD)
                meeples.add(this.getMeeple());
        }

        if ((!alreadyVisited.contains(this.getLeft())) && features.get(GlobalVariables.Direction.WEST) == GlobalVariables.Feature.ROAD) {
            AbstractTile l = this.getLeft();
            Pair<HashSet<Meeple>, Integer> temp = l.scoreRoad(alreadyVisited, meeples);
            if (temp.getValue() == -1) return new Pair(meeples, -1);
            else {
                if (this.getMeeple() != null) {
                    meeples.add(this.getMeeple());
                }
                return new Pair(meeples, 1 + temp.getValue());
            }
        }
        if ((!alreadyVisited.contains(this.getRight())) && features.get(GlobalVariables.Direction.EAST) == GlobalVariables.Feature.ROAD) {
            AbstractTile r = this.getRight();
            Pair<HashSet<Meeple>, Integer> temp = r.scoreRoad(alreadyVisited, meeples);
            if (temp.getValue() == -1) return new Pair(meeples, -1);
            else {
                if (this.getMeeple() != null) {
                    meeples.add(this.getMeeple());
                }
                return new Pair(meeples, 1 + temp.getValue());
            }
        }
        if ((!alreadyVisited.contains(this.getTop())) && features.get(GlobalVariables.Direction.NORTH) == GlobalVariables.Feature.ROAD) {
            AbstractTile t = this.getTop();
            Pair<HashSet<Meeple>, Integer> temp = t.scoreRoad(alreadyVisited, meeples);
            if (temp.getValue() == -1) return new Pair(meeples, -1);
            else {
                if (this.getMeeple() != null) {
                    meeples.add(this.getMeeple());
                }
                return new Pair(meeples, 1 + temp.getValue());
            }
        }
        if ((!alreadyVisited.contains(this.getBottom())) && features.get(GlobalVariables.Direction.SOUTH) == GlobalVariables.Feature.ROAD) {
            AbstractTile b = this.getBottom();
            Pair<HashSet<Meeple>, Integer> temp = b.scoreRoad(alreadyVisited, meeples);
            if (temp.getValue() == -1) return new Pair(meeples, -1);
            else {
                if (this.getMeeple() != null) {
                    meeples.add(this.getMeeple());
                }
                return new Pair(meeples, 1 + temp.getValue());
            }
        }
        return new Pair(meeples, -1);
    }

    /**
     * startScoreCity runs scoreCity in all given directions and then returns the total score and list of meeples found
     *
     * @param alreadyVisited
     * @param meeples
     * @param directions
     * @param completion
     * @return
     */
    public Pair<HashSet<Meeple>, Integer> startScoreCity(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples, Set<GlobalVariables.Direction> directions, boolean completion) {
        int currentScore = 2;
        alreadyVisited.add(this);

        if(this.getInternals().contains(GlobalVariables.Internal.COATOFARMS))
            currentScore += 2;

        if (getMeeple() != null)
            meeples.add(this.getMeeple());

        if (directions.contains(GlobalVariables.Direction.NORTH)) {
            currentScore += getTop().scoreCity(alreadyVisited, meeples, completion).getValue();
        }
        if (directions.contains(GlobalVariables.Direction.WEST)) {
            currentScore += getLeft().scoreCity(alreadyVisited, meeples, completion).getValue();
        }
        if (directions.contains(GlobalVariables.Direction.EAST)) {
            currentScore += getRight().scoreCity(alreadyVisited, meeples, completion).getValue();
        }
        if (directions.contains(GlobalVariables.Direction.SOUTH)) {
            currentScore += getBottom().scoreCity(alreadyVisited, meeples, completion).getValue();
        }
        return new Pair(meeples, currentScore);
    }

    /**
     * Recurses through adjacent tiles that continue the city that is being scored. If completion is true then the method will short circuit if an adjacent tile does not continue the city.
     *
     * @param alreadyVisited
     * @param meeples
     * @param completion
     * @return
     */
    @Override
    public Pair<HashSet<Meeple>,Integer> scoreCity(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples, boolean completion) {
        int cityScore = 2;
        alreadyVisited.add(this);
        Meeple meep = getMeeple();

        if(this.getInternals().contains(GlobalVariables.Internal.COATOFARMS))
            cityScore += 2;

        if (meep != null && meep.getFeature() == GlobalVariables.Feature.CITY)
            meeples.add(meep);

        if (!this.getInternals().contains(GlobalVariables.Internal.CITY)) {
            return new Pair(meeples, cityScore);
        } else {
            if ((!alreadyVisited.contains(this.getBottom())) && getTargetFeature(GlobalVariables.Direction.SOUTH) == GlobalVariables.Feature.CITY) {
                AbstractTile b = this.getBottom();
                int temp = b.scoreCity(alreadyVisited, meeples, completion).getValue();;
                if (completion) {
                    if (temp == -1) {
                        return new Pair(meeples, -1);
                    } else
                        cityScore += temp;
                } else
                    cityScore += temp;
            }
            if ((!alreadyVisited.contains(this.getRight())) && getTargetFeature(GlobalVariables.Direction.EAST) == GlobalVariables.Feature.CITY) {
                AbstractTile r = this.getRight();
                int temp = r.scoreCity(alreadyVisited, meeples, completion).getValue();;
                if (completion) {
                    if (temp == -1) {
                        return new Pair(meeples, -1);
                    } else
                        cityScore += temp;
                } else
                    cityScore += temp;
            }
            if ((!alreadyVisited.contains(this.getLeft())) && getTargetFeature(GlobalVariables.Direction.WEST) == GlobalVariables.Feature.CITY) {
                AbstractTile l = this.getLeft();
                int temp = l.scoreCity(alreadyVisited, meeples, completion).getValue();
                if (completion) {
                    if (temp == -1) {
                        return new Pair(meeples, -1);
                    } else
                        cityScore += temp;
                } else
                    cityScore += temp;
            }
            if ((!alreadyVisited.contains(this.getTop())) && getTargetFeature(GlobalVariables.Direction.NORTH) == GlobalVariables.Feature.CITY) {
                AbstractTile t = this.getTop();
                int temp = t.scoreCity(alreadyVisited, meeples, completion).getValue();
                if (completion) {
                    if (temp == -1) {
                        return new Pair(meeples, -1);
                    } else
                        cityScore += temp;
                } else
                    cityScore += temp;
            }
            return new Pair(meeples,cityScore);
        }
    }

    /**
     * If completion is true will ensure that there are 8 neighbors to complete the scoring. Otherwise returns total neighbors plus this tile.
     *
     * @param completion
     * @return
     */
    public int scoreSurrounding(boolean completion){
        int neighbors = getTotalPlayableNeighbors();
        if(completion && neighbors != 8)
            return -1;
        return neighbors + 1;
    }

    /**
     * Looks at all 8 surrounding tiles to see if they are playable, adding 1 to the total return if they are.
     *
     * @return
     */
    public int getTotalPlayableNeighbors(){
        return getBottom().getValue() + getTop().getValue() + getRight().getValue() + getLeft().getValue() + getTopLeft().getValue() + getTopRight().getValue() + getBottomLeft().getValue() + getBottomRight().getValue();
    }

    @Override
    public int getValue(){
        return 1;
    }

    @Override
    public boolean findFarmer(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from) {
        alreadyVisited.add(this);

        if(this.meeple != null && this.meeple.getFeature() == GlobalVariables.Feature.GRASS) {
            if(hasEWbisector()) {
                if(from == GlobalVariables.Location.TOP)
                    return GlobalVariables.Location.isTop(meeple.getLocation());
                else if(from == GlobalVariables.Location.BOTTOM)
                    return GlobalVariables.Location.isBottom(meeple.getLocation());
            }
            if(hasNSbisector()) {
                if(from == GlobalVariables.Location.LEFT)
                    return GlobalVariables.Location.isLeft(meeple.getLocation());
                else if(from == GlobalVariables.Location.RIGHT)
                    return GlobalVariables.Location.isRight(meeple.getLocation());
            }
            return true;
        }

        //No meeple on this tile, so check others
        boolean found = false;

        if(!alreadyVisited.contains(this.getTop())) {
            if(this.getTopFeature() == GlobalVariables.Feature.GRASS)
                found = this.getTop().findFarmer(alreadyVisited, GlobalVariables.Location.BOTTOM);
        }
        if(found) return true;

        if(!alreadyVisited.contains(this.getBottom())) {
            if(this.getBottomFeature() == GlobalVariables.Feature.GRASS)
                found = this.getBottom().findFarmer(alreadyVisited, GlobalVariables.Location.TOP);
        }
        if(found) return true;

        if(!alreadyVisited.contains(this.getLeft())) {
            if(this.getLeftFeature() == GlobalVariables.Feature.GRASS)
                found = this.getLeft().findFarmer(alreadyVisited, GlobalVariables.Location.RIGHT);
        }
        if(found) return true;

        if(!alreadyVisited.contains(this.getRight())) {
            if(this.getRightFeature() == GlobalVariables.Feature.GRASS)
                found = this.getRight().findFarmer(alreadyVisited, GlobalVariables.Location.LEFT);
        }

        return found;
    }

    /*
    @Override
    protected boolean checkFromBottom(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from) {
        alreadyVisited.add(this);

        if(this.hasEWbisector()) {
            if(this.meeple != null && this.meeple.getFeature() == GlobalVariables.Feature.GRASS && GlobalVariables.Location.isBottom(this.meeple.getLocation()))
                return true;
        }
        else if(this.meeple != null && this.meeple.getFeature() == GlobalVariables.Feature.GRASS)
            return true;

        boolean found = false;

        if(!alreadyVisited.contains(this.getTop()))
            found = this.getTop().checkFromBottom(alreadyVisited, GlobalVariables.Location.BOTTOM);
        if(found) return true;

        if(!alreadyVisited.contains(this.getLeft()))
            found = this.getLeft().checkFromRight(alreadyVisited, GlobalVariables.Location.RIGHT);
        if(found) return found;

        if(!alreadyVisited.contains(this.getRight()))
            found = this.getRight().checkFromLeft(alreadyVisited, GlobalVariables.Location.LEFT);

        return found;

    }

    @Override
    protected boolean checkFromTop(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from) {
        alreadyVisited.add(this);

        if(this.hasEWbisector()) {
            if(this.meeple != null && this.meeple.getFeature() == GlobalVariables.Feature.GRASS && GlobalVariables.Location.isTop(this.meeple.getLocation()))
                return true;
        }
        else if(this.meeple != null && this.meeple.getFeature() == GlobalVariables.Feature.GRASS)
            return true;

        boolean found = false;

        if(!alreadyVisited.contains(this.getBottom()))
            found = this.getBottom().checkFromTop(alreadyVisited, GlobalVariables.Location.TOP);
        if(found) return true;

        if(!alreadyVisited.contains(this.getLeft()))
            found = this.getLeft().checkFromRight(alreadyVisited, GlobalVariables.Location.RIGHT);
        if(found) return found;

        if(!alreadyVisited.contains(this.getRight()))
            found = this.getRight().checkFromLeft(alreadyVisited, GlobalVariables.Location.LEFT);

        return found;
    }

    @Override
    protected boolean checkFromLeft(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from) {
        alreadyVisited.add(this);

        if(this.hasNSbisector()) {
            if(this.meeple != null && this.meeple.getFeature() == GlobalVariables.Feature.GRASS && GlobalVariables.Location.isLeft(this.meeple.getLocation()))
                return true;
        }
        else if(this.meeple != null && this.meeple.getFeature() == GlobalVariables.Feature.GRASS)
            return true;

        boolean found = false;

        if(!alreadyVisited.contains(this.getBottom()))
            found = this.getBottom().checkFromTop(alreadyVisited, GlobalVariables.Location.TOP);
        if(found) return true;

        if(!alreadyVisited.contains(this.getTop()))
            found = this.getTop().checkFromBottom(alreadyVisited, GlobalVariables.Location.BOTTOM);
        if(found) return true;

        if(!alreadyVisited.contains(this.getRight()))
            found = this.getRight().checkFromLeft(alreadyVisited, GlobalVariables.Location.LEFT);

        return found;
    }

    @Override
    protected boolean checkFromRight(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from) {
        alreadyVisited.add(this);

        if(this.hasNSbisector()) {
            if(this.meeple != null && this.meeple.getFeature() == GlobalVariables.Feature.GRASS && GlobalVariables.Location.isRight(this.meeple.getLocation()))
                return true;
        }
        else if(this.meeple != null && this.meeple.getFeature() == GlobalVariables.Feature.GRASS)
            return true;

        boolean found = false;

        if(!alreadyVisited.contains(this.getBottom()))
            found = this.getBottom().checkFromTop(alreadyVisited, GlobalVariables.Location.TOP);
        if(found) return true;

        if(!alreadyVisited.contains(this.getTop()))
            found = this.getTop().checkFromBottom(alreadyVisited, GlobalVariables.Location.BOTTOM);
        if(found) return true;

        if(!alreadyVisited.contains(this.getLeft()))
            found = this.getLeft().checkFromRight(alreadyVisited, GlobalVariables.Location.RIGHT);

        return found;
    } */

    /**
     * Takes a boolean and rotate
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
    //todo move to AbstractTile
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

    public GlobalVariables.Feature getTopFeature() { return getTargetFeature(GlobalVariables.Direction.NORTH); }

    public GlobalVariables.Feature getBottomFeature() { return getTargetFeature(GlobalVariables.Direction.SOUTH); }

    public GlobalVariables.Feature getLeftFeature() { return getTargetFeature(GlobalVariables.Direction.WEST); }

    public GlobalVariables.Feature getRightFeature() { return getTargetFeature(GlobalVariables.Direction.EAST); }

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

    /* Some locations for placing graphics */

    /**
     * Calculates the appropriate pixel at which to place an object in the center of a tile
     * @param objectSize the size of the object being placed
     * @return the x or y value of the object's location
     */
    private int getHalfwayLocation(int objectSize) {
        return (TILE_PIXEL_SIZE / 2) - objectSize;
    }

    /**
     * Calculates the pixel at which to place an object to the far right or bottom of a tile
     * @return the x or y value of the object's location
     */
    private int getBottomLocation() {
        return TILE_PIXEL_SIZE - TILE_INNER_MARGIN;
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
           // int w2 = (this.getIconWidth() % 2) == 0 ? 0 : -1;
            g2d.translate(x + w1, y + w1);
            g2d.rotate(rotation * Math.PI * .5);
            super.paintIcon(component, g2d, -w1, -w1);
        }
    }

    public AdjustableIcon getIcon() {
        return new AdjustableIcon(this.getAdjustedImage(), this.rotation);
    }

    /**
     *
     * @return
     */
    public boolean hasNSbisector() {
        if((rotation == 1 || rotation == 3) && this.getInternals().contains(GlobalVariables.Internal.EWBISECTOR))
            return true;
        else if((rotation == 0|| rotation == 2) && this.getInternals().contains(GlobalVariables.Internal.NSBISECTOR))
            return true;
        else
            return false;
    }

    /**
     *
     * @return
     */
    public boolean hasEWbisector() {
        if((rotation == 1 || rotation == 3) && this.getInternals().contains(GlobalVariables.Internal.NSBISECTOR))
            return true;
        else if((rotation == 0 || rotation == 2) && this.getInternals().contains(GlobalVariables.Internal.EWBISECTOR))
            return true;
        else
            return false;
    }
}
