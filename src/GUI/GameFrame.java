package GUI;

import Program.GameWindow;

import javax.swing.*;

/**
 * Created by Inpriron on 3/17/2017.
 */

public class GameFrame extends JFrame {
    public static final int WIDTH_F = GameWindow.FRAME_WIDTH_SIZE;
    public static final int HEIGHT_F = GameWindow.FRAME_HEIGHT_SIZE;
    public static MainPanel mainPanel;

    public GameFrame() {
        setSize(WIDTH_F, HEIGHT_F);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPanel = new MainPanel();
        add(mainPanel);
        setVisible(true);
    }

}

