import java.util.List;
import java.util.Stack;

/**
 * Created by johnsoaa on 5/7/2015.
 */
public class MockGame {

    private List<Player> players;
    private boolean gameOver;
    private boolean riverMode;
    private boolean abbotMode;
    private Stack<PlayableTile> tiles;
    private PlayableTile currentTile;
    private int currentTurn;
    private TurnState currentTurnState;

    public MockGame() {
        tiles = new Stack<PlayableTile>();
        gameOver = false;
        drawTile();
    }

}
