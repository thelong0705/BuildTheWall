import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Inpriron on 3/6/2017.
 */

public class GameWindow extends Frame {
    private BufferedImage backBufferImage;
    private Graphics backGraphics;
    public static final int NUMBER_OF_ROW = 30;
    public static final int NUMBER_OF_COLUMN = 40;
    public static final int SQUARE_LENGTH = 20;
    public static final int FRAME_HEIGHT_SIZE = NUMBER_OF_ROW * SQUARE_LENGTH +40;
    public static final int FRAME_WIDTH_SIZE = (NUMBER_OF_COLUMN)* SQUARE_LENGTH+20;
    Thread thread;
    static Square[][] blockArray = new Square[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
    public static int cycleCounter=0;
    public PlayerDonaldTrump donaldTrump;
    public boolean isKeyLeft = false;
    public boolean isKeyRight = false;
    public boolean isKeyUp = false;
    public boolean isKeyDown = false;
    public static ArrayList<Square> pool1=new ArrayList<>();
    public static ArrayList<Square> pool2=new ArrayList<>();
    public static boolean pool1HasEnemy=false;
    public static boolean pool2HasEnemy=false;
    public GameWindow() {
        setVisible(true);
        setSize(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE);
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
                        isKeyRight = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        isKeyLeft = true;
                        break;
                    case KeyEvent.VK_UP:
                        isKeyUp = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        isKeyDown = true;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        isKeyRight = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        isKeyLeft = false;
                        break;
                    case KeyEvent.VK_UP:
                        isKeyUp = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        isKeyDown = false;
                        break;
                }
            }
        });
        backBufferImage = new BufferedImage(
                FRAME_WIDTH_SIZE,
                FRAME_HEIGHT_SIZE,
                BufferedImage.TYPE_INT_ARGB);
        backGraphics = backBufferImage.getGraphics();
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                blockArray[i][j] = new Square(i, j, Square.enumColor.GRAY);
                if (i == 0 || j == 0 || i == NUMBER_OF_ROW - 1 || j == NUMBER_OF_COLUMN - 1)
                    blockArray[i][j] = new Square(i, j, Square.enumColor.BLUE);
            }
        }
        donaldTrump = new PlayerDonaldTrump(0, 0, Utils.loadImageFromFile("images.png"));
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (isKeyRight&&cycleCounter%3==0)
                        donaldTrump.moveRight();
                    if (isKeyLeft&&cycleCounter%3==0)
                        donaldTrump.moveLeft();
                    if (isKeyUp&cycleCounter%3==0)
                        donaldTrump.moveUp();
                    if (isKeyDown&cycleCounter%3==0)
                        donaldTrump.moveDown();
                    if( blockArray[donaldTrump.row][donaldTrump.column].color== Square.enumColor.GRAY)
                        blockArray[donaldTrump.row][donaldTrump.column].color= Square.enumColor.RED;
                    blockArray[donaldTrump.row][donaldTrump.column].setPictureForColor();

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
            for (int i = 0; i < NUMBER_OF_ROW; i++) {
                for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                    Square square = blockArray[i][j];

                    square.draw(backGraphics);
                }
            }
            if (donaldTrump != null)
                donaldTrump.draw(backGraphics);
            graphics.drawImage(backBufferImage, 0, 0, null);
        }
    }
    public static void floodFill(int row, int column, Square.enumColor sourceColor, Square.enumColor desColor)
    {
        if (row < 0) return;
        if (column < 0) return;
        if(row>=NUMBER_OF_ROW) return;
        if(column>=NUMBER_OF_COLUMN)return;
        if(blockArray[row][column].color!=sourceColor) return;
        blockArray[row][column].color=desColor;
        blockArray[row][column].setPictureForColor();
        floodFill(row-1,column,sourceColor,desColor);
        floodFill(row+1,column,sourceColor,desColor);
        floodFill(row,column-1,sourceColor,desColor);
        floodFill(row,column+1,sourceColor,desColor);
    }
    public static void floodFillpool1(int row, int column, Square.enumColor sourceColor, Square.enumColor desColor)
    {
        if (row < 0) return;
        if (column < 0) return;
        if(row>=NUMBER_OF_ROW) return;
        if(column>=NUMBER_OF_COLUMN)return;
        if(blockArray[row][column].color!=sourceColor) return;
        blockArray[row][column].color=desColor;
        pool1.add(blockArray[row][column]);
        if(row==20&&column==30)
        {
            pool1HasEnemy=true;
        }
        floodFillpool1(row-1,column,sourceColor,desColor);
        floodFillpool1(row+1,column,sourceColor,desColor);
        floodFillpool1(row,column-1,sourceColor,desColor);
        floodFillpool1(row,column+1,sourceColor,desColor);
    }
    public static void floodFillpool2(int row, int column, Square.enumColor sourceColor, Square.enumColor desColor)
    {
        if (row < 0) return;
        if (column < 0) return;
        if(row>=NUMBER_OF_ROW) return;
        if(column>=NUMBER_OF_COLUMN)return;
        if(blockArray[row][column].color!=sourceColor) return;
        blockArray[row][column].color=desColor;
        pool2.add(blockArray[row][column]);
        if(row==20&&column==30)
        {
            pool2HasEnemy=true;
        }
        floodFillpool2(row-1,column,sourceColor,desColor);
        floodFillpool2(row+1,column,sourceColor,desColor);
        floodFillpool2(row,column-1,sourceColor,desColor);
        floodFillpool2(row,column+1,sourceColor,desColor);
    }
}
