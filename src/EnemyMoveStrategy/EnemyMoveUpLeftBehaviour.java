package EnemyMoveStrategy;

import GameControllers.EnemyController;
import GameModels.EnemyModel;
import GUI.GameWindow;

/**
 * Created by Inpriron on 3/12/2017.
 */
public class EnemyMoveUpLeftBehaviour extends EnemyMoveBehaviour {
    @Override
    public void move(EnemyModel model) {
        model.moveUpLeft();
    }
}

