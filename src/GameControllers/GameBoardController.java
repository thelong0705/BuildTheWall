package GameControllers;


import GUI.GameFrame;
import GameModels.EnemyModel;
import GameModels.SquareModel;
import GUI.GameWindow;
import Utils.Utils;

import java.awt.*;
import java.util.ArrayList;

import static GameModels.SquareModel.enumColor.BLUE;
import static GameModels.SquareModel.enumColor.RED;
import static GameModels.SquareModel.enumColor.GRAY;
import static GameModels.SquareModel.enumColor.GREEN;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class GameBoardController {
    private int PLAYER_LIFE = 4;

    public DonaldTrumpController getDonaldTrumpController() {
        return donaldTrumpController;
    }


    public static final int NUMBER_OF_ROW = 30;
    public static final int NUMBER_OF_COLUMN = 40;
    public static final int GAME_BOARD_UP_BORDER = Utils.convertRowToYPixel(0);
    public static final int GAME_BOARD_LEFT_BORDER = Utils.convertColToXPixel(0);
    public static final int GAME_BOARD_DOWN_BORDER = Utils.convertRowToYPixel(NUMBER_OF_ROW - 1);
    public static final int GAME_BOARD_RIGHT_BORDER = Utils.convertColToXPixel(NUMBER_OF_COLUMN - 1);

    private int moveDelay = 0;
    private SquareController[][] gameBoard;
    private DonaldTrumpController donaldTrumpController;
    private SquareController lastSquare;
    private ArrayList<EnemyController> enemyControllers;
    private ArrayList<SquareController> squarePlayerWentBy;
    private ArrayList<SquareController> blueSquareList;

    public GameBoardController() {
        gameBoard = new SquareController[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
        donaldTrumpController = new DonaldTrumpController(0, 0, PLAYER_LIFE);
        enemyControllers = new ArrayList<>();
        squarePlayerWentBy = new ArrayList<>();
        initiateGameBoard();
    }


    public void initiateGameBoard() {
        blueSquareList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                gameBoard[i][j] = new SquareController(i, j, SquareModel.enumColor.GRAY);
                if (i == 0 || j == 0 || i == NUMBER_OF_ROW - 1 || j == NUMBER_OF_COLUMN - 1)

                {
                    gameBoard[i][j] = new SquareController(i, j, BLUE);
                    blueSquareList.add(gameBoard[i][j]);
                }
            }
        }
        lastSquare = gameBoard[0][0];
        spawnEnemy(20, 30, 3);
//        spawnEnemy(5, 10, 3);
    }

    public void run() {
        if (moveDelay == 0) {
            donaldTrumpController.run();
            controlDonaldTrumpMovement();
            if (checkFinishWall()) {
                fillGameBoard();
            } else
                addToWentByList();
            lastSquare = getSquarePlayerStanding();
        }
        runEnemy();
        checkEnemyCollide();
        moveDelay++;
        if (moveDelay > 2)
            moveDelay = 0;

    }

    private void fillGameBoard() {
        fillBoardWithGreen();
        for (EnemyController enemyController : enemyControllers) {
            floodFill(enemyController.getRow(), enemyController.getColumn(), GREEN, GRAY);
        }
        colorBlueAndClearPlayerPath();

    }

    public void spawnEnemy(int row, int column, int speed) {
        EnemyController enemyController = new EnemyController(row, column, speed);
        enemyControllers.add(enemyController);
    }

    public void runEnemy() {
        for (EnemyController enemyController : enemyControllers) {
            enemyController.run();
        }
    }

    public void draw(Graphics graphics) {
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                gameBoard[i][j].draw(graphics);
            }
        }
        donaldTrumpController.draw(graphics);

        for (EnemyController enemyController : enemyControllers) {
            enemyController.draw(graphics);
        }
    }

    public SquareController getSquarePlayerStanding() {
        return gameBoard[donaldTrumpController.gameModel.getRow()][donaldTrumpController.gameModel.getColumn()];
    }

    public void controlDonaldTrumpMovement() {
        if (getSquarePlayerStanding().getColor() == BLUE ||
                getSquarePlayerStanding().getColor() == SquareModel.enumColor.GREEN) {
            GameWindow.isKeyDown = GameWindow.isKeyLeft = GameWindow.isKeyRight = GameWindow.isKeyUp = false;
        } else if (getSquarePlayerStanding().getColor() == RED) {
            donaldTrumpController.hitRedWall();
            clearPlayerPath();
        }
    }

    public void addToWentByList() {
        if (getSquarePlayerStanding().getColor() == SquareModel.enumColor.GRAY) {
            getSquarePlayerStanding().setColor(RED);
            squarePlayerWentBy.add(getSquarePlayerStanding());
        }
    }

    public void clearPlayerPath() {
        ArrayList<SquareController> toRemove = new ArrayList<>();
        for (SquareController square : squarePlayerWentBy) {
            square.setColor(GRAY);
            toRemove.add(square);
        }
        squarePlayerWentBy.removeAll(toRemove);
    }

    public void colorBlueAndClearPlayerPath() {
        ArrayList<SquareController> toRemove = new ArrayList<>();
        for (SquareController square : squarePlayerWentBy) {
            square.setColor(BLUE);
            blueSquareList.add(square);
            toRemove.add(square);
        }
        squarePlayerWentBy.removeAll(toRemove);
    }

    public boolean checkFinishWall() {
        return getSquarePlayerStanding().getColor() == BLUE && lastSquare.getColor() == RED;
    }

    public void floodFill(int row, int column, SquareModel.enumColor sourceColor,
                          SquareModel.enumColor desColor) {
        if (row < 0) return;
        if (column < 0) return;
        if (row >= NUMBER_OF_ROW) return;
        if (column >= NUMBER_OF_COLUMN) return;
        if (gameBoard[row][column].getColor() != sourceColor) return;
        gameBoard[row][column].setColor(desColor);
        floodFill(row - 1, column, sourceColor, desColor);
        floodFill(row + 1, column, sourceColor, desColor);
        floodFill(row, column - 1, sourceColor, desColor);
        floodFill(row, column + 1, sourceColor, desColor);
    }

    public void fillBoardWithGreen() {
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                if (gameBoard[i][j].getColor() == GRAY)
                    gameBoard[i][j].setColor(GREEN);
            }

        }
    }
    private EnemyController enemyTemp;
    public void checkEnemyCollide() {
        for (SquareController squareController : blueSquareList) {
            for (EnemyController enemyController : enemyControllers) {
                if (squareController.gameModel.intersects(enemyController.gameModel)) {
//                    enemyTemp = enemyController.clone();
//                    while (squareController.gameModel.intersects(enemyTemp.gameModel)) {
//                        enemyTemp.setEnemyMoveBehaviour(enemyTemp.getRanDomMoveBehavior());
//                        enemyTemp.run();
//                    }

//                    do{
//                        System.out.println("Square: "+squareController.gameModel.getXPixel()+" "+squareController.gameModel.getYPixel());
//                        enemyTemp= enemyController.clone();
//                        EnemyModel model= (EnemyModel) enemyTemp.gameModel;
//                        System.out.println(model.getX()+" "+model.getY());
//                        enemyTemp.setEnemyMoveBehaviour(enemyTemp.getRanDomMoveBehavior());
//                        enemyTemp.run();
//                        System.out.println(model.getX()+" "+model.getY());
//                    }while(squareController.gameModel.intersects(enemyTemp.gameModel));
//                    enemyController.setEnemyMoveBehaviour(enemyTemp.getEnemyMoveBehaviour());
//                    enemyController.run();
                }
            }
        }
    }
}
