import java.awt.*;

/**
 * Created by johnsoaa on 3/31/2015.
 */
public class OpenTile extends AbstractTile {

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(getxVal(), getyVal(), SIZE, SIZE);
    }
}
