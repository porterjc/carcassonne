package ObjectTests;

import Main.GlobalVariables;
import Objects.AbstractTile;
import Objects.Meeple;
import Objects.PlayableTile;
import Objects.Player;
import javafx.util.Pair;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by johnsoaa on 3/29/2015.
 */
public class MeepleTest extends TestCase {
    private Player p;
    private Meeple m;
    /*
    * For placing a Objects.Meeple
     */
    private Stack<PlayableTile> tiles;
    PlayableTile tile, lastPlaced;

    @Before
    public void setUp() {
        p = new Player(GlobalVariables.PlayerColor.BLUE);
        m = new Meeple(p, GlobalVariables.PlayerColor.BLUE);
        tiles = new Stack<PlayableTile>();
        addTiles();
    }

    //copied from UIComponentsTests.TileAddTest
    public void addTiles() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        tiles.add(new PlayableTile(feature));

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature1 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        tiles.add(new PlayableTile(feature1));

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature2 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile p = new PlayableTile(feature2);
        tiles.add(p);
        //Tiles with blockades
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature3 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature3.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature3.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        p =new PlayableTile(feature3);

        tiles.add(new PlayableTile(feature3));

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature4 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature4.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature4.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature4.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature4.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        p = new PlayableTile(feature4);
        tiles.add(new PlayableTile(feature4));
    }
    @Test
    public void testSetUp() {
        assertEquals(GlobalVariables.PlayerColor.BLUE, m.getColor());
        assertEquals(p, m.getPlayer());

    }

    @Test
    public void testRemove() {
        PlayableTile pt = new PlayableTile();
        m.place(pt, GlobalVariables.Feature.GRASS, GlobalVariables.Location.CENTER);
        assertEquals(GlobalVariables.Feature.GRASS, m.getFeature());
        assertEquals(pt, m.getTile());
        m.remove();
        assertEquals(null, m.getTile());
    }
   /* @Test
    public void testPlaceMeebleOnNull(){
        TestCase.assertEquals(0, p.lastUsedMeeple);
        this.p.placeMeeple(tiles.pop());
        TestCase.assertEquals(1, p.lastUsedMeeple);
        this.p.placeMeeple(tiles.pop());
        TestCase.assertEquals(2, p.lastUsedMeeple);
        this.p.placeMeeple(tiles.pop());
        TestCase.assertEquals(3, p.lastUsedMeeple);
        this.p.placeMeeple(tiles.pop());
        TestCase.assertEquals(4, p.lastUsedMeeple);
        this.p.placeMeeple(null);
        TestCase.assertEquals(5, p.lastUsedMeeple);
        this.p.placeMeeple(null);
        TestCase.assertEquals(6, p.lastUsedMeeple);
        TestCase.assertEquals(false, this.p.placeMeeple(null));
        TestCase.assertEquals(6, p.lastUsedMeeple);
    }*/
    @After
    public void cleanUp() {
        p = null;
        m = null;

    }

    @Test
    public void testPlaceMeeple() {
        PlayableTile t = this.tiles.pop();
        Meeple m = new Meeple(p);
        m.place(t, GlobalVariables.Feature.GRASS, null);
        Pair<AbstractTile, GlobalVariables.Feature> place;
        Meeple m2 = p.getPlacedMeeples().listIterator().next();
        place = m2.getPlacedOn();
        TestCase.assertEquals(t, place.getKey());
    }

    @Test
    public void testPlaceMeeble() {
        PlayableTile pt = new PlayableTile();
        m.place(pt, GlobalVariables.Feature.GRASS, GlobalVariables.Location.CENTER);
        assertEquals(GlobalVariables.Feature.GRASS, m.getFeature());
        assertEquals(pt, m.getTile());
//        assertEquals(p.getMeeples().get(0),m.getTile().getMeeple());
    }
}