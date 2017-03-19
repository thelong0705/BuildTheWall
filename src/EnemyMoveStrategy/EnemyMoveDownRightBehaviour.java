package EnemyMoveStrategy;

        import GameControllers.EnemyController;
        import GameModels.EnemyModel;
        import GUI.GameWindow;

/**
 * Created by Inpriron on 3/12/2017.
 */
public class EnemyMoveDownRightBehaviour extends EnemyMoveBehaviour {
    @Override
    public void move(EnemyModel model) {
        model.moveDownRight();
    }
}
