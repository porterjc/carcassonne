package ObjectTests;

import Main.GlobalVariables;
import Objects.*;
import UIComponents.BottomDisplay;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by johnsoaa on 5/17/2015.
 */
public class PlayableTileScoreRoadTest {
    Game game;
    Player currentUser;

    @Before
    public void setUp() {

        currentUser = new Player(GlobalVariables.PlayerColor.BLACK);
    }

    @After
    public void cleanUp() {
        currentUser = null;
        game =null;
    }

    @Test
    public void testStartScoreRoad() {
        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        PlayableTile left = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), features, internals);

        HashSet<Meeple> meeples = new HashSet<>();
        Pair<Set<Meeple>, Integer> score = left.scoreRoad(alreadyVisited, meeples, false, GlobalVariables.Direction.EAST);
        assertEquals(0, score.getKey().size());
        assertEquals(1, (int) score.getValue());
    }

    @Test
    public void testStartScoreRoadNorth() {
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> topFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        topFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile top = new PlayableTile(topFeatures, intA);
        //Make bottom Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> bottomFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        bottomFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        bottomFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> intB = new HashSet<GlobalVariables.Internal>();
        intB.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile bottom = new PlayableTile(bottomFeatures, intB);
        //Add the meeple
        m.place(bottom, GlobalVariables.Feature.ROAD, GlobalVariables.Location.TOP);
        bottom.setMeeple(m);
        //set the tiles
        top.setBottom(bottom);
        bottom.setTop(top);

        Stack<PlayableTile> stacks = new Stack();
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentUser);
        game = new Game(new BottomDisplay(100),stacks, players );
        game.setCurrentTile(bottom);

        assertEquals(0, this.currentUser.getPlayerScore());
        Pair<Set<Meeple>, Integer> score = game.startScoreRoad(bottom, false);
        assertEquals(1, score.getKey().size());
        assertEquals(2, this.currentUser.getPlayerScore());
    }

    @Test
    public void testScoreRoad() { //this covers complete not end of game
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> topFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        topFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile top = new PlayableTile(topFeatures, intA);
        //Make bottom Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> bottomFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        bottomFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        bottomFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> intB = new HashSet<GlobalVariables.Internal>();
        intB.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile bottom = new PlayableTile(bottomFeatures, intB);
        //Add the meeple
        m.place(bottom, GlobalVariables.Feature.ROAD, GlobalVariables.Location.TOP);
        bottom.setMeeple(m);
        //set the tiles
        top.setBottom(bottom);
        bottom.setTop(top);


        Stack<PlayableTile> stacks = new Stack();
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentUser);
        game = new Game(new BottomDisplay(100),stacks, players );
        game.setCurrentTile(bottom);

        Pair<Set<Meeple>, Integer> score = game.startScoreRoad(bottom, false);
        assertEquals(1, score.getKey().size());
        assertEquals(2, (int) score.getValue());
    }

    @Test
    public void testScoreSouth() {
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> topFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        topFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        topFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile top = new PlayableTile(topFeatures, intA);
        //Make bottom Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> bottomFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        bottomFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        bottomFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        Set<GlobalVariables.Internal> intB = new HashSet<GlobalVariables.Internal>();
        intB.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile bottom = new PlayableTile(bottomFeatures, intB);
        //Add the meeple
        m.place(bottom, GlobalVariables.Feature.ROAD, GlobalVariables.Location.TOP);
        bottom.setMeeple(m);
        //set the tiles
        top.setBottom(bottom);
        bottom.setTop(top);

        Stack<PlayableTile> stacks = new Stack();
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentUser);
        game = new Game(new BottomDisplay(100),stacks, players );
        game.setCurrentTile(top);

        Pair<Set<Meeple>, Integer> score = game.startScoreRoad(top, false);
        assertEquals(1, score.getKey().size());
        assertEquals(2, (int) score.getValue());
    }


    @Test
    public void testIncompleteRoadNotEndOfGame() {

        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
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

        Stack<PlayableTile> stacks = new Stack();
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentUser);
        game = new Game(new BottomDisplay(100),stacks, players );
        game.setCurrentTile(top);

        assertEquals(0, this.currentUser.getPlayerScore());
        Pair<Set<Meeple>, Integer> score = game.startScoreRoad(top, false);
        assertEquals(0, score.getKey().size()); //This should be 1 qhen end of game is on
        assertEquals(0, this.currentUser.getPlayerScore());
    }

    @Test
    public void testIncompleteRoadEndOfGame() {
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
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
        assertEquals(m, bottom.getMeeple());
        top.setBottom(bottom);
        bottom.setTop(top);


        Stack<PlayableTile> stacks = new Stack();
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentUser);
        game = new Game(new BottomDisplay(100),stacks, players );
        game.setCurrentTile(top);

        assertEquals(0, this.currentUser.getPlayerScore());
        Pair<Set<Meeple>, Integer> score = game.startScoreRoad(top, true);
        assertEquals(1, score.getKey().size());
        assertEquals(2, this.currentUser.getPlayerScore());
    }

    @Test
    public void testsRoadToScoreCompleteNotEndOfGameMultipleMeeples() {

        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
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

        Stack<PlayableTile> stacks = new Stack();
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentUser);
        game = new Game(new BottomDisplay(100),stacks, players );
        game.setCurrentTile(top);

        assertEquals(0, this.currentUser.getPlayerScore());
        Pair<Set<Meeple>, Integer> score = game.startScoreRoad(top, false);
        assertEquals(2, score.getKey().size());
        assertEquals(3, this.currentUser.getPlayerScore());
    }

    @Test
    public void testsRoadToScoreComepletedAndNotCompleteEndOfGame() {
        Player p1 =new Player(GlobalVariables.PlayerColor.GREEN);
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        Meeple m2 = new Meeple(p1, GlobalVariables.PlayerColor.GREEN);

        //Make Top Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        tileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD); // |   |
        tileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);// | B_|
        tileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);// | | |
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile top = new PlayableTile(tileFeatures, intA);
        //Make Top Right Tile -- for the incomplete
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> rightTopFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        rightTopFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        rightTopFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);  // |   |
        rightTopFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);  // |---|
        rightTopFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);// |   |
        Set<GlobalVariables.Internal> intD = new HashSet<GlobalVariables.Internal>();
        PlayableTile topRight = new PlayableTile(rightTopFeatures, intD);
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
        m2.place(top, GlobalVariables.Feature.ROAD, GlobalVariables.Location.RIGHT);
        top.setMeeple(m2);

        //set the tiles
        top.setRight(topRight);
        topRight.setLeft(top);
        middle.setTop(top);
        middle.setBottom(bottom);
        top.setBottom(middle);
        bottom.setTop(middle);
        Set<AbstractTile> alreadyvisited = new HashSet<AbstractTile>();
        Set<Meeple> meeples = new HashSet<Meeple>();

        Stack<PlayableTile> stacks = new Stack();
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentUser);
        players.add(p1);
        game = new Game(new BottomDisplay(100),stacks, players );
        game.setCurrentTile(top);




        Pair<Set<Meeple>, Integer> scores = game.startScoreRoad(top, true);//top.scoreRoad(alreadyvisited, meeples, true);
        assertEquals(2, scores.getKey().size()); //TODO FINISH THIS ONE
        assertEquals(3, (int) scores.getValue());

    }

    @Test
    public void testsRoadToScoreCompleteNotEndOfGameFourTiles() {
       Player p1= new Player(GlobalVariables.PlayerColor.GREEN);
        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        Meeple m2 = new Meeple(p1, GlobalVariables.PlayerColor.GREEN);

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures1 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        tileFeatures1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);// |   |
        tileFeatures1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);//  |_B |
        tileFeatures1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);// | | |
        Set<GlobalVariables.Internal> intAa = new HashSet<GlobalVariables.Internal>();
        intAa.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile topright = new PlayableTile(tileFeatures1, intAa);
        //Make Top Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        tileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD); // |   |
        tileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);// | B_|
        tileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);// | | |
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
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
        top.setRight(topright);
        topright.setLeft(top);
        middle.setTop(top);
        middle.setBottom(bottom);
        top.setBottom(middle);
        bottom.setTop(middle);

        Stack<PlayableTile> stacks = new Stack();
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentUser);
        players.add(p1);
        game = new Game(new BottomDisplay(100),stacks, players );
        game.setCurrentTile(topright);

        assertEquals(0, this.currentUser.getPlayerScore());
        Pair<Set<Meeple>, Integer> score = game.startScoreRoad(topright, false);
        assertEquals(2, score.getKey().size());
        assertEquals(4, this.currentUser.getPlayerScore());
    }

    @Test
    public void testsRoadToScoreUpdateTwoMeeplesSamePlayer() {

        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        Meeple m2 = new Meeple(currentUser, currentUser.getPlayerColor());

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures1 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        tileFeatures1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);// |   |
        tileFeatures1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);//  |_B |
        tileFeatures1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);// | | |
        Set<GlobalVariables.Internal> intAa = new HashSet<GlobalVariables.Internal>();
        intAa.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile topright = new PlayableTile(tileFeatures1, intAa);
        //Make Top Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        tileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD); // |   |
        tileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);// | B_|
        tileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);// | | |
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
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
        top.setRight(topright);
        topright.setLeft(top);
        middle.setTop(top);
        middle.setBottom(bottom);
        top.setBottom(middle);
        bottom.setTop(middle);

        Stack<PlayableTile> stacks = new Stack();
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentUser);
        game = new Game(new BottomDisplay(100),stacks, players );
        game.setCurrentTile(topright);

        assertEquals(0, this.currentUser.getPlayerScore());
        Pair<Set<Meeple>, Integer> score = game.startScoreRoad(topright, false);
        assertEquals(2, score.getKey().size());
        assertEquals(4, this.currentUser.getPlayerScore());
    }
    // TODO add test for multiple roads being scored after one placement

    //TODO add better meeple tests ie.- has to be at the right location

    //TODO test end of game Completed road


    @Test
    public void testsRoadToScoreUpdateFiveSideWays() {

        Meeple m = new Meeple(currentUser, currentUser.getPlayerColor());
        Meeple m2 = new Meeple(currentUser, currentUser.getPlayerColor());

        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures1 = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        tileFeatures1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);//  |   |
        tileFeatures1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);//   |_B |
        tileFeatures1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);// |   |
        Set<GlobalVariables.Internal> intAa = new HashSet<GlobalVariables.Internal>();
        intAa.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile rightblock = new PlayableTile(tileFeatures1, intAa);
        //Make Top Tile
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> tileFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        tileFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        tileFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD); // | | |
        tileFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);// | B_|
        tileFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);// | | |
        Set<GlobalVariables.Internal> intA = new HashSet<GlobalVariables.Internal>();
        intA.add(GlobalVariables.Internal.ROADSTOP);
        PlayableTile leftblock = new PlayableTile(tileFeatures, intA);
        //Make Middle right
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> middleFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        middleFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        middleFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD); //   |   |
        middleFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD); //   |___|
        middleFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS); // |   |
        Set<GlobalVariables.Internal> intC = new HashSet<GlobalVariables.Internal>();
        PlayableTile middleright = new PlayableTile(middleFeatures, intC);
        //middletiles
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> middlefs = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        middlefs.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        middlefs.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD); //   |   |
        middlefs.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD); //   |___|
        middlefs.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS); // |   |
        Set<GlobalVariables.Internal> intmid = new HashSet<GlobalVariables.Internal>();
        PlayableTile middle = new PlayableTile(middlefs, intmid);
        //Make middle left
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> bottomFeatures = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        bottomFeatures.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        bottomFeatures.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);//    |   |
        bottomFeatures.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);//    |___|
        bottomFeatures.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);//  |   |
        Set<GlobalVariables.Internal> intB = new HashSet<GlobalVariables.Internal>();
        PlayableTile middleleft = new PlayableTile(bottomFeatures, intB);
        //Add the meeples
        m.place(leftblock, GlobalVariables.Feature.ROAD, GlobalVariables.Location.RIGHT);
        leftblock.setMeeple(m);
        m2.place(middleright, GlobalVariables.Feature.ROAD, GlobalVariables.Location.LEFT);
        middleright.setMeeple(m2);

        //set the tiles
        leftblock.setRight(middleleft);
        middleleft.setLeft(leftblock);
        middleleft.setRight(middle);
        middle.setLeft(middleleft);
        middle.setRight(middleright);
        middleright.setLeft(middle);
        middleright.setRight(rightblock);
        rightblock.setLeft(middleright);

        Stack<PlayableTile> stacks = new Stack();
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentUser);
        game = new Game(new BottomDisplay(100),stacks, players);
        game.setCurrentTile(leftblock);

        assertEquals(0, this.currentUser.getPlayerScore());
        Pair<Set<Meeple>, Integer> score = game.startScoreRoad(leftblock, false);
        assertEquals(2, score.getKey().size());
        assertEquals(5, this.currentUser.getPlayerScore());
    }
}
