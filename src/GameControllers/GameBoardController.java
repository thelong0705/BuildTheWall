package GameControllers;


import GameModels.DonaldTrumpModel;
import GameModels.EnemyModel;
import GameModels.SquareModel;
import GUI.GameWindow;
import Utils.Utils;

import java.awt.*;
import java.util.ArrayList;

import static GUI.GameWindow.FRAME_HEIGHT_SIZE;
import static GUI.GameWindow.FRAME_WIDTH_SIZE;
import static GameModels.SquareModel.enumColor.BLUE;
import static GameModels.SquareModel.enumColor.RED;
import static GameModels.SquareModel.enumColor.GRAY;
import static GameModels.SquareModel.enumColor.GREEN;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class GameBoardController {
    private int PLAYER_LIFE = 2;

    public DonaldTrumpController getDonaldTrumpController() {
        return donaldTrumpController;
    }


    public static final int NUMBER_OF_ROW = 30;
    public static final int NUMBER_OF_COLUMN = 40;

    private int moveDelay = 0;
    private SquareController[][] gameBoard;
    private DonaldTrumpController donaldTrumpController;
    private SquareController lastSquare;
    private ArrayList<EnemyController> enemyControllers;
    private ArrayList<SquareController> squarePlayerWentBy;
    private ArrayList<SquareController> blueSquareList;
    private float percentagePlayerFill;
    private Image image = Utils.loadImageFromFile("background.png");

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
                    if (i == 0 || i == NUMBER_OF_ROW - 1) {
                        gameBoard[i][j].setCelling(true);
                    }
                    if (j == 0 || j == NUMBER_OF_COLUMN - 1) {
                        gameBoard[i][j].setWall(true);
                    }
                }
            }
        }
        lastSquare = gameBoard[0][0];
        spawnEnemy(20, 30, 3, 4);
        spawnEnemy(20, 30, 5, 8);
    }

    public void run() {
        if (moveDelay == 0) {
            donaldTrumpController.run();
            controlDonaldTrumpMovement();
            if (checkFinishWall()) {
                {
                    fillGameBoard();
                    updatePercentage();
                }
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
        getSquarePlayerStanding().setCelling(true);
        getSquarePlayerStanding().setWall(true);
        fillBoardWithGreen();
        for (EnemyController enemyController : enemyControllers) {
            floodFill(enemyController.getRow(), enemyController.getColumn(), GREEN, GRAY);
        }
        colorBlueAndClearPlayerPath();
    }

    public void spawnEnemy(int row, int column, int xspeed, int yspeed) {
        EnemyController enemyController = new EnemyController(row, column, xspeed, yspeed);
        enemyControllers.add(enemyController);
    }

    public void runEnemy() {
        for (EnemyController enemyController : enemyControllers) {
            enemyController.run();
        }
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, 0, 0,
                FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE, null);
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                gameBoard[i][j].draw(graphics);
            }
        }
        donaldTrumpController.draw(graphics);

        for (EnemyController enemyController : enemyControllers) {
            enemyController.draw(graphics);
        }
        graphics.setFont(new Font(null, Font.BOLD, 15));
        graphics.setColor(Color.white);
        String scoreString = String.format("PERCENTAGE: %.0f", percentagePlayerFill);
        graphics.drawString(scoreString, 50, 50);
        String liveString=String.format("Lives: %d",((DonaldTrumpModel)donaldTrumpController.gameModel).getLives());
        graphics.drawString(liveString,200,50);
    }

    public SquareController getSquarePlayerStanding() {
        return gameBoard[donaldTrumpController.gameModel.getRow()][donaldTrumpController.gameModel.getColumn()];
    }

    public void controlDonaldTrumpMovement() {
        if (getSquarePlayerStanding().getColor() == BLUE ||
                getSquarePlayerStanding().getColor() == SquareModel.enumColor.GREEN) {
            GameWindow.isKeyDown = GameWindow.isKeyLeft = GameWindow.isKeyRight = GameWindow.isKeyUp = false;
        } else if (getSquarePlayerStanding().getColor() == RED) {
            donaldTrumpController.getHit();
            clearPlayerPathAfterGetHit();
        }
    }

    public void addToWentByList() {
        if (getSquarePlayerStanding().getColor() == SquareModel.enumColor.GRAY) {
            if (GameWindow.isKeyLeft || GameWindow.isKeyRight)
                getSquarePlayerStanding().setCelling(true);
            if (GameWindow.isKeyDown || GameWindow.isKeyUp)
                getSquarePlayerStanding().setWall(true);
            getSquarePlayerStanding().setColor(RED);
            squarePlayerWentBy.add(getSquarePlayerStanding());
        }
    }

    public void clearPlayerPathAfterGetHit() {
        ArrayList<SquareController> toRemove = new ArrayList<>();
        for (SquareController square : squarePlayerWentBy) {
            square.setColor(GRAY);
            toRemove.add(square);
            square.setCelling(false);
            square.setWall(false);
        }
        squarePlayerWentBy.removeAll(toRemove);
    }

    public void colorBlueAndClearPlayerPath() {
        ArrayList<SquareController> toRemove = new ArrayList<>();
        SquareController currentSquare = null;
        SquareController nextSquare = null;
        for (int i = 0; i < squarePlayerWentBy.size() - 1; i++) {

            currentSquare = squarePlayerWentBy.get(i);
            nextSquare = squarePlayerWentBy.get(i + 1);
            if (currentSquare.isCelling() && nextSquare.isWall())
                currentSquare.setWall(true);
            else if (currentSquare.isWall() && nextSquare.isCelling())
                currentSquare.setCelling(true);
            currentSquare.setColor(BLUE);
            blueSquareList.add(currentSquare);
            toRemove.add(currentSquare);
        }
        nextSquare.setColor(BLUE);
        blueSquareList.add(nextSquare);
        toRemove.add(nextSquare);
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

    //    private EnemyController enemyTemp;
    public void checkEnemyCollide() {
        checkEnemyBounceWall();
        checkEnemyHit();
    }

    public void checkEnemyHit() {
        for (EnemyController enemyController : enemyControllers) {
            for (SquareController squareController : squarePlayerWentBy) {
                if (enemyController.gameModel.intersects(squareController.gameModel)
                        || enemyController.gameModel.intersects(donaldTrumpController.gameModel)) {
                    System.out.println("wtf");
                    donaldTrumpController.getHit();
                    clearPlayerPathAfterGetHit();
                    break;
                }

            }
        }
    }

    public void checkEnemyBounceWall() {
        for (EnemyController enemyController : enemyControllers) {
            for (SquareController squareController : blueSquareList) {
                if (squareController.gameModel.intersects(enemyController.gameModel)) {
                    EnemyModel model = (EnemyModel) enemyController.gameModel;
                    if (squareController.isCelling() && squareController.isWall()) {

                        model.setXspeed(model.getXspeed() * -1);
                        model.setYspeed(model.getYspeed() * -1);
                    } else if (squareController.isCelling())
                        model.setYspeed(model.getYspeed() * -1);
                    else if (squareController.isWall())
                        model.setXspeed(model.getXspeed() * -1);
                    break;
                }
            }
        }
    }

    public void updatePercentage() {
        int squarePlayerTakeCounter = 0;
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                if (gameBoard[i][j].getColor() != GRAY)
                    squarePlayerTakeCounter++;
            }
        }
        percentagePlayerFill = (float) squarePlayerTakeCounter / (NUMBER_OF_ROW * NUMBER_OF_COLUMN) * 100;
    }

    public boolean checkWin() {
        if (percentagePlayerFill >= 80)
            return true;
        else
            return false;
    }
    public boolean checkLose()
    {
        DonaldTrumpModel model= (DonaldTrumpModel)donaldTrumpController.gameModel;
        if(model.getLives()<1)
            return true;
        else
            return false;
    }
}
