
package Business_Logic;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component("LIC23707_PJT1008_PE_NLP5bf95551-b8f4-415e-bd28-088bf39665a4")
public class attributeContainsValue implements Nlp {
	@InputParams({ @InputParam(name = "xpath", type = "java.lang.String"),
			@InputParam(name = "value", type = "java.lang.String") })
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
		String value = (String) attributes.get("value");

		WebDriver driver = nlpRequestModel.getWebDriver();
		Duration duration = driver.manage().timeouts().getImplicitWaitTimeout();
		boolean res = false;
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			WebElement Element = driver.findElement(By.xpath(xpath));
			String attributValue = Element.getAttribute("value");
			if (attributValue.isEmpty() || attributValue == null) {
				res = false;
				nlpResponseModel.setMessage("Attribute value is empty");
			} else if (attributValue.contains(value)) {
				res = true;
				nlpResponseModel.setMessage("Attribute value :" + attributValue + " contains " + value);
			}
			nlpResponseModel.setStatus(CommonConstants.pass);

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Failed to validate Attribute value " + e.getMessage());
		}
		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}
}