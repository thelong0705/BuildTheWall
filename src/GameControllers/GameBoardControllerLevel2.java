package GameControllers;

import GUI.GameWindow;
import GameModels.EnemyModel;
import Utils.Utils;

import java.util.ArrayList;

/**
 * Created by Inpriron on 3/24/2017.
 */
public class GameBoardControllerLevel2 extends GameBoardController {
    public GameBoardControllerLevel2() {
        initiateGameBoard();
    }

    @Override
    public void initiateGameBoard() {
        buildBoard();
        spawnEnemy(Utils.convertColToXPixel(30), Utils.convertRowToYPixel(20), 2, 5, EnemyController.EnemyType.CHINA);
        spawnEnemy(Utils.convertColToXPixel(20), Utils.convertRowToYPixel(15), 5, 2, EnemyController.EnemyType.CHINA);
        spawnEnemy(Utils.convertColToXPixel(15), Utils.convertRowToYPixel(15), 3, 4, EnemyController.EnemyType.CHINA);
        image = Utils.loadImageFromFile("chinawall.jpg");
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
                if (enemyControllers.size() < 6) {
                    if (enemyControllerI.getAliveTime() > 60 && enemyControllerJ.getAliveTime() > 60) {
                        if (enemyControllerI.gameModel.intersects(enemyControllerJ.gameModel)) {
                            EnemyModel modelI = (EnemyModel) enemyControllerI.gameModel;
                            EnemyModel modelJ = (EnemyModel) enemyControllerJ.gameModel;
                            if (modelI.getXspeed() * modelJ.getXspeed() < 0 || modelI.getYspeed() * modelJ.getYspeed() < 0) {
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


}
