import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by johnsoaa on 3/26/2015.
 */
public class Player {
    private int playerScore;
    private Color pColor;
    private ArrayList meeples;

    public Player(Color color) {
        pColor = color;
        playerScore = 0;
        meeples = new ArrayList<Meeple>();
        meeples.add(new Meeple(color));
        meeples.add(new Meeple(color));
        meeples.add(new Meeple(color));
        meeples.add(new Meeple(color));
        meeples.add(new Meeple(color));
        meeples.add(new Meeple(color));
        meeples.add(new Meeple(color));
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public Color getColor() {
        return pColor;
    }

    public ArrayList getMeeples() {
        return meeples;
    }


}
