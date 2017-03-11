package GameModels;

import Utils.Utils;

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

    public GameModel(int column, int row) {
        this.column = column;
        this.row = row;
    }
    public int getXPixel() {
        return Utils.convertColToXPixel(this.column);
    }
    public int getYPixel(){
        return Utils.convertRowToYPixel(this.row);
    }
}
