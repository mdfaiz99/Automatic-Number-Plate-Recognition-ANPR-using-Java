import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.highgui.Highgui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Morphological{
   public Morphological(){
	   
	         //Loading the OpenCV core library
	         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	         
	         //Reading image data
	         Mat thresh = Highgui.imread("src/output/4. binary.jpg");
	         
	         //Creating destination matrix
	         Mat morph = new Mat(thresh.rows(), thresh.cols(), thresh.type());
	         
	         //Preparing the kernel matrix object
	         Mat kernel = Mat.ones(5,5, CvType.CV_32F);
	         
	         //Applying dilate on the Image
	         Imgproc.morphologyEx(thresh, morph, Imgproc.MORPH_CLOSE, kernel);
	         
	         //Saving image
	         Highgui.imwrite("src/output/morph.jpg", morph);
	         System.out.println("Morph image created");
  
   }
}