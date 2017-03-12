package GameModels;

import GameControllers.SquareController;
import Program.GameWindow;

import Utils.Utils;


import java.util.ArrayList;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class DonaldTrumpModel extends GameModel {
    private ArrayList<SquareController> squarePlayerWentBy = new ArrayList<>();

    public DonaldTrumpModel(int row, int column) {
        super(row, column);
    }

    public void moveUp(SquareController[][] gameBoard) {
        if (row - 1 >= 0) {
            if (gameBoard[row - 1][column].getColor() == SquareModel.enumColor.BLUE &&
                    gameBoard[row][column].getColor() == SquareModel.enumColor.RED) {
                fill(gameBoard);
            }
            if (gameBoard[row - 1][column].getColor() == SquareModel.enumColor.RED &&
                    gameBoard[row - 1][column].getDirection() == SquareModel.enumDirection.DOWN)
                moveDown(gameBoard);
            else {
                row--;
                if (gameBoard[row][column].getColor() == SquareModel.enumColor.GRAY)
                    gameBoard[row][column].setDirection(SquareModel.enumDirection.UP);
                addIntoArrayList(gameBoard);
            }
        }
    }

    public void moveDown(SquareController[][] gameBoard) {
        if (row + 1 <= GameWindow.NUMBER_OF_ROW - 1) {
            if (gameBoard[row + 1][column].getColor() == SquareModel.enumColor.BLUE &&
                    gameBoard[row][column].getColor() == SquareModel.enumColor.RED) {
                fill(gameBoard);
            }
            if (gameBoard[row + 1][column].getColor() == SquareModel.enumColor.RED &&
                    gameBoard[row + 1][column].getDirection() == SquareModel.enumDirection.UP)
                moveUp(gameBoard);
            else {
                row++;
                if (gameBoard[row][column].getColor() == SquareModel.enumColor.GRAY)
                    gameBoard[row][column].setDirection(SquareModel.enumDirection.DOWN);
                addIntoArrayList(gameBoard);
            }
        }
    }

    public void moveRight(SquareController[][] gameBoard) {
        if (column + 1 <= GameWindow.NUMBER_OF_COLUMN - 1) {
            if (gameBoard[row][column + 1].getColor() == SquareModel.enumColor.BLUE &&
                    gameBoard[row][column].getColor() == SquareModel.enumColor.RED) {
                fill(gameBoard);
            }
            if (gameBoard[row][column + 1].getColor() == SquareModel.enumColor.RED &&
                    gameBoard[row][column+1].getDirection() == SquareModel.enumDirection.LEFT)
                moveLeft(gameBoard);
            else {
                column++;
                if (gameBoard[row][column].getColor() == SquareModel.enumColor.GRAY)
                    gameBoard[row][column].setDirection(SquareModel.enumDirection.RIGHT) ;
                addIntoArrayList(gameBoard);
            }
        }
    }
    public void moveLeft(SquareController[][] gameBoard) {
        if (column - 1 >= 0) {
            if (gameBoard[row][column - 1].getColor() == SquareModel.enumColor.BLUE &&
                    gameBoard[row][column].getColor() == SquareModel.enumColor.RED) {
                fill(gameBoard);
            }
            if (gameBoard[row][column - 1].getColor() == SquareModel.enumColor.RED &&
                    gameBoard[row][column-1].getDirection() == SquareModel.enumDirection.RIGHT)
                moveRight(gameBoard);
            else {
                column--;
                if (gameBoard[row][column].getColor() == SquareModel.enumColor.GRAY)
                    gameBoard[row][column].setDirection(SquareModel.enumDirection.LEFT);
                addIntoArrayList(gameBoard);
            }

        }

    }
    public void fill(SquareController[][] gameBoard) {
        ArrayList<SquareController> toRemove = new ArrayList<>();
        for (SquareController square : squarePlayerWentBy) {
            square.setColor(SquareModel.enumColor.BLUE);
            toRemove.add(square);
            Utils.fill4way(square, gameBoard);
        }
        squarePlayerWentBy.removeAll(toRemove);
        Utils.floodFill(20, 30, SquareModel.enumColor.GREEN, SquareModel.enumColor.GRAY, gameBoard);
    }

    public void addIntoArrayList(SquareController[][] gameBoard) {
        if (gameBoard[row][column].getColor() == SquareModel.enumColor.GRAY) {
            gameBoard[row][column].setColor(SquareModel.enumColor.RED);
            squarePlayerWentBy.add(gameBoard[row][column]);
        }
    }
}
