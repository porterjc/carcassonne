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

    /**
     * TODO add real arguments to this constructor
     */
    public Game(){
        players = new ArrayList<Player>();
        score = 0;
        numberOfTilesLeft = 72;
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
}
