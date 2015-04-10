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

        this.setSize(900, 900);
        grid = new TileGrid();
        JScrollPane pane = new JScrollPane(grid, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.setSize(900, 900);
        this.add(pane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        TileGridTest tester = new TileGridTest();
        //tester.testRenderTiles();
        tester.testAddTiles();
    }

    public void testRenderTiles() {
        grid.addTile(new OpenTile(), 0, 0, false);
        grid.addTile(new OpenTile(), 3, 0, false);
        grid.addTile(new OpenTile(), 2, 4, false);
        grid.addTile(new OpenTile(), -1, 0, false);
        grid.addTile(new OpenTile(), 0, -1, false);

    }

    public void testAddTiles() {
        Game newGame = new Game(testingTiles);
        grid.game = newGame;
        newGame.drawTile();
        grid.addTile(TileFactory.getStartTile(), 1, 1, true);

    }

}
