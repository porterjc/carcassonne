package UIComponents;

import Main.GlobalVariables;
import Objects.Game;
import Objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

/**
 * A panel displayed at the end of the game to display the final score
 * Created by robinsat on 5/20/2015.
 */
public class FinalScorePanel extends JPanel {

    /**
     * Constructor
     */
    public FinalScorePanel(Game game) {
        super();
        this.setBackground(GlobalVariables.MEDIUM_BLUE);
        this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.setSize(600, 650);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);
        setUp(game);
    }

    /**
     * Adds the necessary displays to the panel
     * @param game the game object storing all of the info
     */
    private void setUp(Game game) {
        this.add(Box.createRigidArea(new Dimension(400, 25)));
        List<Player> players = game.getPlayers();
        Collections.sort(players, new PlayerComparator());

        //Display the scores, which are now sorted
        for(Player p : game.getPlayers()) {
            this.add(new HorizontalScoreDisplay(p));
        }

        this.add(Box.createRigidArea(new Dimension(400, 25)));
        this.add(new GraphicButton(400, 50, "Quit") {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        this.add(Box.createRigidArea(new Dimension(400, 25)));

        repaint();
    }

    /**
     * Sorts the players based on who has the highest score
     */
    private class PlayerComparator implements Comparator<Player> {

        /**
         * Compares the players
         * @param p1 The first player to compare
         * @param p2 The second player to compare
         * @return the difference between scores
         */
        @Override
        public int compare(Player p1, Player p2) {
            return p2.getPlayerScore() - p1.getPlayerScore();
        }
    }

    /**
     * A panel that formats a player's score horizontally
     */
    private class HorizontalScoreDisplay extends JPanel{

        /**
         * Constructor
         * @param p The player this panel represents
         */
        public HorizontalScoreDisplay(Player p) {
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.setOpaque(false);
            this.add(new JLabel(new ImageIcon(p.getPlayerColor().getMeepleImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT))));
            this.add(new GameLabel("Final Score: " + p.getPlayerScore()));
        }
    }
}
