package GameControllers;


import GameModels.SquareModel;
import Program.GameWindow;

import java.awt.*;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class ControllerManager {
    private int NUMBER_OF_ROW = GameWindow.NUMBER_OF_ROW;
    private int NUMBER_OF_COLUMN = GameWindow.NUMBER_OF_COLUMN;
    public SquareController[][] gameBoard = new SquareController[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
    public DonaldTrumpController donaldTrumpController;


    public ControllerManager() {
        SquareController[][] gameBoard = new SquareController[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
        donaldTrumpController = new DonaldTrumpController(0, 0);
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
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                gameBoard[i][j].draw(graphics);
            }
        }
    }
}
