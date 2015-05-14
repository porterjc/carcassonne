package ObjectTests;

import Main.GlobalVariables;
import Objects.*;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Created by robinsat on 4/1/2015
 */
public class PlayableTileTest {

    private Stack<PlayableTile> tiles;
    PlayableTile tile, lastPlaced;
    Player currentUser;
    //private HashMap<Main.GlobalVariables.Direction, Main.GlobalVariables.Feature> features;

    @Before
    public void setUp() {
        currentUser = new Player(GlobalVariables.PlayerColor.BLACK);
        tiles = new Stack<PlayableTile>();
        addTiles();
        lastPlaced = tiles.pop();
        tile = new PlayableTile();
    }

    //copied from UIComponentsTests.TileAddTest
    public void addTiles() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        tiles.add(new PlayableTile(feature));

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        tiles.add(new PlayableTile(feature1));

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature2 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile p = new PlayableTile(feature2);
        Object m = currentUser.getMeeples().get(0);
        tiles.add(p);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature3 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature3.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature3.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature3.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        tiles.add(new PlayableTile(feature3));

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature4 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature4.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature4.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature4.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature4.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        p = new PlayableTile(feature4);
        currentUser.getMeeples().get(currentUser.lastUsedMeeple);
        tiles.add(new PlayableTile(feature4));
    }

    @After
    public void cleanUp() {
        tile = null;
        tiles = null;
        currentUser = null;
    }

    @Test
    public void testGetTotalPlayableNeighbors() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);

        PlayableTile tile2 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);

        tile1.setBottom(tile2);
        tile2.setTop(tile1);

        assertEquals(1, tile1.getTotalPlayableNeighbors());
    }

    @Test
    public void testGetTotalPlayableNeighbors2() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);

        PlayableTile tile2 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);

        PlayableTile tile3 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);

        tile1.setTop(tile3);
        tile3.setBottom(tile1);
        tile1.setBottom(tile2);
        tile2.setTop(tile1);

        assertEquals(2, tile1.getTotalPlayableNeighbors());
    }

    @Test
    public void testGetTotalPlayableNeighbors4() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1);

        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), tile1, new OpenTile(), feature1);

        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), tile1, new OpenTile(), feature1);

        PlayableTile tile4 = new PlayableTile(new OpenTile(), new OpenTile(), tile1, new OpenTile(), feature1);

        PlayableTile tile5 = new PlayableTile(new OpenTile(), new OpenTile(), tile1, new OpenTile(), feature1);

        tile1.setLeft(tile4);
        tile4.setRight(tile1);
        tile1.setRight(tile5);
        tile5.setLeft(tile1);
        tile1.setTop(tile3);
        tile3.setBottom(tile1);
        tile1.setBottom(tile2);
        tile2.setTop(tile1);

        assertEquals(4, tile1.getTotalPlayableNeighbors());
    }

    @Test
    public void testGetTotalPlayableNeighbors8() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile2 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile3 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile4 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile5 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile6 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile7 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile8 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile9 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);

        tile8.setTop(tile4);
        tile4.setBottom(tile8);
        tile8.setRight(tile2);
        tile2.setLeft(tile8);
        tile9.setLeft(tile2);
        tile2.setRight(tile9);
        tile9.setTop(tile5);
        tile5.setBottom(tile9);
        tile6.setBottom(tile4);
        tile4.setTop(tile6);
        tile6.setRight(tile3);
        tile3.setLeft(tile6);
        tile7.setLeft(tile3);
        tile3.setRight(tile7);
        tile7.setBottom(tile5);
        tile5.setTop(tile7);
        tile1.setLeft(tile4);
        tile4.setRight(tile1);
        tile1.setRight(tile5);
        tile5.setLeft(tile1);
        tile1.setTop(tile3);
        tile3.setBottom(tile1);
        tile1.setBottom(tile2);
        tile2.setTop(tile1);

        assertEquals(8, tile1.getTotalPlayableNeighbors());
    }

    @Test
    public void testScoreSurroundingForCompletionIncomplete() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile2 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile3 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile4 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile5 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile6 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile7 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile8 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile9 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);

        tile9.setLeft(tile2);
        tile2.setRight(tile9);
        tile9.setTop(tile5);
        tile5.setBottom(tile9);
        tile6.setBottom(tile4);
        tile4.setTop(tile6);
        tile6.setRight(tile3);
        tile3.setLeft(tile6);
        tile7.setLeft(tile3);
        tile3.setRight(tile7);
        tile7.setBottom(tile5);
        tile5.setTop(tile7);
        tile1.setLeft(tile4);
        tile4.setRight(tile1);
        tile1.setRight(tile5);
        tile5.setLeft(tile1);
        tile1.setTop(tile3);
        tile3.setBottom(tile1);
        tile1.setBottom(tile2);
        tile2.setTop(tile1);

        assertEquals(-1, tile1.scoreSurrounding(true));
    }

    @Test
    public void testScoreSurroundingIncompleteCompletionFalse() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile2 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile3 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile4 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile5 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile6 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile7 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile8 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);
        PlayableTile tile9 = new PlayableTile(new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()),
                new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile()), feature1);


        tile9.setLeft(tile2);
        tile2.setRight(tile9);
        tile9.setTop(tile5);
        tile5.setBottom(tile9);
        tile6.setBottom(tile4);
        tile4.setTop(tile6);
        tile6.setRight(tile3);
        tile3.setLeft(tile6);
        tile7.setLeft(tile3);
        tile3.setRight(tile7);
        tile7.setBottom(tile5);
        tile5.setTop(tile7);
        tile1.setLeft(tile4);
        tile4.setRight(tile1);
        tile1.setRight(tile5);
        tile5.setLeft(tile1);
        tile1.setTop(tile3);
        tile3.setBottom(tile1);
        tile1.setBottom(tile2);
        tile2.setTop(tile1);

        assertEquals(8, tile1.scoreSurrounding(false));
    }

    @Test
    public void testNSbisector0rotations() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.NSBISECTOR);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);
        assertTrue(tile1.hasNSbisector());
    }

    @Test
    public void testNSbisector1rotations() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.NSBISECTOR);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);
        tile1.rotateTile();
        assertFalse(tile1.hasNSbisector());
    }

    @Test
    public void testNSbisector2rotations() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.NSBISECTOR);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);
        tile1.rotateTile();
        tile1.rotateTile();
        assertTrue(tile1.hasNSbisector());
    }

    @Test
    public void testNSbisector3rotations() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.NSBISECTOR);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);
        tile1.rotateTile();
        tile1.rotateTile();
        tile1.rotateTile();
        assertFalse(tile1.hasNSbisector());
    }

    @Test
    public void testNSbisector0rotationsNoNSbisector() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);
        assertFalse(tile1.hasNSbisector());
    }

    @Test
    public void testNSbisector1rotationsWithEWbisector() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.EWBISECTOR);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);
        tile1.rotateTile();
        assertTrue(tile1.hasNSbisector());
    }

    @Test
    public void testNSbisector2rotationsWithNSbisector() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.NSBISECTOR);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);
        tile1.rotateTile();
        tile1.rotateTile();
        assertTrue(tile1.hasNSbisector());
    }

    @Test
    public void testEWbisector0rotations() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.EWBISECTOR);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);
        assertTrue(tile1.hasEWbisector());
    }

    @Test
    public void testEWbisector1rotations() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.EWBISECTOR);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);
        tile1.rotateTile();
        assertFalse(tile1.hasEWbisector());
    }

    @Test
    public void testEWbisector2rotations() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.EWBISECTOR);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);
        tile1.rotateTile();
        tile1.rotateTile();
        assertTrue(tile1.hasEWbisector());
    }

    @Test
    public void testEWbisector3rotations() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.EWBISECTOR);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);
        tile1.rotateTile();
        tile1.rotateTile();
        tile1.rotateTile();
        assertFalse(tile1.hasEWbisector());
    }

    @Test
    public void testEWbisector0rotationsNoEWbisector() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);
        assertFalse(tile1.hasEWbisector());
    }

    @Test
    public void testEWbisector1rotationsWithNSbisector() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.NSBISECTOR);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);
        tile1.rotateTile();
        assertTrue(tile1.hasEWbisector());
    }

    @Test
    public void testEWbisector2rotationsWithEWbisector() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.EWBISECTOR);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);
        tile1.rotateTile();
        tile1.rotateTile();
        assertTrue(tile1.hasEWbisector());
    }
}

