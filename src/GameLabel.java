import javax.swing.*;
import java.awt.*;

/**
 * Created by robinsat on 4/12/2015.
 * All text labels should use this constructor so that they look the same
 */
public class GameLabel extends JLabel {

    public GameLabel(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.PLAIN, 24));
        this.setVerticalAlignment(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.CENTER);
    }
}
