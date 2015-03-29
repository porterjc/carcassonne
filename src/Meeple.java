import javafx.scene.paint.Color;

/**
 * Created by johnsoaa on 3/29/2015.
 */
public class Meeple {


    private Color mColor;
    private Player meepleOwner;

    public Meeple(Player p, Color aqua) {
        mColor = aqua;
        meepleOwner = p;
    }

    public Color getColor() {
        return mColor;
    }

    public Player getPlayer() {
        return meepleOwner;
    }
}
