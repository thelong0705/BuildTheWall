package GameControllers;

import GameModels.DonaldTrumpModel;
import GameModels.GameModel;
import GameModels.SquareModel;
import GameViews.GameView;
import Program.GameWindow;
import Program.Square;
import Utils.Utils;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class DonaldTrumpController extends GameController {
    public DonaldTrumpController(GameModel gameModel, GameView gameView) {
        super(gameModel, gameView);
    }

    public DonaldTrumpController(int row, int column) {
        this(new DonaldTrumpModel(row, column), new GameView(Utils.loadImageFromFile("images.png")));
    }

    @Override
    public void run() {
        if (gameModel instanceof DonaldTrumpModel) {
            SquareController[][] gameBoard=GameWindow.controllerManager.gameBoard;
            DonaldTrumpModel model = (DonaldTrumpModel) gameModel;
            if (GameWindow.isKeyLeft && GameWindow.cycleCounter % 3 == 0)
                model.moveLeft(gameBoard);
            if (GameWindow.isKeyRight && GameWindow.cycleCounter % 3 == 0)
                model.moveRight(gameBoard);
            if (GameWindow.isKeyUp && GameWindow.cycleCounter % 3 == 0)
                model.moveUp(gameBoard);
            if (GameWindow.isKeyDown && GameWindow.cycleCounter % 3 == 0)
                model.moveDown(gameBoard);
            if ((gameBoard[model.getRow()][model.getColumn()].getColor() == SquareModel.enumColor.BLUE
                    || gameBoard[model.getRow()][model.getColumn()].getColor() == SquareModel.enumColor.GREEN)
                    && GameWindow.cycleCounter % 3 == 0) {
                GameWindow.isKeyRight = GameWindow.isKeyDown = GameWindow.isKeyLeft = GameWindow.isKeyUp = false;
            }
        }

    }


}
