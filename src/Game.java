import java.awt.*;
import java.util.ArrayList;
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


    /**
     * Constructor for a game
     * @param grid The grid that stores all of the tiles in this game
     */
    public Game(TileGrid grid) {
        this.grid = grid;
        tiles = new Stack<>();
        gameOver = false;
        drawTile();
    }

    public Game(Stack<PlayableTile> stack, ArrayList<Player> players) {
        this(stack, players, false, false);
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
        currentTurn = 0;
        currentTurnState = TurnState.TILE_PlACEMENT;
        tiles = stack;
        gameOver = false;
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
        if (this.currentTurn < this.players.size() - 1)
            this.currentTurn++;
        else
            this.currentTurn = 0;
        //Done add logic for switching to the next player in the GUI (getCurrentTurnPlayer & colors)
        //TODO consider implementing the observer pattern for when a score is updated
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

    private enum TurnState {
        TILE_PlACEMENT,
        MEEPLE_PLACEMENT,
        SCORING
    }
}
