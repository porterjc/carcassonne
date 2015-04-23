import javax.swing.*;
import java.awt.*;

/**
 * Created by robinsat on 4/23/2015.
 */
public class TileRotateGraphicTest extends JFrame {

    public TileRotateGraphicTest() {
        this.setSize(1500, 1500);
        this.setVisible(true);
       // this.setLayout(new FlowLayout());
        PlayableTile tile = TileFactory.getStartTile();
        tile.setBounds(100, 100, AbstractTile.TILE_PIXEL_SIZE, AbstractTile.TILE_PIXEL_SIZE);
        tile.setVisible(true);
        this.add(tile);
        tile.drawSelf();

    }

    public static void main(String[] args) {
        new TileRotateGraphicTest();
    }
}
