import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by porterjc on 3/27/2015.
 */
public class GridDisplay {

    BufferedImage pic;
    public GridDisplay() {
        super();
        try {
            pic = ImageIO.read(new File("C:\\Users\\porterjc\\Documents\\CSSESQA\\carcassonne\\extra_stuff\\castle.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
