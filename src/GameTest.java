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
        assert board.getScore() == 0 : "Score: 0";
        assert board.getNumberOfTilesLeft() ==72 :" Tiles";
        assert board.getPlayers() != null : "List of players is not null";

  }
    @Test
    public void testDrawBoard(){
        Game board = new Game();
        assert board.getNumberOfTilesLeft() ==72 :" Tiles";
        board.drawBoard();
        assert board.getNumberOfTilesLeft() ==71 :" Tiles";
        board.drawBoard();
        board.drawBoard();
        assert board.getNumberOfTilesLeft() ==69 :" Tiles";
    }
    @Test
    public void testIsGameOver(){
        Game board= new Game();
        assert board.isGameOver() == false: "game not over";
        board.drawBoard();

    }
}
