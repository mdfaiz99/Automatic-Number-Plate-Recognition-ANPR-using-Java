import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.highgui.Highgui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class EdgeDetection {

	   BufferedImage image;
	   
	   public EdgeDetection() {
	      try {

	         File input = new File("src/output/2. grayscale.jpg");
	         image = ImageIO.read(input);
	         
	         //detect edges using sobel operator
	         int x = image.getWidth();
	         int y = image.getHeight();
	         int maxGval = 0;
	         int[][] edgeColors = new int[x][y];
	         int maxGradient = -1;
	         
	         for (int i = 1; i < x - 1; i++) {
	             for (int j = 1; j < y - 1; j++) {

	                 int val00 = getGrayScale(image.getRGB(i - 1, j - 1));
	                 int val01 = getGrayScale(image.getRGB(i - 1, j));
	                 int val02 = getGrayScale(image.getRGB(i - 1, j + 1));

	                 int val10 = getGrayScale(image.getRGB(i, j - 1));
	                 int val11 = getGrayScale(image.getRGB(i, j));
	                 int val12 = getGrayScale(image.getRGB(i, j + 1));

	                 int val20 = getGrayScale(image.getRGB(i + 1, j - 1));
	                 int val21 = getGrayScale(image.getRGB(i + 1, j));
	                 int val22 = getGrayScale(image.getRGB(i + 1, j + 1));

	                 int gx =  ((-1 * val00) + (0 * val01) + (1 * val02)) 
	                         + ((-2 * val10) + (0 * val11) + (2 * val12))
	                         + ((-1 * val20) + (0 * val21) + (1 * val22));

	                 int gy =  ((-1 * val00) + (-2 * val01) + (-1 * val02))
	                         + ((0 * val10) + (0 * val11) + (0 * val12))
	                         + ((1 * val20) + (2 * val21) + (1 * val22));

	                 double gval = Math.sqrt((gx * gx) + (gy * gy));
	                 int g = (int) gval;

	                 if(maxGradient < g) {
	                     maxGradient = g;
	                 }

	                 edgeColors[i][j] = g;
	             }
	         }
	         
	         double scale = 255.0 / maxGradient;

	         for (int i = 1; i < x - 1; i++) {
	             for (int j = 1; j < y - 1; j++) {
	                 int edgeColor = edgeColors[i][j];
	                 edgeColor = (int)(edgeColor * scale);
	                 edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
	                 image.setRGB(i, j, edgeColor);
	             }
	         }
	        
	         //save image
	         File output = new File("src/output/3. edgedetection.jpg");
	         System.out.println("Max gradient: " + maxGradient);
	         System.out.println("Edge detected from image.");
	         ImageIO.write(image, "jpg", output);
	         
	      } 
	      
	      catch (Exception e) {
	    	  
	      }
	   }
	   
	   public static int  getGrayScale(int rgb) {
	        int r = (rgb >> 16) & 0xff;
	        int g = (rgb >> 8) & 0xff;
	        int b = (rgb) & 0xff;

	        //calculating luminance
	        int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
	        return gray;
	    }
	   
}