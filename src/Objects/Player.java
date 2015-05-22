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
    private ArrayList<Meeple> placedMeeples;
    private Meeple abbot;
    private int numMeeples;

    public Player(GlobalVariables.PlayerColor color) {
        placedMeeples = new ArrayList<>();
        pColor = color;
        playerScore = 0;
        numMeeples = 7;
        abbot = new Meeple(this, color);
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public ArrayList<Meeple> getPlacedMeeples() {
        return placedMeeples;
    }

    public int getNumMeeples() {
        return numMeeples;
    }

    public GlobalVariables.PlayerColor getPlayerColor() {
        return pColor;
    }

    public void updateScore(int points)
    {   if(points>0)
            this.playerScore += points;
    }

    //Testing for this method is included in ObjectTests.PlayableTileTest
   /* public boolean placeMeeple(PlayableTile t) {
       /* if (lastUsedMeeple < meeples.size() - 1) {
            lastUsedMeeple++;
            return true;
        }*/
        //return false;
       /* Meeple m = removeMeeple();
        if(m != null) {
            m.place(p);
        }
    } */

    public boolean hasMeeplesLeft() {
        return numMeeples > 0;
    }

    public void addMeeple() {
        numMeeples++;
    }
    public Meeple removeMeeple() {
        if(!hasMeeplesLeft())
            return null;
        numMeeples--;
        return new Meeple(this);
    }

    public void addPlacedMeeple(Meeple m) {
        placedMeeples.add(m);
    }

    public Meeple getAbbot() {
        return abbot;
    }

}
