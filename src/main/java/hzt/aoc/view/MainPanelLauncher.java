package hzt.aoc.view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainPanelLauncher {

    public void demo() {
        JFrame frame = new JFrame("Advent of code");
        JPanel overallPanel = buildOverallPanel();
        frame.getContentPane().add(overallPanel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(840, 420));
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
    }

    private JPanel buildOverallPanel() {
        JPanel overallPanel = new JPanel(new BorderLayout());
        overallPanel.add(new ControlPanel(), BorderLayout.WEST);
        JPanel mainPanel = new JPanel(new GridLayout(1, 0, 10, 10));
        overallPanel.add(mainPanel, BorderLayout.CENTER);
        return overallPanel;
    }
}
