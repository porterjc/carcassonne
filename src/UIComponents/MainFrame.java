package UIComponents;

import Main.GlobalVariables;
import Main.TileFactory;
import Objects.Game;
import Objects.PlayableTile;
import Objects.Player;
import Objects.TileGrid;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by porterjc on 3/26/2015.
 * This is the main frame that will appear and render all GUI components
 */
public class MainFrame extends JFrame {

    private JPanel mainpanel;
    private JPanel bottompanel;
    private JPanel backPanel;
    private JPanel optionsPanel;
    private ScorePanel scorePanel;
    private JLabel tilesLeftLabel;
    private JLabel tiledisplay;
    private JLabel currentPlayer;
    private TileGrid boardDisplay;
    private JScrollPane scrollBoard;
    private BufferedImage pic;
    private ArrayList<PlayableTile> tiles;

    private int screenWidth;
    private int screenHeight;

    // Some graphic constants
    private final int COMPONENT_MARGIN = 20;

    private final int buttonWidth = 400;
    private final int buttonHeight = 50;

    private final int smallMargin = 10;
    private final int largeMargin = 25;

    public MainFrame() {
        super();

        //this.setUndecorated(true);
        GraphicsDevice graphD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        graphD.setFullScreenWindow(this);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setupMainPanel();
        setupMainMenu();
       // setupGamePlay();
        setVisible(true);
        screenWidth = this.getWidth();
        screenHeight = this.getHeight();

    }

