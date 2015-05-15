package UIComponents;

import javax.swing.*;
import java.awt.*;

/**
 * This panel displays the rules of the game and provides the necessary visuals.
 * Created by robinsat on 5/11/2015.
 */
public class RulesPanel extends JPanel {

    /**
     * Constructor
     * @param width the width of this UI component
     */
    public RulesPanel(int width) {
        super();
        this.setPreferredSize(new Dimension(width, 1000));
        this.setBackground(Color.WHITE);
        this.displayRules();
    }

    /**
     * This adds the necessary text and images to this panel to clarify all the rules of the game.
     */
    public void displayRules() {
        //TODO: implement this
        JTextArea text = new JTextArea("Carcassone is a game of claiming land and strategically expanding your territory, and it may be played by 2-5 players. Our goal is to create a new version of the classic game, allowing players to compete against a computer through a simple graphical interface. \n" +
                "Each player begins the game with seven meeples, which are people that may be placed onto landscape features that the player controls. At the start of the game, one “start tile” is already in place. \n" +
                "Each player’s turn consists of three steps: drawing and placing a tile, optionally placing a meeple, and scoring a feature. Players take their turns in sequence until all of the tiles have been played, at which point the game is over. \n" +
                "At the start of a player’s turn, they draw a tile from the stack. They then must place the tile adjacent to an existing tile in such a way that it continues the features of the landscape. For example, if two tiles share an edge, and one tile has a road leading to that edge, the other tile must also have a road leading to that edge or the move is invalid. (Note: Our program should verify that there is at least one possible spot where the tile can be placed. If there are no possible moves, the player gets a new tile). \n" +
                "Once a player has put a tile down, they may choose to place one of their meeples onto this tile. When a player places a meeple onto a feature, they claim this feature as their own. This can only be done if there are no other meeples on this feature, including extensions of this feature on another tile. For example, if a player has just put down a tile with a city segment on it, they may designate one of their meeples as a ‘knight’ to guard the city. However, this is not allowed if the city is connected to another city tile with a ‘knight’ already in it. \n" +
                "At the end of a player’s turn, they must tally the score for any newly completed feature. For example, if a player just placed a tile that forms an enclosed city, the city’s owner now scores points for every tile that is a part of that city. This player may then return the meeple that was stationed on that tile to their hand.", 100, 45);

        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        this.add(text);

    }
}
