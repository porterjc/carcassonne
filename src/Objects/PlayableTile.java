package Objects;

import Main.GlobalVariables;
import UIComponents.PlaceAbbotButton;
import UIComponents.PlaceMeepleButton;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by robinsat on 4/1/2015.
 */
public class PlayableTile extends AbstractTile {
    //TODO only allow one type of constructor
    private BufferedImage image;
    private int rotation = 0; //represents how much much this tile has been rotated. 0 is the default value before rotations happen;
    private Meeple meeple = null;

    public static final int TILE_INNER_MARGIN = 5;
    public static final int GARDEN_OFFSET = 35;

    public PlayableTile() {
        super();
    }

    public PlayableTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals) {
        super(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        super.isPlayable = true;
    }

    public PlayableTile(AbstractTile o, AbstractTile o1, AbstractTile o2, AbstractTile o3, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        super(o, o1, o2, o3, features);
        super.isPlayable = true;
    }

    public PlayableTile(AbstractTile o, AbstractTile o1, AbstractTile o2, AbstractTile o3, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals) {
        super(o, o1, o2, o3, features, internals);
        super.isPlayable = true;
    }

    public PlayableTile(AbstractTile o, AbstractTile o1, AbstractTile o2, AbstractTile o3, BufferedImage image, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals) {
        super(o, o1, o2, o3, features, internals);
        super.isPlayable = true;
        this.image = image;
    }


    public PlayableTile(BufferedImage image, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        super(features);
        this.image = image;
        Image scaled = image.getScaledInstance(AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE, Image.SCALE_DEFAULT);
        super.isPlayable = true;
        this.setIcon(new ImageIcon(scaled));
    }

    public PlayableTile(BufferedImage image, HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals) {
        super(features, internals);
        super.isPlayable = true;
        this.image = image;
    }

    public PlayableTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features) {
        super(features);
        super.isPlayable = true;
    }

    public PlayableTile(HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features, Set<GlobalVariables.Internal> internals, Meeple m) {
        super(features, internals);
        super.isPlayable = true;
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
        return raw.getScaledInstance(AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE, Image.SCALE_DEFAULT);
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
    public void addMeepleButtons(Player currentPlayer) {
        int half = getHalfwayLocation(PlaceMeepleButton.BUTTON_SIZE);
        int far = getBottomLocation(PlaceMeepleButton.BUTTON_SIZE);

        // Center (Pretty much only for monasteries)
        if (this.getInternals().contains(GlobalVariables.Internal.MONASTERY))
            this.add(new PlaceMeepleButton(null, GlobalVariables.Internal.MONASTERY, currentPlayer, GlobalVariables.Location.CENTER, half, half));
        if (this.getInternals().contains(GlobalVariables.Internal.GARDEN))
            this.add(new PlaceAbbotButton(GlobalVariables.Internal.GARDEN, currentPlayer, this.rotation));

        GlobalVariables.Feature t = getTopFeature();
        GlobalVariables.Feature l = getLeftFeature();
        GlobalVariables.Feature r = getRightFeature();
        GlobalVariables.Feature b = getBottomFeature();

        //Edges
        if (shouldHaveButton(GlobalVariables.Direction.NORTH)) { //Top
            if(this.getInternals().contains(GlobalVariables.Internal.GARDEN) && rotation == 1)
                this.add(new PlaceMeepleButton(t, null, currentPlayer, GlobalVariables.Location.TOP, half, TILE_INNER_MARGIN));
            else
                this.add(new PlaceMeepleButton(t, null, currentPlayer, GlobalVariables.Location.TOP, half, TILE_INNER_MARGIN));
        }
        if (shouldHaveButton(GlobalVariables.Direction.WEST)) { //Left
            if(this.getInternals().contains(GlobalVariables.Internal.GARDEN) && rotation == 0)
                this.add(new PlaceMeepleButton(l, null, currentPlayer, GlobalVariables.Location.LEFT, TILE_INNER_MARGIN, half - GARDEN_OFFSET));
            else
                this.add(new PlaceMeepleButton(l, null, currentPlayer, GlobalVariables.Location.LEFT, TILE_INNER_MARGIN, half));
        }
        if (shouldHaveButton(GlobalVariables.Direction.EAST)) { //Right
            if(this.getInternals().contains(GlobalVariables.Internal.GARDEN) && rotation == 2)
                this.add(new PlaceMeepleButton(r, null, currentPlayer, GlobalVariables.Location.RIGHT, far, half));
            else
                this.add(new PlaceMeepleButton(r, null, currentPlayer, GlobalVariables.Location.RIGHT, far, half));
        }
        if (shouldHaveButton(GlobalVariables.Direction.SOUTH)) { //Bottom
            if(this.getInternals().contains(GlobalVariables.Internal.GARDEN) && rotation == 3)
                this.add(new PlaceMeepleButton(b, null, currentPlayer, GlobalVariables.Location.BOTTOM, half, far));
            else
                this.add(new PlaceMeepleButton(r, null, currentPlayer, GlobalVariables.Location.RIGHT, far, half));
        }

        //Corners
        if ((t == GlobalVariables.Feature.ROAD || t == GlobalVariables.Feature.RIVER) && (l == GlobalVariables.Feature.ROAD || l == GlobalVariables.Feature.RIVER) && !findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.TOPLEFT))
            this.add(new PlaceMeepleButton(GlobalVariables.Feature.GRASS, null, currentPlayer, GlobalVariables.Location.TOPLEFT, TILE_INNER_MARGIN, TILE_INNER_MARGIN)); //Top Left
        if ((t == GlobalVariables.Feature.ROAD || t == GlobalVariables.Feature.RIVER) && (r == GlobalVariables.Feature.ROAD || r == GlobalVariables.Feature.RIVER) && !findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.TOPRIGHT))
            this.add(new PlaceMeepleButton(GlobalVariables.Feature.GRASS, null, currentPlayer, GlobalVariables.Location.TOPRIGHT, far, TILE_INNER_MARGIN)); // Top Right
        if ((b == GlobalVariables.Feature.ROAD || b == GlobalVariables.Feature.RIVER) && (l == GlobalVariables.Feature.ROAD || l == GlobalVariables.Feature.RIVER) && !findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.BOTTOMLEFT))
            this.add(new PlaceMeepleButton(GlobalVariables.Feature.GRASS, null, currentPlayer, GlobalVariables.Location.BOTTOMLEFT, TILE_INNER_MARGIN, far)); // Bottom Left
        if ((b == GlobalVariables.Feature.ROAD || b == GlobalVariables.Feature.RIVER) && (r == GlobalVariables.Feature.ROAD || r == GlobalVariables.Feature.RIVER) && !findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.BOTTOMRIGHT))
            this.add(new PlaceMeepleButton(GlobalVariables.Feature.GRASS, null, currentPlayer, GlobalVariables.Location.BOTTOMRIGHT, far, far)); // Bottom Right

       /* this.add(new UIComponents.PlaceMeepleButton(Main.GlobalVariables.redMeeple, TILE_INNER_MARGIN, TILE_INNER_MARGIN));
        this.add(new UIComponents.PlaceMeepleButton(Main.GlobalVariables.redMeeple, half, TILE_INNER_MARGIN));
        this.add(new UIComponents.PlaceMeepleButton(Main.GlobalVariables.redMeeple, far, TILE_INNER_MARGIN));
        this.add(new UIComponents.PlaceMeepleButton(Main.GlobalVariables.redMeeple, TILE_INNER_MARGIN, half));
        this.add(new UIComponents.PlaceMeepleButton(Main.GlobalVariables.redMeeple, half, half));
        this.add(new UIComponents.PlaceMeepleButton(Main.GlobalVariables.redMeeple, far, half));
        this.add(new UIComponents.PlaceMeepleButton(Main.GlobalVariables.redMeeple, TILE_INNER_MARGIN, far));
        this.add(new UIComponents.PlaceMeepleButton(Main.GlobalVariables.redMeeple, half, far));
        this.add(new UIComponents.PlaceMeepleButton(Main.GlobalVariables.redMeeple, far, far)); */
    }

    private boolean shouldHaveButton(GlobalVariables.Direction dir) {
        GlobalVariables.Feature feat = getTargetFeature(dir);
        switch (feat) {
            case GRASS:
                return !findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER);
            case ROAD:
                return false;
            case CITY:
                HashSet<GlobalVariables.Direction> directions = new HashSet<>();
                if(this.getInternals().contains(GlobalVariables.Internal.CITY)) {
                    if(getTopFeature() == GlobalVariables.Feature.CITY)
                        directions.add(GlobalVariables.Direction.NORTH);
                    if(getLeftFeature() == GlobalVariables.Feature.CITY)
                        directions.add(GlobalVariables.Direction.WEST);
                    if(getRightFeature() == GlobalVariables.Feature.CITY)
                        directions.add(GlobalVariables.Direction.EAST);
                    if(getBottomFeature() == GlobalVariables.Feature.CITY)
                        directions.add(GlobalVariables.Direction.SOUTH);
                }
                else
                    directions.add(dir);
                Pair<HashSet<Meeple>, Integer> cityData = startScoreCity(directions, true);
                if (cityData.getValue() > 0 || !cityData.getKey().isEmpty())
                    return false;
                return true;
        }
        return false;
    }


    private void addMeeple(Set<Meeple> meeples, GlobalVariables.Location local, boolean isRoadBlocked) {
        Meeple tileM = this.getMeeple();
        if (tileM != null) {//TODO what Was I using isRoadBlocked For
            if (tileM.getFeature() == GlobalVariables.Feature.ROAD)
                if (tileM.getLocation() == local)
                    meeples.add(tileM);
        }
    }


    public Pair<Set<Meeple>, Integer> startScoreRoad(boolean isEndOfGame) {
        int currentScore = 0;
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Set<Meeple> meeples = new HashSet<Meeple>();

        alreadyVisited.add(this);
        Pair<HashSet<Meeple>, Integer> score = null;
        //only score if a roadstop is placed
        if(this.getInternals().contains(GlobalVariables.Internal.ROADSTOP)) {
            if(this.meeple!=null){
                meeples.add(this.meeple);
            }
            if (this.featuresMap.get(GlobalVariables.Direction.NORTH) == GlobalVariables.Feature.ROAD) {
                score = this.getTop().scoreCity(alreadyVisited, meeples, false);
            }
            if (this.featuresMap.get(GlobalVariables.Direction.SOUTH) == GlobalVariables.Feature.ROAD) {
                currentScore = 33;
            }
            if (this.featuresMap.get(GlobalVariables.Direction.EAST) == GlobalVariables.Feature.ROAD) {
                currentScore += 10;
            }
            if (this.featuresMap.get(GlobalVariables.Direction.WEST) == GlobalVariables.Feature.ROAD) {
                currentScore += 10;
            }
        }
        //todo add scoring here
        return new Pair(score.getKey(), currentScore + score.getValue());
    }

    @Override
    public Pair<Set<Meeple>, Integer> scoreRoad(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples, boolean completion) {
        int currentscore = 1;
        Map<GlobalVariables.Direction, GlobalVariables.Feature> features = this.getFeatures();
        alreadyVisited.add(this);
        if (this.getInternals().contains(GlobalVariables.Internal.ROADSTOP) && alreadyVisited.size() > 1) {
            addMeeple(meeples, GlobalVariables.Location.BOTTOM, true);
            return new Pair<>(meeples, currentscore);
        }
        return new Pair<Set<Meeple>, Integer>(meeples, currentscore);
    }

    /**
     * startScoreCity runs scoreCity in all given directions and then returns the total score and list of meeples found
     *
     * @param completion
     * @return
     */
    public Pair<HashSet<Meeple>, Integer> startScoreCity(Set<GlobalVariables.Direction> directions, boolean completion) {
        int currentScore = 2;
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Set<Meeple> meeples = new HashSet<Meeple>();
        alreadyVisited.add(this);

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
        if(currentScore == 1){
            currentScore = -1;
        }
        if (this.getInternals().contains(GlobalVariables.Internal.COATOFARMS))
            currentScore += 2;
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
    public Pair<HashSet<Meeple>, Integer> scoreCity(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples, boolean completion) {
        int cityScore = 2;
        alreadyVisited.add(this);
        Meeple meep = getMeeple();

        if (this.getInternals().contains(GlobalVariables.Internal.COATOFARMS))
            cityScore += 2;

        if (meep != null && meep.getFeature() == GlobalVariables.Feature.CITY)
            meeples.add(meep);

        if (!this.getInternals().contains(GlobalVariables.Internal.CITY)) {
            return new Pair(meeples, cityScore);
        } else {
            if ((!alreadyVisited.contains(this.getBottom())) && getTargetFeature(GlobalVariables.Direction.SOUTH) == GlobalVariables.Feature.CITY) {
                AbstractTile b = this.getBottom();
                int temp = b.scoreCity(alreadyVisited, meeples, completion).getValue();
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
                int temp = r.scoreCity(alreadyVisited, meeples, completion).getValue();
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
            return new Pair(meeples, cityScore);
        }
    }

    /**
     * If completion is true will ensure that there are 8 neighbors to complete the scoring. Otherwise returns total neighbors plus this tile.
     *
     * @param completion
     * @return
     */
    public int scoreSurrounding(boolean completion) {
        int neighbors = getTotalPlayableNeighbors();
        if (completion && neighbors != 8)
            return -1;
        return neighbors + 1;
    }

    /**
     * Looks at all 8 surrounding tiles to see if they are playable, adding 1 to the total return if they are.
     *
     * @return
     */
    public int getTotalPlayableNeighbors() {
        return getBottom().getValue() + getTop().getValue() + getRight().getValue() + getLeft().getValue() + getTopLeft().getValue() + getTopRight().getValue() + getBottomLeft().getValue() + getBottomRight().getValue();
    }

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public boolean findFarmer(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from) {
        alreadyVisited.add(this);

        if (this.meeple != null && this.meeple.getFeature() == GlobalVariables.Feature.GRASS) {
            if (isAdjacentToFarmer(from))
                return true;
        }

        //No meeple on this tile, so check others
        boolean found = false;

        if (!alreadyVisited.contains(this.getTop())) {
            GlobalVariables.Feature topFeature = this.getTopFeature();
            if (topFeature == GlobalVariables.Feature.GRASS)
                found = this.getTop().findFarmer(alreadyVisited, GlobalVariables.Location.BOTTOM);
            else if (topFeature == GlobalVariables.Feature.ROAD || topFeature == GlobalVariables.Feature.RIVER)
                found = this.getTop().findFarmer(alreadyVisited, GlobalVariables.Location.goDown(from));
        }
        if (found) return true;

        if (!alreadyVisited.contains(this.getBottom())) {
            GlobalVariables.Feature bottomFeature = this.getBottomFeature();
            if (bottomFeature == GlobalVariables.Feature.GRASS)
                found = this.getBottom().findFarmer(alreadyVisited, GlobalVariables.Location.TOP);
            else if (bottomFeature == GlobalVariables.Feature.ROAD || bottomFeature == GlobalVariables.Feature.RIVER)
                found = this.getBottom().findFarmer(alreadyVisited, GlobalVariables.Location.goUp(from));
        }
        if (found) return true;

        if (!alreadyVisited.contains(this.getLeft())) {
            GlobalVariables.Feature leftFeature = this.getLeftFeature();
            if (leftFeature == GlobalVariables.Feature.GRASS)
                found = this.getLeft().findFarmer(alreadyVisited, GlobalVariables.Location.RIGHT);
            else if (leftFeature == GlobalVariables.Feature.ROAD || leftFeature == GlobalVariables.Feature.RIVER)
                found = this.getLeft().findFarmer(alreadyVisited, GlobalVariables.Location.goRight(from));
        }
        if (found) return true;

        if (!alreadyVisited.contains(this.getRight())) {
            GlobalVariables.Feature rightFeature = this.getRightFeature();
            if (rightFeature == GlobalVariables.Feature.GRASS)
                found = this.getRight().findFarmer(alreadyVisited, GlobalVariables.Location.LEFT);
            else if (rightFeature == GlobalVariables.Feature.ROAD || rightFeature == GlobalVariables.Feature.RIVER)
                found = this.getRight().findFarmer(alreadyVisited, GlobalVariables.Location.goLeft(from));

        }

        return found;
    }

    private boolean isAdjacentToFarmer(GlobalVariables.Location from) {
        GlobalVariables.Feature top = getTopFeature();
        GlobalVariables.Feature bottom = getBottomFeature();
        GlobalVariables.Feature left = getLeftFeature();
        GlobalVariables.Feature right = getRightFeature();

        if (GlobalVariables.Location.isTop(from)) {
            if (GlobalVariables.Location.isTop(meeple.getLocation())) {
                if (top == GlobalVariables.Feature.GRASS)
                    return true;
                else if (top == GlobalVariables.Feature.ROAD || top == GlobalVariables.Feature.RIVER)
                    // Are they on the same side of the river or road?
                    return GlobalVariables.Location.isLeft(from) == GlobalVariables.Location.isLeft(meeple.getLocation());
            } else if (hasEWbisector())
                return false;
            else {
                if (bottom == GlobalVariables.Feature.ROAD || bottom == GlobalVariables.Feature.RIVER) {
                    if ((left == GlobalVariables.Feature.ROAD || left == GlobalVariables.Feature.RIVER) && GlobalVariables.Location.isLeft(meeple.getLocation()))
                        return false;
                    else if ((right == GlobalVariables.Feature.ROAD || right == GlobalVariables.Feature.RIVER) && GlobalVariables.Location.isRight(meeple.getLocation()))
                        return false;
                    return GlobalVariables.Location.isLeft(from) == GlobalVariables.Location.isLeft(meeple.getLocation());
                }
                return true;
            }

        } else if (GlobalVariables.Location.isBottom(from)) {
            if (GlobalVariables.Location.isBottom(meeple.getLocation())) {
                if (bottom == GlobalVariables.Feature.GRASS)
                    return true;
                else if (bottom == GlobalVariables.Feature.ROAD || bottom == GlobalVariables.Feature.RIVER)
                    // Are they on the same side of the river or road?
                    return GlobalVariables.Location.isLeft(from) == GlobalVariables.Location.isLeft(meeple.getLocation());
            } else if (hasEWbisector())
                return false;
            else {
                if (top == GlobalVariables.Feature.ROAD || top == GlobalVariables.Feature.RIVER) {
                    if ((left == GlobalVariables.Feature.ROAD || left == GlobalVariables.Feature.RIVER) && GlobalVariables.Location.isLeft(meeple.getLocation()))
                        return false;
                    else if ((right == GlobalVariables.Feature.ROAD || right == GlobalVariables.Feature.RIVER) && GlobalVariables.Location.isRight(meeple.getLocation()))
                        return false;
                }
                return GlobalVariables.Location.isLeft(from) == GlobalVariables.Location.isLeft(meeple.getLocation());
            }
        } else if (GlobalVariables.Location.isLeft(from)) {
            if (GlobalVariables.Location.isLeft(meeple.getLocation())) {
                if (left == GlobalVariables.Feature.GRASS)
                    return true;
                else if (left == GlobalVariables.Feature.ROAD || left == GlobalVariables.Feature.RIVER)
                    // Are they on the same side of the river or road?
                    return GlobalVariables.Location.isTop(from) == GlobalVariables.Location.isTop(meeple.getLocation());
            } else if (hasNSbisector())
                return false;
            else {
                if (right == GlobalVariables.Feature.ROAD || right == GlobalVariables.Feature.RIVER) {
                    if ((top == GlobalVariables.Feature.ROAD || top == GlobalVariables.Feature.RIVER) && GlobalVariables.Location.isTop(meeple.getLocation()))
                        return false;
                    else if ((bottom == GlobalVariables.Feature.ROAD || bottom == GlobalVariables.Feature.RIVER) && GlobalVariables.Location.isBottom(meeple.getLocation()))
                        return false;
                }
                return GlobalVariables.Location.isTop(from) == GlobalVariables.Location.isTop(meeple.getLocation());
            }
        } else { // From right
            if (GlobalVariables.Location.isRight(meeple.getLocation())) {
                if (right == GlobalVariables.Feature.GRASS)
                    return true;
                else if (right == GlobalVariables.Feature.ROAD || right == GlobalVariables.Feature.RIVER)
                    // Are they on the same side of the river or road?
                    return GlobalVariables.Location.isTop(from) == GlobalVariables.Location.isTop(meeple.getLocation());
            } else if (hasNSbisector())
                return false;
            else {
                if (left == GlobalVariables.Feature.ROAD || left == GlobalVariables.Feature.RIVER) {
                    if ((top == GlobalVariables.Feature.ROAD || top == GlobalVariables.Feature.RIVER) && GlobalVariables.Location.isTop(meeple.getLocation()))
                        return false;
                    else if ((bottom == GlobalVariables.Feature.ROAD || bottom == GlobalVariables.Feature.RIVER) && GlobalVariables.Location.isBottom(meeple.getLocation()))
                        return false;
                }
                return GlobalVariables.Location.isTop(from) == GlobalVariables.Location.isTop(meeple.getLocation());
            }
        }

        return true;
    }

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

    public GlobalVariables.Feature getTopFeature() {
        return getTargetFeature(GlobalVariables.Direction.NORTH);
    }

    public GlobalVariables.Feature getBottomFeature() {
        return getTargetFeature(GlobalVariables.Direction.SOUTH);
    }

    public GlobalVariables.Feature getLeftFeature() {
        return getTargetFeature(GlobalVariables.Direction.WEST);
    }

    public GlobalVariables.Feature getRightFeature() {
        return getTargetFeature(GlobalVariables.Direction.EAST);
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

    /* Some locations for placing graphics */

    /**
     * Calculates the appropriate pixel at which to place an object in the center of a tile
     *
     * @param objectSize the size of the object being placed
     * @return the x or y value of the object's location
     */
    private int getHalfwayLocation(int objectSize) {
        return (AbstractTile.TILE_PIXEL_SIZE / 2) - (objectSize / 2);
    }

    /**
     * Calculates the pixel at which to place an object to the far right or bottom of a tile
     *
     * @return the x or y value of the object's location
     */
    private int getBottomLocation(int objectSize) {
        return AbstractTile.TILE_PIXEL_SIZE - TILE_INNER_MARGIN - objectSize;
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
     * @return
     */
    public boolean hasNSbisector() {
        if ((rotation == 1 || rotation == 3) && this.getInternals().contains(GlobalVariables.Internal.EWBISECTOR))
            return true;
        else
            return (rotation == 0 || rotation == 2) && this.getInternals().contains(GlobalVariables.Internal.NSBISECTOR);
    }

    /**
     * @return
     */
    public boolean hasEWbisector() {
        if ((rotation == 1 || rotation == 3) && this.getInternals().contains(GlobalVariables.Internal.NSBISECTOR))
            return true;
        else
            return (rotation == 0 || rotation == 2) && this.getInternals().contains(GlobalVariables.Internal.EWBISECTOR);
    }
}
