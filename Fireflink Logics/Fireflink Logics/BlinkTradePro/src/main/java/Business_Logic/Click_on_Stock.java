package Business_Logic;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
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
import io.appium.java_client.android.AndroidDriver;

@Component("LIC17078_PJT1001_PE_NLP6db38498-e3b9-45b5-8886-ea06ec2cdc3f")

public class Click_on_Stock implements Nlp {
	@InputParams({ @InputParam(name = "Xpath", type = "java.lang.String"),
			@InputParam(name = "StockName", type = "java.lang.String"),
			@InputParam(name = "Input1", type = "java.lang.String"),
			@InputParam(name = "Input2", type = "java.lang.String") })

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
		@SuppressWarnings("unchecked")
		String xpath = (String) attributes.get("Xpath");
		String StockName = (String) attributes.get("StockName");
		String Input1 = (String) attributes.get("Input1");
		String Input2 = (String) attributes.get("Input2");
		AndroidDriver driver = (AndroidDriver) nlpRequestModel.getAndroidDriver();
		try {
			stock(xpath, driver, StockName, Input1, Input2);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Stock found Successfully");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Stock not found");
		}
		return nlpResponseModel;
	}

	public static void stock(String xpath, AndroidDriver driver, String StockName, String Input1, String Input2)throws MalformedURLException, InterruptedException {		
		List<WebElement> elementsList = driver.findElements(By.xpath(xpath));
		System.out.println(elementsList.size());
		boolean res = false;
		try {
			for (int i = 1; i <= elementsList.size(); i++) {
				String xpath2 = '(' + xpath + ')' + '[' + i + ']';
				System.out.println(xpath2);
				WebElement element = driver.findElement(By.xpath(xpath2));
				String contentDesc = element.getAttribute("content-desc");
				if (contentDesc.contains(StockName) && contentDesc.contains(Input1) && contentDesc.contains(Input2)) {
					System.out.println("stock is available");
					System.out.println(contentDesc);
					element.click();
					res = true;
					break;
				}
			}

				boolean swipeUp = false;
				Dimension screenSize = driver.manage().window().getSize();
				int screenCenter = (int) (screenSize.getWidth() * 0.5);// Identify center point of screen for Y axis
				int startPoint = (int) (screenSize.getHeight() * 0.5);// Identify beginning point of scroll for X axis
				int endPoint = (int) (screenSize.getHeight() * 0.2);// Identify ending point of scroll
				int count = 0;				
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				while (swipeUp == false) {
					Sequence swipe = new Sequence(finger, 1);
					swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(),
							screenCenter, startPoint));
					swipe.addAction(finger.createPointerDown(0));
					swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(),
							screenCenter, endPoint));
					swipe.addAction(finger.createPointerUp(0));
					((RemoteWebDriver) driver).perform(Arrays.asList(swipe));
					if (++count > 2) {
						break;
					}
					if (count >= 2) {
						swipeUp = true;
					} else {
						
					}
				}

				if (res != true) {
					stock(xpath, driver, StockName, Input1, Input2);
				}
		}

		catch (Exception e) {

		}
	}
}
