import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
/**
 * Created by Millena on 31/03/2017.
 */
public class Exercício1 {

    public BufferedImage colorPalette(BufferedImage img) throws IOException
    {
        //pega o tamanho da imagem
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(),	BufferedImage.TYPE_INT_RGB);


        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //Le o pixel
                Color pixel = new Color(img.getRGB(x, y));

                //pega as cores
                float r = pixel.getRed();
                float g = pixel.getGreen();
                float b = pixel.getBlue();

                if (r < 40) {
                    r = 0;
                } else if(r >=40 && r < 120) {
                    r = 85;
                } else if(r >=120 && r < 215) {
                    r = 170;
                } else if (r >=215 && r < 255) {
                    r = 255;
                }

                if (g < 40) {
                    g = 0;
                } else if(g >=40 && g < 120) {
                    g = 85;
                } else if(g >=120 && g < 215) {
                    g = 170;
                } else if (g >=215 && g < 255) {
                    g = 255;
                }

                if (b < 40) {
                    b = 0;
                } else if(b >=40 && b < 120) {
                    b = 85;
                } else if(b >=120 && b < 215) {
                    b = 170;
                } else if (b >=215 && b < 255) {
                    b = 255;
                }


                Color pixel2 = new Color((int)r, (int)g, (int)b);

                out.setRGB(x,  y,  pixel2.getRGB());
            }
        }
        ImageIO.write(out, "png", new File("puppy_color.png"));

        return img;
    }

    public void run() throws IOException {

        BufferedImage img = ImageIO.read(new File("puppy.png"));
        colorPalette(img);

    }

    public static void main(String[] args) throws IOException {
        new Exercício1().run();

    }
}