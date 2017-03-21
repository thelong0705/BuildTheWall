package GameControllers;

//import EnemyMoveStrategy.EnemyMoveBehaviour;
//import EnemyMoveStrategy.EnemyMoveUpLeftBehaviour;

import EnemyMoveStrategy.*;
import GameModels.EnemyModel;
import GameModels.GameModel;
import GameViews.GameView;
import GUI.GameWindow;
import Utils.Utils;

import java.awt.*;
import java.util.Random;

/**
 * Created by Inpriron on 3/12/2017.
 */
public class EnemyController extends GameController {
    private static final int UP_LEFT = 0;
    private static final int UP_RIGHT = 1;
    private static final int DOWN_LEFT = 2;
    private static final int DOWN_RIGHT = 3;

    private EnemyMoveBehaviour enemyMoveBehaviour;

    public EnemyController(GameModel gameModel, GameView gameView) {
        super(gameModel, gameView);
        enemyMoveBehaviour = new EnemyMoveCrossBehaviour();
    }

    public EnemyController(int row, int column, int xspeed, int yspeed) {
        this(new EnemyModel(row, column, xspeed,yspeed), new GameView(Utils.loadImageFromFile("loco.png")));
    }

    @Override
    public void run() {
        enemyMoveBehaviour.move((EnemyModel)gameModel);
    }

    @Override
    public void draw(Graphics graphics) {
        EnemyModel model = (EnemyModel) gameModel;
        graphics.drawImage(gameView.getImage(), model.getX(), model.getY(),
                20, 20, null);
    }

    public int getRow()
    {
        EnemyModel model = (EnemyModel) gameModel;
        return model.getRow();

    }

    public void setEnemyMoveBehaviour(EnemyMoveBehaviour enemyMoveBehaviour) {
        this.enemyMoveBehaviour = enemyMoveBehaviour;
    }

    public int getColumn()
    {
        EnemyModel model = (EnemyModel) gameModel;
        return model.getColumn();

    }


    public EnemyMoveBehaviour getEnemyMoveBehaviour() {
        return enemyMoveBehaviour;
    }

}
