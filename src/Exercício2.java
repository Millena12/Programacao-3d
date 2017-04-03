
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

    public static void Convolve(BufferedImage img, float[][] kernel)
    {
        BufferedImage out = new BufferedImage(img.getWidth(),img.getHeight(), BufferedImage.TYPE_INT_RGB);

        int r = 0, g = 0, b = 0;

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                r=0;
                g=0;
                b=0;

                for(int i = 0; i < kernel.length; i++ ) {
                    for(int j = 0; j < kernel.length; j++) {
                        if(x + i >= 0 && y + j >= 0 && x + i < img.getWidth() && y + j <img.getHeight()) {
                            r += (int)(new Color(img.getRGB(x+i, y+j)).getRed()*kernel[i][j]);
                            g += (int)(new Color(img.getRGB(x+i, y+j)).getGreen()*kernel[i][j]);
                            b += (int)(new Color(img.getRGB(x+i, y+j)).getBlue()*kernel[i][j]);

                        } else {
                            r += 0;
                            g += 0;
                            b += 0;
                        }
                    }
                }

                r = r < 0 ? r = 0 :(r > 255 ? r = 255: r);
                g = g < 0 ? g = 0 :(g > 255 ? g = 255: g);
                b = b < 0 ? b = 0 :(b > 255 ? b = 255: b);

                out.setRGB(x, y, new Color(r,g,b).getRGB());
            }
        }

        ImageIO.write(img, "png", new File("puppy_pixel1.png"));

    }

    public BufferedImage pixelateFile(BufferedImage img, int pixelSize) throws IOException
    {
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),	BufferedImage.TYPE_INT_RGB);

        for(int y = 0 ; y < img.getHeight() ; y+=pixelSize)
        {
            for(int x = 0 ; x < img.getWidth() ; x+=pixelSize)
            {
                Color pixelColor = new Color(img.getRGB(x, y));
                for (int y2 = 0; y2 < pixelSize; y2++) {
                    for (int x2 = 0; x2 < pixelSize; x2++) {
                        if((x+x2) < img.getWidth()) && (y+y2) < img.getHeight()) {
                            img.setRGB(x2 + x, y2 + y, pixelColor.getRBG());
                        }
                    }
                }
            }
        }

        // output file
       return out;
    }

    public void run() throws IOException {
        BufferedImage img = ImageIO.read(new File("puppy.png"));
        BufferedImage img1;
        img1=pixelateFile(img, 10);

        img1 = Convolve(img1,contrast);

        ImageIO.write(img1, "png", new File("puppy_pixel_contrast.png"));
    }

    public static void main(String[] args) throws IOException {
        new Exercício2().run();

    }
}
