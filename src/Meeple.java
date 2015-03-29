import javafx.scene.paint.Color;

/**
 * Created by johnsoaa on 3/29/2015.
 */
public class Meeple {


    private Color mColor;
    private Player player;

    public Meeple(Player p, Color aqua) {
        mColor = aqua;
    }

    public Color getColor() {
        return mColor;
    }

    public Player getPlayer() {
        return player;
    }
}
