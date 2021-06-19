import java.util.ArrayList;
import java.util.List;
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

public class Binary {
    public void run(String[] args) {
    	
    	// Load the native library.
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	
        // Load the image
        Mat src = Highgui.imread("src/output/3. edgedetection.jpg", Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        
        // Check if image is loaded fine
        if( src.empty() ) {
            System.out.println("Error opening image: " + args[0]);
            System.exit(-1);
        }
        
        // Transform source image to gray if it is not already
        Mat gray = new Mat();
        if (src.channels() == 3) {
            Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        }
        else {
            gray = src;
        }

        // Apply adaptiveThreshold at the bitwise_not of gray using otsu method
        Mat bw = new Mat();
        Core.bitwise_not(gray, gray);
        boolean invert = true;
		Imgproc.threshold(gray, bw, 0, 255,(invert ? Imgproc.THRESH_BINARY_INV : Imgproc.THRESH_BINARY) | Imgproc.THRESH_OTSU);
        
        //save image 
        Highgui.imwrite("src/output/4. binary.jpg", bw);
        System.out.println("Binary image created");
        
		//Finding contours
		List<MatOfPoint> contours = new ArrayList<>();
		Mat hierarchey = new Mat();
		Imgproc.findContours(bw, contours, hierarchey, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
		//Drawing the contours
		Mat drawcontour = new Mat(bw.size(), bw.type());
		Scalar color = new Scalar(255,0,0);
		Imgproc.drawContours(drawcontour, contours, -1, color, 2, Core.LINE_8, hierarchey, 2, new Point());
		
		//save image
		Highgui.imwrite("src/output/5. findcontour.jpg", drawcontour);
	        
		//Finding top contours
		List<MatOfPoint> newcont = new ArrayList<MatOfPoint>();
		Mat ori = Highgui.imread(Main.getName());
		Mat newori = ori;
		double maxArea = 0;
			      
		for(int i=0; i<contours.size(); i++) {
			double area = Imgproc.contourArea(contours.get(i));
			   	   
			if(maxArea < area) {
				maxArea = area;
				newcont.add(contours.get(i));
			}
		}
		
		Scalar colors = new Scalar(0,0,255); 	      
		Imgproc.drawContours(newori, newcont, -1, colors, 2);
		
		//save image
		Highgui.imwrite("src/output/6. topcontour.jpg", newori);
		
	    //Finding number plate area
	    List<MatOfPoint> rectangle = new ArrayList<MatOfPoint>();
	    Mat img2 = Highgui.imread(Main.getName());
	    Mat topcontour = img2;
	    MatOfPoint2f approx = new MatOfPoint2f();
	        
	    for (MatOfPoint contour : newcont) {	
			MatOfPoint2f cont2f = new MatOfPoint2f();
			contour.convertTo(cont2f, CvType.CV_32F);
			double perimeter = Imgproc.arcLength(cont2f, true);
			double approximationAccuracy = 0.02 * perimeter;
			Imgproc.approxPolyDP(cont2f, approx, approximationAccuracy, true);
	            
			if (approx.total() == 4) {
				rectangle.add(contour);
			}
		}
	    
		for (int i=0; i<rectangle.size(); i++) {
			Imgproc.drawContours(topcontour, rectangle, i, colors, 2);
		}
	    
		//save image
		Highgui.imwrite("src/output/7. platecontour.jpg", topcontour);
		
		//Cropping the number plate
		Rect rect = new Rect();
		Mat topcont = topcontour;
		Mat ROI = new Mat();
		double minAR = 2;
		double maxAR = 5.4;
						
		for (int i=0; i<rectangle.size(); i++) {
			double contarea = Imgproc.contourArea(rectangle.get(i));
			rect = Imgproc.boundingRect(rectangle.get(i));
			double area = rect.width / rect.height; 
							
			if(area >= minAR && area <= maxAR) {
				Core.rectangle(topcont, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), colors, 3);
				ROI = topcont.submat(rect.y, rect.y + rect.height, rect.x, rect.x + rect.width);
			}
		}
				      
		if(ROI.empty()) {
			
		}
				      
		else {
			Highgui.imwrite("src/output/8. plate.jpg", topcont);
			Highgui.imwrite("src/output/9. cropplate.jpg", ROI);
		}
    }
   
}