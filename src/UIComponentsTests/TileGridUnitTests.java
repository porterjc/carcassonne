package UIComponentsTests;

import Objects.*;
import Main.*;
import Objects.OpenTile;
import Objects.PlayableTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

/**
 * Created by porterjc on 5/7/2015.
 */
public class TileGridUnitTests {
    TileGrid grid;

    @Before
    public void setUp() {
        grid = new TileGrid();
    }

    @After
    public void tearDown() {
        grid = null;
    }

    @Test
    public void testAreValidMoves() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(feature);
        //TODO FIX THE GV
//        GlobalVariables.openTiles = new ArrayList<OpenTile>();
//        GlobalVariables.openTiles.add(new OpenTile(tile1, tile1, tile1, tile1));
        assertTrue(grid.areValidMoves(tile1));
    }

    @Test
    public void testAreValidMovesMoreTiles() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile1 = new PlayableTile(feature);

        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile2 = new PlayableTile(feature);
//         TODO FIX THE GV
//        GlobalVariables.openTiles = new ArrayList<OpenTile>();
//        GlobalVariables.openTiles.add(new OpenTile(tile1, tile1, tile1, tile1));

        assertTrue(grid.areValidMoves(tile2));
    }
}