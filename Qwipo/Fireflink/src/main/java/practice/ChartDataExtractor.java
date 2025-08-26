package practice;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.Point;

import net.sourceforge.tess4j.*;

public class ChartDataExtractor {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    static final String[] months = { "January", "February", "March", "April", "May", "June", "July" };

    public static void main(String[] args) throws Exception {
        Mat image = Imgcodecs.imread("path_to/simulated_chart.png");

        Map<String, Integer> greenMap = extractColorData(image, new Scalar(0, 100, 0), new Scalar(50, 255, 50), "green");
        Map<String, Integer> blueMap  = extractColorData(image, new Scalar(200, 0, 0), new Scalar(255, 50, 50), "blue");

        System.out.println("Green Data: " + greenMap);
        System.out.println("Blue Data: " + blueMap);
    }

    static Map<String, Integer> extractColorData(Mat image, Scalar lowerBound, Scalar upperBound, String label) throws Exception {
        Mat mask = new Mat();
        Core.inRange(image, lowerBound, upperBound, mask);

        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        List<Point> centers = new ArrayList<>();
        for (MatOfPoint contour : contours) {
            Moments m = Imgproc.moments(contour);
            if (m.get_m00() != 0) {
                int x = (int)(m.get_m10() / m.get_m00());
                int y = (int)(m.get_m01() / m.get_m00());
                centers.add(new Point(x, y));
            }
        }

        // Sort by x-axis
        centers.sort(Comparator.comparingDouble(p -> p.x));

        Map<String, Integer> valueMap = new LinkedHashMap<>();
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("tessdata");  // path to tessdata directory

        BufferedImage fullImage = ImageIO.read(new File("path_to/simulated_chart.png"));

        for (int i = 0; i < centers.size(); i++) {
            Point p = centers.get(i);
            int size = 30;
            int x = (int)p.x, y = (int)p.y;

            // Crop a region around the point
            BufferedImage sub = fullImage.getSubimage(
                Math.max(x - size, 0),
                Math.max(y - size, 0),
                Math.min(size * 2, fullImage.getWidth() - x + size),
                Math.min(size * 2, fullImage.getHeight() - y + size)
            );

            // OCR the number
            String text = tesseract.doOCR(sub).replaceAll("[^0-9]", "").trim();
            Integer value = text.isEmpty() ? null : Integer.parseInt(text);

            valueMap.put(months[i], value);
        }

        return valueMap;
    }
}
