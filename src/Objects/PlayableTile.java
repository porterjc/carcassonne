package Objects;

import Main.GlobalVariables;
import UIComponents.PlaceAbbotButton;
import UIComponents.PlaceMeepleButton;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by robinsat on 4/1/2015.
 */
public class PlayableTile extends AbstractTile {
    //TODO only allow one type of constructor
    private BufferedImage image;
    private int rotation = 0; //represents how much much this tile has been rotated. 0 is the default value before rotations happen;
    private Meeple meeple = null;

    public static final int TILE_INNER_MARGIN = 5;
    public static final int GARDEN_OFFSET = 45;
    public static final int CITY_OFFSET = 25;

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

    public Map<GlobalVariables.Direction, GlobalVariables.Feature> getFeatures() {
        return featuresMap;
    }

    @Override
    public GlobalVariables.Direction updateAdjacent() {
        TileGrid grid = (TileGrid) this.getParent();
        OpenTile openTop = new OpenTile();
        OpenTile openLeft = new OpenTile();
        OpenTile openRight = new OpenTile();
        OpenTile openBottom = new OpenTile();
        GlobalVariables.Direction topdir = this.getTop().getClass() != OpenTile.class ? this.getTop().addTile(openTop) : null;
        GlobalVariables.Direction leftdir = this.getLeft().getClass() != OpenTile.class ? this.getLeft().addTile(openLeft) : null;
        GlobalVariables.Direction rightdir = this.getRight().getClass() != OpenTile.class ? this.getRight().addTile(openRight) : null;
        GlobalVariables.Direction bottomdir = this.getBottom().getClass() != OpenTile.class ? this.getBottom().addTile(openBottom) : null;

        if (topdir != null) {
            grid.addSlot(openTop);
        }
        if (leftdir != null) {
            grid.addSlot(openLeft);
        }
        if (rightdir != null) {
            grid.addSlot(openRight);
        }
        if (bottomdir != null) {
            grid.addSlot(openBottom);
        }

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
    public void addMeepleButtons(Player currentPlayer, boolean abbot) {
        int half = getHalfwayLocation(PlaceMeepleButton.BUTTON_SIZE);
        int far = getBottomLocation(PlaceMeepleButton.BUTTON_SIZE);

        // Center (Pretty much only for monasteries)
        if (this.getInternals().contains(GlobalVariables.Internal.MONASTERY))
            this.add(new PlaceMeepleButton(null, GlobalVariables.Internal.MONASTERY, currentPlayer, GlobalVariables.Location.CENTER, half, half));
        if (abbot && this.getInternals().contains(GlobalVariables.Internal.GARDEN))
            this.add(new PlaceAbbotButton(GlobalVariables.Internal.GARDEN, currentPlayer, this.rotation));

        GlobalVariables.Feature t = getTopFeature();
        GlobalVariables.Feature l = getLeftFeature();
        GlobalVariables.Feature r = getRightFeature();
        GlobalVariables.Feature b = getBottomFeature();

        //Edges
        if (shouldHaveButton(GlobalVariables.Direction.NORTH)) { //Top
            if (!this.getInternals().contains(GlobalVariables.Internal.GARDEN) || rotation != 1)
                this.add(new PlaceMeepleButton(t, null, currentPlayer, GlobalVariables.Location.TOP, half, TILE_INNER_MARGIN));
            else if (l != GlobalVariables.Feature.GRASS)
                placeCornerButton(currentPlayer, GlobalVariables.Location.TOPLEFT, TILE_INNER_MARGIN, t == GlobalVariables.Feature.CITY ? TILE_INNER_MARGIN + CITY_OFFSET : TILE_INNER_MARGIN);
        }
        if (shouldHaveButton(GlobalVariables.Direction.WEST)) { //Left
            if (!this.getInternals().contains(GlobalVariables.Internal.GARDEN) || rotation != 0)
                this.add(new PlaceMeepleButton(l, null, currentPlayer, GlobalVariables.Location.LEFT, TILE_INNER_MARGIN, half));
            else if (t != GlobalVariables.Feature.GRASS)
                placeCornerButton(currentPlayer, GlobalVariables.Location.TOPLEFT, TILE_INNER_MARGIN, t == GlobalVariables.Feature.CITY ? TILE_INNER_MARGIN + CITY_OFFSET : TILE_INNER_MARGIN);
        }
        if (shouldHaveButton(GlobalVariables.Direction.EAST)) { //Right
            if (!this.getInternals().contains(GlobalVariables.Internal.GARDEN) || rotation != 2)
                this.add(new PlaceMeepleButton(r, null, currentPlayer, GlobalVariables.Location.RIGHT, far, half));
            else if (b != GlobalVariables.Feature.GRASS)
                placeCornerButton(currentPlayer, GlobalVariables.Location.BOTTOMRIGHT, far, b == GlobalVariables.Feature.CITY ? far - CITY_OFFSET : far);
        }
        if (shouldHaveButton(GlobalVariables.Direction.SOUTH)) { //Bottom
            if (!this.getInternals().contains(GlobalVariables.Internal.GARDEN) || rotation != 3)
                this.add(new PlaceMeepleButton(b, null, currentPlayer, GlobalVariables.Location.BOTTOM, half, far));
            else if (r != GlobalVariables.Feature.GRASS)
                placeCornerButton(currentPlayer, GlobalVariables.Location.TOPLEFT, far, b == GlobalVariables.Feature.CITY ? far - CITY_OFFSET : far);
        }

        //Corners
        if (shouldHaveCornerButton(t, l, GlobalVariables.Location.TOPLEFT)) {
            if (t == GlobalVariables.Feature.CITY)
                placeCornerButton(currentPlayer, GlobalVariables.Location.TOPLEFT, TILE_INNER_MARGIN, TILE_INNER_MARGIN + CITY_OFFSET);
            else if (l == GlobalVariables.Feature.CITY)
                placeCornerButton(currentPlayer, GlobalVariables.Location.TOPLEFT, TILE_INNER_MARGIN + CITY_OFFSET, TILE_INNER_MARGIN);
            else
                placeCornerButton(currentPlayer, GlobalVariables.Location.TOPLEFT, TILE_INNER_MARGIN, TILE_INNER_MARGIN);
        }
        if (shouldHaveCornerButton(t, r, GlobalVariables.Location.TOPRIGHT)) {
            if (t == GlobalVariables.Feature.CITY)
                placeCornerButton(currentPlayer, GlobalVariables.Location.TOPRIGHT, far, TILE_INNER_MARGIN + CITY_OFFSET);
            else if (r == GlobalVariables.Feature.CITY)
                placeCornerButton(currentPlayer, GlobalVariables.Location.TOPRIGHT, far - CITY_OFFSET, TILE_INNER_MARGIN);
            else
                placeCornerButton(currentPlayer, GlobalVariables.Location.TOPRIGHT, far, TILE_INNER_MARGIN);
        }
        if (shouldHaveCornerButton(b, l, GlobalVariables.Location.BOTTOMLEFT)) {
            if (b == GlobalVariables.Feature.CITY)
                placeCornerButton(currentPlayer, GlobalVariables.Location.BOTTOMLEFT, TILE_INNER_MARGIN, far - CITY_OFFSET);
            else if (l == GlobalVariables.Feature.CITY)
                placeCornerButton(currentPlayer, GlobalVariables.Location.BOTTOMLEFT, TILE_INNER_MARGIN + CITY_OFFSET, far);
            else
                placeCornerButton(currentPlayer, GlobalVariables.Location.BOTTOMLEFT, TILE_INNER_MARGIN, far);
        }
        if (shouldHaveCornerButton(b, r, GlobalVariables.Location.BOTTOMRIGHT)) {
            if (b == GlobalVariables.Feature.CITY)
                placeCornerButton(currentPlayer, GlobalVariables.Location.BOTTOMRIGHT, far, far - CITY_OFFSET);
            else if (r == GlobalVariables.Feature.CITY)
                placeCornerButton(currentPlayer, GlobalVariables.Location.BOTTOMRIGHT, far - CITY_OFFSET, far);
            else
                placeCornerButton(currentPlayer, GlobalVariables.Location.BOTTOMRIGHT, far, far);
        }
    }

    /**
     * Determines whether a button should be placed on the edge
     *
     * @param dir the direction of the edge being examined
     * @return true if a button can be placed on the given edge
     */
    private boolean shouldHaveButton(GlobalVariables.Direction dir) {
        GlobalVariables.Feature feat = getTargetFeature(dir);
        HashSet<GlobalVariables.Direction> directions = new HashSet<>();
        List<GlobalVariables.Direction> directionz = new ArrayList<>();
        directions.add(dir);
        switch (feat) {
            case GRASS:
                return !hasFarmer(dir.getLocation());
            case ROAD:
                if (this.getFeatures().get(dir) == GlobalVariables.Feature.ROAD)
                    return true;
            case CITY:
                directions = new HashSet<>();// TODO Alia Check this-- it through an error for double duplication so I deleted the first part
                if (this.getInternals().contains(GlobalVariables.Internal.CITY)) {
                    if (getTopFeature() == GlobalVariables.Feature.CITY)
                        directions.add(GlobalVariables.Direction.NORTH);
                    if (getLeftFeature() == GlobalVariables.Feature.CITY)
                        directions.add(GlobalVariables.Direction.WEST);
                    if (getRightFeature() == GlobalVariables.Feature.CITY)
                        directions.add(GlobalVariables.Direction.EAST);
                    if (getBottomFeature() == GlobalVariables.Feature.CITY)
                        directions.add(GlobalVariables.Direction.SOUTH);
                } else
                    directions.add(dir);
                Pair<HashSet<Meeple>, Integer> cityData = startScoreCity(directions, true);
                return !(cityData.getValue() > 0 || !cityData.getKey().isEmpty());
        }
        return false;
    }


    /**
     * Determines whether a button should be placed in the given corner
     *
     * @param f1  The feature on one edge of the corner
     * @param f2  The feature on the other edge of the corner
     * @param loc The location of the corner
     * @return true if a button should be placed on the corner
     */
    private boolean shouldHaveCornerButton(GlobalVariables.Feature f1, GlobalVariables.Feature f2, GlobalVariables.Location loc) {
        return !(f1 == GlobalVariables.Feature.GRASS || f2 == GlobalVariables.Feature.GRASS) && (f1 != GlobalVariables.Feature.CITY || f2 != GlobalVariables.Feature.CITY) && !hasFarmer(loc);
    }


    private void placeCornerButton(Player currentPlayer, GlobalVariables.Location loc, int x, int y) {
        this.add(new PlaceMeepleButton(GlobalVariables.Feature.GRASS, null, currentPlayer, loc, x, y));
    }


    private void addMeeple(Set<Meeple> meeples, GlobalVariables.Location local) {
        Meeple tileM = this.getMeeple();
        if (tileM != null) {
            if (tileM.getFeature() == GlobalVariables.Feature.ROAD)
                if (tileM.getLocation() == local)
                    meeples.add(tileM);
        }
    }

    /**
     * Begins scoring process for roads and updates player's scores after calculation
     * Also, this method removes Meeples
     * TODO may change the return value to only be a set of Meeples
     *
     * @param isEndOfGame
     * @return the complete list of meeples to remove from the UI
     */
    public Pair<Set<Meeple>, Integer> startScoreRoad(boolean isEndOfGame) {
        int currentScore = 1;
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        final Set<Meeple> meeples = new HashSet<Meeple>();
        alreadyVisited.add(this);
        Pair<Set<Meeple>, Integer> score = null;
        //only score if a roadstop is placed
        if (this.getInternals().contains(GlobalVariables.Internal.ROADSTOP)) {
            if ((!alreadyVisited.contains(this.getTop())) && this.featuresMap.get(GlobalVariables.Direction.NORTH) == GlobalVariables.Feature.ROAD) {
                addMeeple(meeples, GlobalVariables.Location.TOP);
                score = scoreRoadHelperMethod(alreadyVisited, meeples, isEndOfGame, currentScore, this.getTop(), GlobalVariables.Direction.NORTH);
                score = getCorrectScore(currentScore, score);
                meeples.addAll(score.getKey());

                if (!isEndOfGame && (score.getValue() + currentScore) < 1)
                    meeples.clear();
                currentScore += score.getValue();
            }
            currentScore = 1;
            if (!alreadyVisited.contains(this.getBottom()) && this.featuresMap.get(GlobalVariables.Direction.SOUTH) == GlobalVariables.Feature.ROAD) {
                addMeeple(meeples, GlobalVariables.Location.BOTTOM);
                score = scoreRoadHelperMethod(alreadyVisited, meeples, isEndOfGame, currentScore, getBottom(), GlobalVariables.Direction.SOUTH);
                score = getCorrectScore(currentScore, score);
                currentScore += score.getValue();
                meeples.addAll(score.getKey());
                if (!isEndOfGame && score.getValue() < 1)
                    meeples.clear();

            }
            currentScore = 1;
            if ((!alreadyVisited.contains(this.getRight())) && this.featuresMap.get(GlobalVariables.Direction.EAST) == GlobalVariables.Feature.ROAD) {
                addMeeple(meeples, GlobalVariables.Location.RIGHT);
                score = scoreRoadHelperMethod(alreadyVisited, meeples, isEndOfGame, currentScore, getRight(), GlobalVariables.Direction.EAST);
                score = getCorrectScore(currentScore, score);
                meeples.addAll(score.getKey());
                if (!isEndOfGame && (score.getValue() + currentScore) < 1)
                    meeples.clear();
                currentScore += score.getValue();
            }
            currentScore = 1;
            if ((!alreadyVisited.contains(this.getLeft())) && this.featuresMap.get(GlobalVariables.Direction.WEST) == GlobalVariables.Feature.ROAD) {
                addMeeple(meeples, GlobalVariables.Location.LEFT);
                score = scoreRoadHelperMethod(alreadyVisited, meeples, isEndOfGame, currentScore, getLeft(), GlobalVariables.Direction.WEST);
                score = getCorrectScore(currentScore, score);
                currentScore += score.getValue();
                meeples.addAll(score.getKey());
                if (!isEndOfGame && (score.getValue() + currentScore) < 1)
                    meeples.clear();
            }
        }

        return new Pair(meeples, currentScore + (score == null? 0 :score.getValue()));
    }

    private Pair<Set<Meeple>, Integer> getCorrectScore(int currentScore, Pair<Set<Meeple>, Integer> score) {
        if (score.getValue() + currentScore > 1) {
            scoreUpdate(currentScore, score);
        } else {
            score = new Pair<>(score.getKey(), 0);
        }
        return score;
    }

    private Pair<Set<Meeple>, Integer> scoreRoadHelperMethod(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples, boolean isEndOfGame, int currentTileScore, AbstractTile t, GlobalVariables.Direction dir) {
        Pair<Set<Meeple>, Integer> temp = t.scoreRoad(alreadyVisited, meeples, isEndOfGame, dir);
        if (isEndOfGame && temp.getValue() == -1) {
            return new Pair(meeples, 0);
        } else if (temp.getValue() == -1) {
            return new Pair(meeples, -1);
        } else {
            return new Pair(meeples, temp.getValue());
        }
    }

    private void scoreUpdate(int currentScore, Pair<Set<Meeple>, Integer> score) {
        int points = score.getValue() + currentScore;
        for (Meeple m : score.getKey()) {
            m.getPlayer().updateScore(points);
            m.remove();
        }
    }

    @Override
    public Pair<Set<Meeple>, Integer> scoreRoad(Set<AbstractTile> alreadyVisited, Set<Meeple> meeples, boolean isEndofGame, GlobalVariables.Direction prevDirection) {
        int currentscore = 1;
        Pair<Set<Meeple>, Integer> score;
        Map<GlobalVariables.Direction, GlobalVariables.Feature> features = this.getFeatures();
        alreadyVisited.add(this);
        if (prevDirection != null)
            addMeepleIfOnConnectingFeature(meeples, prevDirection);

        if (this.getInternals().contains(GlobalVariables.Internal.ROADSTOP) && alreadyVisited.size() > 1) {
            return new Pair<>(meeples, currentscore);
        }
        if (this.featuresMap.get(GlobalVariables.Direction.NORTH) == GlobalVariables.Feature.ROAD && !alreadyVisited.contains(this.getTop())) {
            addMeeple(meeples, GlobalVariables.Location.TOP);
            score = scoreRoadHelperMethod(alreadyVisited, meeples, isEndofGame, currentscore, this.getTop(), GlobalVariables.Direction.NORTH);
            currentscore += score.getValue();
        }
        if (this.featuresMap.get(GlobalVariables.Direction.SOUTH) == GlobalVariables.Feature.ROAD) {
            addMeeple(meeples, GlobalVariables.Location.BOTTOM);
            score = scoreRoadHelperMethod(alreadyVisited, meeples, isEndofGame, currentscore, this.getBottom(), GlobalVariables.Direction.SOUTH);
            currentscore += score.getValue();
        }
        if (this.featuresMap.get(GlobalVariables.Direction.EAST) == GlobalVariables.Feature.ROAD) {
            addMeeple(meeples, GlobalVariables.Location.RIGHT);
            score = scoreRoadHelperMethod(alreadyVisited, meeples, isEndofGame, currentscore, this.getRight(), GlobalVariables.Direction.EAST);
            currentscore += score.getValue();

        }
        if (this.featuresMap.get(GlobalVariables.Direction.WEST) == GlobalVariables.Feature.ROAD) {
            addMeeple(meeples, GlobalVariables.Location.LEFT);
            score = scoreRoadHelperMethod(alreadyVisited, meeples, isEndofGame, currentscore, this.getLeft(), GlobalVariables.Direction.WEST);
            currentscore += score.getValue();
        }
        return new Pair<>(meeples, currentscore);
    }

    private void addMeepleIfOnConnectingFeature(Set<Meeple> meeples, GlobalVariables.Direction prevDirection) {
        if (prevDirection == GlobalVariables.Direction.EAST) {
            addMeeple(meeples, GlobalVariables.Location.LEFT);
        } else if (prevDirection == GlobalVariables.Direction.WEST) {
            addMeeple(meeples, GlobalVariables.Location.RIGHT);
        } else if (prevDirection == GlobalVariables.Direction.SOUTH) {
            addMeeple(meeples, GlobalVariables.Location.TOP);
        } else if (prevDirection == GlobalVariables.Direction.NORTH) {
            addMeeple(meeples, GlobalVariables.Location.BOTTOM);
        }
    }


    /**
     * startScoreCity runs scoreCity in all given directions and then returns the total score and list of meeples found
     *
     * @param completion
     * @return
     */
    public Pair<HashSet<Meeple>, Integer> startScoreCity(Set<GlobalVariables.Direction> directions, boolean completion) {
        int currentScore = 2;
        Meeple meep = this.getMeeple();
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        HashSet<Meeple> meeples = new HashSet<Meeple>();
        alreadyVisited.add(this);

        if (meep != null && meep.getFeature() == GlobalVariables.Feature.CITY)
            if ((directions.contains(GlobalVariables.Direction.NORTH) && meep.getLocation() == GlobalVariables.Location.TOP) || (directions.contains(GlobalVariables.Direction.WEST) && meep.getLocation() == GlobalVariables.Location.LEFT)
                    || ((directions.contains(GlobalVariables.Direction.EAST) && meep.getLocation() == GlobalVariables.Location.RIGHT)) ||
                    (directions.contains(GlobalVariables.Direction.SOUTH) && meep.getLocation() == GlobalVariables.Location.BOTTOM) || (directions.contains(GlobalVariables.Internal.CITY) && meep.getLocation() == GlobalVariables.Location.CENTER)) {
                meeples.add(meep);
            }

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
        if (currentScore <= 3) {
            currentScore = -1;
            return new Pair(meeples, currentScore);
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
    public Pair<HashSet<Meeple>, Integer> scoreCity(Set<AbstractTile> alreadyVisited, HashSet<Meeple> meeples, boolean completion) {
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

    /**
     * Runs trace field to determine whether a farmer exists in the field
     * @param loc the location to start
     * @return whether a farmer exists in the field
     */
    public boolean hasFarmer(GlobalVariables.Location loc) {
        return !traceField(new HashSet<AbstractTile>(), loc, new HashSet<Meeple>(), new HashSet<Pair<HashSet<Meeple>, Integer>>(), false).getKey().isEmpty();
    }

    @Override
    public Pair<Set<Meeple>, Set<Pair<HashSet<Meeple>, Integer>>> traceField(Set<AbstractTile> alreadyVisited, GlobalVariables.Location from, Set<Meeple> farmers, Set<Pair<HashSet<Meeple>, Integer>> cities, boolean gameOver) {
        alreadyVisited.add(this);

        if (this.meeple != null && this.meeple.getFeature() == GlobalVariables.Feature.GRASS) {
            if (isOnSameSideOfRoad(from, this.meeple.getLocation())) {
                farmers.add(this.meeple);
                if(!gameOver)
                    return new Pair<>(farmers, cities);
            }
        }

        //No meeple on this tile, so check others
        Pair<Set<Meeple>, Set<Pair<HashSet<Meeple>, Integer>>> found = null;

        GlobalVariables.Feature topFeature = this.getTopFeature();
        if (!alreadyVisited.contains(this.getTop()) && topFeature == GlobalVariables.Feature.GRASS && isOnSameSideOfRoad(from, GlobalVariables.Location.TOP))
            found = this.getTop().traceField(alreadyVisited, GlobalVariables.Location.BOTTOM, farmers, cities, false);
        else if (topFeature == GlobalVariables.Feature.ROAD || topFeature == GlobalVariables.Feature.RIVER){
            if(from == GlobalVariables.Location.TOPLEFT && isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.TOPRIGHT))
                found = this.getTop().traceField(new HashSet<AbstractTile>(), GlobalVariables.Location.BOTTOMRIGHT, farmers, cities, gameOver);
            else if(from == GlobalVariables.Location.TOPRIGHT && isOnSameSideOfRoad(GlobalVariables.Location.TOPRIGHT, GlobalVariables.Location.TOPLEFT))
                found = this.getTop().traceField(new HashSet<AbstractTile>(), GlobalVariables.Location.BOTTOMLEFT, farmers, cities, gameOver);
            else if (!alreadyVisited.contains(this.getTop())){
                if (isOnSameSideOfRoad(from, GlobalVariables.Location.TOPRIGHT)) {
                    HashSet<AbstractTile> temp = new HashSet<>();
                    temp.addAll(alreadyVisited);
                    found = this.getTop().traceField(temp, GlobalVariables.Location.BOTTOMRIGHT, farmers, cities, false);
                }
                if (isOnSameSideOfRoad(from, GlobalVariables.Location.TOPLEFT)) {
                    found = this.getTop().traceField(alreadyVisited, GlobalVariables.Location.BOTTOMLEFT, farmers, cities, false);
                }
            }
        }
        else if (!alreadyVisited.contains(this.getTop()) && topFeature == GlobalVariables.Feature.CITY) {
            //TODO: Figure out how to score a city
        }
        if (found != null && !found.getKey().isEmpty() && !gameOver)
            return found;

     GlobalVariables.Feature bottomFeature = this.getBottomFeature();
        if (!alreadyVisited.contains(this.getBottom()) && bottomFeature == GlobalVariables.Feature.GRASS && isOnSameSideOfRoad(from, GlobalVariables.Location.BOTTOM))
            found = this.getBottom().traceField(alreadyVisited, GlobalVariables.Location.TOP, farmers, cities, false);
        else if (bottomFeature == GlobalVariables.Feature.ROAD || bottomFeature == GlobalVariables.Feature.RIVER) {
            if(from == GlobalVariables.Location.BOTTOMLEFT && isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMLEFT, GlobalVariables.Location.BOTTOMRIGHT))
                found = this.getTop().traceField(new HashSet<AbstractTile>(), GlobalVariables.Location.TOPRIGHT, farmers, cities, gameOver);
            else if(from == GlobalVariables.Location.BOTTOMRIGHT && isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMRIGHT, GlobalVariables.Location.BOTTOMLEFT))
                found = this.getTop().traceField(new HashSet<AbstractTile>(), GlobalVariables.Location.TOPLEFT, farmers, cities, gameOver);
            else if (!alreadyVisited.contains(this.getBottom())) {
                HashSet<AbstractTile> temp = new HashSet<>();
                temp.addAll(alreadyVisited);
                if (isOnSameSideOfRoad(from, GlobalVariables.Location.BOTTOMRIGHT)) {
                    found = this.getBottom().traceField(temp, GlobalVariables.Location.TOPRIGHT, farmers, cities, false);
                }
                if (isOnSameSideOfRoad(from, GlobalVariables.Location.BOTTOMLEFT)) {
                    found = this.getBottom().traceField(alreadyVisited, GlobalVariables.Location.TOPLEFT, farmers, cities, false);
                }
            }
        }
        if (found != null && !found.getKey().isEmpty() && !gameOver)
            return found;

        GlobalVariables.Feature leftFeature = this.getLeftFeature();
        if (!alreadyVisited.contains(this.getLeft()) && leftFeature == GlobalVariables.Feature.GRASS && isOnSameSideOfRoad(from, GlobalVariables.Location.LEFT))
            found = this.getLeft().traceField(alreadyVisited, GlobalVariables.Location.RIGHT, farmers, cities, false);
        else if (leftFeature == GlobalVariables.Feature.ROAD || leftFeature == GlobalVariables.Feature.RIVER) {
            if(from == GlobalVariables.Location.TOPLEFT && isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.BOTTOMLEFT))
                found = this.getLeft().traceField(new HashSet<AbstractTile>(), GlobalVariables.Location.BOTTOMRIGHT, farmers, cities, false);
            else if(from == GlobalVariables.Location.BOTTOMLEFT && isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.BOTTOMLEFT))
                found = this.getLeft().traceField(new HashSet<AbstractTile>(), GlobalVariables.Location.TOPRIGHT, farmers, cities, false);
            else if(!alreadyVisited.contains(this.getLeft())) {
                HashSet<AbstractTile> temp = new HashSet<>();
                temp.addAll(alreadyVisited);
                if (isOnSameSideOfRoad(from, GlobalVariables.Location.TOPLEFT)) {
                    found = this.getLeft().traceField(temp, GlobalVariables.Location.TOPRIGHT, farmers, cities, false);
                }
                if (isOnSameSideOfRoad(from, GlobalVariables.Location.BOTTOMLEFT)) {
                    found = this.getLeft().traceField(alreadyVisited, GlobalVariables.Location.BOTTOMRIGHT, farmers, cities, false);
                }
            }
        }
        if (found != null && !found.getKey().isEmpty() && !gameOver)
            return found;

