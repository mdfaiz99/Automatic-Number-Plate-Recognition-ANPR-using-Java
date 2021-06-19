# Automatic-Number-Plate-Recognition-ANPR-
Automatic Number Plate Recognition (ANPR) is an image processing technology which uses number plate to identify the vehicles. The main objective of this project is to design and develop effective image processing techniques and algorithms to localize the number plate in the captured image using Java programming. Some of the approach like noise reduction, grayscale conversion, edge detection, binarization, finding contour and image segmentation is used to crop the number plate location. Finally, to evaluate the performance of system, the localization is tested with a number of vehicle images. An accuracy of more than 80% was achieved for the localization of vehicle number plates.
1. This system is implemented using Java program.
2. These are the softwares used for this project.
i) Eclipse IDE
ii) OpenCV version 2.4.13.6
iii) OpenCV version 4.5.0
iv) JavaFx version 15.0.1
2. Please use Eclipse IDE to run the program to avoid errors.
3. Load A170522_ANPR java project into Eclipse. 
(File > Open Projects from File System > Directory > browse to A170522_ANPR > Finish)
4. Add external jar file to the project.
(Right click project > Build Path > Add External Archives > browse to A170522_Jar > select all jar files > Open)
5. The system can be implemented using different vehicle images. To use different input images, browse to Main.java and change the filename in the getName() method according to the image path. By default, all the input images are stored in src/input folder. In this case, you just have to change the input image name which are test1 – test10.
6. Finally, running the system.
(Right click Main.java > Run as > Java Application) 
7. The system should show the input image and all the outputs. The output images are stored in src/output folder. 
8. Before running another test, kindly delete all the images in the src/output folder in order to avoid errors for overwriting purposes.
9. If there are errors while running the system, try to change the order of the jar file to make the opencv-2413 jar file above the opencv-450
(Right click project > Build Path > Configure Build Path > Order and Export > Select opencv-2413 > Top)
10. Kindly please contact me if there is other issues or errors besides what have I stated.
