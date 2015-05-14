package ObjectTests;

import Main.GlobalVariables;
import Objects.AbstractTile;
import Objects.Meeple;
import Objects.OpenTile;
import Objects.PlayableTile;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by johnsoaa on 5/14/2015.
 */
public class PlayableTileCityTest {


    @Test
    public void testScoreSmallCity() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        tile1.setBottom(tile2);
        tile2.setTop(tile1);

        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Set<GlobalVariables.Direction> directions = new HashSet<GlobalVariables.Direction>();
        directions.add(GlobalVariables.Direction.SOUTH);
        assertEquals((Integer) 4, tile1.startScoreCity(alreadyVisited, new HashSet<Meeple>(), directions, true).getValue());
    }

    @Test
    public void testScoreSmallishCity() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.CITY);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        tile1.setBottom(tile2);
        tile2.setTop(tile1);
        tile2.setRight(tile3);
        tile3.setLeft(tile2);

        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Set<GlobalVariables.Direction> directions = new HashSet<GlobalVariables.Direction>();
        directions.add(GlobalVariables.Direction.WEST);
        assertEquals((Integer) 6, tile3.startScoreCity(alreadyVisited, new HashSet<Meeple>(), directions, true).getValue());
    }

    @Test
    public void testScoreLargerCity() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.CITY);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
        PlayableTile tile4 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile5 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile6 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        tile1.setBottom(tile2);
        tile2.setTop(tile1);
        tile2.setRight(tile5);
        tile5.setLeft(tile2);
        tile5.setRight(tile3);
        tile3.setLeft(tile5);
        tile2.setLeft(tile4);
        tile4.setRight(tile2);
        tile2.setBottom(tile6);
        tile6.setTop(tile2);

        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Set<GlobalVariables.Direction> directions = new HashSet<GlobalVariables.Direction>();
        directions.add(GlobalVariables.Direction.SOUTH);
        assertEquals((Integer) 12, tile1.startScoreCity(alreadyVisited, new HashSet<Meeple>(), directions, true).getValue());
    }

    @Test
    public void testScoreLarger2City() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.CITY);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile4 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile5 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile6 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        tile1.setTop(tile2);
        tile2.setBottom(tile1);
        tile1.setLeft(tile5);
        tile5.setRight(tile1);
        tile5.setLeft(tile6);
        tile6.setRight(tile5);
        tile5.setTop(tile3);
        tile3.setBottom(tile5);
        tile2.setLeft(tile3);
        tile3.setRight(tile2);
        tile3.setLeft(tile4);
        tile4.setRight(tile3);
        tile4.setBottom(tile6);
        tile6.setTop(tile4);


        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Set<GlobalVariables.Direction> directions = new HashSet<GlobalVariables.Direction>();
        directions.add(GlobalVariables.Direction.NORTH);
        assertEquals((Integer) 12, tile1.startScoreCity(alreadyVisited, new HashSet<Meeple>(), directions, true).getValue());
    }

    @Test
    public void testDoesNotScoreNeighborCity() {
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        tile1.setBottom(tile2);
        tile2.setTop(tile1);
        tile2.setBottom(tile3);
        tile3.setTop(tile2);

        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Set<GlobalVariables.Direction> directions = new HashSet<GlobalVariables.Direction>();
        directions.add(GlobalVariables.Direction.SOUTH);
        assertEquals((Integer) 4, tile1.startScoreCity(alreadyVisited, new HashSet<Meeple>(), directions, true).getValue());
    }

    @Test
    public void testScoreUnCompletedCityForCompletion() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.CITY);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        tile1.setBottom(tile2);
        tile2.setTop(tile1);

        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Set<GlobalVariables.Direction> directions = new HashSet<GlobalVariables.Direction>();
        directions.add(GlobalVariables.Direction.SOUTH);
        assertEquals(1, (int) tile1.startScoreCity(alreadyVisited, new HashSet<Meeple>(), directions, true).getValue());
    }

    @Test
    public void testScoreIncompleteLargeCity() {
        Set<GlobalVariables.Internal> internals = new HashSet<GlobalVariables.Internal>();
        internals.add(GlobalVariables.Internal.CITY);
        HashMap<GlobalVariables.Direction, GlobalVariables.Feature> feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

        PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile4 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
        PlayableTile tile5 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        feature = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
        feature.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
        feature.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
        feature.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
        PlayableTile tile6 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), feature, internals);

        tile1.setTop(tile2);
        tile2.setBottom(tile1);
        tile1.setLeft(tile5);
        tile5.setRight(tile1);
        tile5.setLeft(tile6);
        tile6.setRight(tile5);
        tile5.setTop(tile3);
        tile3.setBottom(tile5);
        tile2.setLeft(tile3);
        tile3.setRight(tile2);
        tile3.setLeft(tile4);
        tile4.setRight(tile3);
        tile4.setBottom(tile6);
        tile6.setTop(tile4);

        Set<AbstractTile> alreadyVisited = new HashSet<AbstractTile>();
        Set<GlobalVariables.Direction> directions = new HashSet<GlobalVariables.Direction>();
        directions.add(GlobalVariables.Direction.NORTH);
        assertEquals(1, (int) tile1.startScoreCity(alreadyVisited, new HashSet<Meeple>(), directions, true).getValue());
    }


}
