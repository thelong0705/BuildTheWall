import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Inpriron on 3/8/2017.
 */
public class PlayerDonaldTrump {
    static ArrayList<Square> squareArrayList = new ArrayList<>();
    int row;
    int column;
    Image image;

    public PlayerDonaldTrump(int row, int column, Image image) {
        this.row = row;
        this.column = column;
        this.image = image;

    }

    public void moveUp() {

        if (row - 1 >= 0) {
            if (GameWindow.blockArray[row - 1][column].color == Square.enumColor.BLUE && GameWindow.blockArray[row][column].color == Square.enumColor.RED) {
                fill();
            }
            if (GameWindow.blockArray[row - 1][column].color == Square.enumColor.RED && GameWindow.blockArray[row - 1][column].direction == Square.enumDirection.DOWN)
                ;
            else {
                row--;
                if (GameWindow.blockArray[row][column].color == Square.enumColor.GRAY)
                    GameWindow.blockArray[row][column].direction = Square.enumDirection.UP;
                addIntoArrayList();
            }

        }
    }

    public void moveDown() {
        if (row + 1 <= GameWindow.NUMBER_OF_ROW - 1) {
            if (GameWindow.blockArray[row + 1][column].color == Square.enumColor.BLUE && GameWindow.blockArray[row][column].color == Square.enumColor.RED) {
                fill();
            }
            row++;
            if (GameWindow.blockArray[row][column].color == Square.enumColor.GRAY)
                GameWindow.blockArray[row][column].direction = Square.enumDirection.DOWN;
            addIntoArrayList();
        }
    }

    public void moveRight() {
        if (column + 1 <= GameWindow.NUMBER_OF_COLUMN - 1) {
            if (GameWindow.blockArray[row][column + 1].color == Square.enumColor.BLUE && GameWindow.blockArray[row][column].color == Square.enumColor.RED) {
                fill();
            }
            column++;
            if (GameWindow.blockArray[row][column].color == Square.enumColor.GRAY)
                GameWindow.blockArray[row][column].direction = Square.enumDirection.RIGHT;
            addIntoArrayList();

        }
    }

    public void moveLeft() {
        if (column - 1 >= 0) {
            if (GameWindow.blockArray[row][column - 1].color == Square.enumColor.BLUE && GameWindow.blockArray[row][column].color == Square.enumColor.RED) {
                fill();
            }
            column--;
            if (GameWindow.blockArray[row][column].color == Square.enumColor.GRAY)
                GameWindow.blockArray[row][column].direction = Square.enumDirection.LEFT;
            addIntoArrayList();
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

    public void fill4way() {
        for (Square square : squareArrayList) {
            if (square.column > 0)
                GameWindow.floodFill(square.row, square.column - 1, Square.enumColor.GRAY, Square.enumColor.GREEN);
            if (square.row < GameWindow.NUMBER_OF_ROW - 1)
                GameWindow.floodFill(square.row + 1, square.column, Square.enumColor.GRAY, Square.enumColor.GREEN);
            if (square.row > 0)
                GameWindow.floodFill(square.row - 1, square.column, Square.enumColor.GRAY, Square.enumColor.GREEN);
            if (square.column < GameWindow.NUMBER_OF_COLUMN - 1)
                GameWindow.floodFill(square.row, square.column + 1, Square.enumColor.GRAY, Square.enumColor.GREEN);

        }
    }

    public void fill() {
        for (Square square : squareArrayList) {
            square.color = Square.enumColor.BLUE;
            square.setPictureForColor();
            fill4way();
        }
        GameWindow.floodFill(20, 30, Square.enumColor.GREEN, Square.enumColor.GRAY);
    }

    public void addIntoArrayList() {
        if (GameWindow.blockArray[row][column].color == Square.enumColor.GRAY) {
            GameWindow.blockArray[row][column].color = Square.enumColor.RED;
            GameWindow.blockArray[row][column].setPictureForColor();
            squareArrayList.add(GameWindow.blockArray[row][column]);
        }
    }
}
