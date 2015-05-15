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
        JTextArea text = new JTextArea("Carcassone is a game of claiming land and strategically expanding your territory. It may be played by 2-5 players. \n" +
                "Each player begins the game with seven meeples, which are people that may be placed onto landscape features that the player controls. At the start of the game, one \u0027start tile\u0027 is already in place. \n" +
                "Each player\u0027s turn consists of three steps: drawing and placing a tile, optionally placing a meeple, and scoring a feature. Players take their turns in sequence until all of the tiles have been played, at which point the game is over. \n" +
                "At the start of a player\u0027s turn, they draw a tile from the stack. They then must place the tile adjacent to an existing tile in such a way that it continues the features of the landscape. For example, if two tiles share an edge, and one tile has a road leading to that edge, the other tile must also have a road leading to that edge or the move is invalid. (Note: Our program should verify that there is at least one possible spot where the tile can be placed. If there are no possible moves, the player gets a new tile). \n" +
                "Once a player has put a tile down, they may choose to place one of their meeples onto this tile. When a player places a meeple onto a feature, they claim this feature as their own. This can only be done if there are no other meeples on this feature, including extensions of this feature on another tile. For example, if a player has just put down a tile with a city segment on it, they may designate one of their meeples as a \u0027knight\u0027 to guard the city. However, this is not allowed if the city is connected to another city tile with a \u0027knight\u0027 already in it. \n" +
                "At the end of a player\u0027s turn, they must tally the score for any newly completed feature. For example, if a player just placed a tile that forms an enclosed city, the city\u0027s owner now scores points for every tile that is a part of that city. This player may then return the meeple that was stationed on that tile to their hand. \n" +
                "\n" +
                "Roads are worth one point per tile. A road is considered \u0027complete\u0027 if both ends stop at some other feature or if the road forms a complete loop. Meeples may take on the role of highwaymen to claim a road for a player.\n" +
                "Cities are worth two points per tile. A city is considered \u0027complete\u0027 if it is enclosed by walls and there are no holes in the city. Meeples may act as knights to guard a city. Some city tiles have a coat of arms, which are worth an additional two points.\n" +
                "A monastery is worth one point per tile that completes it. A monastery is \u0027complete\u0027 when it is completely surrounded by tiles, including diagonally. Therefore, including the monastery itself, one of these tiles is worth nine points when complete. Meeples may be placed as monks to claim a monastery. \n" +
                "In the case that two features are connected in such a way that multiple meeples share one feature, the player with the most meeples on that feature scores the points for that feature. If two players are tied for the most meeples on one feature, both players score all of the points that this feature is worth. \n" +
                "At the end of the game, the meeples remaining in the game are still scored. Each incomplete road is worth one point per tile. Each incomplete city is worth one point per tile and one point per coat of arms. Each incomplete monastery is worth one point (for the monastery tile itself) plus one point for each adjacent tile. \n" +
                "The winner is the player with the highest number of points. ", 100, 47);

        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        this.add(text, BorderLayout.WEST);
        this.setAlignmentX(JPanel.LEFT_ALIGNMENT);
    }
}
