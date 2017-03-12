package GameControllers;

import GameModels.GameModel;
import GameViews.GameView;

import java.awt.*;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class GameController {
    protected GameModel gameModel;
    protected GameView gameView;

    public GameController(GameModel gameModel, GameView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;
    }
    public void draw(Graphics graphics)
    {
        gameView.draw(graphics,gameModel);
    }
    public void run()
    {

    }
}
