package ObjectTests;

import Main.GlobalVariables;
import Objects.*;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by johnsoaa on 5/17/2015.
 */
public class PlayableTileScoreRoadTest {

    private Stack<PlayableTile> tiles;
    PlayableTile tile, lastPlaced;
    Player currentUser;

    @Before
    public void setUp() {
        currentUser = new Player(GlobalVariables.PlayerColor.BLACK);
    }

    @After
    public void cleanUp() {
        currentUser = null;
    }

    @Test
    public void testStartScoreRoad() {
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        PlayableTile left = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);

        HashSet<Meeple> meeples = new HashSet<>();
        Pair<Set<Meeple>, Integer> score = left.scoreRoad(alreadyVisited, meeples, false);
        assertEquals(0, score.getKey().size());
        assertEquals(-1, (int) score.getValue());
    }

    @Test
    public void testStarScoreRS() {
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> topFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        topFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile top = new PlayableTile(topFeatures, intA);
        Pair<HashSet<Meeple>, Integer> score = top.startScoreRoad(false);
        assertEquals(0, score.getKey().size());
        assertEquals(1, (int) score.getValue());//todo this will change
    }

    @Test
    public void testStartScoreRoadNorth() {
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> topFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        topFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile top = new PlayableTile(topFeatures, intA);
        //Make bottom Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> bottomFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        bottomFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        bottomFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> intB = new HashSet<GlobalVariables.Internal>();
        intB.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile bottom = new PlayableTile(bottomFeatures, intB);
        //Add the meeple
        m.place(bottom, GlobalVariables.Feature.ROAD, GlobalVariables.Location.TOP);
        bottom.setMeeple(m);
        //set the tiles
        top.setBottom(bottom);
        bottom.setTop(top);

        Pair<HashSet<Meeple>, Integer> score = bottom.startScoreRoad(false);
        assertEquals(1, score.getKey().size());
        assertEquals(2, (int) score.getValue());
    }

    @Test
    public void testScoreRoad() {
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> topFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        topFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile top = new PlayableTile(topFeatures, intA);
        //Make bottom Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> bottomFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        bottomFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        bottomFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> intB = new HashSet<GlobalVariables.Internal>();
        intB.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile bottom = new PlayableTile(bottomFeatures, intB);
        //Add the meeple
        m.place(bottom, GlobalVariables.Feature.ROAD, GlobalVariables.Location.TOP);
        bottom.setMeeple(m);
        //set the tiles
        top.setBottom(bottom);
        bottom.setTop(top);

        Pair<Set<Meeple>, Integer> score = bottom.scoreRoad(new HashSet<AbstractTile>(), new HashSet<Meeple>(), false);
        assertEquals(1, score.getKey().size());
        assertEquals(2, (int) score.getValue());

    }

}
