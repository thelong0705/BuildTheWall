import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Inpriron on 3/6/2017.
 */
public class Utils {
    public static Image loadImageFromFile(String url) {
        Image image = null;
        try {
            image = ImageIO.read(new File("resources/" + url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static int convertRowToYPixel(int row)
    {
        return 30+GameWindow.SQUARE_LENGTH*row;
    }
    public static int convertColToXPixel(int col)
    {
        return 10+GameWindow.SQUARE_LENGTH*col;
    }
    public static int convertYToRowArray(int y)
    {
        return  (y-30)/GameWindow.SQUARE_LENGTH;
    }
    public static int convertXtoColumnArray(int x)
    {
        return (x-10)/GameWindow.SQUARE_LENGTH;
    }
}
