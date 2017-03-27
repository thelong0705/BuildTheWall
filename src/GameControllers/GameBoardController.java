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


//    public DonaldTrumpController getDonaldTrumpController() {
//        return DonaldTrumpController.donaldTrumpInstance;
//    }


    public static final int NUMBER_OF_ROW = 30;
    public static final int NUMBER_OF_COLUMN = 40;

    protected int moveDelay = 0;
    protected SquareController[][] gameBoard;
    // protected DonaldTrumpController donaldTrumpController;
    protected SquareController lastSquare;
    protected ArrayList<EnemyController> enemyControllers;
    protected ArrayList<SquareController> squarePlayerWentBy;
    protected ArrayList<SquareController> blueSquareList;
    protected float percentagePlayerFill;
    protected Image image = Utils.loadImageFromFile("mexico.jpg");

    public GameBoardController() {
        gameBoard = new SquareController[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
        //donaldTrumpController = new DonaldTrumpController(0, 0, PLAYER_LIFE);
        enemyControllers = new ArrayList<>();
        squarePlayerWentBy = new ArrayList<>();
        initiateGameBoard();
    }


    public void initiateGameBoard() {
        buildBoard();
        spawnEnemy(Utils.convertColToXPixel(1), Utils.convertRowToYPixel(1), -5, -2, EnemyController.EnemyType.MEXICO);

        spawnEnemy(Utils.convertColToXPixel(38), Utils.convertRowToYPixel(28), 2, 5, EnemyController.EnemyType.MEXICO);
        spawnEnemy(Utils.convertColToXPixel(15), Utils.convertRowToYPixel(15), 3, 4, EnemyController.EnemyType.MEXICO);
    }

    public void buildBoard() {
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
    }

    public void run() {
        if (moveDelay == 0) {
            DonaldTrumpController.donaldTrumpInstance.run();
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
        SquareController squareGreen;
        for (EnemyController enemyController : enemyControllers) {
            if (gameBoard[enemyController.getRow()][enemyController.getColumn()].getColor() == BLUE) {
                squareGreen = findTheNearestGreen(gameBoard[enemyController.getRow()][enemyController.getColumn()]);
            } else
                squareGreen = gameBoard[enemyController.getRow()][enemyController.getColumn()];
            floodFill(squareGreen.getRow(), squareGreen.getColumn(), GREEN, GRAY);
        }
        colorBlueAndClearPlayerPath();
    }

    private SquareController findTheNearestGreen(SquareController squareController) {
        int row = squareController.getRow();
        int column = squareController.getColumn();
        SquareController north = null;
        SquareController south = null;
        SquareController west = null;
        SquareController east = null;
        if (row + 1 <= NUMBER_OF_ROW - 1) {
            south = gameBoard[row + 1][column];
            if (south.getColor() == GREEN)
                return south;
        }
        if (row - 1 >= 0) {
            north = gameBoard[row - 1][column];
            if (north.getColor() == GREEN)
                return north;
        }
        if (column + 1 <= NUMBER_OF_COLUMN - 1) {
            east = gameBoard[row][column + 1];
            if (east.getColor() == GREEN)
                return east;
        }
        if (column - 1 >= 0) {
            west = gameBoard[row][column - 1];
            if (west.getColor() == GREEN)
                return west;
        }
        return squareController;
    }


    public void spawnEnemy(int row, int column, int xspeed, int yspeed, EnemyController.EnemyType enemyType) {
        EnemyController enemyController = EnemyController.create(row, column, xspeed, yspeed, enemyType);
        enemyControllers.add(enemyController);
    }

    public void runEnemy() {
        for (EnemyController enemyController : enemyControllers) {
            enemyController.run();
        }
    }

    public void draw(Graphics graphics) {
        String scoreString = String.format("PERCENTAGE: %d/80", (int) percentagePlayerFill);
        String liveString = String.format("Lives: %d", ((DonaldTrumpModel) DonaldTrumpController.donaldTrumpInstance.gameModel).getLives());
        graphics.drawImage(image, 0, 0,
                FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE, null);

        if (checkWin()) {
            graphics.drawImage(image, 0, 0,
                    FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE, null);
            graphics.drawString(scoreString, 50, 50);
            graphics.drawString(liveString, 200, 50);
            String winString = "LEVEL PASSED PRESS SPACE TO MOVE ON TO THE NEXT LEVEL";
            graphics.setFont(new Font(null, Font.BOLD, 20));
            graphics.setColor(Color.red);
            graphics.drawString(winString, 100, FRAME_HEIGHT_SIZE / 2);
        } else {
            for (int i = 0; i < NUMBER_OF_ROW; i++) {
                for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                    gameBoard[i][j].draw(graphics);
                }
            }
            DonaldTrumpController.donaldTrumpInstance.draw(graphics);
            for (EnemyController enemyController : enemyControllers) {
                enemyController.draw(graphics);
            }
            graphics.setFont(new Font(null, Font.BOLD, 15));
            graphics.setColor(Color.white);
            graphics.drawString(scoreString, 50, 50);
            graphics.drawString(liveString, 200, 50);
        }
    }

    public SquareController getSquarePlayerStanding() {
        return gameBoard[DonaldTrumpController.donaldTrumpInstance.gameModel.getRow()][DonaldTrumpController.donaldTrumpInstance.gameModel.getColumn()];
    }

    public void controlDonaldTrumpMovement() {
        if (getSquarePlayerStanding().getColor() == BLUE ||
                getSquarePlayerStanding().getColor() == SquareModel.enumColor.GREEN) {
            GameWindow.isKeyDown = GameWindow.isKeyLeft = GameWindow.isKeyRight = GameWindow.isKeyUp = false;
        } else if (getSquarePlayerStanding().getColor() == RED) {
            DonaldTrumpController.donaldTrumpInstance.getHit();
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

        for (int i = 0; i < squarePlayerWentBy.size(); i++) {

            currentSquare = squarePlayerWentBy.get(i);
            if (i != squarePlayerWentBy.size() - 1) {
                nextSquare = squarePlayerWentBy.get(i + 1);
                if (currentSquare.isCelling() && nextSquare.isWall())
                    currentSquare.setWall(true);
                else if (currentSquare.isWall() && nextSquare.isCelling())
                    currentSquare.setCelling(true);
            }

            currentSquare.setColor(BLUE);
            blueSquareList.add(currentSquare);
            toRemove.add(currentSquare);
        }
        if (nextSquare != null) {
            nextSquare.setCelling(true);
            nextSquare.setWall(true);
            nextSquare.setColor(BLUE);
            blueSquareList.add(nextSquare);
            toRemove.add(nextSquare);
        }


        squarePlayerWentBy.removeAll(toRemove);
    }

    public boolean checkFinishWall() {
        if (getSquarePlayerStanding().getColor() == BLUE && lastSquare.getColor() == RED) {
            getSquarePlayerStanding().setWall(true);
            getSquarePlayerStanding().setCelling(true);
            return true;
        }
        return false;
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

    public void checkEnemyCollide() {
        checkEnemyBounceWall();
        checkEnemyHit();
    }

    public void checkEnemyHit() {
        for (EnemyController enemyController : enemyControllers) {
            for (SquareController squareController : squarePlayerWentBy) {
                if (enemyController.gameModel.intersects(squareController.gameModel)
                        || enemyController.gameModel.intersects(DonaldTrumpController.donaldTrumpInstance.gameModel)) {
                    DonaldTrumpController.donaldTrumpInstance.getHit();
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
//                    if (checkNextToCorner(squareController) || checkCorner(squareController)) {
//                        model.setYspeed(model.getYspeed() * -1);
//                        model.setXspeed(model.getXspeed() * -1);
//                    }
//
                    int xspeed = model.getXspeed();
                    int yspeed = model.getYspeed();
                    if (squareController.isCelling())
                        model.setYspeed(model.getYspeed() * -1);
                    if (squareController.isWall())
                        model.setXspeed(model.getXspeed() * -1);
                    EnemyController temp = EnemyController.create(model.getX(), model.getY(), model.getXspeed(),
                            model.getYspeed(), EnemyController.EnemyType.MEXICO);
                    temp.run();
                    for (SquareController squareTemp : blueSquareList) {
                        if (squareTemp.gameModel.intersects(temp.gameModel)) {
                            model.setXspeed(xspeed * -1);
                            model.setYspeed(yspeed * -1);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

//    public boolean checkNextToCorner(SquareController squareController) {
//        int row = squareController.getRow();
//        int column = squareController.getColumn();
//        SquareController north = null;
//        SquareController south = null;
//        SquareController west = null;
//        SquareController east = null;
//        if (row + 1 <= NUMBER_OF_ROW - 1)
//            south = gameBoard[row + 1][column];
//        if (row - 1 >= 0)
//            north = gameBoard[row - 1][column];
//        if (column + 1 <= NUMBER_OF_COLUMN - 1)
//            east = gameBoard[row][column + 1];
//        if (column - 1 >= 0)
//            west = gameBoard[row][column - 1];
//        if (checkCorner(south) || checkCorner(north) || checkCorner(west) || checkCorner(east))
//            return true;
//        return false;
//    }
//
//    public boolean checkCorner(SquareController squareController) {
//        if (squareController == null)
//            return false;
//        int row = squareController.getRow();
//        int column = squareController.getColumn();
//        if (gameBoard[row][column].isCelling() && gameBoard[row][column].isWall()) {
//            return true;
//        } else
//            return false;
//    }

    public void updatePercentage() {
        int squarePlayerTakeCounter = 0;
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                if (gameBoard[i][j].getColor() != GRAY)
                    squarePlayerTakeCounter++;
            }
        }
        percentagePlayerFill = ((float) squarePlayerTakeCounter) / (NUMBER_OF_ROW * NUMBER_OF_COLUMN) * 100;
    }

    public boolean checkWin() {
        if (percentagePlayerFill >= 80)
            return true;
        else
            return false;
    }

    public boolean checkLose() {
        DonaldTrumpModel model = (DonaldTrumpModel) DonaldTrumpController.donaldTrumpInstance.gameModel;
        if (model.getLives() < 1)
            return true;
        else
            return false;
    }
}
