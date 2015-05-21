package ObjectTests;

import Main.*;
import Objects.*;
import UIComponents.BottomDisplay;
import UIComponentsTests.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by robinsat on 3/31/2015.
 */
public class TileGridTest extends JFrame{

    TileGrid grid;
    Stack<PlayableTile> testingTiles = (new TileAddTest()).getTestingTiles();

    public TileGridTest() {
        super();

        this.setSize(1500, 1500);
        grid = new TileGrid(1500, 1500);
        grid.initialize(TileFactory.getStartTile());
        grid.setGame(new Game(new BottomDisplay(500), TileFactory.loadDeck(), new ArrayList<Player>()));
        JScrollPane pane = new JScrollPane(grid, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setSize(900, 900);
        this.add(pane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        TileGridTest tester = new TileGridTest();
    }

}
