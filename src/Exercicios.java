import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Exercicios {

	public void bright(BufferedImage img, float intensity) throws IOException
	{
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

		if (intensity < 0){
			intensity = intensity + 1;
			if (intensity < 0)
				intensity = 0;

		}
		
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {

				Color pixel = new Color(img.getRGB(x, y));
				float r = pixel.getRed() * intensity;
				float g = pixel.getGreen() * intensity;
				float b = pixel.getBlue() * intensity;
				
				if(r>255) r = 255;
				if(g>255) g = 255;
				if (b>255) b=255;

				Color pixel2 = new Color ((int)r,(int)g,(int)b);
				
				out.setRGB(x, y, pixel2.getRGB());
			}
			
		}
		ImageIO.write(out, "jpg", new File("turtle_bright.jpg"));
	}
	
	public void grayscale(BufferedImage img)throws IOException {
		
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
	
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				Color pixel = new Color(img.getRGB(x, y));
				
				int r = pixel.getRed();
				int g = r;
				int b = r;
				
				//OP�AO 2
				
				//int media = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
				
				//int r = media, g = media, b = media;
				
				//OPCAO 3
				
				//float novacor = 0.3f * pixel.getRed() + 0.59f * pixel.getGreen() + 0.11f * pixel.getBlue();
				//int novacor2 = (int) novacor;
				//int r = novacor2, g = novacor2, b = novacor2;
				
				Color pixel2 = new Color (r, g, b);
				
				out.setRGB(x, y, pixel2.getRGB());
								
			}
		}

		ImageIO.write(out, "jpg", new File("turtle_gray.jpg"));
	}
	
	public void threshold(BufferedImage img, int value) throws IOException{
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for (int y = 0; y < img.getHeight(); y++){
			for (int x = 0; x < img.getWidth(); x++)
			{
				Color pixel = new Color (img.getRGB(x, y));
				
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();
				
				if (r >= value)	r = 255;
				else r = 0;
				if (g >= value) g = 255;
				else g = 0;
				if (b >= value) b = 255;
				else b = 0;
				
				Color pixel2 = new Color(r, g, b);
				
				out.setRGB(x, y, pixel2.getRGB());
				}
			}
		ImageIO.write(out, "jpg", new File("turtle_threshold.jpg"));
	}
	
	public void subtract(BufferedImage img, BufferedImage img2) throws IOException{
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for (int y = 0; y < img.getHeight(); y++){
			for (int x = 0; x < img.getWidth(); x++)
			{
				Color pixel = new Color (img.getRGB(x, y));
				Color pixel2 = new Color (img2.getRGB(x, y));
				
				int r = pixel.getRed() - pixel2.getRed();
				int g = pixel.getGreen() - pixel2.getGreen();
				int b = pixel.getBlue() - pixel2.getBlue();
				
				if (r <0)	r = 0;
				if (g <0)   g = 0;
				if (b <0)   b = 0;
				
				Color pixel3 = new Color(r, g, b);
				
				out.setRGB(x, y, pixel3.getRGB());
			}
		}

		ImageIO.write(out, "png", new File("errosB3.png"));
	}
	
	public void add(BufferedImage img, BufferedImage img2) throws IOException{
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for (int y = 0; y < img.getHeight(); y++){
			for (int x = 0; x < img.getWidth(); x++)
			{
				Color pixel = new Color (img.getRGB(x, y));
				Color pixel2 = new Color (img2.getRGB(x, y));
				
				int r = pixel.getRed() + pixel2.getRed();
				int g = pixel.getGreen() + pixel2.getGreen();
				int b = pixel.getBlue() + pixel2.getBlue();
				
				if (r >255)	  r = 255;
				if (g >255)   g = 255;
				if (b >255)   b = 255;
				
				Color pixel3 = new Color(r, g, b);
				
				out.setRGB(x, y, pixel3.getRGB());
			}
		}

		ImageIO.write(out, "png", new File("errosB4.png"));
	}	
	
	
	public void lerp(BufferedImage img, BufferedImage img2, float percent) throws IOException{
		BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				Color c = new Color(img.getRGB(i, j));
				Color d = new Color(img2.getRGB(i, j));
				int r = (int) (c.getRed() * (1.0f - percent) + (d.getRed() * percent));
				int g = (int) (c.getGreen() * (1.0f - percent) + (d.getGreen() * percent));
				int b = (int) (c.getBlue() * (1.0f - percent) + (d.getBlue() * percent));

				if (r > 255) {
					r = 255;
				}
				if (g > 255) {
					g = 255;
				}
				if (b > 255) {
					b = 255;
				}
				if (r < 0) {
					r = 0;
				}
				if (g < 0) {
					g = 0;
				}
				if (b < 0) {
					b = 0;
				}
				Color pixel3 = new Color (r, g, b);
				
				out.setRGB(i, j, pixel3.getRGB());
				
			}
		}


		ImageIO.write(out, "png", new File("errosB5.png"));
	}
		
	
	public void run() throws IOException {
		BufferedImage img = ImageIO.read(new File("turtle.jpg"));
		BufferedImage img1 = ImageIO.read(new File("errosB1.png"));
		BufferedImage img2 = ImageIO.read(new File("errosB2.png"));

		bright(img,3f);
		grayscale(img);
		threshold(img,5);
		subtract(img1,img2);
		add(img1,img2);
		lerp(img1,img2,10f);
	}

	public static void main(String[] args) throws IOException {
		new Exercicios().run();

	}
}