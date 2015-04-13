import java.awt.Color;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by johnsoaa on 3/29/2015.
 */
public class MeepleTest extends TestCase {
    private Player p;
    private Meeple m;

    @Before
    public void setUp() {
        p = new Player(Color.CYAN);
        m = new Meeple(p, Color.CYAN);
    }

    @Test
    public void testSetUp() {
        assertEquals(Color.CYAN, m.getColor());
        assertEquals(p, m.getPlayer());

    }

    @Test
    public void testPlaceMeeble() {
        assertEquals(null, m.getFeature());
        m.place(GlobalVariables.Feature.GRASS);
        assertEquals(GlobalVariables.Feature.GRASS, m.getFeature());
    }

    @After
    public void cleanUp() {
        p = null;
        m = null;

    }
}