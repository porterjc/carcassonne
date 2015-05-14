package UIComponentsTests;

import Main.GlobalVariables;
import Objects.PlayableTile;
import Objects.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This is a graphical test class intended to test the placement of meeple placement buttons
 *
 * Created by robinsat on 5/6/2015.
 */
public class PlaceMeepleButtonTest extends JFrame {

    /** The player to use for these tests */
    private Player player;

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

    public void runTests() {
        player = new Player(GlobalVariables.PlayerColor.RED);
        testAddGrassButtons();
    }

    public void testAddGrassButtons() {

        try {
            BufferedImage image = ImageIO.read(new File("images/41.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.MONASTERY);

            PlayableTile tile = new PlayableTile(image, features, internals);
            tile.moveTile(200, 200);
            this.add(tile);
            tile.addMeepleButtons(player);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
