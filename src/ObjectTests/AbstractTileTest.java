package ObjectTests;

import Main.GlobalVariables;
import Objects.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Test for the methods shared by abstract tiles
 * Created by johnsoaa on 3/31/2015.
 */
public class AbstractTileTest {

    AbstractTile tile;
    AbstractTile openTile;

    @Before
    public void setUp() {
        tile = new NullTile();
        tile.setLeft(new NullTile());
        tile.setRight(new NullTile());
        tile.setTop(new NullTile());
        tile.setBottom(new NullTile());

        openTile = new OpenTile();
    }

    @After
    public void cleanUp() {
        tile = null;
    }

    @Test
    public void testCreateNullTile() {
        assertEquals(true, tile != null);
    }

    @Test
    public void testGetTopFeatureOfNullTile() {
        assertNull(tile.getTargetFeature(GlobalVariables.Direction.NORTH));
    }

    @Test
    public void testGetTop() {
        assertEquals(tile, tile.getTop().getBottom());
    }

    @Test
    public void testGetBottom() {
        assertEquals(tile, tile.getBottom().getTop());
    }

    @Test
    public void testGetLeft() {
        assertEquals(tile, tile.getLeft().getRight());
    }

    @Test
    public void testGetRight() {
        assertEquals(tile, tile.getRight().getLeft());
    }

    @Test
    public void testGetFeatures() {
        assertNull(tile.getFeatures());
    }

    @Test
    public void testGetInternals() {
        assertTrue(tile.getInternals().isEmpty());
    }

    @Test
    public void testAddTile() {
        OpenTile openTile = new OpenTile();
        AbstractTile originalTop = tile.getTop();

        TileGrid grid = new TileGrid();
        grid.add(tile);

        tile.addTile(openTile);
        assertEquals(openTile, originalTop.getBottom());
    }

    @Test
    public void testGetValue() {
        assertEquals(0, tile.getValue());
    }

    @Test
    public void testUpdateAdjacent() {
        assertNull(tile.updateAdjacent());
        assertNotNull(openTile.updateAdjacent());
    }

    @Test
    public void testMoveTile() {
        tile.moveTile(30, 60);
        assertEquals(30, tile.getX());
        assertEquals(60, tile.getY());
    }

    @Test
    public void testScoreRoad() {
        assertTrue(tile.scoreRoad(new HashSet<AbstractTile>(), new HashSet<Meeple>(), false, GlobalVariables.Direction.NORTH).getValue() < 0);
    }

    @Test
    public void testScoreCity() {
        assertTrue(tile.scoreCity(new HashSet<AbstractTile>(), new HashSet<Meeple>(), false).getValue() < 0);
    }

    @Test
    public void testTraceField() {
        assertTrue(tile.traceField(new HashSet<AbstractTile>(), GlobalVariables.Location.CENTER, new HashSet<Meeple>(), new HashSet<PlayableTile>(), false, 0).getKey().isEmpty());
    }

}