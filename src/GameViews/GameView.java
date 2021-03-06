package GameViews;

import GameModels.GameModel;
import GUI.GameWindow;

import java.awt.*;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class GameView {
    public GameView(Image image) {
        this.image = image;
    }

    public Image getImage() {

        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    private Image image;
    public void draw(Graphics graphics, GameModel gameModel) {
        graphics.drawImage(image, gameModel.getXPixel(), gameModel.getYPixel(),
                20, 20,null);
    }
}
