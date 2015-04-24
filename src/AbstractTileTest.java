import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by johnsoaa on 3/31/2015.
 */
public class AbstractTileTest {

    OpenTile tile;

    @Before
    public void setUp() {
        tile = new OpenTile();
    }

    @After
    public void cleanUp() {
        tile = null;
    }

    @Test
    public void testCreateOpenTile() {
        assertEquals(true, tile != null);
    }

    @Test
    public void testCanPlace() {
        //set up

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);

        PlayableTile l = new PlayableTile(features);
        PlayableTile r = new PlayableTile(features);
        PlayableTile t = new PlayableTile(features);
        PlayableTile b = new PlayableTile(features);
        tile = new OpenTile(l, r, t, b);


        PlayableTile tileToPlace = new PlayableTile(features);
        assertEquals(features, tileToPlace.getFeatures());
        assertEquals(true, tile.canPlace(tileToPlace));

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features2 = new HashMap<>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);

        assertEquals(features, tileToPlace.getFeatures());
        PlayableTile second = new PlayableTile(features2);
        assertEquals(false, tile.canPlace(second));

    }

}