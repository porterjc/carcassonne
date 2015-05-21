package ObjectTests;

import Main.GlobalVariables;
import Objects.AbstractTile;
import Objects.NullTile;
import Objects.OpenTile;
import Objects.PlayableTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Test for the methods shared by abstract tiles
 * Created by johnsoaa on 3/31/2015.
 */
public class AbstractTileTest {

    AbstractTile tile;

    @Before
    public void setUp() {
        tile = new NullTile();
    }

    @After
    public void cleanUp() {
        tile = null;
    }

    @Test
    public void testCreateNullTile() {
        assertEquals(true, tile != null);
    }
    



}