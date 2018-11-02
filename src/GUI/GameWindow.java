package GUI;

import GameControllers.GameBoardController;
import GameControllers.GameBoardControllerLevel2;
import GameControllers.GameBoardControllerLevel3;
import Program.Main;

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
    public static final int FRAME_HEIGHT_SIZE = 30 * 20 + 40 + 20;//NUMBER_OF_ROW * SQUARE_LENGTH + 40;
    public static final int FRAME_WIDTH_SIZE = 40 * 20 + 20;//(NUMBER_OF_COLUMN) * SQUARE_LENGTH + 20;
    Thread thread;
    private GameBoardController gameBoardController;
    public static boolean isKeyLeft;
    public static boolean isKeyRight;
    public static boolean isKeyUp ;
    public static boolean isKeyDown;
    public boolean isEnter;
    private int level=1;
    public GameWindow() {
        isKeyLeft = false;
        isKeyRight = false;
        isKeyUp = false;
        isKeyDown = false;
        isEnter = false;
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
                    case KeyEvent.VK_UP:
                        if (!isKeyDown) {
                            isKeyUp = true;
                            isKeyDown = false;
                            isKeyLeft = false;
                            isKeyRight = false;
                        }

                        break;
                    case KeyEvent.VK_DOWN:
                        if (!isKeyUp) {
                            isKeyDown = true;
                            isKeyUp = false;
                            isKeyLeft = false;
                            isKeyRight = false;
                        }

                        break;
                    case KeyEvent.VK_LEFT:
                        if (!isKeyRight) {
                            isKeyLeft = true;
                            isKeyRight = false;
                            isKeyUp = false;
                            isKeyDown = false;
                        }

                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!isKeyLeft) {
                            isKeyRight = true;
                            isKeyLeft = false;
                            isKeyUp = false;
                            isKeyDown = false;
                        }
                        break;
                    case KeyEvent.VK_ENTER:
                        isEnter = true;
                        break;
                }
            }

        });
        backBufferImage = new BufferedImage(
                FRAME_WIDTH_SIZE,
                FRAME_HEIGHT_SIZE,
                BufferedImage.TYPE_INT_ARGB);   
        gameBoardController = new GameBoardController();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (gameBoardController.checkWin()) {
                        if(isEnter)
                        {
                            isEnter =false;
                            level++;
                            if(level==2) {
                                gameBoardController = new GameBoardControllerLevel2();
                            }
                            else if(level==3)
                            {
                                gameBoardController= new GameBoardControllerLevel3();
                            }
                            else
                            {
                                level=1;
                                GameFrame.mainPanel.showPanel(MainPanel.TAG_WIN);
                                Main.gameFrame.setVisible(true);
                                dispose();
                            }
                        }

                    } else if (gameBoardController.checkLose()) {
                        GameFrame.mainPanel.showPanel(MainPanel.TAG_LOSE);
                        Main.gameFrame.setVisible(true);
                        dispose();
                        break;
                    } else {
                        gameBoardController.run();
                        repaint();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void update(Graphics graphics) {
        if (backBufferImage != null) {

            backGraphics = backBufferImage.getGraphics();
            gameBoardController.draw(backGraphics);
            graphics.drawImage(backBufferImage, 0, 0, null);

        }
    }


}