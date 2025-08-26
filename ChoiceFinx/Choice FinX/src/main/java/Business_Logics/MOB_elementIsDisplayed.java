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

import io.appium.java_client.android.AndroidDriver;


import org.springframework.stereotype.Component;

@Component("LIC20369_PJT1002_PE_NLP21063b25-6828-4be7-bcb7-46ed41aea2c0")
public class MOB_elementIsDisplayed implements Nlp {

	@InputParams({ @InputParam(name = "Xpath", type = "java.lang.String") ,@InputParam(name = "waitTime", type = "java.lang.Integer")})
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
		Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
		
		boolean IsDisplayed = true;
		try {
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
			
			IsDisplayed = driver.findElement(By.xpath(path)).isDisplayed();
			if (IsDisplayed==true) {
				IsDisplayed = true;				
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element is Displayed in the Screen");
		}

		catch (Exception e) {
			IsDisplayed = false;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element is not Displayed in the Screen " + e);
		}
		finally {
			driver.manage().timeouts().implicitlyWait(implicitWait);
		}

		nlpResponseModel.getAttributes().put("Result", IsDisplayed);
		return nlpResponseModel;
	}

}
