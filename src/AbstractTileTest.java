import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by johnsoaa on 3/31/2015.
 */
public class AbstractTileTest extends TestCase {


    @Test
    protected void testCreateTile() {
            assertEquals(null, AbstractTile.getOxwner());
    }
}