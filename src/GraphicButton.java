import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by porterjc on 3/26/2015.
 */
public abstract class GraphicButton extends JPanel implements MouseListener{

    public GraphicButton(){
        super();
        this.setBackground(Color.BLUE);
        this.setSize(200, 200);
    }

    @Override
    public abstract void mouseClicked(MouseEvent e);

    @Override
    public void mousePressed(MouseEvent e) {
        //does nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //does nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //does nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //does nothing
    }
}
