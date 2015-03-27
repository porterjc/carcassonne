import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by porterjc on 3/26/2015.
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        super();
        GraphicsDevice graphD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        //graphD.setFullScreenWindow(this);
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        setupScreen();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setupScreen(){
        GridDisplay gd = new GridDisplay();
        this.add(gd, BorderLayout.CENTER);
        QuitButton quitButton = new QuitButton();
        this.add(quitButton, BorderLayout.SOUTH);
    }

    private class QuitButton extends GraphicButton {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    }

}
