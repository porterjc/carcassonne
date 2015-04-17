import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by porterjc on 3/25/2015.
 */
public class GameBoardTest {

    @Test
    public void constructorTest(){
        GameBoard board = new GameBoard();
        assert board != null : "board not null";
    }

}