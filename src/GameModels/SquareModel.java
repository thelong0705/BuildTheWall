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

    public enumColor getColor() {
        return color;
    }

    public void setColor(enumColor color) {
        this.color = color;
    }

    public enumDirection getDirection() {
        return direction;
    }

    public void setDirection(enumDirection direction) {
        this.direction = direction;
    }

    public SquareModel(int row, int column, enumColor color) {
        super(row, column);
        this.color=color;
    }

}
