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

import static org.junit.Assert.assertEquals;

/**
 * Created by johnsoaa on 5/14/2015.
 */
public class PlayableTileRoadTest {

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
    public void testShortRoadWithNoOtherRoads() {
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.ROADSTOP);

        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        PlayableTile left = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features1 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        features1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        features1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile p = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features1, internals);
        m.place(p, GlobalVariables.Feature.ROAD, GlobalVariables.Location.RIGHT);
        assertEquals(m, p.getMeeple());
        p.setLeft(left);
        left.setRight(p);
        Pair<Set<Meeple>, Integer> result = p.scoreRoad(alreadyVisited, new HashSet<Meeple>(), false).pop();
        assertEquals(1, result.getKey().size());
        assertEquals(m, result.getKey().toArray()[0]);
        assertEquals((Integer) 2, result.getValue());

    }

    @Test
    public void testShortRoadWithOtherRoadsPastBlockage() {
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.ROADSTOP);

        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        PlayableTile left = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features1 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        features1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile p = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features1, internals);
        m.place(p, GlobalVariables.Feature.ROAD, GlobalVariables.Location.RIGHT);
        assertEquals(m, p.getMeeple());
        p.setLeft(left);
        left.setRight(p);
        Pair<Set<Meeple>, Integer> result = p.scoreRoad(alreadyVisited, new HashSet<Meeple>(), false).pop();
        assertEquals(1, result.getKey().size());
        assertEquals(m, result.getKey().toArray()[0]);
        assertEquals((Integer) 2, result.getValue());

    }

    @Test
    public void testShortRoadThreeTilesStartingRight() {
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());

        //Left Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> leftTileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        leftTileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        leftTileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        leftTileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        leftTileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile left = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), leftTileFeatures, intA);
        //Right Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> rightFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        rightFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        rightFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        rightFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        rightFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> intB = new HashSet<GlobalVariables.Internal>();
        intB.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile right = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), rightFeatures, intB);
        //Top Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> topTileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        topTileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        topTileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        topTileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        topTileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile top = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), topTileFeatures, new HashSet<GlobalVariables.Internal>());

        left.setTop(top);
        top.setBottom(left);
        right.setLeft(left);
        left.setRight(right);
        m.place(right, GlobalVariables.Feature.ROAD, GlobalVariables.Location.LEFT);
        assertEquals(m, right.getMeeple());

        Pair<Set<Meeple>, Integer> result2 = right.scoreRoad(new HashSet<AbstractTile>(), new HashSet<Meeple>(), false).pop();
        assertEquals(1, result2.getKey().size());
        assertEquals(m, result2.getKey().toArray()[0]);
        assertEquals((Integer) 2, result2.getValue());
    }


    @Test
    public void testShortRoadThreeTilesStartingLeft() {
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        //Left Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> leftTileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        leftTileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        leftTileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        leftTileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        leftTileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile left = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), leftTileFeatures, intA);
        //Right Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> rightFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        rightFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        rightFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        rightFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        rightFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> intB = new HashSet<GlobalVariables.Internal>();
        intB.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile right = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), rightFeatures, intB);
        //Top Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> topTileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        topTileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        topTileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        topTileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        topTileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile top = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), topTileFeatures, new HashSet<GlobalVariables.Internal>());

        left.setTop(top);
        top.setBottom(left);
        right.setLeft(left);
        left.setRight(right);
        m.place(right, GlobalVariables.Feature.ROAD, GlobalVariables.Location.LEFT);
        assertEquals(m, right.getMeeple());

        Pair<Set<Meeple>, Integer> result2 = left.scoreRoad(new HashSet<AbstractTile>(), new HashSet<Meeple>(), false).pop();
        assertEquals(1, result2.getKey().size());
        assertEquals(m, result2.getKey().toArray()[0]);
        assertEquals((Integer) 2, result2.getValue());
    }
    @Test
    public void testLongerRoadWithNoEnd() {
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        //make meeples for testing
        Meeple p1m = new Meeple(new Player(GlobalVariables.PlayerColor.YELLOW), GlobalVariables.PlayerColor.YELLOW);
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile tl = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        internals = new HashSet<GlobalVariables.Internal>();
        PlayableTile bl = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        p1m.place(bl, GlobalVariables.Feature.ROAD, GlobalVariables.Location.TOP);
        features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile br = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        m.place(br, GlobalVariables.Feature.ROAD, GlobalVariables.Location.LEFT);
        br.setLeft(bl);
        bl.setRight(br);
        bl.setTop(tl);
        tl.setBottom(bl);
        tl.setBottom(bl);
        Pair<Set<Meeple>, Integer> temp = br.scoreRoad(alreadyVisited, new HashSet<Meeple>(), false).pop();
        assertEquals(-1, (int) temp.getValue());
        assertEquals(2, temp.getKey().size());
    }

    @Test
    public void testLongerRoad() {
        Player p = new Player(GlobalVariables.PlayerColor.YELLOW);
        Meeple p1m = new Meeple(p, GlobalVariables.PlayerColor.YELLOW);
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile tr = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        internals = new HashSet<GlobalVariables.Internal>();
        PlayableTile tl = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        internals = new HashSet<GlobalVariables.Internal>();
        PlayableTile bl = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile br = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        p1m.place(bl, GlobalVariables.Feature.ROAD, GlobalVariables.Location.TOP);
        m.place(br, GlobalVariables.Feature.ROAD, GlobalVariables.Location.LEFT);
        br.setLeft(bl);
        br.setTop(tr);
        bl.setRight(br);
        bl.setTop(tl);
        tl.setBottom(bl);
        tl.setRight(tr);
        tr.setLeft(tl);
        tl.setBottom(bl);
        Pair<Set<Meeple>, Integer> temp = br.scoreRoad(alreadyVisited, new HashSet<Meeple>(), false).pop();
        assertEquals((Integer) 4, temp.getValue());
        assertEquals(2, temp.getKey().size());

        /**
         * Testing updating player score
         */
        for (Meeple meep : temp.getKey()) {
            meep.getPlayer().updateScore(temp.getValue());
        }
        Assert.assertEquals(4, currentUser.getScore());
        Assert.assertEquals(4, p.getScore());
        currentUser.updateScore(3); //arbitrary score update
        Assert.assertEquals(7, currentUser.getScore());

    }

    @Test
    public void testEndOfGameScoringLongerRoadWithNoEnd() {
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        //make meeples for testing
        Meeple p1m = new Meeple(new Player(GlobalVariables.PlayerColor.YELLOW), GlobalVariables.PlayerColor.YELLOW);
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile tl = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        internals = new HashSet<GlobalVariables.Internal>();
        PlayableTile bl = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        p1m.place(bl, GlobalVariables.Feature.ROAD, GlobalVariables.Location.TOP);
        features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile br = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        m.place(br, GlobalVariables.Feature.ROAD, GlobalVariables.Location.LEFT);
        br.setLeft(bl);
        bl.setRight(br);
        bl.setTop(tl);
        tl.setBottom(bl);
        tl.setBottom(bl);

        Pair<Set<Meeple>, Integer> temp = br.scoreRoad(alreadyVisited, new HashSet<Meeple>(), true).pop();
        assertEquals(3, (int) temp.getValue());
        assertEquals(2, temp.getKey().size());
    }


    @Test
    public void testEndOfGameScoringLongerRoadWithNoEndInOtherDirection() {
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        //make meeples for testing
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> fls = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.ROADSTOP);
        fls = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        fls.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        fls.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        fls.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        fls.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile tl1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), fls, internals);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> fs = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        fs.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        fs.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        fs.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        fs.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        internals = new HashSet<GlobalVariables.Internal>();
        PlayableTile bl1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), fs, internals);
        bl1.setTop(tl1);
        tl1.setBottom(bl1);

        assertEquals(bl1, tl1.getBottom());
        assertEquals(tl1, bl1.getTop());


        Pair<Set<Meeple>, Integer> temp = bl1.scoreRoad(alreadyVisited, new HashSet<Meeple>(), true).pop();
        assertEquals(2, (int) temp.getValue());
        assertEquals(0, temp.getKey().size());

        m.place(tl1, GlobalVariables.Feature.ROAD, GlobalVariables.Location.RIGHT);

        temp = tl1.scoreRoad(new HashSet<AbstractTile>(), new HashSet<Meeple>(), true).pop();
        assertEquals(2, (int) temp.getValue());
        assertEquals(1, temp.getKey().size());
    }

}
