package GUI;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Inpriron on 3/28/2017.
 */
public class LosePanel extends JPanel {
    private JLabel btnCombatFinish;

    public LosePanel() {
        setLayout(null);
        initComp();
    }

    private void initComp() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (mouseEvent.getSource().equals(btnCombatFinish)) {
                    GameFrame.mainPanel.showPanel(MainPanel.TAG_MENU);
                }
            }
        };

        ImageIcon icon = new ImageIcon(Utils.loadImageFromFile("back-1.png"));
        btnCombatFinish = new JLabel(icon);
        btnCombatFinish.setBounds((GameFrame.WIDTH_F - icon.getIconWidth()) / 2, 515, icon.getIconWidth(), icon.getIconHeight());
        btnCombatFinish.setFocusable(false);
        add(btnCombatFinish);
        btnCombatFinish.addMouseListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Image image = Utils.loadImageFromFile("download.jpg");
        graphics.drawImage(image, 0, 0, GameFrame.WIDTH_F, GameFrame.HEIGHT_F, null);

    }
}
