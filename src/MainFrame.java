import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

/**
 * Created by porterjc on 3/26/2015.
 */
public class MainFrame extends JFrame {

    private JPanel mainpanel;
    private JPanel bottompanel;
    private JPanel scoretable;
    private JPanel tilesLeftPanel;
    private JPanel tiledisplay;

    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;
    private final int COMPONENT_MARGIN = 20;

    public MainFrame() {
        super();

        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        //GraphicsDevice graphD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        //graphD.setFullScreenWindow(this);
        setupScreen();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setupScreen(){

        BorderLayout bl = new BorderLayout();
        mainpanel = new JPanel(bl);
        mainpanel.setBackground(new Color(39, 40, 49));
        this.add(mainpanel);

        JPanel tilepanel = new JPanel();
        tilepanel.setBackground(Color.WHITE);
        mainpanel.add(tilepanel, BorderLayout.CENTER);

        JPanel bottomouter = new JPanel(new BorderLayout());
        bottomouter.setBackground(new Color(39, 40, 49));
        bottomouter.setPreferredSize(new Dimension(SCREEN_WIDTH, 200));
        bottomouter.setBorder(BorderFactory.createRaisedBevelBorder());
        JPanel jp1 = new JPanel();
        jp1.setOpaque(false);
        JPanel jp2 = new JPanel();
        jp2.setOpaque(false);
        JPanel jp3 = new JPanel();
        jp3.setOpaque(false);
        JPanel jp4 = new JPanel();
        jp4.setOpaque(false);
        bottomouter.add(jp1, BorderLayout.EAST);
        bottomouter.add(jp2, BorderLayout.WEST);
        bottomouter.add(jp3, BorderLayout.NORTH);
        bottomouter.add(jp4, BorderLayout.SOUTH);

        bottompanel = new JPanel();
        bottompanel.setLayout(new BoxLayout(bottompanel, BoxLayout.X_AXIS));
        //bottompanel.setPreferredSize(new Dimension(SCREEN_WIDTH - COMPONENT_MARGIN, 200 - COMPONENT_MARGIN));
        bottompanel.setBorder(BorderFactory.createLoweredBevelBorder());
        mainpanel.add(bottomouter, BorderLayout.SOUTH);
        bottomouter.add(bottompanel, BorderLayout.CENTER);



        tiledisplay = new JPanel();
        tiledisplay.setSize(400, 400);
        tiledisplay.setBackground(Color.GREEN);

        DragFrame scroller = new DragFrame(tiledisplay);
        JScrollPane.getViewport().addMouseMotionListener(scroller);
        JScrollPane.getViewport().addMouseListener(scroller);

        JPanel t2 = new JPanel();
        t2.setPreferredSize(new Dimension(400, 400));
        t2.setBackground(Color.MAGENTA);
        bottompanel.add(tiledisplay);
        bottompanel.add(t2);

        //QuitButton quitButton = new QuitButton();
       // mainpanel.add(bottompanel);
    }

    private class QuitButton extends GraphicButton {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    }

    private class TilePanel extends JPanel {

    }

}
