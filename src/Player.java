import javafx.scene.paint.Color;

import java.awt.*;

/**
 * Created by johnsoaa on 3/26/2015.
 */
public class Player {
    private int playerScore;
    private Color color;
    private List meeples;

    public Player(Color color) {
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public Color getColor() {
        return color;
    }

    public List getMeeples() {
        return meeples;
    }


}
