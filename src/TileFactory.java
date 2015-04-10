import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

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
    public static Collection<PlayableTile> loadDeck(boolean includeStart) {
        Stack<PlayableTile> deck = new Stack<PlayableTile>();
        if(includeStart) {
            deck.add(getStartTile());
        }

        Collections.shuffle(deck);
        return deck;
    }

    /**
     * By default, do not include the starting tile in the deck
     * @return a randomly sorted collection of tiles
     */
    public static Collection<PlayableTile> loadDeck() {
        return loadDeck(false);
    }

    /**
     * Creates the starting tile
     * @return the starting tile
     */
    public static PlayableTile getStartTile() {
        try {
            Image image = ImageIO.read(new File("images/start.png"));
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

    //TODO: do this once the images are fixed
    /**
     * Creates the starting river tile
     * @return the starting river tile
     */
    public static PlayableTile getRiverStart() {
        try {
            Image image = ImageIO.read(new File("images/start.png"));
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
    public static PlayableTile getRiverEnd() {
        try {
            Image image = ImageIO.read(new File("images/rlast.png"));
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


}
