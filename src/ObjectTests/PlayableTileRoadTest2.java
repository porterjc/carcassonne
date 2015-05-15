package ObjectTests;

import Main.*;
import Objects.*;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by johnsoaa on 5/14/2015.
 */
public class PlayableTileRoadTest2 {

    Player currentUser;

    @Before
    public void setUp() {
        currentUser = new Player(GlobalVariables.PlayerColor.BLACK);
    }

    @After
    public void cleanUp() {
        currentUser = null;
    }

    @Test
    public void testsNoRoadsToScore() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        tileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        tileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        tileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile tile = new PlayableTile(tileFeatures, intA);
        Set<AbstractTile> alreadyvisited = new HashSet<AbstractTile>();
        Set<Meeple> meeples = new HashSet<Meeple>();
        Pair<Set<Meeple>, Integer> score = tile.scoreRoad(alreadyvisited, meeples, false);
        assertEquals(0, score.getKey().size());
        assertEquals(-1, (int) score.getValue());
    }
    //TODO need test incomplete road not end of game
    @Test
    public void testIncompleteRoadNotEndOfGame(){


    }
    @Test
    public void testIncompleteRoadEndOfGame(){
        Meeple m = new Meeple(currentUser, currentUser.getColor());
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        tileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        tileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        tileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile top = new PlayableTile(tileFeatures, intA);
        //Make bottom Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> bottomFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        bottomFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        bottomFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> intB = new HashSet<GlobalVariables.Internal>();
        PlayableTile bottom = new PlayableTile(bottomFeatures, intB);
        //place the meeple
        m.place(bottom, GlobalVariables.Feature.ROAD, GlobalVariables.Location.TOP);
        bottom.setMeeple(m);

        top.setBottom(bottom);
        bottom.setTop(top);
        Set<AbstractTile> alreadyvisited = new HashSet<AbstractTile>();
        Set<Meeple> meeples = new HashSet<Meeple>();
        Pair<Set<Meeple>, Integer> score = top.scoreRoad(alreadyvisited, meeples, true);
        assertEquals(1, score.getKey().size());
        assertEquals(2, (int) score.getValue());

    }
    //TODO add test incomplete road end of game

    @Test
    public void testsRoadToScoreCompleteNotEndOfGame() {
        Meeple m = new Meeple(currentUser, currentUser.getColor());
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        tileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        tileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        tileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile top = new PlayableTile(tileFeatures, intA);
        //Make bottom Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> bottomFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        bottomFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        bottomFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> intB = new HashSet<GlobalVariables.Internal>();
        intB.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile bottom = new PlayableTile(bottomFeatures, intB);
        //Add the meeple
        m.place(bottom, GlobalVariables.Feature.ROAD, GlobalVariables.Location.TOP);
        bottom.setMeeple(m);


        top.setBottom(bottom);
        bottom.setTop(top);
        Set<AbstractTile> alreadyvisited = new HashSet<AbstractTile>();
        Set<Meeple> meeples = new HashSet<Meeple>();
        Pair<Set<Meeple>, Integer> score = top.scoreRoad(alreadyvisited, meeples, false);
        assertEquals(1, score.getKey().size());
        assertEquals(2, (int) score.getValue());
    }

    @Test
    public void testsRoadToScoreCompleteNotEndOfGameMultipleMeeples() {

        Meeple m = new Meeple(currentUser, currentUser.getColor());
        Meeple m2 = new Meeple(new Player(GlobalVariables.PlayerColor.GREEN), GlobalVariables.PlayerColor.GREEN);
        //Make Top Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        tileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD); // |   |
        tileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);// | B_|
        tileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);// | | |
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile top = new PlayableTile(tileFeatures, intA);
        //Make Middle Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> middleFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        middleFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        middleFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS); // | | |
        middleFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS); // | | |
        middleFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD); // | | |
        Set<GlobalVariables.Internal> intC = new HashSet<GlobalVariables.Internal>();
        PlayableTile middle = new PlayableTile(middleFeatures, intC);

        //Make bottom Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> bottomFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        bottomFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        bottomFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);//  | | |
        bottomFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);//  | B |
        bottomFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);//  |   |
        Set<GlobalVariables.Internal> intB = new HashSet<GlobalVariables.Internal>();
        intB.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile bottom = new PlayableTile(bottomFeatures, intB);
        //Add the meeples
        m.place(bottom, GlobalVariables.Feature.ROAD, GlobalVariables.Location.TOP);
        bottom.setMeeple(m);
        m2.place(top, GlobalVariables.Feature.ROAD, GlobalVariables.Location.BOTTOM);
        top.setMeeple(m2);

        //set the tiles
        middle.setTop(top);
        middle.setBottom(bottom);
        top.setBottom(middle);
        bottom.setTop(middle);
        Set<AbstractTile> alreadyvisited = new HashSet<AbstractTile>();
        Set<Meeple> meeples = new HashSet<Meeple>();
        Pair<Set<Meeple>, Integer> score = top.scoreRoad(alreadyvisited, meeples, false);
        assertEquals(2, score.getKey().size());
        assertEquals(3, (int) score.getValue());
    }

}
