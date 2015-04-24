import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by robinsat on 4/1/2015
 */
public class PlayableTileTest {

    private Stack<PlayableTile> tiles;
    PlayableTile tile, lastPlaced;
    Player currentUser;
    //private HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features;

    @Before
    public void setUp() {
        currentUser = new Player(Color.black);
        tiles = new Stack<PlayableTile>();
        addTiles();
        lastPlaced = tiles.pop();
        tile = new PlayableTile();
    }

    //copied from TileAddTest
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
    public void testCreatePlayableTile() {
        assertEquals(true, tile != null);
    }

    @Test
    public void testGetNextAvailableMeeple() {
    }

    @Test
    public void testScoreRoad() {
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        alreadyVisited.add(lastPlaced);
        assertEquals(-1, lastPlaced.scoreRoad(alreadyVisited, new HashSet<Meeple>()));
    }

    @Test
    public void testShortRoad() {
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.ROADSTOP);

        PlayableTile left = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        PlayableTile p = new PlayableTile(features, internals);
        p.setLeft(left);
        left.setRight(p);
        assertEquals(2, p.scoreRoad(alreadyVisited, new HashSet<Meeple>()));
    }

    @Test
    public void testLongerRoadWithNoEnd() {
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
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
        features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile br = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);
        br.setLeft(bl);
        bl.setRight(br);
        bl.setTop(tl);
        tl.setBottom(bl);
        tl.setBottom(bl);
        assertEquals(-1, br.scoreRoad(alreadyVisited, new HashSet<Meeple>()));
    }

    @Test
    public void testLongerRoad() {
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
        br.setLeft(bl);
        br.setTop(tr);
        bl.setRight(br);
        bl.setTop(tl);
        tl.setBottom(bl);
        tl.setRight(tr);
        tr.setLeft(tl);
        tl.setBottom(bl);
        assertEquals(4, br.scoreRoad(alreadyVisited, new HashSet<Meeple>()));

    }

    @Test
    public void testScoreSmallCity() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        tile1.setBottom(tile2);
        tile2.setTop(tile1);

        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        assertEquals(4, tile1.scoreCity(alreadyVisited, new HashSet<Meeple>()));
    }

    @Test
    public void testScoreSmallishCity() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        tile1.setBottom(tile2);
        tile2.setTop(tile1);
        tile2.setRight(tile3);
        tile3.setLeft(tile2);

        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        assertEquals(6, tile1.scoreCity(alreadyVisited, new HashSet<Meeple>()));
    }

    @Test
<<<<<<< HEAD
    public void testScoreLargerCity() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile tile4 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile5 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile6 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        tile1.setBottom(tile2);
        tile2.setTop(tile1);
        tile2.setRight(tile5);
        tile5.setLeft(tile2);
        tile5.setRight(tile3);
        tile3.setLeft(tile5);
        tile2.setLeft(tile4);
        tile4.setRight(tile2);
        tile2.setBottom(tile6);
        tile6.setTop(tile2);

        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        assertEquals(12, tile1.scoreCity(alreadyVisited, new HashSet<Meeple>()));
    }

    @Test
    public void testScoreLarger2City() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile4 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile5 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile6 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        tile1.setTop(tile2);
        tile2.setBottom(tile1);
        tile1.setRight(tile5);
        tile5.setLeft(tile1);
        tile5.setRight(tile6);
        tile6.setLeft(tile5);
        tile5.setTop(tile3);
        tile3.setBottom(tile5);
        tile2.setRight(tile3);
        tile3.setLeft(tile2);
        tile3.setRight(tile4);
        tile4.setLeft(tile3);
        tile4.setBottom(tile6);
        tile6.setTop(tile4);
        



        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        assertEquals(12, tile1.scoreCity(alreadyVisited, new HashSet<Meeple>()));
    }

    @Test
    public void testScoreBasicGrass() {
=======
    public void testFindNoFarmerBasicGrass() {
>>>>>>> origin/master
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);
        assertFalse(tile1.findFarmer(new HashSet<AbstractTile>()));
    }

    @Test
    public void testFindFarmerBasicGrass() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);
        Meeple m = new Meeple(new Player(Color.RED), Color.RED);
        m.place(tile1, GlobalVariables.Feature.GRASS);
        tile1.setMeeple(m);
        assertTrue(tile1.findFarmer(new HashSet<AbstractTile>()));
    }

    @Test
    public void testFindFarmerTwoGrassTiles() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature2 = new HashMap<>();
        feature2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), tile1, new OpenTile(), new OpenTile(), feature1);

        Meeple m = new Meeple(new Player(Color.RED), Color.RED);
        m.place(tile2, GlobalVariables.Feature.GRASS);
        tile2.setMeeple(m);
        assertTrue(tile1.findFarmer(new HashSet<AbstractTile>()));
    }


}