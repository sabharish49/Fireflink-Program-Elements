package Business_Logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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

@Component("LIC20665_PJT1001_PE_NLP2d24af89-3d3e-44b1-ac07-90b2baca4b2e")
public class MOB_elementIsDisplayed implements Nlp {

	@InputParams({ @InputParam(name = "Xpath", type = "java.lang.String"),
			@InputParam(name = "waitTime", type = "java.lang.Integer") })
	@ReturnType(name = "Result", type = "java.lang.Boolean")

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
		
		String path = (String) attributes.get("Xpath");
		Integer waitTime = (Integer) attributes.get("waitTime");

		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		IOSDriver iosDriver = nlpRequestModel.getIosDriver();
	

		boolean result = false;
		
		try {
			if (driver!=null) {
				Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();

				try {
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
					
					boolean IsDisplayed = driver.findElement(By.xpath(path)).isDisplayed();
					if (IsDisplayed == true) {
						result = true;
						nlpResponseModel.setStatus(CommonConstants.pass);
						nlpResponseModel.setMessage("Element is Displayed in the Screen");
					}
				} catch (Exception e) {
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Element is not Displayed in the Screen "+ e);
					
				} finally {
					driver.manage().timeouts().implicitlyWait(implicitWait);
				}

			} else {

				Duration implicitWait = iosDriver.manage().timeouts().getImplicitWaitTimeout();

				try {
					iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
					
					boolean	IsDisplayed = iosDriver.findElement(By.xpath(path)).isDisplayed();
					if (IsDisplayed == true) {
						result = true;
						nlpResponseModel.setStatus(CommonConstants.pass);
						nlpResponseModel.setMessage("Element is Displayed in the Screen");
					}
				} catch (Exception e) {
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Element is not Displayed in the Screen "+e);
				} finally {
					iosDriver.manage().timeouts().implicitlyWait(implicitWait);
				}
			}

			
		}

		catch (Exception e) {
			
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element is not Displayed in the Screen " + e);
		}
		nlpResponseModel.getAttributes().put("Result", result);
		return nlpResponseModel;
	}

}
