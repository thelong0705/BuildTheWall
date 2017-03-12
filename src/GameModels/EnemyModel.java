package GameModels;

import EnemyMoveStrategy.EnemyMoveDownLeftBehaviour;
import EnemyMoveStrategy.EnemyMoveDownRightBehaviour;
import EnemyMoveStrategy.EnemyMoveUpLeftBehaviour;
import EnemyMoveStrategy.EnemyMoveUpRightBehaviour;
import GameControllers.EnemyController;
import GameControllers.SquareController;
import Program.GameWindow;
import Utils.Utils;

import java.util.ArrayList;

/**
 * Created by Inpriron on 3/12/2017.
 */
public class EnemyModel extends GameModel {
    private int x;
    private int y;
    private int speed;

    public EnemyModel(int row, int column) {
        super(row, column);
        x = Utils.convertColToXPixel(column);
        y = Utils.convertRowToYPixel(row);
    }

    public void moveUpLeft(SquareController[][] gameBoard,EnemyController enemyController) {
        SquareController currentSquare = gameBoard[row][column];
        SquareController nextSquare = gameBoard[row - 1][column - 1];

        if (nextSquare.getColor() == SquareModel.enumColor.RED) {
            {
                hitPlayer();
            }
        } else if (nextSquare.getColor() == SquareModel.enumColor.BLUE) {
            enemyController.enemyMoveBehaviour=new EnemyMoveDownRightBehaviour();
        } else {
            row--;
            column--;
        }

    }

    public void moveUpRight(SquareController[][] gameBoard, EnemyController enemyController) {
        {
            SquareController currentSquare = gameBoard[row][column];
            SquareController nextSquare = gameBoard[row - 1][column + 1];

            if (nextSquare.getColor() == SquareModel.enumColor.RED) {
                hitPlayer();
            } else if (nextSquare.getColor() == SquareModel.enumColor.BLUE) {
                enemyController.enemyMoveBehaviour=new EnemyMoveDownLeftBehaviour();
            } else {
                row--;
                column++;
            }
        }
    }

    public void moveDownRight(SquareController[][] gameBoard, EnemyController enemyController) {
        SquareController currentSquare = gameBoard[row][column];
        SquareController nextSquare = gameBoard[row + 1][column + 1];

        if (nextSquare.getColor() == SquareModel.enumColor.RED) {
            hitPlayer();
        } else if (nextSquare.getColor() == SquareModel.enumColor.BLUE) {
            enemyController.enemyMoveBehaviour= new EnemyMoveUpLeftBehaviour();
        } else {
            row++;
            column++;
        }
    }

    public void moveDownLeft(SquareController[][] gameBoard, EnemyController enemyController) {
        SquareController currentSquare = gameBoard[row][column];
        SquareController nextSquare = gameBoard[row + 1][column - 1];
        if (nextSquare.getColor() == SquareModel.enumColor.RED) {
            hitPlayer();
        } else if (nextSquare.getColor() == SquareModel.enumColor.BLUE) {
            enemyController.enemyMoveBehaviour=new EnemyMoveUpRightBehaviour();
        } else {
            row--;
            column--;
        }
    }
    public void hitPlayer()
    {
        clearPlayerPath();
        GameWindow.controllerManager.donaldTrumpController.setRow(0);
        GameWindow.controllerManager.donaldTrumpController.setColumn(0);
    }
    public void clearPlayerPath() {
        ArrayList<SquareController> toRemove = new ArrayList<>();
        ArrayList<SquareController> squarePlayerWentBy=GameWindow.controllerManager.squarePlayerWentBy;
        for (SquareController square : squarePlayerWentBy) {
            square.setColor(SquareModel.enumColor.GRAY);
            toRemove.add(square);
        }
        squarePlayerWentBy.removeAll(toRemove);
    }
}
