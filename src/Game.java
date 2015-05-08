import javafx.util.Pair;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * Created by johnsoaa on 3/25/2015.
 */
public class Game {
    private TileGrid grid;
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
     * @param grid The grid that stores all of the tiles in this game
     */
    public Game(TileGrid grid) {
        this.grid = grid;
        tiles = new Stack<>();
        gameOver = false;
        GlobalVariables.openTiles = new ArrayList<OpenTile>();

        numberOfOpenTilesOnBoard = 0;
        drawTile();
    }

    //TODO get rid of all constructors and just make one
    public Game(Stack<PlayableTile> stack, ArrayList<Player> players) {
        this(stack, players, false, false);
        numberOfOpenTilesOnBoard = 0;
        GlobalVariables.openTiles = new ArrayList<OpenTile>();
    }

    /**
     * Done add parameters for passing a list of players
     *
     * @param stack
     */
    public Game(Stack<PlayableTile> stack, ArrayList<Player> players, boolean river, boolean abbot) {
        if (players.size() > 1) {
            this.players = players;
        }
        riverMode = river;
        abbotMode = abbot;
        numberOfOpenTilesOnBoard = 0;
        currentTurn = 0;
        GlobalVariables.openTiles = new ArrayList<OpenTile>();
        currentTurnState = TurnState.TILE_PlACEMENT;
        tiles = stack;
        gameOver = false;
    }

    public Game(TileGrid grid, Stack<PlayableTile> stack, ArrayList<Player> players, boolean river, boolean abbot) {
        this.grid = grid;
        if (players.size() > 1) {
            this.players = players;
        }
        riverMode = river;
        abbotMode = abbot;
        numberOfOpenTilesOnBoard = 0;
        GlobalVariables.openTiles = new ArrayList<OpenTile>();
        currentTurn = 0;
        currentTurnState = TurnState.TILE_PlACEMENT;
        tiles = stack;
        gameOver = false;
    }

    public Game(TileGrid tileGrid, Stack<PlayableTile> playableTiles, ArrayList<Player> players) {
        this(tileGrid, playableTiles, players, false, false);
        numberOfOpenTilesOnBoard = 0;
        GlobalVariables.openTiles = new ArrayList<OpenTile>();
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
        System.out.println("N: " + (currentTile.getFeatures().get(GlobalVariables.Direction.NORTH) == null ? null : currentTile.getFeatures().get(GlobalVariables.Direction.NORTH)));
        System.out.println("E: " + (currentTile.getFeatures().get(GlobalVariables.Direction.EAST) == null ? null : currentTile.getFeatures().get(GlobalVariables.Direction.EAST)));
        System.out.println("W: " + (currentTile.getFeatures().get(GlobalVariables.Direction.WEST) == null ? null : currentTile.getFeatures().get(GlobalVariables.Direction.WEST)));
        System.out.println("S: " + (currentTile.getFeatures().get(GlobalVariables.Direction.SOUTH) == null ? null : currentTile.getFeatures().get(GlobalVariables.Direction.SOUTH)));
        System.out.println();
        return true;
    }

    public PlayableTile getCurrentTile() {
        return currentTile;
    }

    public void passTiles(Stack<PlayableTile> tiles) {
        this.tiles = tiles;
        drawTile();
    }

    public boolean moveToNextTurn() {
        if (isGameOver()) return false;
        if (this.currentTurn < this.players.size() - 1) {

            this.currentTurn++;
        } else
            this.currentTurn = 0;
        //Done add logic for switching to the next player in the GUI (getCurrentTurnPlayer & colors)

        //as we don't want too much coupling between the UI and the GAME class over sharing Player objects

        return true;
    }

    public boolean moveToNextState() {
        if (isGameOver()) return false;

        switch (currentTurnState) {
            case TILE_PlACEMENT:
                currentTurnState = TurnState.MEEPLE_PLACEMENT;
                return true;
            case MEEPLE_PLACEMENT:
                currentTurnState = TurnState.SCORING;
                return true;
            case SCORING:
                currentTurnState = TurnState.TILE_PlACEMENT;
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

    public void updateScore(Player p, int i) {
        p.updateScore(i);
    }

    public boolean updateAllScores() {
        return false;
    }


    private enum TurnState {
        TILE_PlACEMENT,
        MEEPLE_PLACEMENT,
        SCORING
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
