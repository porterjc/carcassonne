package ObjectTests;

import Main.GlobalVariables;
import Objects.AbstractTile;
import Objects.Meeple;
import Objects.PlayableTile;
import Objects.Player;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public  void testsNoRoadsToScore(){
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        tileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        tileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        tileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile tile = new PlayableTile(tileFeatures,intA);
        Set<AbstractTile> alreadyvisited = new HashSet<AbstractTile>();
        Set<Meeple> meeples= new HashSet<Meeple>();
        Pair<HashSet<Meeple>, Integer> score = tile.scoreRoad(alreadyvisited, meeples,false);
        assertEquals(0, score.getKey().size());
        assertEquals( -1, (int) score.getValue());
    }
    @Test
    public  void testsRoadToScoreNotCompleteNotEndOfGame(){
        Meeple m = new Meeple(currentUser, currentUser.getColor());
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        tileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        tileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        tileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile top = new PlayableTile(tileFeatures,intA);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> bottomFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        bottomFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        bottomFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> intB = new HashSet<GlobalVariables.Internal>();
        intB.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile bottom = new PlayableTile(bottomFeatures,intB);

        m.place(bottom, GlobalVariables.Feature.ROAD, GlobalVariables.Location.TOP);
        bottom.setMeeple(m);


        top.setBottom(bottom);
        bottom.setTop(top);
        Set<AbstractTile> alreadyvisited = new HashSet<AbstractTile>();
        Set<Meeple> meeples= new HashSet<Meeple>();
        Pair<HashSet<Meeple>, Integer> score = top.scoreRoad(alreadyvisited, meeples, false);
        assertEquals(1, score.getKey().size());
        assertEquals( 2, (int) score.getValue());
    }

}
