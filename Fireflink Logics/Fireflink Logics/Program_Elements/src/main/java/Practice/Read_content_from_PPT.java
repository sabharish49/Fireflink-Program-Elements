package Practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class Read_content_from_PPT {

    public static void main(String[] args) {
        // Replace these with appropriate paths and selectors
        String downloadPath = "C:\\Users\\User\\Downloads\\Blue Copper POC PPT.pptx";
        
        try {
            FileInputStream fis = new FileInputStream(downloadPath);
            XMLSlideShow ppt = new XMLSlideShow(fis);
                        for (XSLFSlide slide : ppt.getSlides()) {
                if (slide.getShapes().stream().anyMatch(shape -> shape instanceof XSLFTextShape &&
                        ((XSLFTextShape) shape).getText().contains("Final Deliverables"))) {
                    System.out.println("Validation passed for slide: " + slide.getSlideNumber());
                } else {
                    System.out.println("Validation failed for slide: " + slide.getSlideNumber());
                }
            }        
                        for (XSLFSlide slide : ppt.getSlides()) {
                            System.out.println("Slide " + slide.getSlideNumber() + ":");
                            for (org.apache.poi.xslf.usermodel.XSLFShape shape : slide.getShapes()) {
                                if (shape instanceof org.apache.poi.xslf.usermodel.XSLFTextShape) {
                                    org.apache.poi.xslf.usermodel.XSLFTextShape textShape = (org.apache.poi.xslf.usermodel.XSLFTextShape) shape;
                                    System.out.println(textShape.getText());
                                }
                            }
                        }
            fis.close();
            ppt.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
