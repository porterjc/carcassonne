import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by johnsoaa on 3/25/2015.
 */
public class GameTest {

    @Test
    public void testCreateGame(){
        Game board = new Game();
        assert board != null : "board not null";
        assert board.score == 0 : "Score: 0";
        assert board.players != null : "List of players is not null";
    }
}