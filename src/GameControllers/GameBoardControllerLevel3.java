package GameControllers;

import GameModels.EnemyModel;
import GameModels.SquareModel;
import Utils.Utils;

import java.util.ArrayList;

/**
 * Created by Inpriron on 3/25/2017.
 */
public class GameBoardControllerLevel3 extends GameBoardController {
    public GameBoardControllerLevel3() {
        gameBoard = new SquareController[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
        DonaldTrumpController.donaldTrumpInstance.setColumn(0);
        DonaldTrumpController.donaldTrumpInstance.setRow(0);
        enemyControllers = new ArrayList<>();
        squarePlayerWentBy = new ArrayList<>();
        initiateGameBoard();
    }

    @Override
    public void initiateGameBoard() {
        buildBoard();
        spawnEnemy(Utils.convertColToXPixel(30), Utils.convertRowToYPixel(20), 2, 5, EnemyController.EnemyType.IRAQ);
        spawnEnemy(Utils.convertColToXPixel(20), Utils.convertRowToYPixel(15), 5, 2, EnemyController.EnemyType.IRAQ);
        spawnEnemy(Utils.convertColToXPixel(15), Utils.convertRowToYPixel(15), 3, 4, EnemyController.EnemyType.IRAQ);
        image = Utils.loadImageFromFile("background.png");
    }

    @Override
    public void checkEnemyBounceWall() {
        SquareController toRemove = null;
        for (EnemyController enemyController : enemyControllers) {
            for (SquareController squareController : blueSquareList) {
                if (squareController.gameModel.intersects(enemyController.gameModel)) {
                    if (squareController.getRow() != 0 && squareController.getColumn() != 0 &&
                            squareController.getRow() != NUMBER_OF_ROW - 1 && squareController.getColumn() != NUMBER_OF_COLUMN - 1) {
                        squareController.setColor(SquareModel.enumColor.GRAY);
                        toRemove = squareController;
                        setNearBySquare(squareController);
                    }
                    EnemyModel model = (EnemyModel) enemyController.gameModel;
                    int xspeed = model.getXspeed();
                    int yspeed = model.getYspeed();
                    if (squareController.isCelling())
                        model.setYspeed(model.getYspeed() * -1);
                    if (squareController.isWall())
                        model.setXspeed(model.getXspeed() * -1);
                    EnemyController temp = EnemyController.create(model.getX(), model.getY(), model.getXspeed(),
                            model.getYspeed(), EnemyController.EnemyType.MEXICO);
                    temp.run();
                    for (SquareController squareTemp : blueSquareList) {
                        if (squareTemp.gameModel.intersects(temp.gameModel)) {
                            model.setXspeed(xspeed * -1);
                            model.setYspeed(yspeed * -1);
                            break;
                        }
                    }
                    break;
                }
            }
            blueSquareList.remove(toRemove);
        }
    }

    private void setNearBySquare(SquareController squareController) {
        int row = squareController.getRow();
        int column = squareController.getColumn();
        SquareController north = null;
        SquareController south = null;
        SquareController west = null;
        SquareController east = null;
        if (row + 1 <= NUMBER_OF_ROW - 1) {
            south = gameBoard[row + 1][column];
            if (south.getColor() == SquareModel.enumColor.GREEN) {
                south.setColor(SquareModel.enumColor.BLUE);
                blueSquareList.add(south);
            }

        }
        if (row - 1 >= 0) {
            north = gameBoard[row - 1][column];
            if (north.getColor() == SquareModel.enumColor.GREEN) {
                north.setColor(SquareModel.enumColor.BLUE);
                blueSquareList.add(north);
            }
        }
        if (column + 1 <= NUMBER_OF_COLUMN - 1) {
            east = gameBoard[row][column + 1];
            if (east.getColor() == SquareModel.enumColor.GREEN) {
                east.setColor(SquareModel.enumColor.BLUE);
                blueSquareList.add(east);
            }
        }

        if (column - 1 >= 0) {
            west = gameBoard[row][column - 1];
            if (west.getColor() == SquareModel.enumColor.GREEN) {
                west.setColor(SquareModel.enumColor.BLUE);
                blueSquareList.add(west);
            }
        }
    }
   
}
