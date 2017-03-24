package Utils;

import GameControllers.SquareController;
import GameModels.SquareModel;
import GUI.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Inpriron on 3/6/2017.
 */
public class Utils {
    public static Image loadImageFromFile(String url) {
        Image image = null;
        try {
            image = ImageIO.read(new File("resources/" + url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static int convertRowToYPixel(int row) {
        return 30 + 20 * row+20;
    }

    public static int convertColToXPixel(int col) {
        return 10 +20 * col;
    }

    public static int convertYToRowArray(int y) {
        return (y - 50) /20;
    }

    public static int convertXtoColumnArray(int x) {
        return (x - 10) /20;
    }

//    public static void floodFill(int row, int column, SquareModel.enumColor sourceColor,
//                                 SquareModel.enumColor desColor, SquareController[][] gameBoard) {
//        if (row < 0) return;
//        if (column < 0) return;
//        if (row >= GameWindow.NUMBER_OF_ROW) return;
//        if (column >= GameWindow.NUMBER_OF_COLUMN) return;
//        if (gameBoard[row][column].getColor() != sourceColor) return;
//        gameBoard[row][column].setColor(desColor);
//        floodFill(row - 1, column, sourceColor, desColor, gameBoard);
//        floodFill(row + 1, column, sourceColor, desColor, gameBoard);
//        floodFill(row, column - 1, sourceColor, desColor, gameBoard);
//        floodFill(row, column + 1, sourceColor, desColor, gameBoard);
//    }

//    public static void fill4way(SquareController square,SquareController[][] gameBoard) {
//
//        if (square.getColumn() > 0)
//            floodFill(square.getRow(), square.getColumn() - 1, SquareModel.enumColor.GRAY, SquareModel.enumColor.GREEN,gameBoard);
//        if (square.getRow() < GameWindow.NUMBER_OF_ROW - 1)
//            floodFill(square.getRow() + 1, square.getColumn(), SquareModel.enumColor.GRAY, SquareModel.enumColor.GREEN,gameBoard);
//        if (square.getRow() > 0)
//            floodFill(square.getRow() - 1, square.getColumn(), SquareModel.enumColor.GRAY, SquareModel.enumColor.GREEN,gameBoard);
//        if (square.getColumn() < GameWindow.NUMBER_OF_COLUMN - 1)
//            floodFill(square.getRow(), square.getColumn() + 1, SquareModel.enumColor.GRAY, SquareModel.enumColor.GREEN,gameBoard);
//
//    }

}
