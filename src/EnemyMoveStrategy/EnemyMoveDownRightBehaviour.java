package EnemyMoveStrategy;

import GameControllers.EnemyController;
import GameControllers.GameController;
import GameModels.EnemyModel;
import Program.GameWindow;

/**
 * Created by Inpriron on 3/12/2017.
 */
public class EnemyMoveDownRightBehaviour extends EnemyMoveBehaviour {
    @Override
    public void move(EnemyModel model, EnemyController controller) {
        model.moveDownRight(GameWindow.controllerManager.gameBoard,controller);
    }
}
