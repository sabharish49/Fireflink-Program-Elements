
package Business_Logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;




public class clickOnCornerElementWithinDuration implements Nlp {
	@InputParams({ @InputParam(name = "duration", type = "java.lang.String") })
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
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		String duration = (String) attributes.get("duration");

		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
		boolean b=false;
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

			String s = duration.substring(0, duration.length() - 1);
			int number = Integer.parseInt(s);
			String type = duration.substring(duration.length() - 1);
			switch (type) {
			case "s":
				number = number * 1000;
				break;
			case "m":
				number = number * 60 * 1000;
				break;
			case "h":
				number = number * 60 * 60 * 1000;
				break;
			default:
				break;
			}

			long startTime = System.currentTimeMillis();

			while (System.currentTimeMillis() - startTime <= number && b==false) {
				try {
					driver.findElement(By.xpath(
							"//android.widget.FrameLayout[@resource-id='com.choiceequitybroking.jiffy:id/balloon']"));
					Thread.sleep(500);
					System.out.println("element is displayed");
					TouchAction<?> touchAction = new TouchAction<>(driver);
					touchAction.tap(TapOptions.tapOptions().withPosition(PointOption.point(995, 2168))).perform();
					System.out.println("action performed,click on another element,,entered catch");
					b=true;
					break;
				} catch (Exception e) {
				 	System.out.println("element is not present in DOM,so continue");
					continue;
				}

			}
			
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully tapped on Coordinates");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Failed to tap on Coordinates "+e);
		} finally {
			driver.manage().timeouts().implicitlyWait(implicitWait);
		}

		nlpResponseModel.getAttributes().put("result", b);
		return nlpResponseModel;
	}
}