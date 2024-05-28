package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

public class Compare_PPT {
    public static void main(String[] args) {
        String firstPPTPath ="C:\\Users\\User\\Downloads\\Blue Copper POC PPT.pptx\\";
        String secondPPTPath = "C:\\Users\\User\\Downloads\\Robowaves_Template.pptx\\";
        
        try {
        	
            FileInputStream fis1 = new FileInputStream(firstPPTPath);
            FileInputStream fis2 = new FileInputStream(secondPPTPath);           
            XMLSlideShow ppt1 = new XMLSlideShow(fis1);
            XMLSlideShow ppt2 = new XMLSlideShow(fis2);           
            compareSlides(ppt1, ppt2);       
            fis1.close();
            fis2.close();
            ppt1.close();
            ppt2.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void compareSlides(XMLSlideShow ppt1, XMLSlideShow ppt2) {
    	
        List<XSLFSlide> slides1 = ppt1.getSlides();
        List<XSLFSlide> slides2 = ppt2.getSlides();      
        int numOfSlides = Math.min(slides1.size(), slides2.size());       
        for (int i = 0; i < numOfSlides; i++) {
            XSLFSlide slide1 = slides1.get(i);
            XSLFSlide slide2 = slides2.get(i);      
            System.out.println("Comparing slide " + (i + 1) + ":");        
            String contentSlide1 = getContent(slide1);
            String contentSlide2 = getContent(slide2);          
            if (contentSlide1.equals(contentSlide2)) {
                String a=("Content of slide " + (i + 1) + " is identical.");
                System.out.println(a);
            } else {
              String b=("Content of slide " + (i + 1) + " is different.");
              System.out.println(b);
                break;
            }
            
        }
    }
   
    private static String getContent(XSLFSlide slide) {
    
        StringBuilder content = new StringBuilder();       
        for (org.apache.poi.xslf.usermodel.XSLFShape shape : slide.getShapes()) {      	
            if (shape instanceof org.apache.poi.xslf.usermodel.XSLFTextShape) {
                org.apache.poi.xslf.usermodel.XSLFTextShape textShape = (org.apache.poi.xslf.usermodel.XSLFTextShape) shape;
                content.append(textShape.getText());
            }
            
        }
        return content.toString();
    }
}
