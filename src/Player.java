import java.awt.*;
import java.util.ArrayList;

/**
 * Created by johnsoaa on 3/26/2015.
 */
public class Player {
    private int playerScore;
    private Color pColor;
    private ArrayList meeples;
    public int lastUsedMeeple;

    public Player(Color color) {
        pColor = color;
        playerScore = 0;
        lastUsedMeeple = 0;
        meeples = new ArrayList<Meeple>();
        meeples.add(new Meeple(this, color));
        meeples.add(new Meeple(this, color));
        meeples.add(new Meeple(this, color));
        meeples.add(new Meeple(this, color));
        meeples.add(new Meeple(this, color));
        meeples.add(new Meeple(this, color));
        meeples.add(new Meeple(this, color));
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


    public void updateScore(int points) {
        this.playerScore += points;
    }

    //Testing for this method is included in PlayableTileTest
    public boolean placeMeeple() {
        if (lastUsedMeeple < meeples.size()) {
            lastUsedMeeple++;
            return true;
        }
        return false;
    }
}
