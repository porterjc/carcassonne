package Objects;

import Main.GlobalVariables;
import Main.TileFactory;
import UIComponents.BottomDisplay;
import javafx.util.Pair;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.util.*;

/**
 * Created by johnsoaa on 3/25/2015.
 */
public class Game {
    private BottomDisplay bottomDisplay;
    private List<Player> players;
    private boolean gameOver;
    private boolean riverMode;
    private boolean abbotMode;
    private Stack<PlayableTile> tiles;
    private PlayableTile currentTile;
    private int currentTurn;
    private TurnState currentTurnState;
    private int numberOfOpenTilesOnBoard;

    /**
     * The meeples placed in monasteries that must be continuously checked for completion each time a tile is placed
     */
    private List<Meeple> monks;


    /**
     * Default constructor // not used
     *
     * @param display
     * @param playableTiles
     * @param players
     */
    public Game(BottomDisplay display, Stack<PlayableTile> playableTiles, ArrayList<Player> players) {
        this(display, playableTiles, players, false, false);
    }

    public Game(BottomDisplay bottomDisplay, Stack<PlayableTile> stack, ArrayList<Player> players, boolean river, boolean abbot) {
        this.bottomDisplay = bottomDisplay;
        if (players.size() > 1) {
            this.players = players;
        }
        riverMode = river;
        abbotMode = abbot;
        numberOfOpenTilesOnBoard = 0;
        currentTurn = 0;
        currentTurnState = TurnState.TILE_PLACEMENT;
        tiles = stack;
        gameOver = false;
        monks = new ArrayList<>();
        drawTile();
    }


    //TODO determine where to handle score

    public List<Player> getPlayers() {
        return players;
    }

    public int getNumberOfTilesLeft() {
        return tiles.size();
    }

    public boolean isGameOver() {
        if (tiles.size() == 0 && !isRiverMode()) {
            gameOver = true;
        }
        return gameOver;
    }

    /**
     * TODO 1. add functionality to pass an image url back to the UI
     *
     * @return
     */
    public boolean drawTile() {
        if (tiles.size() == 0) {
            if (isRiverMode()) {
                riverMode = false;
                finishRiver(currentTile);
                TileFactory factory = new TileFactory();
                tiles = factory.loadDeck(true);
                bottomDisplay.placedTileUpdate();
            } else
                return false;
        }

        currentTile = tiles.pop();
        TileGrid grid = (TileGrid) currentTile.getParent();
        if(grid != null){
            while(!grid.areValidMoves(currentTile)) {
                currentTile = tiles.pop();
            }
        }
        return true;
    }

    public PlayableTile getCurrentTile() {
        return currentTile;
    }


    public boolean moveToNextTurn() {
        if (isGameOver()) return false;
        if (this.currentTurn < this.players.size() - 1) {

            this.currentTurn++;
        } else
            this.currentTurn = 0;
        //Done add logic for switching to the next player in the GUI (getCurrentTurnPlayer & colors)

        //as we don't want too much coupling between the UI and the GAME class over sharing Objects.Player objects
        if (drawTile())
            bottomDisplay.finishedScoringUpdate();
        else {
            gameOver = true;
            currentTurnState = TurnState.GAME_OVER;
            endGame();
            return true;
        }
        return true;
    }

    public boolean moveToNextState() {
        if (isGameOver()) return false;
        boolean startScore = false;

        switch (currentTurnState) {
            case TILE_PLACEMENT:
                currentTurnState = TurnState.MEEPLE_PLACEMENT;
                bottomDisplay.placedTileUpdate();
                System.out.println("CURRENT: MEEPLE");
                if (getCurrentTurnPlayer().getMeeples().isEmpty())
                    return moveToNextState();
                else
                    return true;
            case MEEPLE_PLACEMENT:
                currentTurnState = TurnState.SCORING;
                bottomDisplay.placedMeepleUpdate();
                System.out.println("CURRENT: SCORING");
                startScore = true;
                break;
            case SCORING:
                currentTurnState = TurnState.TILE_PLACEMENT;
                System.out.println("CURRENT: TILE");
                return moveToNextTurn();
            case GAME_OVER:
                return false;
        }

        if (startScore)
            scoreCurrentTurn();

        return startScore;
    }

