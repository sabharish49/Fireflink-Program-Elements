package Business_logic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;
import org.springframework.stereotype.Component;

@Component("LIC14172_PJT1001_PE_NLPd0b7faaf-9c58-44fe-882c-35b9138457bd")
public class MOB_FetchListOfStocks implements Nlp {
	@InputParams({@InputParam(name = "Element", type = "org.openqa.selenium.WebElement"),
		@InputParam(name = "Locatorvalue", type = "java.lang.String"),
		@InputParam(name = "maxSwipeCount", type = "java.lang.Integer")})
	@ReturnType(name = "list", type = "java.util.List")

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
		List<String> list=null;

		try {
			WebElement Element = (WebElement) attributes.get("Element");
			String Locatorvalue = (String) attributes.get("Locatorvalue");
			Integer maxSwipeCount=(Integer) attributes.get("maxSwipeCount");
			AndroidDriver driver= nlpRequestModel.getAndroidDriver();

			Dimension screenSize = driver.manage().window().getSize();// Identify screen dimension
			Point location = Element.getLocation(); // Get location from element to swipe to
			int screenCenter = (int) (screenSize.getWidth() * 0.5);// Identify center point of screen for Y axis
			int startPoint = (int) (screenSize.getHeight() * 0.4);// Identify beginning point of scroll for X axis
			int endPoint = (int) (screenSize.getHeight() * 0.1);// Identify ending point of scroll
			int count = 0;

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence swipe = new Sequence(finger, 1);

			swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), screenCenter,
					startPoint));

			swipe.addAction(finger.createPointerDown(0));

			swipe.addAction(finger.createPointerMove(Duration.ofMillis(50), PointerInput.Origin.viewport(), screenCenter, endPoint));


			swipe.addAction(finger.createPointerUp(0));

			Set correctList=new HashSet();

			List<WebElement> elements;

			while(true) {
				elements = driver.findElements(By.xpath(Locatorvalue));

				for (WebElement element : elements) {

					correctList.add(element.getAttribute("content-desc"));
				}

				driver.perform(Arrays.asList(swipe));

				if (++count >= maxSwipeCount) {
					break;
				}
			}


			nlpResponseModel.setMessage("Succesfully fetched the stocks list and the count is "+correctList.size());
			nlpResponseModel.setStatus(CommonConstants.pass);

			list = new ArrayList<String>(correctList);

		}catch (Exception e) {
			nlpResponseModel.setMessage("Failed to fetch the stocks " +e);
			nlpResponseModel.setStatus(CommonConstants.fail);
		}



		nlpResponseModel.getAttributes().put("list", list);
		return nlpResponseModel;
	}
} 


