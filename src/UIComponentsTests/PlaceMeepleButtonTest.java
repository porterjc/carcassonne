package UIComponentsTests;

import Main.GlobalVariables;
import Objects.PlayableTile;
import Objects.Player;

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

            PlayableTile tile0 = new PlayableTile(image0, features0, internals);
            tile0.moveTile(50, 50);
            tiles.add(tile0);

            BufferedImage image1 = ImageIO.read(new File("images/04.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features1 = new HashMap<>();
            features1.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features1.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features1.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features1.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            PlayableTile tile1 = new PlayableTile(image1, features1);
            tile1.moveTile(250, 50);
            tiles.add(tile1);

            BufferedImage image2 = ImageIO.read(new File("images/12.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features2 = new HashMap<>();
            features2.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features2.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features2.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features2.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            PlayableTile tile2 = new PlayableTile(image2, features2);
            tile2.moveTile(450, 50);
            tiles.add(tile2);

            BufferedImage image3 = ImageIO.read(new File("images/26.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features3 = new HashMap<>();
            features3.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features3.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features3.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features3.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);
            Set<GlobalVariables.Internal> internals1 = new HashSet<>();
            internals.add(GlobalVariables.Internal.ROADSTOP);
            internals.add(GlobalVariables.Internal.NSBISECTOR);

            PlayableTile tile3 = new PlayableTile(image3, features3);
            tile3.moveTile(650, 50);
            tiles.add(tile3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testAddMeepleButtons() {
        for(PlayableTile tile : tiles) {
            this.add(tile);
            tile.addMeepleButtons(player);
        }

    }
}