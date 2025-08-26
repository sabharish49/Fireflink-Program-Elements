package Business_Logic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

@Component("LIC19774_PJT1002_PE_NLPcff1f224-046a-4730-92f8-edeb0b3aa35d")
public class Verify_if_element_is_displayed implements Nlp {
	@InputParams({ @InputParam(name = "Xpath", type = "java.lang.String") })
	@ReturnType(name = "isDisplay", type = "java.lang.Boolean")

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
		String Xpath = (String) attributes.get("Xpath");

		boolean isDisplay = true;

		WebDriver driver = nlpRequestModel.getWebDriver();
		Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			WebElement element = driver.findElement(By.xpath(Xpath));
			if (isDisplay == element.isDisplayed()) {
				isDisplay = true;
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element is Displayed");
		} catch (Exception e) {
			isDisplay = false;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element is not Displayed");

		}
		finally {
			driver.manage().timeouts().implicitlyWait(implicitWait);
		}

		// String string3 = "Return Value";
		nlpResponseModel.getAttributes().put("isDisplay", isDisplay);
		return nlpResponseModel;
	}

}