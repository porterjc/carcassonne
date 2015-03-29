import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by johnsoaa on 3/27/2015.
 */
public class PlayerTest {
    private Player p;

    @Before
    public void setUp() throws Exception {
        p = new Player(Color.AQUA);
    }

    @Test
    public void testSetup() {
        assertEquals(true, p != null);
        assertEquals(0, p.getPlayerScore());
        assertEquals(Color.AQUA, p.getColor());
        assertEquals(7, p.getMeeples().getSize());
    }

    @After
    public void cleanUp() {
        p = null;
    }
}
