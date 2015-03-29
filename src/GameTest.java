import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by johnsoaa on 3/25/2015.
 */
public class GameTest {
    public final int numberOfTiles = 72;

    @Test
    public void testCreateGame() {
        Game board = new Game();
        assertEquals(true, board != null );
        assertEquals(0, board.getNumberOfTilesLeft() );
        assertEquals(true, board.getPlayers() != null );
    }


    @Test
    public void testDrawTile() {
        Game game = makeNewGame();
        assert game.getNumberOfTilesLeft() == numberOfTiles : " Tiles";
        game.drawTile();
        assert game.getNumberOfTilesLeft() == numberOfTiles - 1 : " Tiles";
        game.drawTile();
        game.drawTile();
        assert game.getNumberOfTilesLeft() == numberOfTiles - 3 : " Tiles";
    }

    @NotNull
    private Game makeNewGame() {
        Stack<Tile> testStack = new Stack<Tile>();
        for (int i = 0; i < numberOfTiles; i++) {
            testStack.push(new Tile());
        }
        return new Game(testStack);
    }

    @Test
    public void testEmptyTileList() {
        Game game = makeNewGame();
        for (int i = 0; i < numberOfTiles; i++) {
            game.drawTile();
        }
        assert game.getNumberOfTilesLeft() == 0 : " Tiles";
        assert game.drawTile() == false : "cannot draw tile from empty stack";
    }

    @Test
    public void testIsGameOver() {
        Game game = makeNewGame();
        assert game.isGameOver() == false : "game not over";
        for (int i = 0; i < numberOfTiles; i++) {
            game.drawTile();
        }
        assert game.isGameOver() == true : "game is over";
    }
}
