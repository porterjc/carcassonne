package ObjectTests;

import Main.GlobalVariables;
import Objects.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by johnsoaa on 5/14/2015.
 */
public class PlayableTileFarmerTest {

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
    public void testCreatePlayableTile() {
        assertEquals(true, tile != null);
    }

    @Test
    public void testGetNextAvailableMeeple() {
    }


    @Test
    public void testFindNoFarmerBasicGrass() {

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);
        assertFalse(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER));
    }


    @Test
    public void testFindFarmerOnGrassToLeft() {
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
        PlayableTile tile2 = new PlayableTile(new OpenTile(), tile1, new OpenTile(), new OpenTile(), feature2);

        Meeple m = new Meeple(new Player(GlobalVariables.PlayerColor.RED), GlobalVariables.PlayerColor.RED);
        m.place(tile2, GlobalVariables.Feature.GRASS, GlobalVariables.Location.CENTER);
        tile2.setMeeple(m);
        assertTrue(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER));
    }

    @Test
    public void testFindFarmerOnGrassToRight() {
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
        PlayableTile tile2 = new PlayableTile(tile1, new OpenTile(), new OpenTile(), new OpenTile(), feature2);

        Meeple m = new Meeple(new Player(GlobalVariables.PlayerColor.RED), GlobalVariables.PlayerColor.RED);
        m.place(tile2, GlobalVariables.Feature.GRASS, GlobalVariables.Location.CENTER);
        tile2.setMeeple(m);
        assertTrue(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER));
    }

    @Test
    public void testFindFarmerOnGrassAbove() {
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
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), tile1, feature2);

        Meeple m = new Meeple(new Player(GlobalVariables.PlayerColor.RED), GlobalVariables.PlayerColor.RED);
        m.place(tile2, GlobalVariables.Feature.GRASS, GlobalVariables.Location.CENTER);
        tile2.setMeeple(m);
        assertTrue(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER));
    }

    @Test
    public void testFindFarmerOnGrassBelow() {
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
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), tile1, new OpenTile(), feature2);

        Meeple m = new Meeple(new Player(GlobalVariables.PlayerColor.RED), GlobalVariables.PlayerColor.RED);
        m.place(tile2, GlobalVariables.Feature.GRASS, GlobalVariables.Location.CENTER);
        tile2.setMeeple(m);
        assertTrue(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER));
    }

    @Test
    public void testFindNoFarmerInSurroundingGrass() {
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
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature2);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature3 = new HashMap<>();
        feature3.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature3);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature4 = new HashMap<>();
        feature4.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature4.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature4.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature4.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile4 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature4);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature5 = new HashMap<>();
        feature5.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature5.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature5.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature5.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile5 = new PlayableTile(tile1, tile2, tile3, tile4, feature5);

        assertFalse(tile5.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER));
    }

    @Test
    public void testFindFarmerPastCity() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature2 = new HashMap<>();
        feature2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), tile1, new OpenTile(), new OpenTile(), feature2);

        Meeple m = new Meeple(new Player(GlobalVariables.PlayerColor.RED), GlobalVariables.PlayerColor.RED);
        m.place(tile2, GlobalVariables.Feature.GRASS, GlobalVariables.Location.CENTER);
        tile2.setMeeple(m);
        /**
         * This is failing not because of refactoring, but because of adding the set of Meeples to the return
         * TODO fix
         */
        assertFalse(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER));
    }

    @Test
    public void testFindFarmerPastRoad() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature2 = new HashMap<>();
        feature2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        HashSet<GlobalVariables.Internal> internals = new HashSet<>();
        internals.add(GlobalVariables.Internal.EWBISECTOR);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), tile1, new OpenTile(), feature2, internals);

        Meeple m = new Meeple(new Player(GlobalVariables.PlayerColor.RED), GlobalVariables.PlayerColor.RED);
        m.place(tile2, GlobalVariables.Feature.GRASS, GlobalVariables.Location.BOTTOM);
        tile2.setMeeple(m);
        assertFalse(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER));
    }

    @Test
    public void testFindFarmerTwoTilesDown() {
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
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), tile1, new OpenTile(), feature2);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature3 = new HashMap<>();
        feature3.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), tile2, new OpenTile(), feature3);

        Meeple m = new Meeple(new Player(GlobalVariables.PlayerColor.RED), GlobalVariables.PlayerColor.RED);
        m.place(tile3, GlobalVariables.Feature.GRASS, GlobalVariables.Location.BOTTOM);
        tile3.setMeeple(m);
        assertTrue(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER));
    }

    @Test
    public void testFindFarmerBottomLeft() {
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
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), tile1, new OpenTile(), feature2);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature3 = new HashMap<>();
        feature3.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), tile2, new OpenTile(), new OpenTile(), feature3);

        Meeple m = new Meeple(new Player(GlobalVariables.PlayerColor.RED), GlobalVariables.PlayerColor.RED);
        m.place(tile3, GlobalVariables.Feature.GRASS, GlobalVariables.Location.BOTTOM);
        tile3.setMeeple(m);
        assertTrue(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER));
    }

    @Test
    public void testFindFarmerTopRight() {
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
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), tile1, feature2);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature3 = new HashMap<>();
        feature3.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile3 = new PlayableTile(tile2, new OpenTile(), new OpenTile(), new OpenTile(), feature3);

        Meeple m = new Meeple(new Player(GlobalVariables.PlayerColor.RED), GlobalVariables.PlayerColor.RED);
        m.place(tile3, GlobalVariables.Feature.GRASS, GlobalVariables.Location.BOTTOM);
        tile3.setMeeple(m);
        assertTrue(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER));
    }

    @Test
    public void testFindFarmerDownVerticalRoad() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        internal1.add(GlobalVariables.Internal.NSBISECTOR);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature2 = new HashMap<>();
        feature2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal2 = new HashSet<>();
        internal2.add(GlobalVariables.Internal.NSBISECTOR);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), tile1, new OpenTile(), feature2, internal2);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature3 = new HashMap<>();
        feature3.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature3.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal3 = new HashSet<>();
        internal3.add(GlobalVariables.Internal.NSBISECTOR);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), tile2, new OpenTile(), feature3, internal3);

        Meeple m = new Meeple(new Player(GlobalVariables.PlayerColor.RED), GlobalVariables.PlayerColor.RED);
        m.place(tile3, GlobalVariables.Feature.GRASS, GlobalVariables.Location.RIGHT);
        tile3.setMeeple(m);
        assertTrue(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.RIGHT));
    }


    @Test
    public void testFindFarmerAcrossVerticalRoad() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        internal1.add(GlobalVariables.Internal.NSBISECTOR);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature2 = new HashMap<>();
        feature2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal2 = new HashSet<>();
        internal2.add(GlobalVariables.Internal.NSBISECTOR);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), tile1, new OpenTile(), feature2, internal2);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature3 = new HashMap<>();
        feature3.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature3.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal3 = new HashSet<>();
        internal3.add(GlobalVariables.Internal.NSBISECTOR);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), tile2, new OpenTile(), feature3, internal3);

        Meeple m = new Meeple(new Player(GlobalVariables.PlayerColor.RED), GlobalVariables.PlayerColor.RED);
        m.place(tile3, GlobalVariables.Feature.GRASS, GlobalVariables.Location.LEFT);
        tile3.setMeeple(m);
        assertFalse(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.RIGHT));
    }

    @Test
    public void testFindFarmerAcrossBentRoad() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        internal1.add(GlobalVariables.Internal.NSBISECTOR);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature2 = new HashMap<>();
        feature2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal2 = new HashSet<>();
        internal2.add(GlobalVariables.Internal.NSBISECTOR);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), tile1, new OpenTile(), feature2, internal2);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature3 = new HashMap<>();
        feature3.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature3.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature3.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), tile2, new OpenTile(), feature3);

        Meeple m = new Meeple(new Player(GlobalVariables.PlayerColor.RED), GlobalVariables.PlayerColor.RED);
        m.place(tile3, GlobalVariables.Feature.GRASS, GlobalVariables.Location.TOPLEFT);
        tile3.setMeeple(m);
        assertFalse(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.RIGHT));
    }
