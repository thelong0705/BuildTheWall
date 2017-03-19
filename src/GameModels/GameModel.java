package GameModels;

import Utils.Utils;

import java.awt.*;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class GameModel {
    protected int column;
    protected int row;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public GameModel(int row, int column) {
        this.column = column;
        this.row = row;
    }
    public int getXPixel() {
        return Utils.convertColToXPixel(this.column);
    }
    public int getYPixel(){
        return Utils.convertRowToYPixel(this.row);
    }
    public Rectangle getRect() {
        return new Rectangle(getXPixel(), getYPixel(),SquareModel.SQUARE_LENGTH , SquareModel.SQUARE_LENGTH);
    }

    public boolean intersects(GameModel other) {
        Rectangle rect1 = this.getRect();
        Rectangle rect2 = other.getRect();
        return rect1.intersects(rect2);
    }
}
