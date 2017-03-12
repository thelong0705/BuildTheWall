package GameControllers;


import GameModels.SquareModel;
import Program.GameWindow;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class ControllerManager {
    private int NUMBER_OF_ROW = GameWindow.NUMBER_OF_ROW;
    private int NUMBER_OF_COLUMN = GameWindow.NUMBER_OF_COLUMN;
    public SquareController[][] gameBoard ;
    public DonaldTrumpController donaldTrumpController;
    public ArrayList<EnemyController> enemyControllers;
    public ArrayList<SquareController> squarePlayerWentBy = new ArrayList<>();
    public ControllerManager() {
        gameBoard = new SquareController[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
        donaldTrumpController = new DonaldTrumpController(0, 0,GameWindow.PLAYER_LIFE);
        enemyControllers= new ArrayList<>();
        squarePlayerWentBy= new ArrayList<>();
    }

    public SquareController[][] getGameBoard() {
        return gameBoard;
    }

    public void initiateGameBoard() {
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                gameBoard[i][j] = new SquareController(i, j, SquareModel.enumColor.GRAY);
                if (i == 0 || j == 0 || i == NUMBER_OF_ROW - 1 || j == NUMBER_OF_COLUMN - 1)
                    gameBoard[i][j] = new SquareController(i, j, SquareModel.enumColor.BLUE);
            }
        }
    }
    public void drawGameBoard(Graphics graphics)
    {
        GameWindow.blueSquare=0;
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                gameBoard[i][j].draw(graphics);
                if(gameBoard[i][j].getColor()== SquareModel.enumColor.BLUE|| gameBoard[i][j].getColor()== SquareModel.enumColor.GREEN)
                    GameWindow.blueSquare++;
            }
        }
    }
    public void spawnEnemy(int row, int column)
    {
        EnemyController enemyController= new EnemyController(row,column);
        enemyControllers.add(enemyController);
    }
    public void drawEnemy(Graphics graphics)
    {
        for(EnemyController enemyController: enemyControllers)
        {
            enemyController.draw(graphics);
        }
    }
    public void runEnemy()
    {
        for(EnemyController enemyController: enemyControllers)
        {
            enemyController.run();
        }
    }

}
