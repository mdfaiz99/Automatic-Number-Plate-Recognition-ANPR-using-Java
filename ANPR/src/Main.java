import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.opencv.core.Core;

public class Main {
	
	//change test file here
	//can get it from src/input
	public static String getName() {
		String filename = "src/input/test1.jpg";
		return filename;
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------");
		System.out.println("             Original            ");
		System.out.println("---------------------------------");
		
		//shows original image
		new OriginalViewer(); 
		System.out.println("This is the original image");
		
		System.out.println();
		synchronized(args) {
			args.wait(3000);
		}
		
		System.out.println("---------------------------------");
		System.out.println("         Noise Reduction         ");
		System.out.println("---------------------------------");
		
		//reduce noise
		new NoiseReduction(); 
		
		//show image after noise reduction
		new NoiseReductionViewer();
		
		System.out.println();
		synchronized(args) {
			args.wait(3000);
		}
		
		System.out.println("---------------------------------");
		System.out.println("            Grayscale            ");
		System.out.println("---------------------------------");
		
		//converts rgb to grayscale
		Grayscale obj = new Grayscale(); 
		
		//shows grayscale image
		new GrayscaleViewer(); 
		
		System.out.println();
		synchronized(args) {
			args.wait(3000);
		}
		
		System.out.println("---------------------------------");
		System.out.println("          Edge Detection         ");
		System.out.println("---------------------------------");
		
		//detects edges
		new EdgeDetection(); 
		
		//show image
		new EdgeDetectionViewer();
		
		System.out.println(); 
		synchronized(args) {
			args.wait(3000);
		}
		
		System.out.println("---------------------------------");
		System.out.println("              Binary             ");
		System.out.println("---------------------------------");
		
		//converts grayscale to binary
		new Binary().run(args);
		
		//show binary image
		new BinaryViewer();
		
		System.out.println(); 
		synchronized(args) {
			args.wait(3000);
		}
		
//		System.out.println("---------------------------------");
//		System.out.println("    Morphological Operation      ");
//		System.out.println("---------------------------------");
//		
//		//apply close morphological operation
//		new Morphological();
//		
//		//show image
//		new MorphologicalViewer();
//		
//		System.out.println(); 
//		synchronized(args) {
//			args.wait(1000);
//		}
		
		System.out.println("---------------------------------");
		System.out.println("           Find Contour          ");
		System.out.println("---------------------------------");
		
		//show image
		new FindContourViewer();
		 
		synchronized(args) {
			args.wait(3000);
		}
		
		System.out.println("---------------------------------");
		System.out.println("           Top Contour           ");
		System.out.println("---------------------------------");
		
		//show image
		new TopContourViewer();
		
		synchronized(args) {
			args.wait(3000);
		}
		
		System.out.println("---------------------------------");
		System.out.println("          Plate Contour          ");
		System.out.println("---------------------------------");
		
		//show image
		new PlateContourViewer();

		synchronized(args) {
			args.wait(3000);
		}
		
		System.out.println("---------------------------------");
		System.out.println("           Number Plate          ");
		System.out.println("---------------------------------");
		
		//show image
		new PlateViewer();
		
		synchronized(args) {
			args.wait(3000);
		}
		
		System.out.println("---------------------------------");
		System.out.println("        Crop Number Plate        ");
		System.out.println("---------------------------------");
		
		//show image
		new CropPlateViewer();

		synchronized(args) {
			args.wait(3000);
		}
	}
	
}