        GlobalVariables.Feature rightFeature = this.getRightFeature();
        if (!alreadyVisited.contains(this.getRight()) && rightFeature == GlobalVariables.Feature.GRASS && isOnSameSideOfRoad(from, GlobalVariables.Location.RIGHT))
            found = this.getRight().traceField(alreadyVisited, GlobalVariables.Location.LEFT, farmers, cities, false);
        else if (rightFeature == GlobalVariables.Feature.ROAD || rightFeature == GlobalVariables.Feature.RIVER) {
            if(from == GlobalVariables.Location.TOPRIGHT && isOnSameSideOfRoad(GlobalVariables.Location.TOPRIGHT, GlobalVariables.Location.BOTTOMRIGHT))
                found = this.getRight().traceField(new HashSet<AbstractTile>(), GlobalVariables.Location.BOTTOMLEFT, farmers, cities, false);
            else if(from == GlobalVariables.Location.BOTTOMRIGHT && isOnSameSideOfRoad(GlobalVariables.Location.TOPRIGHT, GlobalVariables.Location.BOTTOMRIGHT))
                found = this.getRight().traceField(new HashSet<AbstractTile>(), GlobalVariables.Location.TOPLEFT, new HashSet<Meeple>(),cities, false);
            else if(!alreadyVisited.contains(this.getRight())) {
                HashSet<AbstractTile> temp = new HashSet<>();
                temp.addAll(alreadyVisited);
                if (isOnSameSideOfRoad(from, GlobalVariables.Location.TOPRIGHT)) {
                    found = this.getRight().traceField(temp, GlobalVariables.Location.TOPLEFT, farmers, cities, false);
                }
                if (isOnSameSideOfRoad(from, GlobalVariables.Location.BOTTOMRIGHT)) {
                    found = this.getRight().traceField(alreadyVisited, GlobalVariables.Location.BOTTOMLEFT, farmers, cities, false);
                }
            }

        }

