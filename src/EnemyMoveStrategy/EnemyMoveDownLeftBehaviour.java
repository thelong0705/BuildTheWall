package EnemyMoveStrategy;

import GameControllers.EnemyController;
import GameModels.EnemyModel;
import Program.GameWindow;

/**
 * Created by Inpriron on 3/12/2017.
 */
public class EnemyMoveDownLeftBehaviour extends EnemyMoveBehaviour {
    @Override
    public void move(EnemyModel model, EnemyController controller) {
        model.moveDownLeft(GameWindow.controllerManager.gameBoard,controller);
    }
}
