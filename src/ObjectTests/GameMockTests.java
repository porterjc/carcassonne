package ObjectTests;

import Main.GlobalVariables;
import Main.TileFactory;
import Objects.Game;
import Objects.PlayableTile;
import Objects.OpenTile;
import Objects.Player;
import Objects.Meeple;
import UIComponents.BottomDisplay;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

import java.util.*;

/**
 * Created by porterjc on 5/20/2015.
 */
public class GameMockTests {
    Game testGame;
    public int numberOfTiles = 0;
    public ArrayList<Player> players;
    public BottomDisplay bdstub;
    public Stack<PlayableTile> testStack;
    Player user1;
    Player user2;

    @Before
    public void setUp() {
        TileFactory fact = new TileFactory();
        JPanel panel = new JPanel();
        user1 = new Player(GlobalVariables.PlayerColor.BLACK);
        user2 = new Player(GlobalVariables.PlayerColor.RED);
        this.testStack = new Stack<PlayableTile>();
        testStack.add(fact.getStartTile());
        this.players = new ArrayList<Player>();
        this.players.add(user1);
        this.players.add(user2);
        this.bdstub = new BottomDisplay(500);
        testGame = new Game(bdstub, testStack, players, false, false);
        this.bdstub.initializeLabels(testGame);
        panel.add(bdstub);
    }

    @Test
    public void testScoreEndOfGameCity(){
        Meeple m = user1.removeMeeple();
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.CITY);
        internals.add(GlobalVariables.Internal.COATOFARMS);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile4 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile5 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile6 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        tile1.setTop(tile2);
        tile2.setBottom(tile1);
        tile1.setLeft(tile5);
        tile5.setRight(tile1);
        tile5.setLeft(tile6);
        tile6.setRight(tile5);
        tile5.setTop(tile3);
        tile3.setBottom(tile5);
        tile2.setLeft(tile3);
        tile3.setRight(tile2);
        tile3.setLeft(tile4);
        tile4.setRight(tile3);
        tile4.setBottom(tile6);
        tile6.setTop(tile4);

        m.place(tile1, GlobalVariables.Feature.CITY, GlobalVariables.Location.TOP);
        tile1.setMeeple(m);

        testGame.setCurrentTile(tile5);
        testGame.endGame();

        assertEquals(24, user1.getPlayerScore());

    }
}
