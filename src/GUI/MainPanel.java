package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Inpriron on 3/17/2017.
 */
public class MainPanel extends JPanel {
    public static final String TAG_MENU = "tag_menu";
    public static final String TAG_GAME = "tag_game";
    public static final String TAG_WIN = "tag_win";
    public static final String TAG_LOSE="tag_lose";
    private CardLayout cardLayout;
    private GameWindow gameWindow;
    private MenuPanel menuPanel;
    private WinPanel winPanel;
    private LosePanel losePanel;
//    private GameOverPanel gameOverPanel;
    public MainPanel()
    {
        cardLayout= new CardLayout();
        setLayout(cardLayout);
        menuPanel= new MenuPanel();
        add(menuPanel,TAG_MENU);
    }
    public void showPanel(String tag) {
        if (tag.equals(TAG_WIN)) {

            winPanel = new WinPanel();
            add(winPanel, tag);
            cardLayout.show(this, tag);

        } else if (tag.equals(TAG_LOSE)) {
             losePanel= new LosePanel();
            add(losePanel, TAG_LOSE);
            cardLayout.show(this, tag);
        } else {
            cardLayout.show(this, tag);
        }
    }
}
