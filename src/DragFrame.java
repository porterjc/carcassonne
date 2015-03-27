import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by porterjc on 3/27/2015.
 */
public class DragFrame extends JComponent {
    protected Point anchor;

    public DragFrame() {
        addListeners();
    }

    public void addListeners() {
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                anchor = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = anchor.x;
                int y = anchor.y;

                Point parentLocation = getParent().getLocationOnScreen();
                Point mouseLocation = e.getLocationOnScreen();
                Point position = new Point (mouseLocation.x - parentLocation.x - x,
                        mouseLocation.y - parentLocation.y - y);
                setLocation(position);
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0,0,getWidth(),getHeight());
        }
    }

    private void removeListeners() {
        for (MouseMotionListener listener : this.getMouseMotionListeners()) {
            removeMouseMotionListener(listener);
        }
    }
}
