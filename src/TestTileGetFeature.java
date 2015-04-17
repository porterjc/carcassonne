import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by porterjc on 4/16/2015.
 */
public class TestTileGetFeature {

    PlayableTile tile;

    @Before
    public void setUp() {
        //Image image = ImageIO.read(new File("images/01.png"));
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        tile = new PlayableTile(features);
    }

    @After
    public void cleanUp() {
        tile = null;
    }

    @Test
        public void testGetNorthFeature(){
        GlobalVariables.Feature n = GlobalVariables.Feature.ROAD;
        assertEquals(n, tile.getTargetFeature(GlobalVariables.Direction.NORTH));
    }

    @Test
    public void testGetWestFeature(){
        GlobalVariables.Feature w = GlobalVariables.Feature.GRASS;
        assertEquals(w, tile.getTargetFeature(GlobalVariables.Direction.WEST));
    }






}

