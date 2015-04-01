import javax.swing.*;
import java.awt.*;

/**
 * Created by robinsat on 3/31/2015.
 */
public class TileGridTest extends JFrame{

    TileGrid grid;

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
        tester.testRenderTiles();
    }

    public void testRenderTiles() {
        grid.addTile(new OpenTile(0,0));
        grid.addTile(new OpenTile(-2, 0));
        grid.addTile(new OpenTile(-3, 0));
    }

}
