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
    private Stack<AbstractTile> tiles;

    /**
     * TODO add real arguments to this constructor
     */
    public Game() {
        players = new ArrayList<Player>();
        tiles = new Stack<AbstractTile>();
        gameOver = false;
    }

    /**
     * TODO add parameters for passing a list of players
     *
     * @param testStack
     */
    public Game(Stack<AbstractTile> testStack) {
        players = new ArrayList<Player>();
        tiles = testStack;
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
        tiles.pop();
        return true;
    }


}
