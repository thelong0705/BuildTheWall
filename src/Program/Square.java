package Program;

import Utils.Utils;

import java.awt.*;

/**
 * Created by Inpriron on 3/6/2017.
 */
public class Square {
    public enum enumColor {
        GRAY, BLUE, RED,GREEN
    }
    public enum enumDirection{
        UP,DOWN,LEFT,RIGHT
    }
    public int column;
    public int row;
    Image image;
    enumColor color;
    enumDirection direction;
    public Square(int row, int column, enumColor Color) {
        this.column = column;
        this.row = row;
        this.color = Color;
        switch (Color) {
            case BLUE:
                this.image = Utils.loadImageFromFile("block.png");
                break;
            case GRAY:
                this.image = Utils.loadImageFromFile("blockgray.png");
                break;
            case RED:
                this.image = Utils.loadImageFromFile("block2.png");
                break;
            case GREEN:
                this.image=Utils.loadImageFromFile("green.png");
                break;
        }
    }
    public void setPictureForColor()
    {
        switch (this.color) {
            case BLUE:
                this.image = Utils.loadImageFromFile("block.png");
                break;
            case GRAY:
                this.image = Utils.loadImageFromFile("blockgray.png");
                break;
            case RED:
                this.image = Utils.loadImageFromFile("block2.png");
                break;
            case GREEN:
                this.image=Utils.loadImageFromFile("block.png");
                break;
            default:
                break;
        }
    }

    public int getXPixel() {
        return Utils.convertColToXPixel(this.column);
    }
    public int getYPixel(){
        return Utils.convertRowToYPixel(this.row);
    }
    public void draw(Graphics graphics) {
        graphics.drawImage(image, getXPixel(), getYPixel(),
                GameWindow.SQUARE_LENGTH, GameWindow.SQUARE_LENGTH,null);
    }
}