    public void begin() {
        this.gameOver = false;
    }

    public int getCurrentTurn() {
        return this.currentTurn;
    }

    public Player getCurrentTurnPlayer() {
        return players.get(currentTurn);
    }

    public boolean canPlaceMeeple() {
        return currentTurnState == TurnState.MEEPLE_PLACEMENT;
    }

    public String getCurrentStateText() {
        return this.currentTurnState.getText();
    }

    /**
     * Determines whether this game has river rules enabled
     *
     * @return whether this game has river rules enabled
     */
    public boolean isRiverMode() {
        return riverMode;
    }

    /**
     * Determines whether this game has abbot rules enabled
     *
     * @return whether this game has abbot rules enabled
     */
    public boolean isAbbotMode() {
        return abbotMode;
    }

    public void updateScore(Player p, int i) {
        p.updateScore(i);
    }

    private void scoreCurrentTurn() {
//        for (Meeple meeple : monks) {
//            int monasteryScore = meeple.getTile().scoreSurrounding(true);
//            if (monasteryScore > 0) {
//                meeple.getPlayer().updateScore(monasteryScore);
//                meeple.getTile().removeMeeple();
//            }
//        }

        Pair<Set<Meeple>, Integer> roads;
        roads = startScoreRoad(currentTile,isGameOver());
        //TODO add code for removing meeples on scored roads from UI

        Stack<Pair<HashSet<Meeple>, Integer>> cities = new Stack<>();
        HashSet<GlobalVariables.Direction> directions = new HashSet<>();
        if (currentTile.getInternals().contains(GlobalVariables.Internal.CITY)) {
            if (currentTile.getTopFeature() == GlobalVariables.Feature.CITY)
                directions.add(GlobalVariables.Direction.NORTH);
            if (currentTile.getLeftFeature() == GlobalVariables.Feature.CITY)
                directions.add(GlobalVariables.Direction.WEST);
            if (currentTile.getRightFeature() == GlobalVariables.Feature.CITY)
                directions.add(GlobalVariables.Direction.EAST);
            if (currentTile.getBottomFeature() == GlobalVariables.Feature.CITY)
                directions.add(GlobalVariables.Direction.SOUTH);
            cities.push(currentTile.startScoreCity(directions, true));
        } else {
            if (currentTile.getTopFeature() == GlobalVariables.Feature.CITY) {
                HashSet<GlobalVariables.Direction> direc = new HashSet<>();
                direc.add(GlobalVariables.Direction.NORTH);
                cities.push(currentTile.startScoreCity(direc, true));
            }
            if (currentTile.getLeftFeature() == GlobalVariables.Feature.CITY) {
                HashSet<GlobalVariables.Direction> direc = new HashSet<>();
                direc.add(GlobalVariables.Direction.WEST);
                cities.push(currentTile.startScoreCity(direc, true));
            }
            if (currentTile.getRightFeature() == GlobalVariables.Feature.CITY) {
                HashSet<GlobalVariables.Direction> direc = new HashSet<>();
                direc.add(GlobalVariables.Direction.EAST);
                cities.push(currentTile.startScoreCity(direc, true));
            }
            if (currentTile.getBottomFeature() == GlobalVariables.Feature.CITY) {
                HashSet<GlobalVariables.Direction> direc = new HashSet<>();
                direc.add(GlobalVariables.Direction.SOUTH);
                cities.push(currentTile.startScoreCity(direc, true));
            }
        }

        while (!cities.isEmpty()) {
            Pair<HashSet<Meeple>, Integer> city = cities.pop();
            if (city.getValue() > 0 && !city.getKey().isEmpty()) {
                for (Meeple m : city.getKey()) {
                    m.getPlayer().updateScore(city.getValue());
                    m.getTile().removeMeeple();
                }
            }
        }

        //TODO: Calculate who ACTUALLY deserves the score among shared features

        moveToNextState();
    }
    //updates the scores for the given meeple set
    private void scoreUpdate(int currentScore, Pair<Set<Meeple>, Integer> score) {
        int points = score.getValue() + currentScore;
        List<Player> playersScored = new ArrayList<>();
        for (Meeple m : score.getKey()) {
            if (!playersScored.contains(m.getPlayer())) {
                playersScored.add(m.getPlayer());
                m.getPlayer().updateScore(points);
            }
            m.remove();
        }
    }

