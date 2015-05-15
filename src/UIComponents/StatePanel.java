package UIComponents;

import Objects.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This class controls the functionality for a panel that displays the current game state and whose turn it is
 * Created by robinsat on 5/14/2015.
 */
public class StatePanel extends JPanel {

    /** The button that shows whose turn it is, also can be used to pass */
    private GraphicButton passButton;

    /** The label showing the current state of the game */
    private GameLabel stateLabel;

    /** The game that this label must communicate with */
    private Game game;

    /**
     * Constructor
     * @param game the game that this panel communicates with
     */
    public StatePanel(final Game game) {
        this.game = game;

        this.setSize(new Dimension(200, 100));
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        stateLabel = new GameLabel("Place a tile");
        this.add(stateLabel);

        passButton = new GraphicButton(120, 50) {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.passButtonPressed();
            }
        };
        passButton.setBackground(game.getCurrentTurnPlayer().getPlayerColor().getColor());
        this.add(passButton);
    }

    /**
     * Updates the "pass" button to display properly after each turn state
     * @param showPass whether or not to display the text "pass" on the button
     */
    public void updatePassButton(boolean showPass) {
        passButton.clear();
        if(showPass)
            passButton.setText("Pass");
        passButton.setBackground(game.getCurrentTurnPlayer().getPlayerColor().getColor());
    }

    /**
     * Updates the label to appropriately display the current game state
     */
    public void updateStateLabel() {
        stateLabel.setText(game.getCurrentStateText());
    }

}
