package UIComponents;

import Main.GlobalVariables;
import Objects.Game;
import Objects.PlayableTile;
import Objects.Player;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * This panel is displayed at the bottom of the screen and must be continuously updated as the game progresses. 
 * Created by robinsat on 5/15/2015.
 */
public class BottomDisplay extends JPanel{

    private JLabel tilesLeftLabel;
    
    public BottomDisplay(int screenWidth, int screenHeight, final Game game) {
        this.setBackground(GlobalVariables.DARK_BLUE);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(screenWidth, 200));
        //this.setPreferredSize(new Dimension(SCREEN_WIDTH - COMPONENT_MARGIN, 200 - COMPONENT_MARGIN));
        this.setBorder(new CompoundBorder(new EmptyBorder(22, 20, 20, 20), BorderFactory.createLoweredBevelBorder()));


        GraphicButton rotateLabel = new GraphicButton(100, 100) {
            @Override
            public void mouseClicked(MouseEvent e) {
                PlayableTile current = game.getCurrentTile();
                current.rotateTile();
               // updateTile();

            }
        };

        rotateLabel.setBackground(Color.RED);
        rotateLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        this.add(Box.createRigidArea(new Dimension(20, 20)));
        this.add(rotateLabel);
        this.add(Box.createRigidArea(new Dimension(20, 20)));

        JLabel tiledisplay = new JLabel(game.getCurrentTile().getIcon());
        this.add(tiledisplay);
        this.add(Box.createRigidArea(new Dimension(20, 20)));

        JPanel tilesLeftPanel = new JPanel();
        tilesLeftPanel.setLayout(new BoxLayout(tilesLeftPanel, BoxLayout.Y_AXIS));
        tilesLeftPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
        JLabel tilesLabel = new GameLabel("Tiles Left:");
        tilesLabel.setAlignmentX(CENTER_ALIGNMENT);
        GameLabel tilesLeftLabel = new GameLabel(game.getNumberOfTilesLeft() + "");
        tilesLeftLabel.setForeground(Color.RED);
        tilesLeftLabel.setFont(new Font(tilesLeftLabel.getFont().getName(), Font.BOLD, 64));
        tilesLeftLabel.setAlignmentX(CENTER_ALIGNMENT);
        tilesLeftPanel.add(tilesLabel);
        tilesLeftPanel.add(tilesLeftLabel);
        this.add(tilesLeftPanel);

        StatePanel statePanel = new StatePanel(game);
        this.add(Box.createRigidArea(new Dimension(20, 20)));
        this.add(statePanel);
        this.add(Box.createRigidArea(new Dimension(20, 20)));

        //boardDisplay.setTileLabels(tiledisplay, tilesLeftLabel, statePanel);

        // this.add(Box.createRigidArea(new Dimension(20, 20)));
        // Just for GUI testing. TODO: delete
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(GlobalVariables.PlayerColor.RED));
        players.add(new Player(GlobalVariables.PlayerColor.YELLOW));
        players.add(new Player(GlobalVariables.PlayerColor.GREEN));
        ScorePanel scorePanel = new ScorePanel(players);
        this.add(scorePanel);
    }
}
