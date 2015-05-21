package UIComponentsTests;

import Main.GlobalVariables;
import Objects.Game;
import Objects.PlayableTile;
import Objects.Player;
import UIComponents.BottomDisplay;
import UIComponents.FinalScorePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Tests the FinalScorePanel class
 * Created by robinsat on 5/20/2015.
 */
public class FinalScorePanelTest extends JFrame{

    /**
     * Constructor
     */
    public FinalScorePanelTest() {
        super();
        this.setSize(1000, 800);
        this.setVisible(true);

        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player(GlobalVariables.PlayerColor.YELLOW);
        p1.updateScore(40);
        players.add(p1);
        Player p2 = new Player(GlobalVariables.PlayerColor.BLACK);
        p2.updateScore(30);
        players.add(p2);
        Player p3 = new Player(GlobalVariables.PlayerColor.BLUE);
        p3.updateScore(80);
        players.add(p3);
        Player p4 = new Player(GlobalVariables.PlayerColor.RED);
        p4.updateScore(10);
        players.add(p4);
        Player p5 = new Player(GlobalVariables.PlayerColor.GREEN);
        p5.updateScore(65);
        players.add(p5);

        Game game = new Game(new BottomDisplay(0), new Stack<PlayableTile>(), players);

        this.add(new FinalScorePanel(game));
    }

    public static void main(String[] args) {
        new FinalScorePanelTest();
    }
}
