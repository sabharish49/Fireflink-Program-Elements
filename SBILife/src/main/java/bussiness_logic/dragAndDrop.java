package bussiness_logic;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;


public class dragAndDrop implements Nlp {
	
	@ReturnType(name = "result", type = "java.lang.Boolean")

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
		//Map<String, Object> attributes = nlpRequestModel.getAttributes();

		boolean res = false;

		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		try {

			List<WebElement> dragList = driver.findElements(By.xpath("//android.view.View[contains(@resource-id,'drag')]"));
			//List<WebElement> dropelementsList = driver.findElements(By.xpath("//android.view.View[contains(@resource-id,'droppable')]"));
			int size = dragList.size();
			System.out.println(size);
			for (int i = 0; i < size; i++) {

				WebElement goal = driver.findElement(By.xpath("//android.view.View[contains(@resource-id,'drag')][last()]"));
				WebElement priority = driver.findElement(By.xpath("//android.view.View[contains(@resource-id,'droppable')][last()]"));


				dragAndDropUsingSequence(goal, priority,driver);

			}

			res = true;  
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully dragged and dropped the goal to priority position");


		} catch (Exception e) {
			System.out.println(e.getMessage());
			res = false;
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to dragged and dropped the goal to priority position");

		} 
		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}

	private static void dragAndDropUsingSequence(WebElement source, WebElement target,AndroidDriver driver ) {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");


		int startX = source.getLocation().getX() + (source.getSize().getWidth() / 2);
		int startY = source.getLocation().getY() + (source.getSize().getHeight() / 2);
		int endX = target.getLocation().getX() + (target.getSize().getWidth() / 2);
		int endY = target.getLocation().getY() + (target.getSize().getHeight() / 2);


		Sequence dragAndDrop = new Sequence(finger, 1)
				.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


		driver.perform(Arrays.asList(dragAndDrop));
	}

}

