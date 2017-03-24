package GUI;

import Program.Main;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Inpriron on 3/17/2017.
 */
public class MenuPanel extends JPanel {
    private JLabel btnStart;
    private JLabel btnExit;
    public MenuPanel() {
        setLayout(null);
        initComp();
    }

    private void initComp() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (mouseEvent.getSource().equals(btnStart)) {
                    Main.gameFrame.setVisible(false);
                    GameWindow gameWindow= new GameWindow();
                }
                if (mouseEvent.getSource().equals(btnExit)) {
                    System.exit(0);
                }
            }
        };

        ImageIcon icon = new ImageIcon("resources/start_en.png");
        btnStart = new JLabel(icon);
        btnStart.setBounds((GameWindow.FRAME_WIDTH_SIZE - icon.getIconWidth()) / 2 , 400, icon.getIconWidth(), icon.getIconHeight());
        btnStart.setFocusable(false);
        add(btnStart);
        btnStart.addMouseListener(mouseAdapter);

        icon = new ImageIcon("resources/exit_btn.png");
        btnExit = new JLabel(icon);
        btnExit.setBounds((GameWindow.FRAME_WIDTH_SIZE - icon.getIconWidth()) / 2 , btnStart.getY() + btnStart.getHeight() + 20, icon.getIconWidth(), icon.getIconHeight());
        btnExit.setFocusable(false);
        add(btnExit);
        btnExit.addMouseListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Image image = Utils.loadImageFromFile("menu.png");
        graphics.drawImage(image, 0, 0, GameWindow.FRAME_WIDTH_SIZE, GameWindow.FRAME_HEIGHT_SIZE, null);
    }
}
