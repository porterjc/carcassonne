
import junit.framework.TestResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by johnsoaa on 3/31/2015.
 */
public class AbstractTileTest {

    private OpenTile openTile;
    private PlayableTile playableTile;


    @Before
    public void setUp() {
        openTile = new OpenTile();
        playableTile = new PlayableTile();
    }

    @After
    public void cleanUp() {
        openTile = null;
        playableTile = null;
    }

    @Test
    protected void testCreateTile() {
        assertEquals(playableTile, openTile.getLeft());
    }
}