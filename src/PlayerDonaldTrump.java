import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Inpriron on 3/8/2017.
 */
public class PlayerDonaldTrump {
    static ArrayList<Square> squareArrayList = new ArrayList<>();
    int row;
    int column;
    Image image;
    int startingRow;
    int getStartingCol;

    public PlayerDonaldTrump(int row, int column, Image image) {
        this.row = row;
        this.column = column;
        this.image = image;

    }

    public void moveUp() {

        if (row - 1 >= 0) {
            row--;
            squareArrayList.add(GameWindow.blockArray[row][column]);
        }
    }

    public void moveDown() {
        if (row + 1 <= GameWindow.NUMBER_OF_ROW - 1) {
            if (GameWindow.blockArray[row + 1][column].color == Square.enumColor.BLUE
                 &&GameWindow.blockArray[row][column].color== Square.enumColor.RED  ) {
                fill();
                squareArrayList.clear();
            }
            row++;
            squareArrayList.add(GameWindow.blockArray[row][column]);
        }


    }

    public void moveRight() {
        if (column + 1 <= GameWindow.NUMBER_OF_COLUMN - 1) {
//            if(GameWindow.blockArray[row][column+1].color== Square.enumColor.BLUE)
//            {
//                GameWindow.floodFill(row+1,column, Square.enumColor.GRAY, Square.enumColor.BLUE);
//            }
            column++;
            squareArrayList.add(GameWindow.blockArray[row][column]);

        }
    }

    public void moveLeft() {
        if (column - 1 >= 0) {
            column--;
            squareArrayList.add(GameWindow.blockArray[row][column]);
        }

    }

    public int getXPixel() {
        return Utils.convertColToXPixel(this.column);
    }

    public int getYPixel() {
        return Utils.convertRowToYPixel(this.row);
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, getXPixel(), getYPixel(), GameWindow.SQUARE_LENGTH, GameWindow.SQUARE_LENGTH, null);
    }

    public void fill() {
        for (Square square : squareArrayList) {
//            if (square.row > 0)
//                GameWindow.floodFill(square.row - 1, square.column, Square.enumColor.GRAY, Square.enumColor.BLUE);
            if (square.column > 0)
                GameWindow.floodFill(square.row, square.column - 1, Square.enumColor.GRAY, Square.enumColor.BLUE);
            if (square.row < GameWindow.NUMBER_OF_ROW - 1)
                GameWindow.floodFill(square.row + 1, square.column, Square.enumColor.GRAY, Square.enumColor.BLUE);
//            if (square.column < GameWindow.NUMBER_OF_COLUMN - 1)
//                GameWindow.floodFill(square.row, square.column + 1, Square.enumColor.GRAY, Square.enumColor.BLUE);
        }
    }
    public void clearList()
    {

    }
}
