package Business_Logic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

@Component("LIC12620_PJT1003_PE_NLP408dbdf3-37e1-43f6-8907-0a163b580c10")
public class ios_Android_Swipe implements Nlp {
	@InputParams({ @InputParam(name = "Android Locator", type = "java.lang.String"),@InputParam(name = "Ios Locator", type = "java.lang.String"),
			@InputParam(name = "Max swipe count", type = "java.lang.String") })

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
		String androidXpath = (String) attributes.get("Android Locator");
		String iosXpath = (String) attributes.get("Ios Locator");
		String maxSwipe = (String) attributes.get("Max swipe count");
		int maxSwipeCount = Integer.parseInt(maxSwipe);
		RemoteWebDriver driver = null;
		Duration impWait = null;
		if (nlpRequestModel.getAndroidDriver() != null) {
			driver = nlpRequestModel.getAndroidDriver();
			impWait = driver.manage().timeouts().getImplicitWaitTimeout();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			swipe(driver, androidXpath, maxSwipeCount, nlpResponseModel, impWait);
		} else if (nlpRequestModel.getIosDriver() != null) {
			driver = nlpRequestModel.getIosDriver();
			impWait = driver.manage().timeouts().getImplicitWaitTimeout();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			swipe(driver, iosXpath, maxSwipeCount, nlpResponseModel, impWait);
		}		
		return nlpResponseModel;
	}

	public void swipe(RemoteWebDriver driver, String xpath, int maxSwipeCount, NlpResponseModel nlpResponseModel,
			Duration impWait) {
		try {
			Dimension screenSize = driver.manage().window().getSize();// Identify screen dimension
			int screenCenter = (int) (screenSize.getWidth() * 0.5);// Identify center point of screen for Y axis
			int startPoint = (int) (screenSize.getHeight() * 0.7);// Identify beginning point of scroll for X axis
			int endPoint = (int) (screenSize.getHeight() * 0.2);// Identify ending point of scroll
			int count = 0;
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence swipe = new Sequence(finger, 1);
			swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(),
					screenCenter, startPoint));
			swipe.addAction(finger.createPointerDown(0));
			swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
					screenCenter, endPoint));
			swipe.addAction(new Pause(finger, Duration.ofMillis(300)));
			swipe.addAction(finger.createPointerUp(0));
			boolean isFound = false;
			while (true) {
				try {
					isFound = driver.findElement(By.xpath(xpath)).isDisplayed();
					if (isFound) {
						break;
					}
				} catch (Exception ignored) {
				}
				driver.perform(Arrays.asList(swipe));
				if (++count >= maxSwipeCount) {
					break;
				}
			}
			if (count >= maxSwipeCount) {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Failed to swipe up  until element is visible");
			} else {
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Swiped up  until element is visible");
			}

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to swipe up until element is visible" + e);
		} finally {
			driver.manage().timeouts().implicitlyWait(impWait);
		}
	}
}