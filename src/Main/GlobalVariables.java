package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by johnsoaa on 3/29/2015.
 * <p/>
 * Global enums and variables that will be used throughout the program are defined here
 */

public class GlobalVariables {

    public enum Feature {ROAD, GRASS, RIVER, CITY}

    public enum Internal {GRASS, ROADSTOP, MONASTERY, CITY, GARDEN, COATOFARMS, NSBISECTOR, EWBISECTOR}

    public enum Direction {NORTH(Location.TOP), SOUTH(Location.BOTTOM), EAST(Location.RIGHT), WEST(Location.LEFT);

        Location location;

        Direction(Location l) {
            location = l;
        }

        public Location getLocation() {
            return location;
        }
    }

    public enum Location {
        TOP, BOTTOM, LEFT, RIGHT, TOPLEFT, TOPRIGHT, BOTTOMLEFT, BOTTOMRIGHT, CENTER;

        public static boolean isBottom(Location loc) {
            switch (loc) {
                case TOPLEFT:
                    return false;
                case TOP:
                    return false;
                case TOPRIGHT:
                    return false;
                case LEFT:
                    return false;
                case CENTER:
                    return false;
                case RIGHT:
                    return false;
                case BOTTOMLEFT:
                    return true;
                case BOTTOM:
                    return true;
                case BOTTOMRIGHT:
                    return true;
            }
            return false;
        }

        public static boolean isTop(Location loc) {
            switch (loc) {
                case TOPLEFT:
                    return true;
                case TOP:
                    return true;
                case TOPRIGHT:
                    return true;
                case LEFT:
                    return false;
                case CENTER:
                    return false;
                case RIGHT:
                    return false;
                case BOTTOMLEFT:
                    return false;
                case BOTTOM:
                    return false;
                case BOTTOMRIGHT:
                    return false;
            }
            return false;
        }

        public static boolean isLeft(Location loc) {
            switch (loc) {
                case TOPLEFT:
                    return true;
                case TOP:
                    return false;
                case TOPRIGHT:
                    return false;
                case LEFT:
                    return true;
                case CENTER:
                    return false;
                case RIGHT:
                    return false;
                case BOTTOMLEFT:
                    return true;
                case BOTTOM:
                    return false;
                case BOTTOMRIGHT:
                    return false;
            }
            return false;
        }

        public static boolean isRight(Location loc) {
            switch (loc) {
                case TOPLEFT:
                    return false;
                case TOP:
                    return false;
                case TOPRIGHT:
                    return true;
                case LEFT:
                    return false;
                case CENTER:
                    return false;
                case RIGHT:
                    return true;
                case BOTTOMLEFT:
                    return false;
                case BOTTOM:
                    return false;
                case BOTTOMRIGHT:
                    return true;
            }
            return false;
        }

        public static Location goDown(Location loc) {
            switch (loc) {
                case TOP:
                    return BOTTOM;
                case TOPLEFT:
                    return BOTTOMLEFT;
                case LEFT:
                    return BOTTOMLEFT;
                case RIGHT:
                    return BOTTOMRIGHT;
                case TOPRIGHT:
                    return BOTTOMRIGHT;
                case CENTER:
                    return BOTTOM;
                case BOTTOMRIGHT:
                    return BOTTOMRIGHT;
                case BOTTOMLEFT:
                    return BOTTOMLEFT;
            }
            return BOTTOM;
        }

        public static Location goUp(Location loc) {
            switch (loc) {
                case BOTTOM:
                    return TOP;
                case BOTTOMRIGHT:
                    return TOPRIGHT;
                case RIGHT:
                    return TOPRIGHT;
                case LEFT:
                    return TOPLEFT;
                case BOTTOMLEFT:
                    return TOPLEFT;
                case CENTER:
                    return TOP;
                case TOPRIGHT:
                    return TOPRIGHT;
                case TOPLEFT:
                    return TOPLEFT;
            }
            return TOP;
        }

        public static Location goLeft(Location loc) {
            switch (loc) {
                case BOTTOM:
                    return BOTTOMLEFT;
                case BOTTOMRIGHT:
                    return BOTTOMLEFT;
                case RIGHT:
                    return LEFT;
                case TOP:
                    return TOPLEFT;
                case TOPRIGHT:
                    return TOPLEFT;
                case CENTER:
                    return LEFT;
                case TOPLEFT:
                    return TOPLEFT;
                case BOTTOMLEFT:
                    return BOTTOMLEFT;
            }
            return LEFT;
        }

