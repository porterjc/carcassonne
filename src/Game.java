import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by johnsoaa on 3/25/2015.
 */
public class Game {


    private List<Player> players;
    private final int numberOfTile =72;
    private boolean gameOver;
    private Stack<Tile> tiles;
    /**
     * TODO add real arguments to this constructor
     */
    public Game(){
        players = new ArrayList<Player>();
        tiles = new Stack<Tile>();

        gameOver = false;
    }
    //TODO determine where to handle score
    public List<Player>  getPlayers(){
        return players;
    }
    public int getNumberOfTilesLeft(){
        return tiles.size();
    }

    public boolean isGameOver() {
        if(tiles.size() == 0){
            gameOver=true;
        }
        return gameOver;
    }

    public boolean drawTile() {
        if(tiles.size() == 0)
        {
            return false;
        }
        tiles.pop();
        return true;
    }
}
