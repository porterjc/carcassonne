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
        this.add(grid, BorderLayout.CENTER);
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
    }

}
