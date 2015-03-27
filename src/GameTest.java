import org.junit.Test;

/**
 * Created by johnsoaa on 3/25/2015.
 */
public class GameTest {
    public final int numberOfTiles =72;
    @Test
    public void testCreateGame(){
        Game board = new Game();
        assert board != null : "board not null";

        assert board.getNumberOfTilesLeft() ==72 :" Tiles";
        assert board.getPlayers() != null : "List of players is not null";

  }
    @Test
    public void testDrawTile(){
        Game game = new Game();
        assert game.getNumberOfTilesLeft() == numberOfTiles :" Tiles";
        game.drawTile();
        assert game.getNumberOfTilesLeft() == numberOfTiles - 1:" Tiles";
        game.drawTile();
        game.drawTile();
        assert game.getNumberOfTilesLeft() == numberOfTiles - 3 :" Tiles";
    }
    @Test
    public void testEmptyTileList(){
        Game game = new Game();
        for(int i =0; i<numberOfTiles;i++){
            game.drawTile();
        }
        assert game.getNumberOfTilesLeft() == 0 :" Tiles";
        assert game.drawTile() == false :"cannot draw tile from empty stack";
    }
    @Test
    public void testIsGameOver(){
        Game game= new Game();
        assert game.isGameOver() == false: "game not over";
        for(int i =0; i<numberOfTiles;i++){
            game.drawTile();
        }
        assert game.isGameOver() == true: "game is over";
    }
}