    private Pair<Set<Meeple>, Integer> getCorrectScore(int currentScore, Pair<Set<Meeple>, Integer> score) {
        if (score.getValue() + currentScore > 1) {
            scoreUpdate(currentScore, score);
        } else {
            score = new Pair<>(score.getKey(), 0);
        }
        return score;
    }

    /**
     * Begins scoring process for roads and updates player's scores after calculation
     * Also, this method removes Meeples
     * TODO may change the return value to only be a set of Meeples
     *
     * @param isEndOfGame
     * @return the complete list of meeples to remove from the UI
     */
    public Pair<Set<Meeple>, Integer> startScoreRoad(PlayableTile currentTile, boolean isEndOfGame) {
        int currentScore = 1;
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        final Set<Meeple> meeples = new HashSet<Meeple>();
        alreadyVisited.add(currentTile);
        Pair<Set<Meeple>, Integer> score = new Pair<>(meeples, 0);
        //only score if a roadstop is placed
        if (currentTile.getInternals().contains(GlobalVariables.Internal.ROADSTOP)) {
            if ((!alreadyVisited.contains(currentTile.getTop())) && currentTile.featuresMap.get(GlobalVariables.Direction.NORTH) == GlobalVariables.Feature.ROAD) {
                currentTile.addMeeple(meeples, GlobalVariables.Location.TOP);
                score = currentTile.scoreRoadHelperMethod(alreadyVisited, meeples, isEndOfGame, currentScore, currentTile.getTop(), GlobalVariables.Direction.NORTH);
                score = getCorrectScore(currentScore, score);
                meeples.addAll(score.getKey());

                if (!isEndOfGame && (score.getValue() + currentScore) < 1)
                    meeples.clear();
                currentScore += score.getValue();
            }
            currentScore = 1;
            if (!alreadyVisited.contains(currentTile.getBottom()) && currentTile.featuresMap.get(GlobalVariables.Direction.SOUTH) == GlobalVariables.Feature.ROAD) {
                currentTile.addMeeple(meeples, GlobalVariables.Location.BOTTOM);
                score = currentTile.scoreRoadHelperMethod(alreadyVisited, meeples, isEndOfGame, currentScore, currentTile.getBottom(), GlobalVariables.Direction.SOUTH);
                score = getCorrectScore(currentScore, score);
                currentScore += score.getValue();
                meeples.addAll(score.getKey());
                if (!isEndOfGame && score.getValue() < 1)
                    meeples.clear();

            }
            currentScore = 1;
            if ((!alreadyVisited.contains(currentTile.getRight())) && currentTile.featuresMap.get(GlobalVariables.Direction.EAST) == GlobalVariables.Feature.ROAD) {
                currentTile.addMeeple(meeples, GlobalVariables.Location.RIGHT);
                score = currentTile.scoreRoadHelperMethod(alreadyVisited, meeples, isEndOfGame, currentScore, currentTile.getRight(), GlobalVariables.Direction.EAST);
                score = getCorrectScore(currentScore, score);
                meeples.addAll(score.getKey());
                if (!isEndOfGame && (score.getValue() + currentScore) < 1)
                    meeples.clear();
                currentScore += score.getValue();
            }
            currentScore = 1;
            if ((!alreadyVisited.contains(currentTile.getLeft())) && currentTile.featuresMap.get(GlobalVariables.Direction.WEST) == GlobalVariables.Feature.ROAD) {
                currentTile.addMeeple(meeples, GlobalVariables.Location.LEFT);
                score = currentTile.scoreRoadHelperMethod(alreadyVisited, meeples, isEndOfGame, currentScore, currentTile.getLeft(), GlobalVariables.Direction.WEST);
                score = getCorrectScore(currentScore, score);
                currentScore += score.getValue();
                meeples.addAll(score.getKey());
                if (!isEndOfGame && (score.getValue() + currentScore) < 1)
                    meeples.clear();
            }
        }
        return new Pair(meeples, currentScore + (score == null ? 0 : score.getValue()));
    }





