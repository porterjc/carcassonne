package UIComponents;

import Main.GlobalVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A versatile panel to read button presses and render in a variety of different styles
 * Created by porterjc on 3/26/2015.
 */
public abstract class GraphicButton extends JPanel implements MouseListener{

    public GraphicButton(int width, int height) {
        super();
        this.setBackground(GlobalVariables.LIGHT_BLUE);
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.addMouseListener(this);
    }

    public GraphicButton(int width, int height, String text) {
        this(width, height);
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.add(new GameLabel(text));
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
