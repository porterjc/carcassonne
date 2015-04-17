import com.sun.istack.internal.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by johnsoaa on 3/25/2015.
 */
public class GameTest {
    public final int numberOfTiles = 72;
    public ArrayList<Player> players;

    @Before
    public void setUp() {
        this.players = new ArrayList<Player>();
    }

    @After
    public void cleanUp() {
        players = null;
    }


    private Stack<PlayableTile> getTiles() {
        Stack<PlayableTile> testStack = new Stack<PlayableTile>();
        for (int i = 0; i < numberOfTiles; i++) {
            testStack.push(new PlayableTile());
        }
        return testStack;
    }


    @Test
    public void testCreateGameWithNoPlayers() {
        Game board = new Game();
        assertEquals(true, board.getPlayers() == null);
    }

    @Test
    public void testCreateGameWithOnePlayers() {
        this.players.add(new Player(Color.RED));
        Game game = new Game(getTiles(),this.players);
        assertEquals(true, game.getPlayers() == null);
    }

    @Test
    public void testCreateGameWithTwoPlayers() {
        Player p1 = new Player(Color.RED);
        Player p2 = new Player(Color.ORANGE);
        players.add(p1);
        players.add(p2);
        Game game = new Game(getTiles(),this.players);
        assertEquals(players, game.getPlayers());
    }

    @Test
    public void testCreateGameWithThreePlayers() {
        Player p1 = new Player(Color.RED);
        Player p2 = new Player(Color.ORANGE);
        Player p3 = new Player(Color.BLUE);
        this.players.add(p1);
        this.players.add(p2);
        this.players.add(p3);
        Game game = new Game(getTiles(),this.players);
        assertEquals(this.players, game.getPlayers());
        assertEquals(3, game.getPlayers().size());
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
        Game game = new Game(getTiles(),this.players);
        assertEquals(4, game.getPlayers().size());
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
        Game game = new Game(getTiles(),this.players);
        assertEquals(5, game.getPlayers().size());
    }

    @Test
    public void testDrawTile() {
        Game game = new Game(getTiles(),this.players);
        assertEquals(numberOfTiles, game.getNumberOfTilesLeft());
        game.drawTile();
        assertEquals(numberOfTiles - 1, game.getNumberOfTilesLeft());
        game.drawTile();
        game.drawTile();
        assertEquals(numberOfTiles - 3, game.getNumberOfTilesLeft());
    }

    @Test
    public void testEmptyTileList() {
        Game game = new Game(getTiles(),this.players);
        for (int i = 0; i < numberOfTiles; i++) {
            game.drawTile();
        }
        assertEquals(0, game.getNumberOfTilesLeft());
        assertEquals(false, game.drawTile());
    }

    @Test
    public void testIsGameOver() {
        Game game = new Game(getTiles(),this.players);
        assertEquals(false, game.isGameOver());
        for (int i = 0; i < numberOfTiles; i++) {
            game.drawTile();
        }
        assertEquals(true, game.isGameOver());
    }

    @Test

    public void testBeginGame() {
        Player p1 = new Player(Color.RED);
        Player p2 = new Player(Color.ORANGE);
        players.add(p1);
        players.add(p2);
        Game game = new Game(getTiles(),this.players);
        game.begin();
        assertEquals(0, game.getCurrentTurn());
        assertEquals(false, game.isGameOver());
    }

    @Test
    public void testPlayerTurn() {
        players.add(new Player(Color.RED));
        players.add(new Player(Color.ORANGE));
        Stack<PlayableTile> testStack = new Stack<PlayableTile>();
        Game game = new Game(testStack, this.players);
        game.begin();
        assertEquals(false, game.moveToNextTurn());

        game = new Game(getTiles(), this.players);
        game.begin();
        assertEquals(true, game.moveToNextTurn());
        assertEquals(1, game.getCurrentTurn());

        assertEquals(true, game.moveToNextTurn());
        assertEquals(0, game.getCurrentTurn());

        this.players.add(new Player(Color.BLACK));
        this.players.add(new Player(Color.BLUE));
        game = new Game(getTiles(), this.players);
        game.begin();
        assertEquals(true, game.moveToNextTurn());
        assertEquals(1, game.getCurrentTurn());
        assertEquals(true, game.moveToNextTurn());
        assertEquals(2, game.getCurrentTurn());
        assertEquals(true, game.moveToNextTurn());
        assertEquals(3, game.getCurrentTurn());
        assertEquals(true, game.moveToNextTurn());
        assertEquals(0, game.getCurrentTurn());
    }
    @Test
    public void testGetCurrentTurnPlayer(){
        players.add(new Player(Color.RED));
        players.add(new Player(Color.ORANGE));
        Game game = new Game(getTiles(), this.players);
        assertEquals(players.get(0), game.getCurrentTurnPlayer());
        game.moveToNextTurn();
        assertEquals(players.get(1), game.getCurrentTurnPlayer());
        game.moveToNextTurn();
        assertEquals(players.get(0), game.getCurrentTurnPlayer());
        game.moveToNextTurn();
        assertEquals(players.get(1), game.getCurrentTurnPlayer());
    }
    @Test
    public void testUpdatePlayerScore(){
        players.add(new Player(Color.RED));
        players.add(new Player(Color.ORANGE));
        Game game = new Game(getTiles(), this.players);
        //TODO for later
        //change ArrayList<Player> to Map<Player,Integer> (map of the player and the corresponding score
        //game.updateScore(Color.RED, 5);
        //assertEquals(5, players.get)


    }

}