    /**
     * Called at the end of the game
     * TODO: Score everything
     */
    public void endGame() {
        Stack<Pair<HashSet<Meeple>, Integer>> cities = new Stack<>();
        for (Player player: players) {
            for(Meeple m : player.getMeeples()){
                if(m.getFeature() == GlobalVariables.Feature.CITY) {
                    HashSet<GlobalVariables.Direction> directions = new HashSet<>();
                    if (m.getTile().getInternals().contains(GlobalVariables.Internal.CITY)) {
                        if (m.getTile().getTopFeature() == GlobalVariables.Feature.CITY)
                            directions.add(GlobalVariables.Direction.NORTH);
                        if (m.getTile().getLeftFeature() == GlobalVariables.Feature.CITY)
                            directions.add(GlobalVariables.Direction.WEST);
                        if (m.getTile().getRightFeature() == GlobalVariables.Feature.CITY)
                            directions.add(GlobalVariables.Direction.EAST);
                        if (m.getTile().getBottomFeature() == GlobalVariables.Feature.CITY)
                            directions.add(GlobalVariables.Direction.SOUTH);
                        cities.push(m.getTile().startScoreCity(directions, false));
                    } else {
                        if (m.getTile().getTopFeature() == GlobalVariables.Feature.CITY) {
                            HashSet<GlobalVariables.Direction> direc = new HashSet<>();
                            direc.add(GlobalVariables.Direction.NORTH);
                            cities.push(m.getTile().startScoreCity(direc, false));
                        }
                        if (m.getTile().getLeftFeature() == GlobalVariables.Feature.CITY) {
                            HashSet<GlobalVariables.Direction> direc = new HashSet<>();
                            direc.add(GlobalVariables.Direction.WEST);
                            cities.push(m.getTile().startScoreCity(direc, false));
                        }
                        if (m.getTile().getRightFeature() == GlobalVariables.Feature.CITY) {
                            HashSet<GlobalVariables.Direction> direc = new HashSet<>();
                            direc.add(GlobalVariables.Direction.EAST);
                            cities.push(m.getTile().startScoreCity(direc, false));
                        }
                        if (m.getTile().getBottomFeature() == GlobalVariables.Feature.CITY) {
                            HashSet<GlobalVariables.Direction> direc = new HashSet<>();
                            direc.add(GlobalVariables.Direction.SOUTH);
                            cities.push(m.getTile().startScoreCity(direc, false));
                        }
                    }
                }
            }
        }

        while (!cities.isEmpty()) {
            Pair<HashSet<Meeple>, Integer> city = cities.pop();
            if (city.getValue() > 0 && !city.getKey().isEmpty()) {
                for (Meeple m : city.getKey()) {
                    m.getPlayer().updateScore(city.getValue());
                    m.getTile().removeMeeple();
                }
            }
        }
        bottomDisplay.displayFinalScore();
    }

/*    private Pair<HashSet<Meeple>, Integer> helpScoreCity(GlobalVariables.Direction d, PlayableTile tile) {
        Set<GlobalVariables.Direction> directions = new HashSet<>();
        directions.add(d);
        return tile.startScoreCity(directions, true);
    } */

