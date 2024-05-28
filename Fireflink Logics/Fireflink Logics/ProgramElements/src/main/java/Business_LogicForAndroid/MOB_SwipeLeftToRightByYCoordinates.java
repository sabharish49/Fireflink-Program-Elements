package Business_LogicForAndroid;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.springframework.stereotype.Component;


@SuppressWarnings("deprecation")
@Component("LIC1710_PJT1014_PE_NLP6426a2cb-2f54-4ab9-8610-19d1297824f6")
public class MOB_SwipeLeftToRightByYCoordinates implements Nlp {
	@InputParams({ @InputParam(name = "Y-Coordinate", type = "java.lang.Integer"),
			@InputParam(name = "Swipe count", type = "java.lang.Integer") })
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

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();

		Integer startY = (Integer) attributes.get("Y-Coordinate");
		Integer count = (Integer) attributes.get("Swipe count");

		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
	Duration impWait=driver.manage().timeouts().getImplicitWaitTimeout();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		TouchAction touchaction = new TouchAction(driver);
		Dimension screenSize = driver.manage().window().getSize();
		int startX = (int) (screenSize.getWidth() * 0.5);
		int endX = (int) (screenSize.getWidth() * 0.1);

		// Your program element business logic goes here ...
		try {
			while (count > 0) {
				touchaction.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY))
						.release().perform();
				count--;
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Swiped successfully");
		} catch (Exception e) {
			// TODO: handle exception
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Swipe");
		}
		finally {
			driver.manage().timeouts().implicitlyWait(impWait);
		}
		return nlpResponseModel;
	}
}