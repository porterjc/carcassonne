import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnsoaa on 3/25/2015.
 */
public class Game {

    public int score;
    public List<Player> players;

    public Game(){
        players = new ArrayList<Player>();
        score =0;
    }
}
