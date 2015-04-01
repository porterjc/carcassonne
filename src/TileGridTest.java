import javax.swing.*;
import java.awt.*;

/**
 * Created by robinsat on 3/31/2015.
 */
public class TileGridTest extends JFrame{

    public TileGridTest() {
        super();

        this.setSize(800, 600);
        this.add(new TileGrid(), BorderLayout.CENTER);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TileGridTest();
    }

}
