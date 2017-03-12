package GameControllers;

import GameModels.EnemyModel;
import GameModels.GameModel;
import GameViews.GameView;
import Program.GameWindow;
import Utils.Utils;

/**
 * Created by Inpriron on 3/12/2017.
 */
public class EnemyController extends GameController {
    public EnemyController(GameModel gameModel, GameView gameView) {
        super(gameModel, gameView);
    }
    public EnemyController(int row, int column)
    {
        this( new EnemyModel(row,column), new GameView(Utils.loadImageFromFile("loco.png")));
    }
    public int getRow()
    {
        return this.gameModel.getRow();
    }
    public int getCol()
    {
        return this.gameModel.getColumn();
    }
}
