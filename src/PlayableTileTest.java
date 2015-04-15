import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by robinsat on 4/1/2015
 */
public class PlayableTileTest {

    PlayableTile tile;

    @Before
    public void setUp() {
        tile = new PlayableTile();
    }

    @After
    public void cleanUp() {
        tile = null;
    }

    @Test
    public void testCreatePlayableTile() {
        assertEquals(true, tile != null);
    }


}