package ObjectTests;
import Objects.PlayableTile;
import Objects.Player;
import Objects.TileGrid;
import Objects.OpenTile;
import Objects.Game;
import Main.GlobalVariables;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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
        Game board = new Game(new TileGrid());
        assertEquals(true, board.getPlayers() == null);
    }

    @Test
    public void testCreateGameWithOnePlayers() {
        this.players.add(new Player(GlobalVariables.PlayerColor.RED));
        Game game = new Game(new TileGrid(), getTiles(), this.players);
        assertEquals(true, game.getPlayers() == null);
    }

    @Test
    public void testCreateGameWithTwoPlayers() {
        Player p1 = new Player(GlobalVariables.PlayerColor.RED);
        Player p2 = new Player(GlobalVariables.PlayerColor.YELLOW);
        players.add(p1);
        players.add(p2);
        Game game = new Game(new TileGrid(), getTiles(), this.players);
        assertEquals(players, game.getPlayers());
    }

    @Test
    public void testCreateGameWithThreePlayers() {
        Player p1 = new Player(GlobalVariables.PlayerColor.RED);
        Player p2 = new Player(GlobalVariables.PlayerColor.YELLOW);
        Player p3 = new Player(GlobalVariables.PlayerColor.BLUE);
        this.players.add(p1);
        this.players.add(p2);
        this.players.add(p3);
        Game game = new Game(new TileGrid(), getTiles(), this.players);
        assertEquals(this.players, game.getPlayers());
        assertEquals(3, game.getPlayers().size());
    }

    @Test
    public void testCreateGameWithFourPlayers() {
        Player p1 = new Player(GlobalVariables.PlayerColor.RED);
        Player p2 = new Player(GlobalVariables.PlayerColor.GREEN);
        Player p3 = new Player(GlobalVariables.PlayerColor.BLUE);
        Player p4 = new Player(GlobalVariables.PlayerColor.YELLOW);
        this.players.add(p1);
        this.players.add(p2);
        this.players.add(p3);
        this.players.add(p4);
        Game game = new Game(new TileGrid(), getTiles(), this.players);
        assertEquals(4, game.getPlayers().size());
    }

    @Test
    public void testCreateGameWithFivePlayers() {
        Player p1 = new Player(GlobalVariables.PlayerColor.RED);
        Player p2 = new Player(GlobalVariables.PlayerColor.GREEN);
        Player p3 = new Player(GlobalVariables.PlayerColor.BLUE);
        Player p4 = new Player(GlobalVariables.PlayerColor.YELLOW);
        Player p5 = new Player(GlobalVariables.PlayerColor.BLACK);
        this.players.add(p1);
        this.players.add(p2);
        this.players.add(p3);
        this.players.add(p4);
        this.players.add(p5);
        Game game = new Game(new TileGrid(), getTiles(), this.players);
        assertEquals(5, game.getPlayers().size());
    }

    @Test
    public void testDrawTile() {
        Game game = new Game(new TileGrid(), getTiles(), this.players);
        assertEquals(numberOfTiles, game.getNumberOfTilesLeft());
        game.drawTile();
        assertEquals(numberOfTiles - 1, game.getNumberOfTilesLeft());
        game.drawTile();
        game.drawTile();
        assertEquals(numberOfTiles - 3, game.getNumberOfTilesLeft());
    }

    @Test
    public void testEmptyTileList() {
        Game game = new Game(new TileGrid(), getTiles(), this.players);
        for (int i = 0; i < numberOfTiles; i++) {
            game.drawTile();
        }
        assertEquals(0, game.getNumberOfTilesLeft());
        assertEquals(false, game.drawTile());
    }

    @Test
    public void testIsGameOver() {
        Game game = new Game(new TileGrid(), getTiles(), this.players);
        assertEquals(false, game.isGameOver());
        for (int i = 0; i < numberOfTiles; i++) {
            game.drawTile();
        }
        assertEquals(true, game.isGameOver());
    }

    @Test

    public void testBeginGame() {
        Player p1 = new Player(GlobalVariables.PlayerColor.RED);
        Player p2 = new Player(GlobalVariables.PlayerColor.YELLOW);
        players.add(p1);
        players.add(p2);
        Game game = new Game(new TileGrid(), getTiles(), this.players);
        game.begin();
        assertEquals(0, game.getCurrentTurn());
        assertEquals(false, game.isGameOver());
    }

    @Test
    public void testPlayerTurn() {
        players.add(new Player(GlobalVariables.PlayerColor.RED));
        players.add(new Player(GlobalVariables.PlayerColor.YELLOW));
        Stack<PlayableTile> testStack = new Stack<PlayableTile>();
        Game game = new Game(new TileGrid(), testStack, this.players);
        game.begin();
        assertEquals(false, game.moveToNextTurn());

        game = new Game(new TileGrid(), getTiles(), this.players);
        game.begin();
        assertEquals(true, game.moveToNextTurn());
        assertEquals(1, game.getCurrentTurn());

        assertEquals(true, game.moveToNextTurn());
        assertEquals(0, game.getCurrentTurn());

        this.players.add(new Player(GlobalVariables.PlayerColor.BLACK));
        this.players.add(new Player(GlobalVariables.PlayerColor.BLUE));
        game = new Game(new TileGrid(), getTiles(), this.players);
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
    public void testGetCurrentTurnPlayer() {
        players.add(new Player(GlobalVariables.PlayerColor.RED));
        players.add(new Player(GlobalVariables.PlayerColor.YELLOW));
        Game game = new Game(new TileGrid(), getTiles(), this.players);
        assertEquals(players.get(0), game.getCurrentTurnPlayer());
        game.moveToNextTurn();
        assertEquals(players.get(1), game.getCurrentTurnPlayer());
        game.moveToNextTurn();
        assertEquals(players.get(0), game.getCurrentTurnPlayer());
        game.moveToNextTurn();
        assertEquals(players.get(1), game.getCurrentTurnPlayer());
    }

    @Test
    public void testUpdatePlayerScore() {
        players.add(new Player(GlobalVariables.PlayerColor.RED));
        players.add(new Player(GlobalVariables.PlayerColor.YELLOW));
        Game game = new Game(new TileGrid(), getTiles(), this.players);
        game.updateScore(game.getPlayers().get(0), 7);
        assertEquals(7, game.getPlayers().get(0).getPlayerScore());
        game.updateScore(game.getPlayers().get(0), 7);
        assertEquals(14, game.getPlayers().get(0).getPlayerScore());
        game.updateScore(game.getPlayers().get(0), -1);
        assertEquals(14, game.getPlayers().get(0).getPlayerScore());
    }

    @Test
    public void testUpdateAllScores() {
        players.add(new Player(GlobalVariables.PlayerColor.RED));
        players.add(new Player(GlobalVariables.PlayerColor.YELLOW));

        Game game = new Game(new TileGrid(), getTiles(), this.players);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile t1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile t2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1);
        t1.setBottom(t2);
        t2.setTop(t1);
//        TODO change this to utilize the opentiles in the Tile Factory
//        Main.GlobalVariables.openTiles.add((OpenTile) t1.getTop());
//        Main.GlobalVariables.openTiles.add((OpenTile) t1.getLeft());
//        Main.GlobalVariables.openTiles.add((OpenTile) t1.getRight());
//        Main.GlobalVariables.openTiles.add((OpenTile) t2.getBottom());
//        Main.GlobalVariables.openTiles.add((OpenTile) t2.getRight());
//        Main.GlobalVariables.openTiles.add((OpenTile) t2.getLeft());
        game.setCurrentTile(t1);
        assertEquals(true, game.updateAllScores());
        assertEquals(0, players.get(0).getPlayerScore());
        assertEquals(0, players.get(1).getPlayerScore());
    }

    @Test
    public void testUpdateAllScoresWithCity() {
        players.add(new Player(GlobalVariables.PlayerColor.RED));
        players.add(new Player(GlobalVariables.PlayerColor.YELLOW));

        Game game = new Game(new TileGrid(), getTiles(), this.players);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile t1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);
        players.get(0).getMeeples().get(0).place(t1, GlobalVariables.Feature.CITY, GlobalVariables.Location.BOTTOM);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile t2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1);
        t1.setBottom(t2);
        t2.setTop(t1);

        game.setCurrentTile(t1);
        assertEquals(true, game.updateAllScores());
        assertEquals(2, players.get(0).getPlayerScore());
        assertEquals(0, players.get(1).getPlayerScore());

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature2 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> internals = new HashSet<>();
        internals.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile t3 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature2,internals);
        players.get(1).getMeeples().get(0).place(t3, GlobalVariables.Feature.ROAD, GlobalVariables.Location.BOTTOM);
        internals = new HashSet<>();
        internals.add(GlobalVariables.Internal.ROADSTOP);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> featuress = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        featuress.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        featuress.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        featuress.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        featuress.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile t4 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), featuress, internals);
        t3.setBottom(t1);
        t1.setTop(t3);
        t1.setRight(t4);
        t4.setLeft(t1);

        game.setCurrentTile(t3);
        assertEquals(true, game.updateAllScores());
        assertEquals(2, game.getPlayers().get(0).getPlayerScore());
        assertEquals(3, game.getPlayers().get(1).getPlayerScore());

    }
}