        public static Location goRight(Location loc) {
            switch (loc) {
                case BOTTOM:
                    return BOTTOMRIGHT;
                case BOTTOMLEFT:
                    return BOTTOMRIGHT;
                case LEFT:
                    return RIGHT;
                case TOP:
                    return TOPRIGHT;
                case TOPLEFT:
                    return TOPRIGHT;
                case CENTER:
                    return RIGHT;
                case TOPRIGHT:
                    return TOPRIGHT;
                case BOTTOMRIGHT:
                    return BOTTOMRIGHT;
            }
            return RIGHT;
        }

    }
/*
    // Some Color constants
    public static Color RED = new Color(227, 6, 16);
    public static Color YELLOW = new Color(245, 234, 0);
    public static Color GREEN = new Color(4, 148, 7);
    public static Color BLUE = new Color(47, 47, 255);
    public static Color BLACK = new Color(45, 45, 45);
*/

    // Graphical Constants
    public static Color LIGHT_BLUE = new Color(167, 171, 209);
    public static Color MEDIUM_BLUE = new Color(100, 105, 153);
    public static Color DARK_BLUE = new Color(39, 40, 49);

    //Images that will be reused many times

    /**
     * The image for a red meeple
     */
    public static Image redMeeple;

    static {
        try {
            redMeeple = ImageIO.read(new File("images/meeples/meeple_red.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The image for a yellow meeple
     */
    public static Image yellowMeeple;

    static {
        try {
            yellowMeeple = ImageIO.read(new File("images/meeples/meeple_yellow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The image for a green meeple
     */
    public static Image greenMeeple;

    static {
        try {
            greenMeeple = ImageIO.read(new File("images/meeples/meeple_green.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The image for a blue meeple
     */
    public static Image blueMeeple;

    static {
        try {
            blueMeeple = ImageIO.read(new File("images/meeples/meeple_blue.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The image for a black meeple
     */
    public static Image blackMeeple;

    static {
        try {
            blackMeeple = ImageIO.read(new File("images/meeples/meeple_black.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The image for a red abbot
     */
    public static Image redAbbot;

    static {
        try {
            redAbbot = ImageIO.read(new File("images/meeples/abbot_red.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The image for a yellow abbot
     */
    public static Image yellowAbbot;

    static {
        try {
            yellowAbbot = ImageIO.read(new File("images/meeples/abbot_yellow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The image for a green abbot
     */
    public static Image greenAbbot;

    static {
        try {
            greenAbbot = ImageIO.read(new File("images/meeples/abbot_green.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The image for a blue abbot
     */
    public static Image blueAbbot;

    static {
        try {
            blueAbbot = ImageIO.read(new File("images/meeples/abbot_blue.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The image for a black abbot
     */
    public static Image blackAbbot;

    static {
        try {
            blackAbbot = ImageIO.read(new File("images/meeples/abbot_black.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convieniently stores the necessary colors and images
     */
    public enum PlayerColor {RED (new Color(227, 6, 16), redMeeple, redAbbot),
                                    YELLOW (new Color(245, 234, 0), yellowMeeple, yellowAbbot),
                                    GREEN (new Color(4, 148, 7), greenMeeple, greenAbbot),
                                    BLUE (new Color(47, 47, 255), blueMeeple, blueAbbot),
                                    BLACK (new Color(45, 45, 45), blackMeeple, blackAbbot);


        /**
         * The color to display
         */
        private Color color;
        /**
         * The image to display for a meeple of this color
         */
        private Image meepleImage;
        /**
         * The image to display for an abbot of this color
         */
        private Image abbotImage;

        /**
         * Constructor
         *
         * @param color       The color
         * @param meepleImage The meeple image associated with this color
         * @param abbotImage  The abbot image associated with this color
         */
        PlayerColor(Color color, Image meepleImage, Image abbotImage) {
            this.color = color;
            this.meepleImage = meepleImage;
            this.abbotImage = abbotImage;
        }

        /**
         * Getter method for the color
         *
         * @return the color
         */
        public Color getColor() {
            return color;
        }

        /**
         * Getter method for the meeple Image
         *
         * @return the meeple Image
         */
        public Image getMeepleImage() {
            return meepleImage;
        }

        /**
         * Getter method for the abbot Image
         *
         * @return the abbot Image
         */
        public Image getAbbotImage() {
            return abbotImage;
        }
    }
}
