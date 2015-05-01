/**
 * Created by johnsoaa on 3/29/2015.
 */
public class GlobalVariables {
    public static enum Feature {ROAD, GRASS, RIVER, CITY}

    public static enum Internal {ROAD, GRASS, RIVER, ROADSTOP, MONASTERY, CITY, GARDEN, COATOFARMS}

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
            }
            return RIGHT;
        }

    }

}
