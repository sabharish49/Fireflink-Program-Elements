package Business_LogicForAndroid;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;

@Component("LIC1710_PJT1014_PE_NLPbaab7cfb-0ef3-47b0-bf40-e9a3c5cdf204")
public class MOB_Wait_till_Element_isVisible implements Nlp {
	@InputParams({ @InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
			@InputParam(name = "elementName", type = "java.lang.String"),
			@InputParam(name = "elementType", type = "java.lang.String"),
			@InputParam(name = "explicitWait", type = "java.lang.String") })

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

		try {
			WebElement element = (WebElement) attributes.get("element");
			String waitTime = (String) attributes.get("explicitWait");
			AndroidDriver driver = (AndroidDriver) nlpRequestModel.getDriver().getSpecificIDriver();
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(waitTime)));
			wait1.until(ExpectedConditions.visibilityOf(element));
			nlpResponseModel.setMessage("Successfully waited until element is visible in UI");
			nlpResponseModel.setStatus(CommonConstants.pass);

		} catch (Exception e) {
			nlpResponseModel.setMessage("Failed to wait until element is visible in UI");
			nlpResponseModel.setStatus(CommonConstants.fail);

		}
		return nlpResponseModel;
	}
}
