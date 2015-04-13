import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by robinsat on 4/12/2015.
 * This class gives the functionality of a check box but it looks prettier
 */
public class GraphicCheckBox extends JPanel {

    private CheckPanel checkPanel;

    /**
     * Constructor
     * @param text the text for this label
     */
    public GraphicCheckBox(String text) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setOpaque(false);
        checkPanel = new CheckPanel();
        this.add(checkPanel);
        this.add(Box.createRigidArea(new Dimension(10, 10)));
        this.add(new GameLabel(text));

    }

    /**
     * Returns true if this checkbox is checked
     * @return true if this checkbox is checked
     */
    public boolean isChecked() {
        return this.checkPanel.isChecked();
    }

    /**
     * A visual check box display
     */
    private class CheckPanel extends JPanel implements MouseListener {

        private boolean checked;

        private final Color CHECKED = new Color(39, 40, 49);
        private final Color MOUSEOVER_LIGHT = new Color(167, 171, 209);
        private final Color MOUSEOVER_DARK = new Color(55, 55, 80);

        public CheckPanel() {
            this.setMaximumSize(new Dimension(30, 30));
            this.setBackground(Color.WHITE);
            this.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createLineBorder(Color.WHITE, 2)));
            this.addMouseListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(this.checked) {
                this.checked = false;
                this.setBackground(MOUSEOVER_LIGHT);
            }
            else {
                this.checked = true;
                this.setBackground(MOUSEOVER_DARK);
            }


        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(checked)
                this.setBackground(MOUSEOVER_DARK);
            else
                this.setBackground(MOUSEOVER_LIGHT);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            showStatus();
        }

        /**
         * Updates the panel
         */
        public void showStatus() {
            if(checked)
                showChecked();
            else
                showUnchecked();
        }

        /**
         * Display the panel as "checked"
         */
        public void showChecked() {
            this.setBackground(CHECKED);
        }

        /**
         * Display the panel as "uncheked"
         */
        public void showUnchecked() {
            this.setBackground(Color.WHITE);
        }

        /**
         * Determines whether this box is checked
         * @return whether this box is checked
         */
        public boolean isChecked() {
            return checked;
        }
    }
}
