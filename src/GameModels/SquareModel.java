package GameModels;

import Utils.Utils;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class SquareModel extends GameModel {

    public enum enumColor {
        GRAY, BLUE, RED,GREEN
    }
    public static final int SQUARE_LENGTH=20;
    private enumColor color;

    public enumColor getColor() {
        return color;
    }

    public void setColor(enumColor color) {
        this.color = color;
    }


    public SquareModel(int row, int column, enumColor color) {
        super(row, column);
        this.color=color;
    }

}
