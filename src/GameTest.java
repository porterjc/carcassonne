import com.sun.istack.internal.NotNull;
import org.junit.After;
import org.junit.Before;
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
    public ArrayList<Player> players;

    @NotNull
    private Game makeNewGame() {
        Stack<PlayableTile> testStack = new Stack<PlayableTile>();
        for (int i = 0; i < numberOfTiles; i++) {
            testStack.push(new PlayableTile());
        }
        return new Game(testStack);
    }

    @Before
    public void setUp() {
       this.players = new ArrayList<Player>();
    }

    @After
    public void cleanUp() {
        players = null;
    }

    @Test
    public void testCreateGameWithNoPlayers() {
        Game board = new Game();
        assertEquals(true, board.getPlayers() == null);
    }

    @Test
    public void testCreateGameWithOnePlayers() {
        this.players.add(new Player(Color.RED));
        Game board = new Game(players);
        assertEquals(true, board.getPlayers() == null);
    }

    @Test
    public void testCreateGameWithTwoPlayers() {
        Player p1 = new Player(Color.RED);
        Player p2 = new Player(Color.ORANGE);
        players.add(p1);
        players.add(p2);
        Game board = new Game(players);
        assertEquals(players, board.getPlayers());
    }

    @Test
    public void testCreateGameWithThreePlayers() {
        Player p1 = new Player(Color.RED);
        Player p2 = new Player(Color.ORANGE);
        Player p3 = new Player(Color.BLUE);
        this.players.add(p1);
        this.players.add(p2);
        this.players.add(p3);
        Game board = new Game(this.players);
        assertEquals(this.players, board.getPlayers());
        assertEquals(3, board.getPlayers().size());
    }

    @Test
    public void testCreateGameWithFourPlayers() {
        Player p1 = new Player(Color.RED);
        Player p2 = new Player(Color.ORANGE);
        Player p3 = new Player(Color.BLUE);
        Player p4 = new Player(Color.YELLOW);
        this.players.add(p1);
        this.players.add(p2);
        this.players.add(p3);
        this.players.add(p4);
        Game board = new Game(this.players);
        assertEquals(4, board.getPlayers().size());
    }

    @Test
    public void testCreateGameWithFivePlayers() {
        Player p1 = new Player(Color.RED);
        Player p2 = new Player(Color.ORANGE);
        Player p3 = new Player(Color.BLUE);
        Player p4 = new Player(Color.YELLOW);
        Player p5 = new Player(Color.PINK);
        this.players.add(p1);
        this.players.add(p2);
        this.players.add(p3);
        this.players.add(p4);
        this.players.add(p5);
        Game board = new Game(this.players);
        assertEquals(5, board.getPlayers().size());
    }
    @Test
    public void testDrawTile() {
        Game game = makeNewGame();
        assertEquals(numberOfTiles, game.getNumberOfTilesLeft());
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
        assertEquals(false, game.drawTile());
    }

    @Test
    public void testIsGameOver() {
        Game game = makeNewGame();
        assertEquals(false, game.isGameOver());
        for (int i = 0; i < numberOfTiles; i++) {
            game.drawTile();
        }
        assertEquals(true, game.isGameOver());
    }
}
