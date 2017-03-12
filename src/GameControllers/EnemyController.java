package GameControllers;

import EnemyMoveStrategy.EnemyMoveBehaviour;
import EnemyMoveStrategy.EnemyMoveUpLeftBehaviour;
import GameModels.EnemyModel;
import GameModels.GameModel;
import GameViews.GameView;
import Program.GameWindow;
import Utils.Utils;

/**
 * Created by Inpriron on 3/12/2017.
 */
public class EnemyController extends GameController {
    public EnemyMoveBehaviour enemyMoveBehaviour;
    public EnemyController(GameModel gameModel, GameView gameView) {
        super(gameModel, gameView);
        enemyMoveBehaviour=new EnemyMoveUpLeftBehaviour();
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

    @Override
    public void run() {
        if(GameWindow.cycleCounter%12==0)
        {
            if(gameModel instanceof  EnemyModel)
            {
                EnemyModel model= (EnemyModel) gameModel;
                enemyMoveBehaviour.move(model,this);
            }

        }
    }
}
