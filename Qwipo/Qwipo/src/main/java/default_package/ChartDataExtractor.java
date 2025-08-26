package default_package;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.core.Core;
import org.openqa.selenium.Point;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class ChartDataExtractor {

    static {
        System.loadLibrary(Core.CATEGORY_NAME);
    }
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

        centers.sort(Comparator.comparingDouble(p -> p.x)); // Sort from left to right
        Map<String, Integer> valueMap = new LinkedHashMap<>();
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("tessdata"); // Make sure this folder contains eng.traineddata
        BufferedImage fullImage = ImageIO.read(new File("path_to/simulated_chart.png"));
        for (int i = 0; i < centers.size() && i < months.length; i++) {
            Point p = centers.get(i);
            int size = 30;
            int x = (int)p.x, y = (int)p.y;

            int cropX = Math.max(x - size, 0);
            int cropY = Math.max(y - size, 0);
            int cropWidth = Math.min(size * 2, fullImage.getWidth() - cropX);
            int cropHeight = Math.min(size * 2, fullImage.getHeight() - cropY);

            BufferedImage sub = fullImage.getSubimage(cropX, cropY, cropWidth, cropHeight);

            String text = tesseract.doOCR(sub).replaceAll("[^0-9]", "").trim();
            Integer value = text.isEmpty() ? null : Integer.parseInt(text);

            valueMap.put(months[i], value);
        }

        return valueMap;
    }
}