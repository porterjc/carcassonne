import org.junit.Test;

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
    public void testDrawTile(){
        Game board = new Game();
        assert board.getNumberOfTilesLeft() ==72 :" Tiles";
        board.drawTile();
        assert board.getNumberOfTilesLeft() ==71 :" Tiles";
        board.drawTile();
        board.drawTile();
        assert board.getNumberOfTilesLeft() ==69 :" Tiles";
    }
    @Test
    public void testIsGameOver(){
        Game board= new Game();
        assert board.isGameOver() == false: "game not over";
        for(int i =0; i<72;i++){
            board.drawTile();
        }
        assert board.isGameOver() == true: "game is over";
    }
}
