import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by porterjc on 3/26/2015.
 */
public class MainFrame extends JFrame {

    private JPanel mainpanel;
    private JPanel bottompanel;
    private JPanel optionsPanel;
    private JPanel scoretable;
    private JPanel tilesLeftPanel;
    private JPanel tiledisplay;
    private JPanel boardDisplay;
    private JScrollPane scrollBoard;
    private BufferedImage pic;
    private ArrayList<PlayableTile> tiles;

    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;
    private final int COMPONENT_MARGIN = 20;

    public MainFrame() {
        super();

        //this.setUndecorated(true);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        GraphicsDevice graphD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        graphD.setFullScreenWindow(this);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setupMainMenu();
        setupGameOptions();
        setVisible(true);
    }

    private void setupMainMenu() {
        JPanel backPanel = new JPanel();
        backPanel.setBackground(new Color(39, 40, 49));
        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
        this.add(backPanel);
        backPanel.add(Box.createRigidArea(new Dimension(600, 100)));

        optionsPanel = new JPanel();
        optionsPanel.setBackground(new Color(100, 105, 153));
        optionsPanel.setMaximumSize(new Dimension(600, 800));
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        backPanel.add(optionsPanel);

    }

    /**
     * This sets up the UI to allow a user to set up the options for a game they're going to start
     */
    private void setupGameOptions() {
        int buttonWidth = 400;
        int buttonHeight = 50;

        int smallMargin = 15;
        int largeMargin = 60;

        optionsPanel.add(getMarginArea(largeMargin));

        // This lets the user choose the color of meeple they want to use
        GameLabel colorLabel = new GameLabel("Choose a color:");
        colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(colorLabel);
        optionsPanel.add(getMarginArea(smallMargin));

        SelectPanel meepleSelectPanel = new SelectPanel();
        MeepleButton redbutton = new MeepleButton(Color.RED, meepleSelectPanel);
        meepleSelectPanel.add(redbutton);
        meepleSelectPanel.add(new MeepleButton(Color.YELLOW, meepleSelectPanel));
        meepleSelectPanel.add(new MeepleButton(Color.GREEN, meepleSelectPanel));
        meepleSelectPanel.add(new MeepleButton(Color.BLUE, meepleSelectPanel));
        meepleSelectPanel.add(new MeepleButton(Color.BLACK, meepleSelectPanel));

        meepleSelectPanel.setSelected(redbutton);
        redbutton.select();

        optionsPanel.add(meepleSelectPanel);
        optionsPanel.add(getMarginArea(largeMargin));

        // This lets the user choose how many computer players they want to play against
        GameLabel playersLabel = new GameLabel("Choose a number of opponents:");
        playersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(playersLabel);
        optionsPanel.add(getMarginArea(smallMargin));

        SelectPanel playersSelectPanel = new SelectPanel();
        SelectableButton button1 = new SelectableButton(50, 50, "1", playersSelectPanel);
        playersSelectPanel.add(button1);
        playersSelectPanel.add(Box.createRigidArea(new Dimension(20, 80)));
        playersSelectPanel.add(new SelectableButton(50, 50, "2", playersSelectPanel));
        playersSelectPanel.add(Box.createRigidArea(new Dimension(20, 80)));
        playersSelectPanel.add(new SelectableButton(50, 50, "3", playersSelectPanel));
        playersSelectPanel.add(Box.createRigidArea(new Dimension(20, 80)));
        playersSelectPanel.add(new SelectableButton(50, 50, "4", playersSelectPanel));
        optionsPanel.add(playersSelectPanel);
        optionsPanel.add(getMarginArea(largeMargin));


        GraphicButton startButton = new GraphicButton(buttonWidth, buttonHeight, "Start Game") {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Button Pressed");
                setupGamePlay();
            }
        };

        optionsPanel.add(startButton);
    }

    /**
     * Creates a margin component
     * @return a margin component
     */
    private Component getMarginArea(int height) {
        return Box.createRigidArea(new Dimension(400, height));
    }

    private void setupGamePlay(){
        this.removeAll();
        BorderLayout bl = new BorderLayout();
        mainpanel = new JPanel(bl);
        mainpanel.setBackground(new Color(39, 40, 49));
        this.add(mainpanel);

        TileGrid tileGrid = new TileGrid();
        mainpanel.add(tileGrid, BorderLayout.CENTER);

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

        boardDisplay = new JPanel();
        boardDisplay.setSize(SCREEN_WIDTH, SCREEN_HEIGHT - 200);

        scrollBoard = new JScrollPane(boardDisplay, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollBoard.setSize(SCREEN_WIDTH, SCREEN_HEIGHT - 200);
        boardDisplay.add(new TileGrid());

        

        mainpanel.add(scrollBoard, BorderLayout.CENTER);

        DragFrame scroller = new DragFrame(boardDisplay);
        scrollBoard.getViewport().addMouseMotionListener(scroller);
        scrollBoard.getViewport().addMouseListener(scroller);

        JPanel t2 = new JPanel();
        t2.setPreferredSize(new Dimension(400, 400));
        t2.setBackground(Color.MAGENTA);
        bottompanel.add(tiledisplay);
        bottompanel.add(t2);

        //QuitButton quitButton = new QuitButton();
       // mainpanel.add(bottompanel);
    }

    /*
    private class QuitButton extends GraphicButton {

        public QuitButton(int width, int height) {
            super(width, height);
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    } */


    private class MeepleButton extends SelectableButton {

        Color color;

        public MeepleButton(Color color, SelectPanel panel) {
            super(100, 100, panel);
            this.color = color;
            this.setBackground(color);
        }


    }


}
