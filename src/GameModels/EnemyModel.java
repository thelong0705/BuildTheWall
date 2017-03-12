package GameModels;

import Program.GameWindow;
import Utils.Utils;

/**
 * Created by Inpriron on 3/12/2017.
 */
public class EnemyModel extends GameModel {
    private  int x;
    private  int y;
    private  int speed;
    public EnemyModel(int row, int column) {
        super(row, column);
        x=Utils.convertColToXPixel(column);
        y=Utils.convertRowToYPixel(row);
    }
}
