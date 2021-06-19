import java.util.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class NoiseReduction {

	BufferedImage image;
	   Color[] pixel = new Color[9];
       int[] red = new int[9];
       int[] blue = new int[9];
       int[] green = new int[9];
	   
	   public NoiseReduction() {
	      try {
	    	 
	         File input = new File(Main.getName());
	         image = ImageIO.read(input);
	         
	         //reduce noise using median filter
	         for(int i = 1; i < image.getWidth()-1; i++) {
		            for(int j = 1; j < image.getHeight()-1; j++) {
		               pixel[0] = new Color(image.getRGB(i-1, j-1));
		               pixel[1] = new Color(image.getRGB(i-1, j));
		               pixel[2] = new Color(image.getRGB(i-1, j+1));
		               pixel[3] = new Color(image.getRGB(i, j+1));
		               pixel[4] = new Color(image.getRGB(i+1, j+1));
		               pixel[5] = new Color(image.getRGB(i+1, j));
		               pixel[6] = new Color(image.getRGB(i+1, j-1));
		               pixel[7] = new Color(image.getRGB(i, j-1));
		               pixel[8] = new Color(image.getRGB(i, j));
		               
		               for(int k = 0; k < 9; k++){
		                   red[k] = pixel[k].getRed();
		                   blue[k] = pixel[k].getBlue();
		                   green[k] = pixel[k].getGreen();	                   
		               }
		               
		               Arrays.sort(red);
		               Arrays.sort(green);
		               Arrays.sort(blue);
		               image.setRGB(i, j, new Color(red[4], blue[4], green[4]).getRGB());
		            }
		     }
	         
	         //save image
	         File output = new File("src/output/1. noisereduction.jpg");
	         System.out.println("Noise reduced from image.\n");
	         ImageIO.write(image, "jpg", output);
	      } 
	      catch (Exception e) {
	    	  
	      }
	   }

}