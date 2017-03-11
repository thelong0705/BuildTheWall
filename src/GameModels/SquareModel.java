package GameModels;

import Utils.Utils;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class SquareModel extends GameModel {

    public enum enumColor {
        GRAY, BLUE, RED,GREEN
    }
    public enum enumDirection{
        UP,DOWN,LEFT,RIGHT
    }
    private enumColor color;
    private enumDirection direction;
    public SquareModel(int column, int row,enumColor color) {
        super(column, row);
        this.color=color;
    }

}
