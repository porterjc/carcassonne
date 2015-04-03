import java.awt.*;

/**
 * Created by johnsoaa on 3/31/2015.
 */
public class OpenTile extends AbstractTile {

    public OpenTile(int x, int y) {
        super(x, y);
    }

   /* @Override
    public void draw(Graphics g, int offsetX, int offsetY) {
        int x = (getxVal() - offsetX) * 300;
        int y = (getyVal() - offsetY) * 300;
        g.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        g.drawRect(x, y, SIZE, SIZE);
        g.setColor(Color.WHITE);
        g.fillRect(x, y, SIZE, SIZE);
    }*/
}
