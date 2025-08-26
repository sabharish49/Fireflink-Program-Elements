package business_logics_Android;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.ios.IOSDriver;


public class ToClickByImageWithSubImage implements Nlp {
	@InputParams({ @InputParam(name = "ScreenShot", type = "java.lang.String"),
		@InputParam(name = "element", type = "org.openqa.selenium.WebElement") })
	@ReturnType(name = "FilePath", type = "java.lang.String")
	@Override
	
	public List<String> getTestParameters() throws NlpException {
		List<String> params = new ArrayList<>();
		return params;
	}

	@Override
	public StringBuilder getTestCode() throws NlpException {
		StringBuilder sb = new StringBuilder();
		return sb;
	}


	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		WebElement element = (WebElement) programElementsInput.get("element");
		String mainImagePath = (String) programElementsInput.get("ScreenShot");
		String s = null;
		
		try {
			List<Map.Entry<String, Object>> entryList = new ArrayList<>(programElementsInput.entrySet());
			
				String imageName =null;
				for (int i = 0; i<entryList.size(); i++) {
					if ( entryList.get(i).getKey().toString().contains("png")) {
						imageName= entryList.get(i).getKey();
						break;
					}
				}
				InputStream inputStream = new ByteArrayInputStream((byte[]) programElementsInput.get(imageName));
			 IOSDriver driver = nlpRequestModel.getIosDriver();
			 BufferedImage mainImage = ImageIO.read(new File(mainImagePath));
			  BufferedImage subImage = ImageIO.read(inputStream);


			List s1 = cooredinates(mainImage, subImage);

			int xCoordinate = (int) s1.get(0) + (subImage.getWidth() / 2);
			int yCoordinate = (int) s1.get(1) + (subImage.getHeight() / 2);
			if (s1.size() > 0) {
				s = (xCoordinate * driver.manage().window().getSize().getWidth()) / mainImage.getWidth() + ","
						+ (yCoordinate * driver.manage().window().getSize().getHeight()) / mainImage.getHeight() + "";

				
				
		         PointerInput finger11 = new PointerInput(PointerInput.Kind.TOUCH, "finger");

				
				int clickX = (xCoordinate * driver.manage().window().getSize().getWidth()) / mainImage.getWidth();
			      int clickY = (yCoordinate * driver.manage().window().getSize().getHeight()) / mainImage.getHeight();

			      Sequence clickSequence = new Sequence(finger11, 1);
			      clickSequence.addAction(finger11.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), clickX, clickY));
			      clickSequence.addAction(finger11.createPointerDown(0));
			      clickSequence.addAction(finger11.createPointerUp(0));

			      driver.perform(Arrays.asList(clickSequence));
				nlpResponseModel.setMessage("Successfully clicked on Given Image");
				s = (xCoordinate * driver.manage().window().getSize().getWidth()) / mainImage.getWidth() + ","
						+ (yCoordinate * driver.manage().window().getSize().getHeight()) / mainImage.getHeight() + "";
				nlpResponseModel.setStatus(CommonConstants.pass);
			} else {
				System.out.println("Sub-image not found in the main image.");
				nlpResponseModel.setMessage("Sub-image not found in the main image.");
				nlpResponseModel.setStatus(CommonConstants.fail);
			}
		} catch (Exception e) {
			nlpResponseModel.setMessage("Sub-image not found in the main image exception" +e.getMessage());
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		nlpResponseModel.getAttributes().put("FilePath", s);
		return nlpResponseModel;
		
	}

    public static List cooredinates( BufferedImage mainImage, BufferedImage subImage ) throws IOException {


        Pattern searchImage = new Pattern(subImage).similar((float) 0.9);
        Finder objFinder = new Finder(mainImage);
        objFinder.find(searchImage);
        Map slist=new LinkedHashMap();
		List s1 = new ArrayList();

        int counter = 0;
        while (objFinder.hasNext()) {
            Match objMatch = objFinder.next();
            int x = objMatch.getX();
            int y = objMatch.getY();
            int w = objMatch.getW();
            int h = objMatch.getH();
            List as=new ArrayList();
            as.add(x);
            as.add(y);
            slist.put(counter, as);
           // System.out.println("Match found at: " + x + ", " + y + ", " + (x + w) + ", " + (y + h));
            counter++;
        }
        if (counter != 0 ) {
        	List sa=(List) slist.get(0);
        	s1.add(sa.get(0));
			s1.add(sa.get(1));
        	
            System.out.println("Match Found!");
        } else {
            System.out.println("Match not Found!");
        }
        return s1;
    }
	public static void main(String[] args) throws IOException {
		  String mainImagePath = "C:\\Users\\User\\Downloads\\banjosimage.jpg";
	       String subImagePath = "C:\\Users\\User\\Documents\\add.png";
	       
	     // System.out.println( Coordinates(mainImagePath, subImagePath));
	}
}
