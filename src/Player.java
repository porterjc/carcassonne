import java.awt.*;
import java.util.ArrayList;

/**
 * Created by johnsoaa on 3/26/2015.
 */
public class Player {
    private int playerScore;
    private Color pColor;
    private ArrayList<Meeple> meeples;
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

    public ArrayList<Meeple> getMeeples() {
        return meeples;
    }


    public void updateScore(int points) {
        this.playerScore += points;
    }

    //Testing for this method is included in PlayableTileTest
    public boolean placeMeeple(PlayableTile t) {
        if (lastUsedMeeple < meeples.size() - 1) {
            lastUsedMeeple++;
            return true;
        }
        return false;
    }

    public int getScore() {
        return this.playerScore;
    }
}
