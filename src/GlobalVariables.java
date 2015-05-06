import java.awt.*;

/**
 * Created by johnsoaa on 3/29/2015.
 */
public class GlobalVariables {
    public static enum Feature {ROAD, GRASS, RIVER, CITY}

    public static enum Internal {GRASS, ROADSTOP, MONASTERY, CITY, GARDEN, COATOFARMS, NSBISECTOR, EWBISECTOR}

    public static enum Direction {NORTH, SOUTH, EAST, WEST}

    public static enum Location {TOP, BOTTOM, LEFT, RIGHT, TOPLEFT, TOPRIGHT, BOTTOMLEFT, BOTTOMRIGHT, CENTER;

       public static boolean isBottom(Location loc) {
           switch(loc) {
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
            switch(loc) {
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
            switch(loc) {
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
            switch(loc) {
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

    // COLORS

    // Player colors
    public static Color RED = new Color(227, 6, 16);
    public static Color YELLOW = new Color(245, 234, 0);
    public static Color GREEN = new Color(4, 148, 7);
    public static Color BLUE = new Color(47, 47, 255);
    public static Color BLACK = new Color(45, 45, 45);

    // Graphical Constants
    public static Color LIGHT_BLUE = new Color(167, 171, 209);
    public static Color MEDIUM_BLUE = new Color(100, 105, 153);
    public static Color DARK_BLUE = new Color(39, 40, 49);
}
