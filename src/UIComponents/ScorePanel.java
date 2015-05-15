package UIComponents;

import Objects.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by robinsat on 4/12/2015.
 * This class will draw a table to show the score and remaining meeples of all the players in the game
 */
public class ScorePanel extends JPanel {

    // The players in this game
    private List<Player> players;

    // The labels in the table
    private List<PlayerInfoLabel> meepleLabels;
    private List<PlayerInfoLabel> scoreLabels;

    /**
     * Constructor
     * @param players A list of players
     */
    public ScorePanel(List<Player> players) {
        super();
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setLayout(new GridLayout(3, players.size() + 1));
        this.players = players;

        meepleLabels = new ArrayList<>();
        scoreLabels = new ArrayList<>();

        drawTable();
    }

    /**
     * Initially adds all of the components to the table
     */
    public void drawTable() {
        this.add(new JPanel()); // Blank space

        for(Player p : players) { // Add color labels
            JPanel colorPanel = new JPanel();
            colorPanel.setBackground(p.getPlayerColor().getColor());
            this.add(colorPanel);
        }

        this.add(new GameLabel("Meeples:")); // Meeples label

        for(Player p : players) { // Individual labels
            PlayerInfoLabel label = new PlayerInfoLabel(p);
            meepleLabels.add(label);
            this.add(label);
            label.displayMeeples();
        }

        this.add(new GameLabel("Score:")); // Score label

        for(Player p : players) { // Individual labels
            PlayerInfoLabel label = new PlayerInfoLabel(p);
            scoreLabels.add(label);
            this.add(label);
            label.displayScore();
        }
    }

    /**
     * Updates the scores and meeple count
     */
    public void updateTable() {
        for(PlayerInfoLabel label : scoreLabels) {
            label.displayScore();
        }

        for(PlayerInfoLabel label : meepleLabels) {
            label.displayMeeples();
        }
    }

    /**
     * This is a label that conveniently stores a reference to the player it represents
     */
    private class PlayerInfoLabel extends JLabel {

        private Player player;

        /**
         * Constructor
         * @param p The player that this label represents
         */
        public PlayerInfoLabel(Player p) {
            super();
            this.setSize(50, 50);
            player = p;
        }

        /**
         * Sets the text of this label to the player's current score
         */
        public void displayScore() {
            this.setText(player.getPlayerScore() + "");
        }

        /**
         * Sets the text of this label to the player's current number of meeples
         */
        public void displayMeeples() {
            this.setText(player.getMeeples().size() + "");
        }
    }

}
