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
        enemyMoveBehaviour = getRanDomMoveBehavior();
    }

    public EnemyController(int row, int column, int speed) {
        this(new EnemyModel(row, column, speed), new GameView(Utils.loadImageFromFile("loco.png")));
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
    public EnemyMoveBehaviour getRanDomMoveBehavior() {
        Random random = new Random();
        EnemyMoveBehaviour enemyMoveBehaviour = null;
        switch (random.nextInt(4)) {
            case UP_LEFT:
//                System.out.println("0");
                enemyMoveBehaviour = new EnemyMoveUpLeftBehaviour();
                break;
            case UP_RIGHT:
//                System.out.println("1");
                enemyMoveBehaviour = new EnemyMoveUpRightBehaviour();
                break;
            case DOWN_LEFT:
//                System.out.println("2");
                enemyMoveBehaviour = new EnemyMoveDownLeftBehaviour();
                break;
            case DOWN_RIGHT:
//                System.out.println("3");
                enemyMoveBehaviour = new EnemyMoveDownRightBehaviour();
                break;
        }
        return enemyMoveBehaviour;
    }

    public EnemyMoveBehaviour getEnemyMoveBehaviour() {
        return enemyMoveBehaviour;
    }

    public EnemyController clone()
    {
        EnemyController enemyControllerClone= new EnemyController(0,0,3);
        EnemyModel model= (EnemyModel)enemyControllerClone.gameModel;
        model.setX(((EnemyModel) this.gameModel).getX());
        model.setY(((EnemyModel) this.gameModel).getY());
        return enemyControllerClone;

    }
}
