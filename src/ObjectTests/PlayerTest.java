package ObjectTests;

import Main.*;
import Objects.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.annotation.XmlElementDecl;
import java.awt.*;

import static org.junit.Assert.*;

/**
 * Test methods for the player class
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
        assertTrue(p.getPlacedMeeples().isEmpty());
        assertTrue(p.hasMeeplesLeft());
        assertNotNull(p.getAbbot());
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

    @Test
    public void testRemoveMeeple(){
        Meeple m = p.removeMeeple();
        assertEquals(p, m.getPlayer());

        assertNotNull(p.removeMeeple());
        assertNotNull(p.removeMeeple());
        assertNotNull(p.removeMeeple());
        assertNotNull(p.removeMeeple());
        assertNotNull(p.removeMeeple());
        assertNotNull(p.removeMeeple());
        assertNull(p.removeMeeple());
    }

    @Test
    public void testPlaceMeeple(){
        Meeple m = p.removeMeeple();
        p.addPlacedMeeple(m);
        assertFalse(p.getPlacedMeeples().isEmpty());
    }

    @Test
    public void testAddMeeple(){
        int original = p.getNumMeeples();
        p.addMeeple();
        assertEquals(original + 1, p.getNumMeeples());
    }

    @After
    public void cleanUp() {
        p = null;
    }
}
