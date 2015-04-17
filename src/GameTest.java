import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by johnsoaa on 3/25/2015.
 */
public class GameTest {
    public final int numberOfTiles = 72;

    @NotNull
    private Game makeNewGame() {
        Stack<PlayableTile> testStack = new Stack<PlayableTile>();
        for (int i = 0; i < numberOfTiles; i++) {
            testStack.push(new PlayableTile());
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
    public void testCreateGameWithTwoPlayers() {
        Player p1 = new Player(Color.RED);
        Player p2 = new Player(Color.ORANGE);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        Game board = new Game(players);
        assertEquals(true, board != null);
        assertEquals(players, board.getPlayers());
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