        return found;
    }

    /**
     * Determines whether two locations are on the same side of a road
     *
     * @param loc1 the first location
     * @param loc2 the second location
     * @return true if the two locations are on the same side of a road, or false if there is a road between them
     */
    public boolean isOnSameSideOfRoad(GlobalVariables.Location loc1, GlobalVariables.Location loc2) {
        if (hasNSbisector() && GlobalVariables.Location.isLeft(loc1) != GlobalVariables.Location.isLeft(loc2))
            return false;
        if (hasEWbisector() && GlobalVariables.Location.isTop(loc1) != GlobalVariables.Location.isTop(loc2))
            return false;

        boolean rightSplit = getRightFeature() == GlobalVariables.Feature.ROAD || getRightFeature() == GlobalVariables.Feature.RIVER;
        boolean leftSplit = getLeftFeature() == GlobalVariables.Feature.ROAD || getLeftFeature() == GlobalVariables.Feature.RIVER;

        if (GlobalVariables.Location.isTop(loc1)) {
            if (getTopFeature() == GlobalVariables.Feature.ROAD || getTopFeature() == GlobalVariables.Feature.RIVER) {
                if (GlobalVariables.Location.isTop(loc2))
                    return (!leftSplit && !rightSplit) || GlobalVariables.Location.isLeft(loc1) == GlobalVariables.Location.isLeft(loc2);
                if (GlobalVariables.Location.isRight(loc1) && rightSplit)
                    return false;
                else if (GlobalVariables.Location.isLeft(loc1) && leftSplit)
                    return false;
            }
        }

        if (GlobalVariables.Location.isBottom(loc1)) {
            if (getBottomFeature() == GlobalVariables.Feature.ROAD || getBottomFeature() == GlobalVariables.Feature.RIVER) {
                if (GlobalVariables.Location.isBottom(loc2))
                    return (!leftSplit && !rightSplit) || GlobalVariables.Location.isLeft(loc1) == GlobalVariables.Location.isLeft(loc2);
                else if (GlobalVariables.Location.isRight(loc1) && rightSplit)
                    return false;
                else if (GlobalVariables.Location.isLeft(loc1) && leftSplit)
                    return false;
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

    /**
     * Removes the meeple from this tile
     */
    public void removeMeeple() {
        this.meeple = null;
        this.removeAll();
        this.repaint();
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
