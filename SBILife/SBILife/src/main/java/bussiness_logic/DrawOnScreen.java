package bussiness_logic;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;


public class DrawOnScreen implements Nlp {

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

			drawLine(driver, 255, 1246, 410, 968); 
			drawLine(driver, 410, 968, 495, 1243); 
			drawLine(driver, 495, 1243, 685, 972); 		
			driver.findElement(By.xpath("//android.widget.Button[@text='Save']")).click();			
			res = true;  
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully drew a line on the screen");


		} catch (Exception e) {
			System.out.println(e.getMessage());
			res = false;
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to drew a line on the screen");

		} 
		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}

	public static void drawLine(AndroidDriver driver, int startX, int startY, int endX, int endY) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);


		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));

		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

		swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY));

		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


		driver.perform(Arrays.asList(swipe));


	}

}

