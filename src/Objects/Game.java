package Objects;

import UIComponents.BottomDisplay;
import javafx.util.Pair;

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
     * Constructor for a game
     *
     */
   /* public Game(BottomDisplay bottomDisplay) {
        this.bottomDisplay = bottomDisplay;
        tiles = new Stack<>();
        gameOver = false;
//        GlobalVariables.openTiles = new ArrayList<OpenTile>(); //TODO change this!this

        numberOfOpenTilesOnBoard = 0;
        drawTile();
    }*/

    //TODO get rid of all constructors and just make one
   /* public Game(Stack<PlayableTile> stack, ArrayList<Player> players) {
        this(stack, players, false, false);
        numberOfOpenTilesOnBoard = 0;
//        GlobalVariables.openTiles = new ArrayList<OpenTile>();
    }*/

   /* public Game(Stack<PlayableTile> stack, ArrayList<Player> players, boolean river, boolean abbot) {
        if (players.size() > 1) {
            this.players = players;
        }
        riverMode = river;
        abbotMode = abbot;
        numberOfOpenTilesOnBoard = 0;
        currentTurn = 0;
//        GlobalVariables.openTiles = new ArrayList<OpenTile>();
        currentTurnState = TurnState.TILE_PLACEMENT;
        tiles = stack;
        gameOver = false;
    } */

    public Game(BottomDisplay display, Stack<PlayableTile> playableTiles, ArrayList<Player> players) {
        this(display, playableTiles, players, false, false);
//        GlobalVariables.openTiles = new ArrayList<OpenTile>();
    }

    public Game(BottomDisplay bottomDisplay, Stack<PlayableTile> stack, ArrayList<Player> players, boolean river, boolean abbot) {
        this.bottomDisplay = bottomDisplay;
        if (players.size() > 1) {
            this.players = players;
        }
        riverMode = river;
        abbotMode = abbot;
        numberOfOpenTilesOnBoard = 0;
//        GlobalVariables.openTiles = new ArrayList<OpenTile>();
        currentTurn = 0;
        currentTurnState = TurnState.TILE_PLACEMENT;
        tiles = stack;
        gameOver = false;
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
        if (tiles.size() == 0) {
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
            return false;
        }
        currentTile = tiles.pop();
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
        drawTile();
        bottomDisplay.finishedScoringUpdate();
        return true;
    }

    public boolean moveToNextState() {
        if (isGameOver()) return false;

        switch (currentTurnState) {
            case TILE_PLACEMENT:
                currentTurnState = TurnState.MEEPLE_PLACEMENT;
                bottomDisplay.placedTileUpdate();
                System.out.println("CURRENT: MEEPLE");
                return true;
            case MEEPLE_PLACEMENT:
                currentTurnState = TurnState.SCORING;
                bottomDisplay.placedMeepleUpdate();
                System.out.println("CURRENT: SCORING");
                return true;
            case SCORING:
                currentTurnState = TurnState.TILE_PLACEMENT;
                System.out.println("CURRENT: TILE");
                return moveToNextTurn();
        }

        return false;
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

    public boolean canManuallyPass() {
        return currentTurnState == TurnState.MEEPLE_PLACEMENT;
    }

    public String getCurrentStateText() { return this.currentTurnState.getText(); }

    public void updateScore(Player p, int i) {
        p.updateScore(i);
    }

    public boolean updateAllScores() {
        Pair<HashSet<Meeple>, Integer> scoreCity, scoreRoad, scoreFarmer;
        scoreCity = this.currentTile.scoreCity(new HashSet<AbstractTile>(), new HashSet<Meeple>(), false);
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Set<Meeple> meeples = new HashSet<Meeple>();
//        scoreRoad = this.currentTile.scoreRoad(alreadyVisited, meeples, false);
        if (scoreCity.getValue() > 0) {
            for (Meeple m : scoreCity.getKey()) {
                m.getPlayer().updateScore(scoreCity.getValue());
            }
            return true;
        }

//        if (scoreRoad.getValue() > 0) {
//            for (Meeple m : scoreRoad.getKey()) {
//                m.getPlayer().updateScore(scoreRoad.getValue());
//            }
//            return true;
//        }
        return false;
    }

    public void passTurn() {
        if(currentTurnState == TurnState.MEEPLE_PLACEMENT) {
            moveToNextState();
            moveToNextState();
        }
    }


    private enum TurnState {
        TILE_PLACEMENT("Place a tile"),
        MEEPLE_PLACEMENT("Place a meeple"),
        SCORING("Scoring, please wait...");

        String text;

        TurnState(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }

    class ListResponseModel<E> extends AbstractListModel {

        private static final long serialVersionUID = 1L;

        private ArrayList<OpenTile> delegate = new ArrayList<OpenTile>();

        @Override
        public int getSize() {
            return delegate.size();
        }

        @Override
        public Object getElementAt(int index) {
            return delegate.get(index);
        }

        public void add(OpenTile e) {
            int index = delegate.size();
            delegate.add(e);
            fireIntervalAdded(this, index, index);
        }

        @Override
        protected void fireIntervalAdded(Object source, int index0, int index1) {
            super.fireIntervalAdded(source, index0, index1);//no idea what this does

        }

        @Override
        protected void fireIntervalRemoved(Object source, int index0, int index1) {
            super.fireIntervalRemoved(source, index0, index1);
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
}
