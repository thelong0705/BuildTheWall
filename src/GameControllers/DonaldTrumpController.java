package GameControllers;

import GameModels.DonaldTrumpModel;
import GameModels.GameModel;
import GameModels.SquareModel;
import GameViews.GameView;
import GUI.GameWindow;
import Utils.Utils;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class DonaldTrumpController extends GameController {
    public DonaldTrumpController(GameModel gameModel, GameView gameView) {
        super(gameModel, gameView);
    }

    public DonaldTrumpController(int row, int column, int lives) {
        this(new DonaldTrumpModel(row, column, lives), new GameView(Utils.loadImageFromFile("images.png")));
    }


    @Override
    public void run() {
        if (gameModel instanceof DonaldTrumpModel) {
            DonaldTrumpModel model = (DonaldTrumpModel) gameModel;

            if (GameWindow.isKeyRight)
                model.moveRight();
            else if (GameWindow.isKeyLeft)
                model.moveLeft();
            else if (GameWindow.isKeyUp)
                model.moveUp();
            else if (GameWindow.isKeyDown)
                model.moveDown();


//            if ((gameBoard[model.getRow()][model.getColumn()].getColor() == SquareModel.enumColor.BLUE
//                    || gameBoard[model.getRow()][model.getColumn()].getColor() == SquareModel.enumColor.GREEN)
//                    && GameWindow.cycleCounter % 3 == 0) {
//                GameWindow.isKeyRight = GameWindow.isKeyDown = GameWindow.isKeyLeft = GameWindow.isKeyUp = false;
        }

    }

//    public DonaldTrumpModel getDonaldTrumpModel() {
//        DonaldTrumpModel model = (DonaldTrumpModel) this.gameModel;
//        return model;
//    }
    public void hitRedWall()
    {
        DonaldTrumpModel model = (DonaldTrumpModel) this.gameModel;
        model.setRow(0);
        model.setColumn(0);
        model.setLives(model.getLives()-1);
    }

}
