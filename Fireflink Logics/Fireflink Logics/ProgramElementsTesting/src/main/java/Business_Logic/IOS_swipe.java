package Business_Logic;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.ios.IOSDriver;

@Component("LIC12620_PJT1003_PE_NLP63f91089-87d5-459b-9d5d-76d3df8141d2")
public class IOS_swipe implements Nlp {
	@InputParams({@InputParam(name = "xpath", type = "java.lang.String")})

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
		String xpath=(String) attributes.get("xpath");
		boolean isDisplayed=false;


		int count=0;
		IOSDriver driver = (IOSDriver) nlpRequestModel.getIosDriver();
		try {
		while(count==0) {
			try {
				driver.findElement(By.xpath(xpath));
				isDisplayed=true;
				if(isDisplayed) {
					break;
				}
				
			}catch (Exception e) {
				
				swipeUp(driver, xpath, nlpResponseModel,isDisplayed);
			}
		}
		nlpResponseModel.setStatus(CommonConstants.pass);
		nlpResponseModel.setMessage("Swiped up until  is visible");
		}catch (Exception e) {
			// TODO: handle exception
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to swipe up until is visible "+e);
		}
		return nlpResponseModel;
	}

	public static void swipeUp(IOSDriver driver,String xpath,NlpResponseModel nlpResponseModel,boolean isDisplayed) {
		Actions action= new Actions(driver);
		while(isDisplayed!=true) {
			int startX = driver.manage().window().getSize().getWidth() / 2;
			int startY = driver.manage().window().getSize().getHeight() * 4 / 5;
			int endY = driver.manage().window().getSize().getHeight() / 5;

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence swipe = new Sequence(finger, 0);
			swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
					PointerInput.Origin.viewport(), startX, startY));
			swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			swipe.addAction(new Pause(finger, Duration.ofMillis(200)));
			swipe.addAction(finger.createPointerMove(Duration.ofMillis(600),
					PointerInput.Origin.viewport(), startX, endY));
			swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Arrays.asList(swipe));
			try {
				driver.findElement(By.xpath(xpath));
				isDisplayed=true;
			}catch (Exception e) {
				
			}

		}	
	}
} 


