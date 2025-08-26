
package bussiness_logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component("LIC20128_PJT1001_PE_NLP44ac7779-9cb9-4d34-a61a-9252803ed27b")
public class elementIsDisplayed implements Nlp {
	@InputParams({ @InputParam(name = "xpath", type = "java.lang.String") })
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
		String xpath = (String) attributes.get("xpath");

		WebDriver driver = nlpRequestModel.getWebDriver();
		Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		boolean res = false;
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
				res = true;

				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Element is displayed successfully " + xpath);
			} else {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Element is not displayed");
			}

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Element is not displayed" + e);
		}
		finally {
			driver.manage().timeouts().implicitlyWait(implicitWait);
		}

		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}
}