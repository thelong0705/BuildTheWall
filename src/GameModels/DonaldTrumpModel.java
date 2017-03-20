package GameModels;

//import GameControllers.EnemyController;
import GameControllers.GameBoardController;
import GameControllers.SquareController;
import GUI.GameWindow;

import Utils.Utils;


import java.util.ArrayList;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class DonaldTrumpModel extends GameModel {
    private int lives;
    public DonaldTrumpModel(int row, int column, int lives) {
        super(row, column);
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }


    public void moveUp() {
        if(row-1>=0)
        {
            row--;
        }
    }

    public void moveDown() {
        if(row+1<= GameBoardController.NUMBER_OF_ROW-1)
        {
            row++;
        }
    }

    public void moveRight() {

       if(column+1<=GameBoardController.NUMBER_OF_COLUMN-1)
        {
            column++;
        }
    }

    public void moveLeft() {
        if(column-1>=0)
        {
            column--;
        }

    }

}
