import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnsoaa on 3/25/2015.
 */
public class Game {

    private int score;
    private List<Player> players;
    private int numberOfTilesLeft;
    private boolean gameOver;
    /**
     * TODO add real arguments to this constructor
     */
    public Game(){
        players = new ArrayList<Player>();
        score = 0;
        //TODO change the numberofTilesLeft to be a LIST of tiles
        numberOfTilesLeft = 72;
        gameOver = false;
    }
    public int getScore(){
        return score;
    }
    public List<Player>  getPlayers(){
        return players;
    }
    public int getNumberOfTilesLeft(){
        return numberOfTilesLeft;
    }

    public boolean isGameOver() {
        if(numberOfTilesLeft == 0){
            gameOver=true;
        }
        return gameOver;
    }

    public boolean drawTile() {
        //TODO change the number of tiles left variable to call tileList.size()
        if(numberOfTilesLeft == 0)
        {
            return false;
        }
        numberOfTilesLeft--;
        return true;
    }
}
