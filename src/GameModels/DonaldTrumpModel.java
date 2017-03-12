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
            SquareController currentSquare = gameBoard[row][column];
            SquareController nextSquare = gameBoard[row - 1][column];
            if (checkFinish(currentSquare, nextSquare)) {
                fill(gameBoard);
            }
            if (checkMeetRedSquare(nextSquare)) {
                if (nextSquare.getDirection() == SquareModel.enumDirection.DOWN)
                    moveDown(gameBoard);
                else {
                    row = column = 0;
                    clearPlayerPath();
                }
            } else {
                row--;
                if (nextSquare.getColor() == SquareModel.enumColor.GRAY)
                    nextSquare.setDirection(SquareModel.enumDirection.UP);
                addIntoArrayList(gameBoard);
            }
        }
    }

    public void moveDown(SquareController[][] gameBoard) {
        if (row + 1 <= GameWindow.NUMBER_OF_ROW - 1) {
            SquareController currentSquare = gameBoard[row][column];
            SquareController nextSquare = gameBoard[row + 1][column];
            if (checkFinish(currentSquare, nextSquare)) {
                fill(gameBoard);
            }
            if (checkMeetRedSquare(nextSquare)) {
                if (nextSquare.getDirection() == SquareModel.enumDirection.UP)
                    moveUp(gameBoard);
                else {
                    row = column = 0;
                    clearPlayerPath();
                }
            } else {
                row++;
                if (nextSquare.getColor() == SquareModel.enumColor.GRAY)
                    nextSquare.setDirection(SquareModel.enumDirection.DOWN);
                addIntoArrayList(gameBoard);
            }
        }
    }

    public void moveRight(SquareController[][] gameBoard) {

        if (column + 1 <= GameWindow.NUMBER_OF_COLUMN - 1) {
            SquareController currentSquare = gameBoard[row][column];
            SquareController nextSquare = gameBoard[row][column + 1];
            if (checkFinish(currentSquare, nextSquare)) {
                fill(gameBoard);
            }
            if (checkMeetRedSquare(nextSquare)) {
                if (nextSquare.getDirection() == SquareModel.enumDirection.LEFT)
                    moveLeft(gameBoard);
                else {
                    row = column = 0;
                    clearPlayerPath();
                }
            } else {
                column++;
                if (nextSquare.getColor() == SquareModel.enumColor.GRAY)
                    nextSquare.setDirection(SquareModel.enumDirection.RIGHT);
                addIntoArrayList(gameBoard);
            }
        }
    }

    public void moveLeft(SquareController[][] gameBoard) {
        if (column - 1 >= 0) {
            SquareController currentSquare = gameBoard[row][column];
            SquareController nextSquare = gameBoard[row][column - 1];
            if (checkFinish(currentSquare, nextSquare)) {
                fill(gameBoard);
            }
            if (checkMeetRedSquare(nextSquare)) {
                if (nextSquare.getDirection() == SquareModel.enumDirection.RIGHT)
                    moveRight(gameBoard);
                else {
                    row = column = 0;
                    clearPlayerPath();
                }
            } else {
                column--;
                if (nextSquare.getColor() == SquareModel.enumColor.GRAY)
                    nextSquare.setDirection(SquareModel.enumDirection.LEFT);
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

    public void clearPlayerPath() {
        ArrayList<SquareController> toRemove = new ArrayList<>();
        for (SquareController square : squarePlayerWentBy) {
            square.setColor(SquareModel.enumColor.GRAY);
            toRemove.add(square);
        }
        squarePlayerWentBy.removeAll(toRemove);
    }

    public void addIntoArrayList(SquareController[][] gameBoard) {
        if (gameBoard[row][column].getColor() == SquareModel.enumColor.GRAY) {
            gameBoard[row][column].setColor(SquareModel.enumColor.RED);
            squarePlayerWentBy.add(gameBoard[row][column]);
        }
    }

    public boolean checkFinish(SquareController currentSquare, SquareController nextSquare) {
        return nextSquare.getColor() == SquareModel.enumColor.BLUE &&
                currentSquare.getColor() == SquareModel.enumColor.RED;
    }

    public boolean checkMeetRedSquare(SquareController nextSquare) {
        return nextSquare.getColor() == SquareModel.enumColor.RED;
    }
}
