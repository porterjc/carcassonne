import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by johnsoaa on 3/31/2015.
 */
public class OpenTileTest extends TestCase {

    AbstractTile tile;
    AbstractTile topNeighbor;

    @Before
    public void setUp() {
        tile = new OpenTile(0, 0);
    }

    @After
    public void cleanUp() {
        tile = null;
    }

    @Test
    public void testCreateOpenTile() {
        assertEquals(true, tile != null);
//        assertEquals(, tile.getEdges());

    }

}