/*
    @Test
    public void testFindNoFarmerAcrossTRoad() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        internal1.add(GlobalVariables.Internal.EWBISECTOR);
        internal1.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature2 = new HashMap<>();
        feature2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        HashSet<GlobalVariables.Internal> internal2 = new HashSet<>();
        internal1.add(GlobalVariables.Internal.CITY);
        PlayableTile tile2 = new PlayableTile(tile1, new OpenTile(), new OpenTile(), new OpenTile(), feature2, internal2);

        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        m.place(tile2, GlobalVariables.Feature.GRASS, GlobalVariables.Location.BOTTOMLEFT);
        tile2.setMeeple(m);
       // assertFalse(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.TOPLEFT));
        assertFalse(tile1.findFarmer(new HashSet<AbstractTile>(), GlobalVariables.Location.TOPRIGHT));

    }
*/
    @Test
    public void testOnSameSideOfVerticalRoad() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        internal1.add(GlobalVariables.Internal.NSBISECTOR);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.BOTTOMLEFT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.TOPLEFT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.TOPRIGHT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.BOTTOMRIGHT));
    }

    @Test
    public void testOnSameSideOfHorizontalRoad() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        internal1.add(GlobalVariables.Internal.EWBISECTOR);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.BOTTOMLEFT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.BOTTOMRIGHT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.TOPRIGHT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.TOPLEFT));
    }

    @Test
    public void testQuadrant1BentRoad() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPRIGHT, GlobalVariables.Location.TOPRIGHT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPRIGHT, GlobalVariables.Location.TOPLEFT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPRIGHT, GlobalVariables.Location.BOTTOMLEFT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPRIGHT, GlobalVariables.Location.BOTTOMRIGHT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.BOTTOMRIGHT));
    }

    @Test
    public void testQuadrant2BentRoad() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.TOPLEFT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.TOPRIGHT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.BOTTOMLEFT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.BOTTOMRIGHT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPRIGHT, GlobalVariables.Location.BOTTOMRIGHT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPRIGHT, GlobalVariables.Location.BOTTOMLEFT));
    }

    @Test
    public void testQuadrant3BentRoad() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMLEFT, GlobalVariables.Location.BOTTOMLEFT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMLEFT, GlobalVariables.Location.TOPRIGHT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMLEFT, GlobalVariables.Location.BOTTOMRIGHT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMLEFT, GlobalVariables.Location.TOPLEFT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMRIGHT, GlobalVariables.Location.TOPLEFT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.BOTTOMLEFT));
    }

    @Test
    public void testQuadrant4BentRoad() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMRIGHT, GlobalVariables.Location.BOTTOMRIGHT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMRIGHT, GlobalVariables.Location.TOPRIGHT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMRIGHT, GlobalVariables.Location.BOTTOMLEFT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMRIGHT, GlobalVariables.Location.TOPLEFT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMLEFT, GlobalVariables.Location.TOPLEFT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPRIGHT, GlobalVariables.Location.BOTTOMLEFT));
    }

    @Test
    public void test4WayIntersection() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        internal1.add(GlobalVariables.Internal.ROADSTOP);
        internal1.add(GlobalVariables.Internal.NSBISECTOR);
        internal1.add(GlobalVariables.Internal.EWBISECTOR);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMRIGHT, GlobalVariables.Location.BOTTOMRIGHT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMRIGHT, GlobalVariables.Location.TOPRIGHT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMRIGHT, GlobalVariables.Location.TOPLEFT));
        assertFalse(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMRIGHT, GlobalVariables.Location.BOTTOMLEFT));
    }

    @Test
    public void testTopRoadSegment() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        internal1.add(GlobalVariables.Internal.ROADSTOP);
        internal1.add(GlobalVariables.Internal.MONASTERY);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.TOPRIGHT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPRIGHT, GlobalVariables.Location.BOTTOMLEFT));
    }

    @Test
    public void testLeftRoadSegment() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        internal1.add(GlobalVariables.Internal.ROADSTOP);
        internal1.add(GlobalVariables.Internal.MONASTERY);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.TOPRIGHT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.BOTTOMLEFT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.TOPLEFT, GlobalVariables.Location.BOTTOMRIGHT));
    }

    @Test
    public void testRightRoadSegment() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        internal1.add(GlobalVariables.Internal.ROADSTOP);
        internal1.add(GlobalVariables.Internal.MONASTERY);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMRIGHT, GlobalVariables.Location.TOPRIGHT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMRIGHT, GlobalVariables.Location.TOPLEFT));
    }

    @Test
    public void testBottomRoadSegment() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        HashSet<GlobalVariables.Internal> internal1 = new HashSet<>();
        internal1.add(GlobalVariables.Internal.ROADSTOP);
        internal1.add(GlobalVariables.Internal.MONASTERY);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature1, internal1);

        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMLEFT, GlobalVariables.Location.BOTTOMRIGHT));
        assertTrue(tile1.isOnSameSideOfRoad(GlobalVariables.Location.BOTTOMLEFT, GlobalVariables.Location.TOPRIGHT));
    }


}
