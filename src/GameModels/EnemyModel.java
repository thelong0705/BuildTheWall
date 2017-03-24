package GameModels;
//
//import EnemyMoveStrategy.EnemyMoveDownLeftBehaviour;
//import EnemyMoveStrategy.EnemyMoveDownRightBehaviour;
//import EnemyMoveStrategy.EnemyMoveUpLeftBehaviour;
//import EnemyMoveStrategy.EnemyMoveUpRightBehaviour;
//import GameControllers.EnemyController;

import GameControllers.GameBoardController;
import GameControllers.SquareController;
import GUI.GameWindow;
import Utils.Utils;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Inpriron on 3/12/2017.
 */
public class EnemyModel extends GameModel {
    private int x;
    private int y;
    private int xspeed;
    private int yspeed;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public EnemyModel(int x, int y, int xspeed, int yspeed) {
        super(0, 0);
        this.x =x;
        this.y = y;
        this.xspeed=xspeed;
        this.yspeed=yspeed;
    }


    public int getXspeed() {
        return xspeed;
    }

    public void setXspeed(int xspeed) {
        this.xspeed = xspeed;
    }

    public int getYspeed() {
        return yspeed;
    }

    public void setYspeed(int yspeed) {
        this.yspeed = yspeed;
    }

    public void moveCross()
    {
        x += xspeed;
        y += yspeed;

    }
    @Override
    public int getRow() {
        return (y - 50) / 20;
    }

    public int getColumn() {
        return (x - 10) / 20;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(x, y ,SquareModel.SQUARE_LENGTH , SquareModel.SQUARE_LENGTH);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}
