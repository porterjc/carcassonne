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

        public static Location goDown(Location loc) {
            switch (loc) {
                case TOP:
                    return BOTTOM;
                case TOPLEFT:
                    return BOTTOMLEFT;

            }
            return BOTTOM;
        }

    }

}
