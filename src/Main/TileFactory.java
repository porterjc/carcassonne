package Main;

import Main.GlobalVariables;
import Objects.PlayableTile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * This class is a utility to read tile images from a file and create playable tiles accordingly.
 * Created by robinsat on 4/9/2015.
 */
public class TileFactory {

    /**
     * Creates a collection of tiles and returns them in a randomly ordered stack
     * @param includeStart whether or not to include the starting tile.
     * @return a shuffled stack of tiles
     */
    public static Stack<PlayableTile> loadDeck(boolean includeStart) {
        Stack<PlayableTile> deck = new Stack<PlayableTile>();
        if(includeStart) {
            deck.add(getStartTile());
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/01.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/02.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.EWBISECTOR);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/03.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.CITY);
            internals.add(GlobalVariables.Internal.COATOFARMS);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/04.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/05.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.ROADSTOP);
            internals.add(GlobalVariables.Internal.NSBISECTOR);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/06.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/07.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/08.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/09.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.EWBISECTOR);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/10.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/11.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.CITY);
            internals.add(GlobalVariables.Internal.COATOFARMS);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/12.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.GRASS);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/13.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.CITY);
            internals.add(GlobalVariables.Internal.COATOFARMS);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/14.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.CITY);
            internals.add(GlobalVariables.Internal.GARDEN);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/15.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.CITY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/16.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.CITY);
            internals.add(GlobalVariables.Internal.COATOFARMS);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/17.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.GRASS);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/18.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.CITY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/19.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.CITY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/20.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/21.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.MONASTERY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/22.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/23.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/24.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/25.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.CITY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/26.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/27.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.CITY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/28.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.GRASS);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/29.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/30.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/31.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/32.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/33.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/34.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.MONASTERY);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/35.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/36.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.ROADSTOP);
            internals.add(GlobalVariables.Internal.CITY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/37.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/38.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.CITY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/39.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/40.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.CITY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/41.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.MONASTERY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/42.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.GARDEN);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/43.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/44.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.EWBISECTOR);
            internals.add(GlobalVariables.Internal.COATOFARMS);
            internals.add(GlobalVariables.Internal.CITY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/45.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.GARDEN);
            internals.add(GlobalVariables.Internal.CITY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/46.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.COATOFARMS);
            internals.add(GlobalVariables.Internal.CITY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/47.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/48.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.ROADSTOP);
            internals.add(GlobalVariables.Internal.COATOFARMS);
            internals.add(GlobalVariables.Internal.CITY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/49.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/50.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/51.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.MONASTERY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/52.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.MONASTERY);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/53.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/54.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.EWBISECTOR);
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/55.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/56.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/57.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/58.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.GARDEN);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/59.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.MONASTERY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/60.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.EWBISECTOR);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/61.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.MONASTERY);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/62.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/63.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.EWBISECTOR);
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/64.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            deck.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/65.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/66.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.ROADSTOP);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/67.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.GARDEN);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/68.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.MONASTERY);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/69.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.EWBISECTOR);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/70.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.GARDEN);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/71.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.CITY);
            internals.add(GlobalVariables.Internal.COATOFARMS);

            deck.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }



        Collections.shuffle(deck);
        return deck;
    }

    /**
     * By default, do not include the starting tile in the deck
     * @return a randomly sorted collection of tiles
     */
    public static Stack<PlayableTile> loadDeck() {
        return loadDeck(false);
    }

    /**
     * Creates the starting tile
     * @return the starting tile
     */
    public static PlayableTile getStartTile() {
        try {
            BufferedImage image = ImageIO.read(new File("images/start.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            return new PlayableTile(image, features);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new PlayableTile();
    }

    /**
     * Creates the starting river tile
     * @return the starting river tile
     */
    public static PlayableTile getRiverStart() {
        try {
            BufferedImage image = ImageIO.read(new File("images/rstart.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            return new PlayableTile(image, features);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new PlayableTile();
    }

    /**
     * Creates the starting river tile
     * @return the starting river tile
     */
    public static PlayableTile getRiverEnd() {
        try {
            BufferedImage image = ImageIO.read(new File("images/rlast.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            return new PlayableTile(image, features);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new PlayableTile();
    }

    /**
     * Creates a stack of all of the river tiles
     * @return a stack of all of the river tiles
     */
    public static Stack<PlayableTile> getRiverTiles() {
        Stack<PlayableTile> riverTiles = new Stack<>();

        try {
            BufferedImage image = ImageIO.read(new File("images/r1.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.GARDEN);

            riverTiles.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/r2.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.RIVER);

            riverTiles.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/r3.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.GRASS);

            riverTiles.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/r4.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.RIVER);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);

            riverTiles.add(new PlayableTile(image, features));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/r5.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.RIVER);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.CITY);

            riverTiles.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/r6.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.ROAD);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.ROADSTOP);
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.EWBISECTOR);

            riverTiles.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/r7.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.RIVER);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.ROADSTOP);
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.EWBISECTOR);

            riverTiles.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/r8.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.RIVER);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);

            riverTiles.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/r9.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.CITY);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.CITY);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.EWBISECTOR);

            riverTiles.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(new File("images/r10.png"));
            HashMap<GlobalVariables.Direction, GlobalVariables.Feature> features = new HashMap<GlobalVariables.Direction, GlobalVariables.Feature>();
            features.put(GlobalVariables.Direction.NORTH, GlobalVariables.Feature.RIVER);
            features.put(GlobalVariables.Direction.EAST, GlobalVariables.Feature.ROAD);
            features.put(GlobalVariables.Direction.WEST, GlobalVariables.Feature.GRASS);
            features.put(GlobalVariables.Direction.SOUTH, GlobalVariables.Feature.RIVER);

            Set<GlobalVariables.Internal> internals = new HashSet<>();
            internals.add(GlobalVariables.Internal.NSBISECTOR);
            internals.add(GlobalVariables.Internal.MONASTERY);

            riverTiles.add(new PlayableTile(image, features, internals));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return riverTiles;
    }


}
