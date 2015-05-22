package UIComponents;

import Main.GlobalVariables;
import Main.TileFactory;
import Objects.Game;
import Objects.PlayableTile;
import Objects.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by porterjc on 3/26/2015.
 * This is the main frame that will appear and render all GUI components
 */
public class MainFrame extends JFrame {

    private JPanel backPanel;
    private JPanel optionsPanel;

    private int screenWidth;
    private int screenHeight;

    // Some graphic constants
    private final int buttonWidth = 400;
    private final int buttonHeight = 50;

    private final int largeMargin = 25;

    /**
     * Constructor
     */
    public MainFrame() {
        super();
        //this.setUndecorated(true);
        GraphicsDevice graphD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        graphD.setFullScreenWindow(this);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setupMainPanel();
        setupMainMenu();
        setVisible(true);
        screenWidth = this.getWidth();
        screenHeight = this.getHeight();

    }

    /**
     * Sets up the back panel for displaying the main menu, rules, and options
     */
    private void setupMainPanel() {

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

        GraphicButton newGameButton = new GraphicButton(buttonWidth, buttonHeight, "New Game") {
            @Override
            public void mouseClicked(MouseEvent e) {
                setupGameOptions();
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

        RulesPanel rulesPanel = new RulesPanel(optionsPanel.getWidth() - 50);
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
        int smallMargin = 10;
        optionsPanel.removeAll();

        optionsPanel.add(getMarginArea(largeMargin));

        // This lets the user choose the color of meeple they want to use
        GameLabel colorLabel = new GameLabel("Choose a color:");
        colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(colorLabel);
        optionsPanel.add(getMarginArea(smallMargin));

        final SelectPanel meepleSelectPanel = new SelectPanel();
        SelectableButton redbutton = createMeepleButton(GlobalVariables.PlayerColor.RED, meepleSelectPanel);
        meepleSelectPanel.add(redbutton);
        meepleSelectPanel.add(createMeepleButton(GlobalVariables.PlayerColor.YELLOW, meepleSelectPanel));
        meepleSelectPanel.add(createMeepleButton(GlobalVariables.PlayerColor.GREEN, meepleSelectPanel));
        meepleSelectPanel.add(createMeepleButton(GlobalVariables.PlayerColor.BLUE, meepleSelectPanel));
        meepleSelectPanel.add(createMeepleButton(GlobalVariables.PlayerColor.BLACK, meepleSelectPanel));

        meepleSelectPanel.setSelected(redbutton);

        optionsPanel.add(meepleSelectPanel);
        optionsPanel.add(getMarginArea(largeMargin));

        GameLabel rulesLabel = new GameLabel("Expansions:");
        rulesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(rulesLabel);
        optionsPanel.add(getMarginArea(smallMargin));

       // final GraphicCheckBox riverCheckBox = new GraphicCheckBox("River");
        final GraphicCheckBox abbotCheckBox = new GraphicCheckBox("Abbot");
        /*SelectPanel rulesPanel = new SelectPanel();
        rulesPanel.add(riverCheckBox);
        rulesPanel.add(Box.createRigidArea(new Dimension(20, 10)));
        rulesPanel.add(abbotCheckBox);*/
        optionsPanel.add(abbotCheckBox);
        optionsPanel.add(getMarginArea(largeMargin));


        GraphicButton startButton = new GraphicButton(buttonWidth, buttonHeight, "Start Game") {
            @Override
            public void mouseClicked(MouseEvent e) {
                setupGamePlay(meepleSelectPanel.getAllSelected(), false, abbotCheckBox.isChecked());
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

    private SelectableButton createMeepleButton(GlobalVariables.PlayerColor color, SelectPanel panel) {
        return new SelectableButton(100, 100, color, panel);
    }


    /**
     * Starts a new game and sets up the screen to play the game
     */
    private void setupGamePlay(ArrayList<SelectableButton> playerColors, boolean river, boolean abbot){
        backPanel.removeAll();
        this.remove(backPanel);
        revalidate();
        repaint();

        BorderLayout bl = new BorderLayout();
        JPanel mainpanel = new JPanel(bl);

        this.add(mainpanel);

        BottomDisplay bottompanel = new BottomDisplay(screenWidth);
        mainpanel.add(bottompanel, BorderLayout.SOUTH);

        ArrayList<Player> players = new ArrayList<>();
        for(SelectableButton player : playerColors){
            players.add(new Player(player.getColor()));
        }

        Stack<PlayableTile> tiles;
        if(river) {
            tiles = TileFactory.getRiverTiles();
        } else {
            tiles = TileFactory.loadDeck();
        }
        TileGrid boardDisplay = new TileGrid(screenWidth, screenHeight - 200);

        Game game = new Game(bottompanel, tiles, players, river, abbot);
        if(river)
            boardDisplay.initialize(TileFactory.getRiverStart());
        else
            boardDisplay.initialize(TileFactory.getStartTile());
        boardDisplay.setGame(game);

        JScrollPane scrollBoard = new JScrollPane(boardDisplay, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        bottompanel.initializeLabels(game);
        mainpanel.add(bottompanel, BorderLayout.SOUTH);
        mainpanel.add(scrollBoard, BorderLayout.CENTER);

        DragFrame scroller = new DragFrame(boardDisplay);
        scrollBoard.getViewport().addMouseMotionListener(scroller);
        scrollBoard.getViewport().addMouseListener(scroller);

        revalidate();
        repaint();
    }


    // Private classes needed just for the Main.Main Frame

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