import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by johnsoaa on 3/25/2015.
 */
public class GameTest {
    public final int numberOfTiles = 72;

    @NotNull
    private Game makeNewGame() {
        Stack<AbstractTile> testStack = new Stack<AbstractTile>();
        for (int i = 0; i < numberOfTiles; i++) {
            testStack.push(new OpenTile(0, 0));
        }
        return new Game(testStack);
    }

    @Test
    public void testCreateGame() {
        Game board = new Game();
        assertEquals(true, board != null);
        assertEquals(0, board.getNumberOfTilesLeft());
        assertEquals(true, board.getPlayers() != null );
    }

    @Test
    public void testDrawTile() {
        Game game = makeNewGame();
        assertEquals(numberOfTiles,game.getNumberOfTilesLeft());
        game.drawTile();
        assertEquals(numberOfTiles - 1, game.getNumberOfTilesLeft());
        game.drawTile();
        game.drawTile();
        assertEquals(numberOfTiles - 3, game.getNumberOfTilesLeft());
    }

    @Test
    public void testEmptyTileList() {
        Game game = makeNewGame();
        for (int i = 0; i < numberOfTiles; i++) {
            game.drawTile();
        }
        assertEquals(0, game.getNumberOfTilesLeft());
        assertEquals(false, game.drawTile() );
    }

    @Test
    public void testIsGameOver() {
        Game game = makeNewGame();
        assertEquals(false, game.isGameOver() );
        for (int i = 0; i < numberOfTiles; i++) {
            game.drawTile();
        }
        assertEquals(true, game.isGameOver() );
    }
}
