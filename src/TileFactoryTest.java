import javax.swing.*;

/**
 * Created by robinsat on 4/10/2015.
 */
public class TileFactoryTest extends JFrame {

    public TileFactoryTest() {
        super();
        this.setSize(900, 900);
        this.add(new TileLabel(TileFactory.getStartTile(), 1, 1, new TileGrid()));
        this.setVisible(true);
    }

    public static void main(String[] args) {
        TileFactoryTest test = new TileFactoryTest();
    }
}
