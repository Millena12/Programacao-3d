import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Millena on 31/03/2017.
 */
public class Exercicio3 {

    public static int[][] HistTablePB(BufferedImage img){
            int histTable[][] = new int[256][4];
            int media = 0;

            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    Color pixelColor = new Color(img.getRGB(x, y));
                    media = (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3;
                    histTable[media][3]++;
                }
            }


            for (int y = 0; y < img.getHeight(); y++)
            {
                for (int x = 0; x < img.getWidth(); x++)
                {
                    Color pixel = new Color(img.getRGB(x, y));
                    media = pixel.getRed();
                    histTable[media][0]++;
                }
            }

            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    Color pixel = new Color(img.getRGB(x, y));
                    media = pixel.getGreen();
                    histTable[media][1]++;
                }
            }

            for (int y = 0; y < img.getHeight(); y++)
            {
                for (int x = 0; x < img.getWidth(); x++)
                {
                    Color pixel = new Color(img.getRGB(x, y));
                    media = pixel.getBlue();
                    histTable[media][2]++;
                }
            }

            return histTable;
        }

    public static int[][] AcumHist(int[][] hist, int w, int h)
    {
        int acumHist[][] = new int[256][4];

        //PRETO E BRANCO
        for (int i = 1; i < 256; i++)
        {
            acumHist[i][3] = hist[i][3] + acumHist[i - 1][3];
        }

        //red
        for (int i = 1; i < 256; i++)
        {
            acumHist[i][0] = hist[i][0] + acumHist[i - 1][0];
        }

        //green
        for (int i = 1; i < 256; i++)
        {
            acumHist[i][1] = hist[i][1] + acumHist[i - 1][1];
        }

        //blue
        for (int i = 1; i < 256; i++)
        {
            acumHist[i][2] = hist[i][2] + acumHist[i - 1][2];
        }

        return acumHist;
    }

    public static void Equalize(BufferedImage img, int[][] histogram, int acumHist[][]) throws IOException //S� EQUALIZA IMAGENS EM PRETO E BRANCO
    {
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        float nT[][] = new float[img.getHeight()][img.getWidth()];
        int tom = 0;
        int hMin = 0;

        //ENCONTRA O HMIN

        for (int i = 0; i < 256; i++)
        {
            if (histogram[i][3] > 0)
            {
                hMin = histogram[i][3];
                break;
            }
        }


        //TABELA EQUALIZADA
        int[] equalized = new int[256];

        for (int i = 0; i < 256; i++)
        {
            equalized[i] = (int)(((float)(acumHist[i][3] - hMin) / ((img.getHeight()*img.getWidth()) - hMin)) *(256-1));
        }

        //EQUALIZA A IMAGEM

        for (int y = 0; y < img.getHeight(); y++)
        {
            for (int x = 0; x < img.getWidth(); x++)
            {
                Color pixel = new Color(img.getRGB(x, y));
                tom = (int)((pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3);
                nT[y][x] = equalized[tom];

                Color pixelColor2 = new Color((int)nT[y][x], (int)nT[y][x], (int)nT[y][x]);

                //Grava na imagem de sa�da
                out.setRGB(x, y, pixelColor2.getRGB());
            }
        }

        ImageIO.write(out, "png", new File("car_hist.png"));
    }

    public void run() throws IOException {
        int hist[][];
        BufferedImage img = ImageIO.read(new File("car.png"));

        hist = HistTablePB(img);

        int acumHistogram[][] = AcumHist(hist, img.getWidth(), img.getHeight());
        Equalize(img,hist,acumHistogram);
    }

    public static void main(String[] args) throws IOException {
        new Exercicio3().run();

    }
}
