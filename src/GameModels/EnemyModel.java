package GameModels;
//
//import EnemyMoveStrategy.EnemyMoveDownLeftBehaviour;
//import EnemyMoveStrategy.EnemyMoveDownRightBehaviour;
//import EnemyMoveStrategy.EnemyMoveUpLeftBehaviour;
//import EnemyMoveStrategy.EnemyMoveUpRightBehaviour;
//import GameControllers.EnemyController;

import GameControllers.GameBoardController;
import GameControllers.SquareController;
import GUI.GameWindow;
import Utils.Utils;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Inpriron on 3/12/2017.
 */
public class EnemyModel extends GameModel {
    private int x;
    private int y;
    private int xspeed;
    private int yspeed;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public EnemyModel(int row, int column, int xspeed, int yspeed) {
        super(row, column);
        x = Utils.convertColToXPixel(column);
        y = Utils.convertRowToYPixel(row);
        this.xspeed=xspeed;
        this.yspeed=yspeed;
    }

    public void moveUpLeft() {
        if (x - xspeed >= GameBoardController.GAME_BOARD_LEFT_BORDER && y - yspeed >= GameBoardController.GAME_BOARD_UP_BORDER) {
            x -= xspeed;
            y -= yspeed;
        }

    }

    public void moveUpRight() {
        if (x + xspeed <= GameBoardController.GAME_BOARD_RIGHT_BORDER && y - yspeed >= GameBoardController.GAME_BOARD_UP_BORDER) {
            x += xspeed;
            y -= yspeed;
        }
    }

    public void moveDownLeft() {
        if (x - xspeed >= GameBoardController.GAME_BOARD_LEFT_BORDER && y + yspeed <= GameBoardController.GAME_BOARD_DOWN_BORDER) {
            x -= xspeed;
            y += yspeed;
        }
    }
    public void moveDownRight()
    {
        if (x + xspeed <= GameBoardController.GAME_BOARD_RIGHT_BORDER&& y + yspeed <= GameBoardController.GAME_BOARD_DOWN_BORDER) {
            x += xspeed;
            y += yspeed;
        }
    }

    public int getXspeed() {
        return xspeed;
    }

    public void setXspeed(int xspeed) {
        this.xspeed = xspeed;
    }

    public int getYspeed() {
        return yspeed;
    }

    public void setYspeed(int yspeed) {
        this.yspeed = yspeed;
    }

    public void moveCross()
    {
        x += xspeed;
        y += yspeed;

    }
    @Override
    public int getRow() {
        return (y - 50) / 20;
    }

    public int getColumn() {
        return (x - 10) / 20;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(x, y ,SquareModel.SQUARE_LENGTH , SquareModel.SQUARE_LENGTH);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //
//    public void moveUpRight(SquareController[][] gameBoard, EnemyController enemyController) {
//        {
//            SquareController currentSquare = gameBoard[row][column];
//            SquareController nextSquare = gameBoard[row - 1][column + 1];
//
//            if (nextSquare.getColor() == SquareModel.enumColor.RED) {
//                hitPlayer();
//            } else if (nextSquare.getColor() == SquareModel.enumColor.BLUE) {
//                if (nextSquare.getRow() == 0)
//                    enemyController.enemyMoveBehaviour = new EnemyMoveDownRightBehaviour();
//                else if (nextSquare.getColumn() == GameWindow.NUMBER_OF_COLUMN - 1)
//                    enemyController.enemyMoveBehaviour = new EnemyMoveUpLeftBehaviour();
//                else
//                    enemyController.enemyMoveBehaviour = new EnemyMoveDownLeftBehaviour();
//            } else {
//                row--;
//                column++;
//            }
//        }
//    }
//
//    public void moveDownRight(SquareController[][] gameBoard, EnemyController enemyController) {
//        SquareController currentSquare = gameBoard[row][column];
//        SquareController nextSquare = gameBoard[row + 1][column + 1];
//        if (nextSquare.getColor() == SquareModel.enumColor.RED) {
//            hitPlayer();
//        } else if (nextSquare.getColor() == SquareModel.enumColor.BLUE) {
//            if (nextSquare.getRow() == GameWindow.NUMBER_OF_ROW - 1)
//                enemyController.enemyMoveBehaviour = new EnemyMoveUpRightBehaviour();
//            else if (nextSquare.getColumn() == GameWindow.NUMBER_OF_COLUMN - 1)
//                enemyController.enemyMoveBehaviour = new EnemyMoveDownLeftBehaviour();
//            else
//                enemyController.enemyMoveBehaviour = new EnemyMoveUpLeftBehaviour();
//        } else {
//            row++;
//            column++;
//        }
//    }
//
//    public void moveDownLeft(SquareController[][] gameBoard, EnemyController enemyController) {
//        SquareController currentSquare = gameBoard[row][column];
//        SquareController nextSquare = gameBoard[row + 1][column - 1];
//        if (nextSquare.getColor() == SquareModel.enumColor.RED) {
//            hitPlayer();
//        } else if (nextSquare.getColor() == SquareModel.enumColor.BLUE) {
//            if (nextSquare.getRow() == GameWindow.NUMBER_OF_ROW - 1)
//                enemyController.enemyMoveBehaviour = new EnemyMoveUpRightBehaviour();
//            else if (nextSquare.getColumn() == 0)
//                enemyController.enemyMoveBehaviour = new EnemyMoveDownRightBehaviour();
//            else
//                enemyController.enemyMoveBehaviour = new EnemyMoveUpRightBehaviour();
//        } else {
//            row++;
//            column--;
//        }
//    }
//
//    public void hitPlayer() {
//        clearPlayerPathAfterGetHit();
//        GameWindow.gameBoardController.donaldTrumpController.setRow(0);
//        GameWindow.gameBoardController.donaldTrumpController.setColumn(0);
//    }
//
//    public void clearPlayerPathAfterGetHit() {
//        ArrayList<SquareController> toRemove = new ArrayList<>();
//        ArrayList<SquareController> squarePlayerWentBy = GameWindow.gameBoardController.squarePlayerWentBy;
//        for (SquareController square : squarePlayerWentBy) {
//            square.setColor(SquareModel.enumColor.GRAY);
//            toRemove.add(square);
//        }
//        squarePlayerWentBy.removeAll(toRemove);
//    }
}
