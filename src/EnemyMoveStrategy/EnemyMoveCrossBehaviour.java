package EnemyMoveStrategy;

import GameModels.EnemyModel;

/**
 * Created by Inpriron on 3/20/2017.
 */
public class EnemyMoveCrossBehaviour extends EnemyMoveBehaviour {
    @Override
    public void move(EnemyModel model) {
        model.moveCross();
    }
}
