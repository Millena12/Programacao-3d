
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Millena on 31/03/2017.
 */
public class Exercício2 {

    float[][] contrast = {
            {0,     -0.25f,  0},
            {-0.25f, 2,     -0.25f},
            {0,     -0.25f,  0},
    };

    public void pixelateFile(BufferedImage img, int pixelSize) throws IOException
    {
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),	BufferedImage.TYPE_INT_RGB);

        for(int y = 0 ; y < img.getHeight() ; y+=pixelSize)
        {
            for(int x = 0 ; x < img.getWidth() ; x+=pixelSize)
            {
                Color pixelColor = new Color(img.getRGB(x, y));

                Graphics graphics = img.getGraphics();
                graphics.setColor(pixelColor);
                graphics.fillRect(x, y, pixelSize, pixelSize);
            }
        }

        // output file
        ImageIO.write(img, "jpg", new File("puppy_pixel1.png"));

    }

    public void run() throws IOException {
        BufferedImage img = ImageIO.read(new File("puppy.png"));
        pixelateFile(img, 10);
    }

    public static void main(String[] args) throws IOException {
        new Exercício2().run();

    }
}
