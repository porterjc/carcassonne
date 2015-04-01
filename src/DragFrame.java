import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by porterjc on 3/27/2015.
 */
public class DragFrame extends MouseAdapter {
    private final Point p = new Point();
    private JPanel panel;

    public DragFrame(JPanel pan) {
        this.panel = pan;
    }

        public void mouseDragged(MouseEvent e) {
            JViewport vport = (JViewport)e.getSource();
            Point curp = e.getPoint();
            Point vewp = vport.getViewPosition();
            vewp.translate(p.x-curp.x, p.y-curp.y);
            panel.scrollRectToVisible(new Rectangle(vewp, vport.getSize()));
            p.setLocation(curp);
        }

        public void mousePressed(MouseEvent e)
        {
            p.setLocation(e.getPoint());
        }

        public void mouseReleased(MouseEvent e)
        {
            panel.repaint();
        }
    }
