
package Business_logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.springframework.stereotype.Component;



@Component("LIC14172_PJT1001_PE_NLP2a7b676f-3549-4f2e-93ed-3de32aeda5b7")
public class MOB_swiperight implements Nlp {
	@InputParams({@InputParam(name = "Source", type = "org.openqa.selenium.WebElement"), @InputParam(name = "Destination", type = "org.openqa.selenium.WebElement"), @InputParam(name = "Element", type = "java.lang.String"), @InputParam(name = "maxSwipe", type = "java.lang.Integer"), @InputParam(name = "xCoordinate", type = "java.lang.Integer"), @InputParam(name = "yCoordinate", type = "java.lang.Integer"),@InputParam(name = "LocatorType", type = "java.lang.String")})
	// @ReturnType(name = "string3", type = "java.lang.String")

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
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		WebElement Source = (WebElement) attributes.get("Source");
		WebElement Destination = (WebElement) attributes.get("Destination");
		String Element = (String) attributes.get("Element");
		String LocatorType = (String) attributes.get("LocatorType");
		Integer maxSwipe = (Integer) attributes.get("maxSwipe");
		Integer xCoordinate = (Integer) attributes.get("xCoordinate");
		Integer yCoordinate = (Integer) attributes.get("yCoordinate");

		AndroidDriver driver = (AndroidDriver) nlpRequestModel.getDriver().getSpecificIDriver();
		// Your program element business logic goes here ...
		try {
			int startx=Source.getLocation().getX();
			int starty=Source.getLocation().getY();
			int endx=Destination.getLocation().getX();
			int endy=Destination.getLocation().getY();
			while(driver.findElements((By) By.class.getDeclaredMethod(LocatorType, String.class).invoke(null, Element)).isEmpty()) {

				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence swipe = new Sequence(finger, 0);
				swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
						PointerInput.Origin.viewport(), startx+xCoordinate, starty+yCoordinate));
				swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				swipe.addAction(new Pause(finger, Duration.ofMillis(200)));
				swipe.addAction(finger.createPointerMove(Duration.ofMillis(600),
						PointerInput.Origin.viewport(), endx+xCoordinate, starty+yCoordinate));
				swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				driver.perform(Arrays.asList(swipe));

				if(--maxSwipe == 0) {
					nlpResponseModel.setStatus(CommonConstants.fail);
        			nlpResponseModel.setMessage("Failed to swipe till element within a given count");
					break;
				}
			}
			if (maxSwipe > 0) {
				nlpResponseModel.setStatus(CommonConstants.pass);
    			nlpResponseModel.setMessage("Succesfully swiped till element");
			}
			//     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		}catch(Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to swipe till element"+ e);
		}

	return nlpResponseModel;
}
} 