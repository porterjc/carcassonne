package UIComponents;

import Main.GlobalVariables;
import Objects.Game;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This panel is displayed at the bottom of the screen and must be continuously updated as the game progresses. 
 * Created by robinsat on 5/15/2015.
 */
public class BottomDisplay extends JPanel{

    /** Displays the current tile */
    private JLabel currentTileLabel;
    /** Displays the tiles left in the game */
    private JLabel tilesLeftLabel;
    /** Displays the current game state: who is playing? */
    private StatePanel statePanel;
    /** Displays each player's current score and remaining meeples */
    private ScorePanel scorePanel;
    /** The game in progress */
    private Game game;

    /**
     * Constructor
     * @param screenWidth The width of the screen
     */
    public BottomDisplay(int screenWidth) {
        //Set up how this will look
        this.setBackground(GlobalVariables.DARK_BLUE);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(screenWidth, 200));
        //this.setPreferredSize(new Dimension(SCREEN_WIDTH - COMPONENT_MARGIN, 200 - COMPONENT_MARGIN));
        this.setBorder(new CompoundBorder(new EmptyBorder(22, 20, 20, 20), BorderFactory.createLoweredBevelBorder()));

        // Add button to rotate the current tile
        GraphicButton rotateLabel = new GraphicButton(100, 100) {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleRotatePress();
            }
        };

        rotateLabel.setBackground(Color.RED);
        rotateLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        addMargin();
        this.add(rotateLabel);
        addMargin();

        // Add label to display the current tile
        currentTileLabel = new JLabel();
        this.add(currentTileLabel);
        addMargin();

        // Add panel to display the tiles left in the game
        JPanel tilesLeftPanel = new JPanel();
        tilesLeftPanel.setLayout(new BoxLayout(tilesLeftPanel, BoxLayout.Y_AXIS));
        tilesLeftPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
        JLabel tilesLabel = new GameLabel("Tiles Left:");
        tilesLabel.setAlignmentX(CENTER_ALIGNMENT);
        tilesLeftLabel = new GameLabel("");
        tilesLeftLabel.setForeground(Color.RED);
        tilesLeftLabel.setFont(new Font(tilesLeftLabel.getFont().getName(), Font.BOLD, 64));
        tilesLeftLabel.setAlignmentX(CENTER_ALIGNMENT);
        tilesLeftPanel.add(tilesLabel);
        tilesLeftPanel.add(tilesLeftLabel);
        this.add(tilesLeftPanel);

    }

    /**
     * Initializes the labels based on data in a game object
     * @param game the game object
     */
    public void initializeLabels(Game game) {
        this.game = game;
        currentTileLabel.setIcon(game.getCurrentTile().getIcon());
        tilesLeftLabel.setText(game.getNumberOfTilesLeft() + "");

        // Add panel to display game state
        statePanel = new StatePanel(game);
        addMargin();
        this.add(statePanel);
        addMargin();

        // Add panel to display scores
        scorePanel = new ScorePanel(game.getPlayers());
        this.add(scorePanel);
    }

    /**
     * Adds a margin between the UI components
     */
    private void addMargin() {
        this.add(Box.createRigidArea(new Dimension(20, 20)));
    }

    /**
     * Called when the "rotate tile" button is pressed
     */
    private void handleRotatePress() {
        if(game.canAdjustTile()) {
            game.getCurrentTile().rotateTile();
            updateCurrentTileLabel();
        }
    }

    /**
     * Called each time a tile is placed to update the UI
     */
    public void placedTileUpdate() {
        disableLabel(currentTileLabel);
        updateTilesLeftLabel();
        updateStatePanel();
    }

    /**
     * Called each time a meeple is placed to update the UI
     */
    public void placedMeepleUpdate() {
        updateStatePanel();
    }

    /**
     * Called each time the scoring algorithms have finished running
     */
    public void finishedScoringUpdate() {
        updateCurrentTileLabel();
        updateStatePanel();
        updateScorePanel();
    }

    /**
     * Displays the final score
     */
    public void displayFinalScore() {
        Container parent = getParent();
        parent.removeAll();
        parent.add(new FinalScorePanel(game));
        parent.revalidate();
        parent.repaint();
    }

    /**
     * Updates the current tile label to display the current tile
     */
    private void updateCurrentTileLabel() {
        currentTileLabel.setIcon(game.getCurrentTile().getIcon());
    }

    /**
     * Updates the label showing the number of tiles left
     */
    private void updateTilesLeftLabel() {
        tilesLeftLabel.setText(game.getNumberOfTilesLeft() + "");
    }

    /**
     * Updates necessary components in the state panel
     */
    private void updateStatePanel() {
        statePanel.updateStateLabel();
        statePanel.updatePassButton();
    }

    /**
     * Updates the score panel to show the current results
     */
    private void updateScorePanel() {
        scorePanel.updateTable();
        scorePanel.repaint();
    }

    /**
     * Sets the label to appear as "disabled"
     * @param label the label to disable
     */
    private void disableLabel(JLabel label) {
        //TODO make this appear gray
    }

}
