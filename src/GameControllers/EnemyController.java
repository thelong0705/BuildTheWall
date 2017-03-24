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
    private static Image mexicoEnemyImage= Utils.loadImageFromFile("loco.png");
    private static Image chinaEnemyImage= Utils.loadImageFromFile("chinese.png");
    private int aliveTime;

    public int getAliveTime() {
        return aliveTime;
    }

    private EnemyMoveBehaviour enemyMoveBehaviour;
    public enum EnemyType{
        MEXICO,
        CHINA
    }
    public EnemyController(GameModel gameModel, GameView gameView) {
        super(gameModel, gameView);
//        enemyMoveBehaviour = new EnemyMoveCrossBehaviour();
    }

//    public EnemyController(int row, int column, int xspeed, int yspeed) {
//        this(new EnemyModel(row, column, xspeed,yspeed), new GameView(Utils.loadImageFromFile("loco.png")));
//    }
    public static EnemyController create(int row, int column, int xspeed, int yspeed,EnemyType type)
    {

        EnemyController enemyController=null;
        if(type==EnemyType.MEXICO)
        {
            enemyController=new EnemyController(new EnemyModel(row, column, xspeed,yspeed),
                    new GameView(mexicoEnemyImage));
            enemyController.enemyMoveBehaviour= new EnemyMoveCrossBehaviour();
        }
        else if(type==EnemyType.CHINA)
        {
            enemyController=new EnemyController(new EnemyModel(row, column, xspeed,yspeed),
                    new GameView(chinaEnemyImage));
            enemyController.enemyMoveBehaviour= new EnemyMoveCrossBehaviour();
        }
         return enemyController;
    }
    @Override
    public void run() {
        enemyMoveBehaviour.move((EnemyModel)gameModel);
        aliveTime++;
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

    public int getColumn()
    {
        EnemyModel model = (EnemyModel) gameModel;
        return model.getColumn();

    }
    public int getXSpeed()
    {
        EnemyModel model = (EnemyModel) gameModel;
        return model.getXspeed();

    }public int getYSpeed()
    {
        EnemyModel model = (EnemyModel) gameModel;
        return model.getYspeed();

    }


    public EnemyMoveBehaviour getEnemyMoveBehaviour() {
        return enemyMoveBehaviour;
    }

}
