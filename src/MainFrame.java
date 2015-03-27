import javax.swing.*;
import java.awt.*;

/**
 * Created by porterjc on 3/26/2015.
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        super();
        GraphicsDevice graphD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        graphD.setFullScreenWindow(this);
        setupScreen();
        setVisible(true);
    }

    private void setupScreen(){

    }

}
