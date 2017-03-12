package GameModels;

import EnemyMoveStrategy.EnemyMoveDownLeftBehaviour;
import EnemyMoveStrategy.EnemyMoveDownRightBehaviour;
import EnemyMoveStrategy.EnemyMoveUpLeftBehaviour;
import EnemyMoveStrategy.EnemyMoveUpRightBehaviour;
import GameControllers.EnemyController;
import GameControllers.SquareController;
import Program.GameWindow;
import Utils.Utils;

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
            System.out.println("hit");
        } else if (nextSquare.getColor() == SquareModel.enumColor.BLUE) {
            enemyController.enemyMoveBehaviour=new EnemyMoveUpRightBehaviour();
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
                System.out.println("hit");
            } else if (nextSquare.getColor() == SquareModel.enumColor.BLUE) {
                enemyController.enemyMoveBehaviour=new EnemyMoveDownRightBehaviour();
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
            System.out.println("hit");
        } else if (nextSquare.getColor() == SquareModel.enumColor.BLUE) {
            enemyController.enemyMoveBehaviour= new EnemyMoveDownLeftBehaviour();
        } else {
            row++;
            column++;
        }
    }

    public void moveDownLeft(SquareController[][] gameBoard, EnemyController enemyController) {
        SquareController currentSquare = gameBoard[row][column];
        SquareController nextSquare = gameBoard[row + 1][column - 1];
        if (nextSquare.getColor() == SquareModel.enumColor.RED) {
            System.out.println("hit");
        } else if (nextSquare.getColor() == SquareModel.enumColor.BLUE) {
            enemyController.enemyMoveBehaviour=new EnemyMoveUpLeftBehaviour();
        } else {
            row--;
            column--;
        }
    }

}
