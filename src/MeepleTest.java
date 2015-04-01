import javafx.scene.paint.Color;
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
        p = new Player(Color.AQUA);
        m = new Meeple(p, Color.AQUA);
    }

    @Test
    public void testSetUp() {
        assertEquals(Color.AQUA, m.getColor());
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