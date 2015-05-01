/**
 * Created by johnsoaa on 3/29/2015.
 */
public class GlobalVariables {
    public static enum Feature {ROAD, GRASS, RIVER, CITY}

    public static enum Internal {ROAD, GRASS, RIVER, ROADSTOP, MONASTERY, CITY, GARDEN, COATOFARMS}

    public static enum Direction {NORTH, SOUTH, EAST, WEST}

    public static enum Location {TOP, BOTTOM, LEFT, RIGHT, TOPLEFT, TOPRIGHT, BOTTOMLEFT, BOTTOMRIGHT, CENTER;

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