    /**
     * TODO find out if this is used outside of a test case... ?....
     *
     * @return
     */
    public boolean updateAllScores() {
        Pair<HashSet<Meeple>, Integer> scoreCity, scoreFarmer;
        scoreCity = this.currentTile.scoreCity(new HashSet<AbstractTile>(), new HashSet<Meeple>(), false);
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Set<Meeple> meeples = new HashSet<Meeple>();
        Pair<Set<Meeple>, Integer> roadscore = startScoreRoad(currentTile,false);

        if (scoreCity.getValue() > 0) {
            for (Meeple m : scoreCity.getKey()) {
                m.getPlayer().updateScore(scoreCity.getValue());
            }
        }
        System.out.println("Number of Meeples: " + roadscore.getKey().size());
        System.out.println("Score adding:" + roadscore.getValue());
        int score = roadscore.getValue();
        int meeplelistsize = roadscore.getKey().size();
        if (roadscore.getValue() > 0) {
            Meeple[] meeps = roadscore.getKey().toArray(new Meeple[meeplelistsize]);
            for (int i = 0; i < meeps.length; i++) {
                meeps[i].getPlayer().updateScore(score);
            }
        }

        return true;
    }

    /**
     * If the user chooses not to place a meeple, they may "pass" onto the next turn via this method
     */
    public void passTurn() {
        if (currentTurnState == TurnState.MEEPLE_PLACEMENT) {
            currentTile.removeAll();
            currentTile.repaint();
            moveToNextState();
        }
    }

    /**
     * Determines whether the tiles within a game can be adjusted
     *
     * @return true if the game is in the tile placement state
     */
    public boolean canAdjustTile() {
        return this.currentTurnState == TurnState.TILE_PLACEMENT;
    }


    /**
     * Adds a meeple to the list of monks
     *
     * @param monk The meeple to add
     */
    public void addMonk(Meeple monk) {
        this.monks.add(monk);
    }

    /**
     * Removes a meeple from the list of monks
     *
     * @param monk The meeple to remove
     */
    public void removeMonk(Meeple monk) {
        this.monks.remove(monk);
    }


    private enum TurnState {
        TILE_PLACEMENT("Place a tile"),
        MEEPLE_PLACEMENT("Place a meeple"),
        SCORING("Scoring, please wait..."),
        GAME_OVER("Game Over");

        String text;

        TurnState(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }

    /**
     * For testing purposes only. should never be called elsewhere
     *
     * @param t
     */
    public void setCurrentTile(PlayableTile t) {
        this.currentTile = t;
    }

    public void finishRiver(PlayableTile tile) {
        TileGrid grid = (TileGrid) tile.getParent();
        TileFactory factory = new TileFactory();
        PlayableTile end = factory.getRiverEnd();
        if (tile.getTopFeature() == GlobalVariables.Feature.RIVER && grid.getSlots().contains(tile.getTop())) {
            end.rotateTile();
            tile.getTop().addTile(end);
        } else if (tile.getLeftFeature() == GlobalVariables.Feature.RIVER && grid.getSlots().contains(tile.getLeft())) {
            end.rotateTile();
            end.rotateTile();
            tile.getLeft().addTile(end);
        } else if (tile.getRightFeature() == GlobalVariables.Feature.RIVER && grid.getSlots().contains(tile.getRight())) {
            tile.getRight().addTile(end);
        } else if (tile.getBottomFeature() == GlobalVariables.Feature.RIVER && grid.getSlots().contains(tile.getBottom())) {
            end.rotateTile();
            end.rotateTile();
            end.rotateTile();
            tile.getBottom().addTile(end);
        }
    }
}
