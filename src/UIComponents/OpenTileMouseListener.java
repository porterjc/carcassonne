package UIComponents;

import Main.GlobalVariables;
import Objects.OpenTile;
import Objects.PlayableTile;
import Objects.TileGrid;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The MouseListener used by OpenTiles
 * Created by robinsat on 5/22/2015.
 */
public class OpenTileMouseListener implements MouseListener {

    /** The OpenTile this controls */
    OpenTile tile;

    /**
     * Constructor
     * @param tile the OpenTile this controls
     */
    public OpenTileMouseListener(OpenTile tile) {
        this.tile = tile;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TileGrid grid = (TileGrid) tile.getParent();
        if(!grid.getGame().canAdjustTile())
            return;
        PlayableTile current = grid.getGame().getCurrentTile();

        if (tile.canPlace(current)) {
            grid.removeSlot(tile);
            GlobalVariables.Direction direction = tile.addTile(current);
            if (direction != null) {
                grid.addNullRow(direction);
            }
            current.addMeepleButtons(grid.getGame().getCurrentTurnPlayer(), grid.getGame().isAbbotMode());
            grid.revalidate();
            grid.repaint();

            grid.getGame().moveToNextState();

        }
    }

    // Unnecessary MouseListener Methods

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
