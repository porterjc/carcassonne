package Objects;

import Main.GlobalVariables;
import Objects.Meeple;
import Objects.PlayableTile;

import java.util.ArrayList;

/**
 * Created by johnsoaa on 3/26/2015.
 */
public class Player {
    private int playerScore;
    private GlobalVariables.PlayerColor pColor;
    private ArrayList<Meeple> meeples;
    public int lastUsedMeeple;

    public Player(GlobalVariables.PlayerColor color) {
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

    public GlobalVariables.PlayerColor getColor() {
        return pColor;
    }

    public ArrayList<Meeple> getMeeples() {
        return meeples;
    }

    public void updateScore(int points)
    {   if(points>0)
            this.playerScore += points;
    }

    //Testing for this method is included in ObjectTests.PlayableTileTest
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
