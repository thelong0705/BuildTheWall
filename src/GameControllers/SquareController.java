package GameControllers;

import GameModels.GameModel;
import GameModels.SquareModel;
import GameViews.GameView;

import Utils.Utils;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class SquareController extends GameController {
    private boolean isCelling;
    private boolean isWall;

    public boolean isCelling() {
        return isCelling;
    }

    public void setCelling(boolean celling) {
        isCelling = celling;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public SquareController(GameModel gameModel, GameView gameView) {
        super(gameModel, gameView);
    }
    public SquareController(int row, int column, SquareModel.enumColor color)
    {
        this( new SquareModel(row,column,color), new GameView(null));
        this.setColor(color);
    }
    public void setColor(SquareModel.enumColor color)
    {
        if(gameModel instanceof SquareModel)
        {
            SquareModel model= (SquareModel)gameModel;
            model.setColor(color);
            switch (color) {
                case BLUE:
                    gameView.setImage(Utils.loadImageFromFile("block.png"));
                    break;
                case GRAY:
                    gameView.setImage(Utils.loadImageFromFile("blockgray.png"));
                    break;
                case RED:
                    gameView.setImage(Utils.loadImageFromFile("block2.png"));
                    break;
                case GREEN:
                    gameView.setImage(Utils.loadImageFromFile("block.png"));
                    break;
                default:
                    System.out.println("Invalid Color");
                    break;
            }
        }
    }
    public SquareModel.enumColor getColor()
    {
        if(gameModel instanceof SquareModel)
        {
            SquareModel model= (SquareModel)gameModel;
            return model.getColor();
        }
        return null;
    }

    public int getColumn()
    {
        if(gameModel instanceof SquareModel)
        {
            SquareModel model= (SquareModel)gameModel;
            return model.getColumn();
        }
        return -1;
    }
    public int getRow()
    {
        if(gameModel instanceof SquareModel)
        {
            SquareModel model= (SquareModel)gameModel;
            return model.getRow();
        }
        return -1;
    }
}
