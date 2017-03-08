import java.awt.*;

/**
 * Created by Inpriron on 3/8/2017.
 */
public class PlayerDonaldTrump {

    int row;
    int column;
    Image image;
    public PlayerDonaldTrump(int row, int column, Image image) {
        this.row=row;
        this.column = column;
        this.image = image;

    }

    public void moveUp() {
       if(row-1>=0)
            row--;
    }

    public void moveDown() {
       if(row+1<=GameWindow.NUMBER_OF_ROW)
           row++;
    }

    public void moveRight() {
        if(column +1<=GameWindow.NUMBER_OF_COLUMN)
            column++;
    }

    public void moveLeft() {
        if(column -1>=0)
            column--;
    }
    public int getXPixel() {
        return Utils.convertColToXPixel(this.column);
    }
    public int getYPixel(){
        return Utils.convertRowToYPixel(this.row);
    }
    public void draw(Graphics graphics) {
        graphics.drawImage(image, getXPixel(), getYPixel(), GameWindow.SQUARE_LENGTH,GameWindow.SQUARE_LENGTH, null);
    }
}
