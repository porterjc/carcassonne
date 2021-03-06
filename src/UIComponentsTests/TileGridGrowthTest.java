package UIComponentsTests;
import Main. *;
import Objects.PlayableTile;
import UIComponents.TileGrid;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by robinsat on 4/14/2015
 */
public class TileGridGrowthTest {
    private TileGrid grid;
    private PlayableTile startingTile;

    @Before
    public void setUp() throws Exception {
        grid = new TileGrid(900, 900);
        startingTile = TileFactory.getStartTile();
        grid.initialize(startingTile);
    }

    @Test
    public void testStart() {
        assertNotNull(startingTile);
    }

    @Test
    public void testStartLeft() {
        assertNotNull(startingTile.getLeft());
    }

    @Test
    public void testStartRight() {
        assertNotNull(startingTile.getRight());
    }

    @Test
    public void testStartTop() {
        assertNotNull(startingTile.getTop());
    }

    @Test
    public void testStartBottom() {
        assertNotNull(startingTile.getBottom());
    }

    @Test
    public void testStartBottomRight() {
        assertNotNull(startingTile.getBottomRight());
        assertSame(startingTile.getBottom().getRight(), startingTile.getRight().getBottom());
    }

    @Test
    public void testStartBottomLeft() {
        assertNotNull(startingTile.getBottomLeft());
        assertSame(startingTile.getBottom().getLeft(), startingTile.getLeft().getBottom());
    }

    @Test
    public void testStartTopRight() {
        assertNotNull(startingTile.getTopRight());
        assertSame(startingTile.getTop().getRight(), startingTile.getRight().getTop());
    }

    @Test
    public void testStartTopLeft() {
        assertNotNull(startingTile.getTopLeft());
        assertSame(startingTile.getTop().getLeft(), startingTile.getLeft().getTop());
    }

    @After
    public void cleanUp() {
        grid = null;
        startingTile = null;
    }
}
