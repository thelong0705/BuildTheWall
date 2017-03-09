import java.awt.*;

/**
 * Created by Inpriron on 3/6/2017.
 */
public class Square {
    public enum enumColor {
        GRAY, BLUE, RED,ENEMY,YELLOW,GREEN
    }

    public int column;
    public int row;
    Image image;
    enumColor color;

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
            case ENEMY:
                this.image=Utils.loadImageFromFile("images.png");
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
                this.image = Utils.loadImageFromFile("block.png");
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
                GameWindow.SQUARE_LENGTH,GameWindow.SQUARE_LENGTH,null);
    }
}
