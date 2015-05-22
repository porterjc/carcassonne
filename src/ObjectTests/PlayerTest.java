package ObjectTests;

import Main.*;
import Objects.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.annotation.XmlElementDecl;
import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by johnsoaa on 3/27/2015.
 */
public class PlayerTest {
    private Player p;

    @Before
    public void setUp() throws Exception {
        p = new Player(GlobalVariables.PlayerColor.BLUE);
    }

    @Test
    public void testSetup() {
        assertEquals(false, p == null);
        assertEquals(0, p.getPlayerScore());
        assertEquals(GlobalVariables.PlayerColor.BLUE, p.getPlayerColor());
        assertEquals(7, p.getNumMeeples());
    }
    @Test
    public void testScoreUpdate(){
        p.updateScore(6);
        assertEquals(6, p.getPlayerScore());
        p.updateScore(12);
        assertEquals(18, p.getPlayerScore());
        p.updateScore(38);
        assertEquals(56, p.getPlayerScore());
    }
    @After
    public void cleanUp() {
        p = null;
    }
}