    private void setupMainPanel() {

       // JPanel backPanel;
        try {
            BufferedImage background = ImageIO.read(new File("images/background.png"));
            backPanel = new TiledImagePanel(background);
        } catch (IOException e) {
            e.printStackTrace();
            backPanel = new JPanel();
            backPanel.setBackground(GlobalVariables.DARK_BLUE);
        }

        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
        this.add(backPanel);

        try {
            Image logo = ImageIO.read(new File("images/logo.png"));
            //Image resizedLogo = logo.getScaledInstance(700, 162, Image.SCALE_DEFAULT);
            JLabel logoLabel = new JLabel(new ImageIcon(logo));
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            backPanel.add(logoLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        optionsPanel = new JPanel();
        optionsPanel.setBackground(GlobalVariables.MEDIUM_BLUE);
        optionsPanel.setMaximumSize(new Dimension(600, 650));
        optionsPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        backPanel.add(optionsPanel);
        backPanel.add(getMarginArea(largeMargin));

    }

    /**
     * Sets up the main menu
     */
    private void setupMainMenu() {
        optionsPanel.removeAll();

        GraphicButton newGameButton = new GraphicButton(buttonWidth, buttonHeight, "New Objects.Game") {
            @Override
            public void mouseClicked(MouseEvent e) {
                //setupGameOptions();
                setupGamePlay();
            }
        };

        GraphicButton rulesButton = new GraphicButton(buttonWidth, buttonHeight, "Rules") {

            @Override
            public void mouseClicked(MouseEvent e) {
                setupRuleReader();
            }
        };

        GraphicButton quitButton = new GraphicButton(buttonWidth, buttonHeight, "Quit") {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        };

        optionsPanel.add(getMarginArea(largeMargin));
        optionsPanel.add(newGameButton);
        optionsPanel.add(getMarginArea(largeMargin));
        optionsPanel.add(rulesButton);
        optionsPanel.add(getMarginArea(largeMargin));
        optionsPanel.add(quitButton);

        revalidate();
        repaint();
    }

    /**
     * This sets up the UI to display the rules of the game.
     */
    private void setupRuleReader() {
        optionsPanel.removeAll();

        RulesPanel rulesPanel = new RulesPanel(optionsPanel.getWidth() - COMPONENT_MARGIN);
        JScrollPane scrollPane = new JScrollPane(rulesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(new Dimension(optionsPanel.getWidth() - 50, 100));
        optionsPanel.add(getMarginArea(largeMargin));
        optionsPanel.add(scrollPane);
        optionsPanel.add(getMarginArea(largeMargin));

        GraphicButton backButton = new GraphicButton(buttonWidth, buttonHeight, "Back") {
            @Override
            public void mouseClicked(MouseEvent e) {
                setupMainMenu();
            }
        };
        optionsPanel.add(backButton);
        optionsPanel.add(getMarginArea(largeMargin));

        revalidate();
        repaint();
    }

    /**
     * This sets up the UI to allow a user to set up the options for a game they're going to start
     */
    private void setupGameOptions() {
        optionsPanel.removeAll();

        optionsPanel.add(getMarginArea(largeMargin));

        // This lets the user choose the color of meeple they want to use
        GameLabel colorLabel = new GameLabel("Choose a color:");
        colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(colorLabel);
        optionsPanel.add(getMarginArea(smallMargin));

        SelectPanel meepleSelectPanel = new SelectPanel();
        MeepleButton redbutton = new MeepleButton(GlobalVariables.PlayerColor.RED, meepleSelectPanel);
        meepleSelectPanel.add(redbutton);
        meepleSelectPanel.add(new MeepleButton(GlobalVariables.PlayerColor.YELLOW, meepleSelectPanel));
        meepleSelectPanel.add(new MeepleButton(GlobalVariables.PlayerColor.GREEN, meepleSelectPanel));
        meepleSelectPanel.add(new MeepleButton(GlobalVariables.PlayerColor.BLUE, meepleSelectPanel));
        meepleSelectPanel.add(new MeepleButton(GlobalVariables.PlayerColor.BLACK, meepleSelectPanel));

        meepleSelectPanel.setSelected(redbutton);

        optionsPanel.add(meepleSelectPanel);
        optionsPanel.add(getMarginArea(largeMargin));

        // This lets the user choose how many computer players they want to play against
        GameLabel playersLabel = new GameLabel("Choose number of players:");
        playersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(playersLabel);
        optionsPanel.add(getMarginArea(smallMargin));

        SelectPanel playersSelectPanel = new SelectPanel();
        SelectableButton button1 = new SelectableButton(50, 50, "2", playersSelectPanel);
        playersSelectPanel.add(button1);
        playersSelectPanel.add(Box.createRigidArea(new Dimension(20, 80)));
        playersSelectPanel.add(new SelectableButton(50, 50, "3", playersSelectPanel));
        playersSelectPanel.add(Box.createRigidArea(new Dimension(20, 80)));
        playersSelectPanel.add(new SelectableButton(50, 50, "4", playersSelectPanel));
        playersSelectPanel.add(Box.createRigidArea(new Dimension(20, 80)));
        playersSelectPanel.add(new SelectableButton(50, 50, "5", playersSelectPanel));
        playersSelectPanel.setSelected(button1);
        optionsPanel.add(playersSelectPanel);
        optionsPanel.add(getMarginArea(largeMargin));

        // This lets the user specify additional rules
        GameLabel rulesLabel = new GameLabel("Expansions:");
        rulesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(rulesLabel);
        optionsPanel.add(getMarginArea(smallMargin));

        SelectPanel rulesPanel = new SelectPanel();
        rulesPanel.add(new GraphicCheckBox("River"));
        rulesPanel.add(Box.createRigidArea(new Dimension(20, 10)));
        rulesPanel.add(new GraphicCheckBox("Abbot"));
        optionsPanel.add(rulesPanel);
        optionsPanel.add(getMarginArea(largeMargin));


        GraphicButton startButton = new GraphicButton(buttonWidth, buttonHeight, "Start Objects.Game") {
            @Override
            public void mouseClicked(MouseEvent e) {
                setupGamePlay();
            }
        };
        optionsPanel.add(startButton);
        optionsPanel.add(getMarginArea(largeMargin));

        GraphicButton cancelButton = new GraphicButton(buttonWidth, buttonHeight, "Cancel") {
            @Override
            public void mouseClicked(MouseEvent e) {
                setupMainMenu();
            }
        };
        optionsPanel.add(cancelButton);

        revalidate();
        repaint();

    }

    /**
     * Creates a margin component
     * @return a margin component
     */
    private Component getMarginArea(int height) {
        return Box.createRigidArea(new Dimension(400, height));
    }


    /**
     * Starts a new game and sets up the screen to play the game
     * TODO: none of the "options" do anything and this initializes the game with defaults. Make this happen eventually
     */
    private void setupGamePlay(){
        backPanel.removeAll();
        this.remove(backPanel);
        revalidate();
        repaint();

        BorderLayout bl = new BorderLayout();
        mainpanel = new JPanel(bl);

        this.add(mainpanel);

        bottompanel = new JPanel();
        bottompanel.setBackground(GlobalVariables.DARK_BLUE);
        bottompanel.setLayout(new BoxLayout(bottompanel, BoxLayout.X_AXIS));
        bottompanel.setPreferredSize(new Dimension(screenWidth, 200));
        //bottompanel.setPreferredSize(new Dimension(SCREEN_WIDTH - COMPONENT_MARGIN, 200 - COMPONENT_MARGIN));
        bottompanel.setBorder(new CompoundBorder(new EmptyBorder(22, 20, 20, 20), BorderFactory.createLoweredBevelBorder()));
        mainpanel.add(bottompanel, BorderLayout.SOUTH);

        boardDisplay = new TileGrid(screenWidth, screenHeight - 200);
        boardDisplay.initialize(TileFactory.getStartTile());
        boardDisplay.getGame().passTiles(TileFactory.loadDeck());

        scrollBoard = new JScrollPane(boardDisplay, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        mainpanel.add(scrollBoard, BorderLayout.CENTER);

        GraphicButton rotateLabel = new GraphicButton(100, 100) {
            @Override
            public void mouseClicked(MouseEvent e) {
                PlayableTile current = boardDisplay.getGame().getCurrentTile();
                current.rotateTile();
                updateTile();

            }
        };

        rotateLabel.setBackground(Color.RED);
        rotateLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        bottompanel.add(Box.createRigidArea(new Dimension(20, 20)));
        bottompanel.add(rotateLabel);
        bottompanel.add(Box.createRigidArea(new Dimension(20, 20)));

        tiledisplay = new JLabel(boardDisplay.getGame().getCurrentTile().getIcon());
        bottompanel.add(tiledisplay);
        bottompanel.add(Box.createRigidArea(new Dimension(20, 20)));

        JPanel tilesLeftPanel = new JPanel();
        tilesLeftPanel.setLayout(new BoxLayout(tilesLeftPanel, BoxLayout.Y_AXIS));
        tilesLeftPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
        JLabel tilesLabel = new GameLabel("Tiles Left:");
        tilesLabel.setAlignmentX(CENTER_ALIGNMENT);
        tilesLeftLabel = new GameLabel(boardDisplay.getGame().getNumberOfTilesLeft() + "");
        tilesLeftLabel.setForeground(Color.RED);
        tilesLeftLabel.setFont(new Font(tilesLeftLabel.getFont().getName(), Font.BOLD, 64));
        tilesLeftLabel.setAlignmentX(CENTER_ALIGNMENT);
        tilesLeftPanel.add(tilesLabel);
        tilesLeftPanel.add(tilesLeftLabel);
        bottompanel.add(tilesLeftPanel);


        GameLabel statusLabel = new GameLabel("RED: Place a tile");
        GraphicButton moveOnButton = new GraphicButton(100, 100) {
            @Override
            public void mouseClicked(MouseEvent e) {
                boardDisplay.getGame().moveToNextState();
            }
        };
        moveOnButton.setBackground(Color.RED);
        moveOnButton.setBorder(BorderFactory.createRaisedBevelBorder());
        bottompanel.add(Box.createRigidArea(new Dimension(20, 20)));
        bottompanel.add(moveOnButton);
        bottompanel.add(Box.createRigidArea(new Dimension(20, 20)));

        boardDisplay.setTileLabels(tiledisplay, tilesLeftLabel, moveOnButton);

        bottompanel.add(Box.createRigidArea(new Dimension(20, 20)));
        // Just for GUI testing. TODO: delete
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(GlobalVariables.PlayerColor.RED));
        players.add(new Player(GlobalVariables.PlayerColor.YELLOW));
        players.add(new Player(GlobalVariables.PlayerColor.GREEN));
        scorePanel = new ScorePanel(players);
        bottompanel.add(scorePanel);

        DragFrame scroller = new DragFrame(boardDisplay);
        scrollBoard.getViewport().addMouseMotionListener(scroller);
        scrollBoard.getViewport().addMouseListener(scroller);

        revalidate();
        repaint();
    }

    private Game getGame() {
        return this.boardDisplay.getGame();
    }

    /**
     * Updates the GUI to show the tile that has just been drawn
     */
    public void updateTile() {

        tiledisplay.setIcon(getGame().getCurrentTile().getIcon());

    }


    // Private classes needed just for the Main.Main Frame

    /**
     * This class provides a selectable button that holds a color
     */
    private class MeepleButton extends SelectableButton {

        GlobalVariables.PlayerColor color;

        public MeepleButton(GlobalVariables.PlayerColor color, SelectPanel panel) {
            super(100, 100, panel);
            this.color = color;
            this.setBackground(color.getColor());
        }
        //TODO add button listener?

    }

    /**
     * This class provides a panel that tiles an image as a background
     */
    private class TiledImagePanel extends JPanel {

        BufferedImage image;

        public TiledImagePanel(BufferedImage image) {
            this.image = image;
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(new TexturePaint(image, new Rectangle(400, 400)));
            g2d.fill(this.getBounds());
        }
    }

}