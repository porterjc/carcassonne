import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by johnsoaa on 3/25/2015.
 */
public class Game {
    // TODO add functional variable for turn
    private List<Player> players;
    private boolean gameOver;
    private Stack<PlayableTile> tiles;
    private PlayableTile currentTile;

    /**
     * TODO add real arguments to this constructor
     */
    public Game() {
        players = new ArrayList<Player>();
        tiles = new Stack<PlayableTile>();
        gameOver = false;
        drawTile();
    }

    /**
     * TODO add parameters for passing a list of players
     *
     * @param testStack
     */
    public Game(Stack<PlayableTile> testStack) {
        players = new ArrayList<Player>();
        tiles = testStack;
        gameOver = false;
    }

    public Game(ArrayList<Player> players) {
        this.players=players;
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
    }


}
