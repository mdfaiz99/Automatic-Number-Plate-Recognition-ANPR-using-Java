import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.*;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.util.*;

public class Grayscale {

   BufferedImage image;
   int height;
   int width;
   
   public Grayscale() {
      try {

         File input = new File("src/output/1. noisereduction.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();
         
         //convert rgb to grayscale using weighted method
         for(int i = 0; i < height; i++) {
        	 for(int j = 0; j < width; j++) {
        		 Color c = new Color(image.getRGB(j, i));
        		 int red = (int)(c.getRed() * 0.299);
        		 int green = (int)(c.getGreen() * 0.587);
        		 int blue = (int)(c.getBlue() * 0.114);
        		 Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
        		 image.setRGB(j, i, newColor.getRGB());
            }
         }
         
         //save image
         File output = new File("src/output/2. grayscale.jpg");
         ImageIO.write(image, "jpg", output);
         
         //calculate pixel
         Pixel();
         System.out.println("Grayscale image created.\n");
         
      } 
      catch (Exception e) {
    	  
      }
   }
   
	private static void Pixel() {
		// TODO Auto-generated method stub
		BufferedImage image;
		int width;
		int height;
		   
		try {
	   	 	
	        File input = new File("src/output/2. grayscale.jpg");
	        image = ImageIO.read(input);
	        width = image.getWidth();
	        height = image.getHeight();
	        int pixel = height * width;
	        DecimalFormat df = new DecimalFormat("#.###");
	        int count = 0;
	        
	        for(int i = 0; i < height; i++) {
	       	 	for(int j = 0; j < width; j++) {
	              count++;
	              Color c = new Color(image.getRGB(j, i));          
	            }
	        }
	        
	        System.out.println("Total pixels: " + pixel);
	        Raster raster = image.getRaster();
	        double sum = 0.0; 
	        
	        for(int i = 0; i < image.getHeight(); i++) {
	        	for(int j = 0; j < image.getWidth(); j++) {
	        		sum += raster.getSample(j, i, 0);
	        	}
	        }
	        
	        System.out.println("Mean: "+ sum / (image.getWidth() * image.getHeight()));
	        double mean =+ sum / pixel;
	        double sumOfDiffgrey = 0.0;
	        
	        for(int i = 0; i < image.getHeight(); i++) {
	        	for(int j = 0; j < image.getWidth(); j++) {
	        		Color c = new Color(image.getRGB(j, i));
	               double R = c.getRed() - mean;
	               double B = c.getBlue() - mean;
	               double G = c.getGreen() - mean;
	               double grey_level = 0.3 * (R) + 0.59 * (B) + 0.11 * (G);
	               sumOfDiffgrey += Math.pow(grey_level, 2);
	        	}
	        }
	        
	        double varR = sumOfDiffgrey / ((image.getWidth() * image.getHeight()) - 1);
	        System.out.println("Standard Deviation of Grey Image: " + df.format(Math.sqrt(varR)));
	        int max = 255;
	        int min = 0;
	        
	        for(int y = 0; y < image.getHeight(); y++) {
	        	for(int x = 0; x < image.getWidth(); x++) {
	        		int pixels = image.getRGB(x, y);
	               int red = (pixel >> 16) & 0xff;
	               int green = (pixel >> 8) & 0xff;
	               int blue = (pixel) & 0xff;
	                
	               if(red > green && red > blue) {
	                	max = red;
	               }
	               
	               else if(red < green && red < blue) {
	                	min = red;
	               }
	                
	               if(blue > green && blue > red) {
	                	max = blue;
	               }
	               
	               else if(blue < green && blue < red) {
	                	min = blue;
	               }
	                
	               if(green > blue && green > red) {
	                	max = green;
	               }
	               
	               else if(green < blue && green < red) {
	                	min = green;
	               }
	        	}
	        }
	        
	        System.out.println("Minimum Value of Grey Image: "+ min);
	        System.out.println("Maximum Value of Grey Image: "+ max);
	    
	     }
	              
	     catch (Exception e) {
	   	  
	     }
	}
	
}

