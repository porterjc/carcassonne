import java.awt.*;

/**
 * Created by johnsoaa on 3/31/2015.
 */
public class OpenTile extends AbstractTile {

    public OpenTile(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g, int offset) {
        g.setColor(Color.YELLOW);
        g.fillRect((getxVal() - offset) * 300, (getyVal() - offset) * 300, SIZE, SIZE);
    }
}
