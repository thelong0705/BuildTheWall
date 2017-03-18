package Program;

import GUI.GameFrame;
import GUI.MainPanel;
import GameControllers.ControllerManager;
import Utils.Utils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Inpriron on 3/6/2017.
 */

public class GameWindow extends Frame {
    private BufferedImage backBufferImage;
    private Graphics backGraphics;
    public static final int NUMBER_OF_ROW = 30;
    public static final int NUMBER_OF_COLUMN = 40;
    public static final int SQUARE_LENGTH = 20;
    public static final int FRAME_HEIGHT_SIZE = NUMBER_OF_ROW * SQUARE_LENGTH + 40;
    public static final int FRAME_WIDTH_SIZE = (NUMBER_OF_COLUMN) * SQUARE_LENGTH + 20;
    Thread thread;
    public static final int PLAYER_LIFE=4;
//    static Square[][] blockArray = new Square[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
    public static ControllerManager controllerManager;
    public static int cycleCounter = 0;
   // public PlayerDonaldTrump donaldTrump;
    public static boolean isKeyLeft = false;
    public static boolean isKeyRight = false;
    public static boolean isKeyUp = false;
    public static boolean isKeyDown = false;
    public static int blueSquare=0;
    public GameWindow() {
        blueSquare=0;
        setVisible(true);
        setSize(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE);
        setLocationRelativeTo(null);
        setAutoRequestFocus(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                super.keyTyped(keyEvent);
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                       isKeyRight=true;
                       isKeyDown=false;
                       isKeyUp=false;
                       isKeyLeft=false;
                        break;
                    case KeyEvent.VK_LEFT:
                        isKeyLeft=true;
                        isKeyRight=false;
                        isKeyDown=false;
                        isKeyUp=false;
                        break;
                    case KeyEvent.VK_UP:
                        isKeyUp=true;
                        isKeyLeft=false;
                        isKeyRight=false;
                        isKeyDown=false;
                        break;
                    case KeyEvent.VK_DOWN:
                        isKeyDown=true;
                        isKeyUp=false;
                        isKeyLeft=false;
                        isKeyRight=false;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
//                        isKeyRight = false;
                        break;
                    case KeyEvent.VK_LEFT:
//                        isKeyLeft = false;
                        break;
                    case KeyEvent.VK_UP:
//                        isKeyUp = false;
                        break;
                    case KeyEvent.VK_DOWN:
//                        isKeyDown = false;
                        break;
                }
            }
        });
        backBufferImage = new BufferedImage(
                FRAME_WIDTH_SIZE,
                FRAME_HEIGHT_SIZE,
                BufferedImage.TYPE_INT_ARGB);
        backGraphics = backBufferImage.getGraphics();
        controllerManager= new ControllerManager();
        controllerManager.initiateGameBoard();
        controllerManager.spawnEnemy(20,30);
        controllerManager.spawnEnemy(15,10);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(blueSquare>960)
                    {
                        System.out.println("win");
                        dispose();
                        GameFrame.mainPanel.showPanel(MainPanel.TAG_WIN);
                        Main.gameFrame.setVisible(true);
                        break;
                    }
                    controllerManager.donaldTrumpController.run();
                    controllerManager.runEnemy();
                    repaint();
                    cycleCounter++;
                }
            }
        });
        thread.start();
    }

    @Override
    public void update(Graphics graphics) {
        if (backBufferImage != null) {
            controllerManager.drawGameBoard(backGraphics);
            controllerManager.donaldTrumpController.draw(backGraphics);
            controllerManager.drawEnemy(backGraphics);
            graphics.drawImage(backBufferImage, 0, 0, null);
        }
    }

    public void checkWin()
    {

    }
}