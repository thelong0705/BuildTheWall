package GUI;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class InstructionPanel extends JPanel {
    private JLabel btnCombatFinish;

    public InstructionPanel() {
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

        ImageIcon icon = new ImageIcon(Utils.loadImageFromFile("back_to_menu.png"));
        btnCombatFinish = new JLabel(icon);
        btnCombatFinish.setBounds((GameFrame.WIDTH_F - icon.getIconWidth()) / 2, 515, icon.getIconWidth(), icon.getIconHeight());
        btnCombatFinish.setFocusable(false);
        add(btnCombatFinish);
        btnCombatFinish.addMouseListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Image image = Utils.loadImageFromFile("ins.png");
        graphics.drawImage(image, 0, 0, GameFrame.WIDTH_F, GameFrame.HEIGHT_F, null);

    }
}
