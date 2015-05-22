package ObjectTests;

import Main.GlobalVariables;
import Objects.NullTile;
import Objects.OpenTile;
import Objects.PlayableTile;
import UIComponents.TileGrid;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by johnsoaa on 3/31/2015.
 */
public class OpenTileTest {

    OpenTile tile;
    OpenTile tile1;
    OpenTile tile2;
    OpenTile tile3;
    OpenTile tile4;

    PlayableTile tileToPlace;

    @Before
    public void setUp() {
        tile = new OpenTile(new NullTile(), new NullTile(), new NullTile(), new NullTile());
        tile1 = new OpenTile(new NullTile(), new NullTile(), null, new NullTile());
        tile2 = new OpenTile(new NullTile(), new NullTile(), new NullTile(), null);
        tile3 = new OpenTile(null, new NullTile(), new NullTile(), new NullTile());
        tile4 = new OpenTile(new NullTile(), null, new NullTile(), new NullTile());

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        tileToPlace = new PlayableTile(features);
    }

    @After
    public void cleanUp() {
        tile = null;
    }

    @Test
    public void testCreateOpenTile() {
        assertEquals(true, tile != null);
    }

    @Test
    public void testCanPlaceAllSides() {
        //set up
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);

        PlayableTile l = new PlayableTile(features);
        PlayableTile r = new PlayableTile(features);
        PlayableTile t = new PlayableTile(features);
        PlayableTile b = new PlayableTile(features);
        tile = new OpenTile(l, r, t, b);


        assertEquals(features, tileToPlace.getFeatures());
        assertTrue(tile.canPlace(tileToPlace));
    }

    @Test
    public void testCannotPlace() {

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features2 = new HashMap<>();
        features2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        features2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);

        PlayableTile pt = new PlayableTile(features2);
        tile.setTop(pt);
        PlayableTile second = new PlayableTile(features2);
        assertFalse(tile.canPlace(second));
    }

    @Test
    public void testCanPlaceWithEmptySpots() {
        assertTrue(tile.canPlace(tileToPlace));
    }

    @Test
    public void testUpdateAdjacent() {
        assertNull(tile.updateAdjacent());
        assertEquals(GlobalVariables.Direction.NORTH, tile1.updateAdjacent());
        assertEquals(GlobalVariables.Direction.SOUTH, tile2.updateAdjacent());
        assertEquals(GlobalVariables.Direction.WEST, tile3.updateAdjacent());
        assertEquals(GlobalVariables.Direction.EAST, tile4.updateAdjacent());
    }

    @Test
    public void testAddTile() {
        PlayableTile pt = new PlayableTile(GlobalVariables.blackAbbot, new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>(), new HashSet<GlobalVariables.Internal>());
        new TileGrid().add(tile);
        assertNotNull(tile.addTile(pt));
    }

}