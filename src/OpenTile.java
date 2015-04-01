import java.awt.*;

/**
 * Created by johnsoaa on 3/31/2015.
 */
public class OpenTile extends AbstractTile {

    public OpenTile(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g, int offsetX, int offsetY) {
        g.setColor(Color.YELLOW);
        g.fillRect((getxVal() - offsetX) * 300, (getyVal() - offsetY) * 300, SIZE, SIZE);
    }
}
