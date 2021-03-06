package UIComponentsTests;

import Main.GlobalVariables;
import Objects.PlayableTile;
import Objects.Player;
import Objects.OpenTile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This is a graphical test class intended to test the placement of meeple placement buttons
 *
 * Created by robinsat on 5/6/2015.
 */
public class PlaceMeepleButtonTest extends JFrame {

    /** The player to use for these tests */
    private Player player;
    /** The tiles to use for these tests */
    private java.util.List<PlayableTile> tiles;

    /**
     * Main.Main method creates a frame and runs the tests
     */
    public static void main(String[] args) {

        PlaceMeepleButtonTest testFrame = new PlaceMeepleButtonTest();
        testFrame.setLayout(null);
        testFrame.setSize(900, 900);
        testFrame.setVisible(true);
        testFrame.runTests();
    }

    /**
     * Runs the test methods to add tiles
     */
    public void runTests() {
        player = new Player(GlobalVariables.PlayerColor.YELLOW);
        initializeTiles();
        testAddMeepleButtons();

        revalidate();
        repaint();
    }

    /**
     * Initializes the tiles needed for these tests
     */
    public void initializeTiles() {
        tiles = new ArrayList<PlayableTile>();
        try {
            BufferedImage image0 = ImageIO.read(new File("images/41.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features0 = new HashMap<>();
            features0.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features0.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features0.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features0.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.MONASTERY);

           // PlayableTile tile0 = new PlayableTile(new OpenTile(), image0, features0, internals);
            PlayableTile tile0 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), image0, features0, internals);
            tile0.moveTile(50, 50);
            tiles.add(tile0);

            BufferedImage image1 = ImageIO.read(new File("images/04.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features1 = new HashMap<>();
            features1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            PlayableTile tile1 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), image1, features1, new HashSet<GlobalVariables.Internal>());
            tile1.moveTile(250, 50);
            tiles.add(tile1);

            BufferedImage image2 = ImageIO.read(new File("images/12.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features2 = new HashMap<>();
            features2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            PlayableTile tile2 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), image2, features2, new HashSet<GlobalVariables.Internal>());
            tile2.moveTile(450, 50);
            tiles.add(tile2);

            BufferedImage image3 = ImageIO.read(new File("images/26.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features3 = new HashMap<>();
            features3.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features3.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features3.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features3.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
            Set<GlobalVariables.Internal> internals3 = new HashSet<>();
            internals3.add(GlobalVariables.Internal.ROADSTOP);
            internals3.add(GlobalVariables.Internal.NSBISECTOR);

            PlayableTile tile3 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), image3, features3, internals3);
            tile3.moveTile(650, 50);
            tiles.add(tile3);

            BufferedImage image4 = ImageIO.read(new File("images/42.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features4 = new HashMap<>();
            features4.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features4.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features4.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features4.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
            Set<GlobalVariables.Internal> internals4 = new HashSet<>();
            internals4.add(GlobalVariables.Internal.GARDEN);

            PlayableTile tile4 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), image4, features4, internals4);
            tile4.moveTile(50, 250);
            tile4.rotateTile();
            tile4.rotateTile();
            tile4.rotateTile();
            tiles.add(tile4);

            BufferedImage image5 = ImageIO.read(new File("images/14.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features5 = new HashMap<>();
            features5.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features5.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features5.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features5.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
            Set<GlobalVariables.Internal> internals5 = new HashSet<>();
            internals5.add(GlobalVariables.Internal.GARDEN);
            internals5.add(GlobalVariables.Internal.CITY);

            PlayableTile tile5 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), image5, features5, internals5);
            tile5.moveTile(250, 250);
            tile5.rotateTile();
            tile5.rotateTile();
            tile5.rotateTile();
            tiles.add(tile5);

            BufferedImage image6 = ImageIO.read(new File("images/58.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features6 = new HashMap<>();
            features6.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features6.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features6.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features6.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
            Set<GlobalVariables.Internal> internals6 = new HashSet<>();
            internals6.add(GlobalVariables.Internal.GARDEN);

            PlayableTile tile6 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), image6, features6, internals6);
            tile6.moveTile(450, 250);
            tile6.rotateTile();
            tile6.rotateTile();
            tile6.rotateTile();
            tiles.add(tile6);

            BufferedImage image7 = ImageIO.read(new File("images/70.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features7 = new HashMap<>();
            features7.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features7.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features7.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features7.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
            Set<GlobalVariables.Internal> internals7 = new HashSet<>();
            internals7.add(GlobalVariables.Internal.GARDEN);

            PlayableTile tile7 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), image7, features7, internals7);
            tile7.moveTile(650, 250);
            tile7.rotateTile();
            tile7.rotateTile();
            tile7.rotateTile();
            tiles.add(tile7);

            BufferedImage image8 = ImageIO.read(new File("images/r1.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features8 = new HashMap<>();
            features8.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.RIVER);
            features8.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.RIVER);
            features8.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features8.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);
            Set<GlobalVariables.Internal> internals8 = new HashSet<>();
            internals8.add(GlobalVariables.Internal.GARDEN);

            PlayableTile tile8 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), image8, features8, internals8);
            tile8.moveTile(50, 450);
            tile8.rotateTile();
            tile8.rotateTile();
            tile8.rotateTile();
            tiles.add(tile8);

            BufferedImage image9 = ImageIO.read(new File("images/45.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features9 = new HashMap<>();
            features9.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features9.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features9.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features9.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);
            Set<GlobalVariables.Internal> internals9 = new HashSet<>();
            internals9.add(GlobalVariables.Internal.GARDEN);
            internals9.add(GlobalVariables.Internal.CITY);

            PlayableTile tile9 = new PlayableTile(new OpenTile(), new OpenTile(), new OpenTile(), new OpenTile(), image9, features9, internals9);
            tile9.moveTile(250, 450);
            tile9.rotateTile();
            tile9.rotateTile();
            tile9.rotateTile();
            tiles.add(tile9);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testAddMeepleButtons() {
        for(PlayableTile tile : tiles) {
            this.add(tile);
            tile.addMeepleButtons(player, true);
        }

    }
}
