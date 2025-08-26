package Business_Logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.springframework.stereotype.Component;

@Component("LIC20665_PJT1001_PE_NLP20d7c1b2-bc67-4eb7-b3d4-7fbf48e01909")
public class MOB_ifElementDisplayed implements Nlp {
	@InputParams({ @InputParam(name = "element", type = "org.openqa.selenium.WebElement") })
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

		WebElement element = (WebElement) attributes.get("element");
		boolean isDisplay = false;

		AndroidDriver androidDriver = nlpRequestModel.getAndroidDriver();
		IOSDriver iosDriver = nlpRequestModel.getIosDriver();

		String attributeValue = null;
		try {

			if (androidDriver != null) {
				Duration implicitlyWait = androidDriver.manage().timeouts().getImplicitWaitTimeout();

				try {
					androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if (isDisplay == element.isDisplayed()) {
						isDisplay = true;
					}
				} catch (Exception e) {

				} finally {
					androidDriver.manage().timeouts().implicitlyWait(implicitlyWait);
				}

			} 
			
			else {
				Duration implicitlyWait = iosDriver.manage().timeouts().getImplicitWaitTimeout();

				try {
					iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if (isDisplay == element.isDisplayed()) {
						isDisplay = true;
					}
				} catch (Exception e) {

				} finally {
					iosDriver.manage().timeouts().implicitlyWait(implicitlyWait);
				}

			}
			isDisplay = true;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element is Displayed");
		} catch (

		Exception e) {
			isDisplay = false;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element is not Displayed");

		}
		nlpResponseModel.getAttributes().put("result", isDisplay);
		return nlpResponseModel;
	}

}