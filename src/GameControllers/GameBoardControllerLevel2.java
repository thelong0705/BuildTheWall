package GameControllers;

import GameModels.EnemyModel;
import Utils.Utils;

import java.util.ArrayList;

/**
 * Created by Inpriron on 3/24/2017.
 */
public class GameBoardControllerLevel2 extends GameBoardController {
    public GameBoardControllerLevel2() {
        gameBoard = new SquareController[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
        donaldTrumpController = new DonaldTrumpController(0, 0, PLAYER_LIFE);
        enemyControllers = new ArrayList<>();
        squarePlayerWentBy = new ArrayList<>();
        initiateGameBoard();
    }

    @Override
    public void initiateGameBoard() {
        buildBoard();
        spawnEnemy(Utils.convertColToXPixel(30),Utils.convertRowToYPixel(20) , 5, 6, EnemyController.EnemyType.CHINA);
        spawnEnemy(Utils.convertColToXPixel(38), Utils.convertRowToYPixel(28), 5, 6, EnemyController.EnemyType.CHINA);
        spawnEnemy(Utils.convertColToXPixel(15), Utils.convertRowToYPixel(15), 5, 6, EnemyController.EnemyType.CHINA);
        image = Utils.loadImageFromFile("background.png");
    }

    @Override
    public void checkEnemyCollide() {
        checkEnemyBounceEachOther();
        super.checkEnemyCollide();

    }

    private void checkEnemyBounceEachOther() {
        for (int i = 0; i < enemyControllers.size() - 1; i++)
            for (int j = i + 1; j < enemyControllers.size(); j++) {
                EnemyController enemyControllerI = enemyControllers.get(i);
                EnemyController enemyControllerJ = enemyControllers.get(j);
                if(enemyControllers.size()<8)
                {
                    if (enemyControllerI.getAliveTime() > 60 && enemyControllerJ.getAliveTime() > 60) {
                        if (enemyControllerI.gameModel.intersects(enemyControllerJ.gameModel)) {
                            EnemyModel modelI = (EnemyModel) enemyControllerI.gameModel;
                            EnemyModel modelJ = (EnemyModel) enemyControllerJ.gameModel;
                            if (modelI.getXspeed() == modelJ.getXspeed() * -1 && modelI.getYspeed() == modelJ.getYspeed()*-1) {
                                spawnEnemy(modelI.getX(), modelI.getY(),
                                        enemyControllerI.getXSpeed(), -enemyControllerI.getYSpeed(), EnemyController.EnemyType.CHINA);
                                modelI.setXspeed(modelI.getXspeed() * -1);
                                modelI.setYspeed(modelI.getYspeed() * -1);
                                enemyControllerI.run();
                                modelJ.setXspeed(modelJ.getXspeed() * -1);
                                modelJ.setYspeed(modelJ.getYspeed() * -1);
                                enemyControllerJ.run();
                            }

                        }
                    }
                }
                }


    }

    @Override
    public boolean checkWin() {
        if (percentagePlayerFill >= 80)
            return true;
        else
            return false;
    }
}
