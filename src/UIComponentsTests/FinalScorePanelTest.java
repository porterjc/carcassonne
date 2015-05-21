package UIComponentsTests;

import UIComponents.FinalScorePanel;

import javax.swing.*;

/**
 * Tests the FinalScorePanel class
 * Created by robinsat on 5/20/2015.
 */
public class FinalScorePanelTest extends JFrame{

    /**
     * Constructor
     */
    public FinalScorePanelTest() {
        this.add(new FinalScorePanel());
    }

    public static void main(String[] args) {
        new FinalScorePanelTest();
    }
}
