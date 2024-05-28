
package Business_LogicForWeb;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
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


@Component("LIC1710_PJT1014_PE_NLP3a49bb1a-cd2a-4a7d-b139-95da6fec7147")
public class Web_WaitUtilStalenessClearandClick implements Nlp {
	@InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
		@InputParam(name = "elementType", type = "java.lang.String"),
		@InputParam(name = "elementName", type = "java.lang.String"),
  @InputParam(name = "explicitWaits", type = "java.lang.String")})

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
		String explicitWaits = (String) attributes.get("explicitWaits");
		WebDriver driver = (WebDriver) nlpRequestModel.getWebDriver();
		
		int maxAttempts = 3;
		int attempts = 0;
		boolean success = false;
		
		

		try {

			while (attempts < maxAttempts && !success) {
				try {
					WebDriverWait ExplicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
					ExplicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
					success = true;
				} catch (StaleElementReferenceException e) {
					attempts++;

				}

			}
			element.click();
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully Waited till Stale Element Reference is Cleared");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Wait the Stale Element Reference is Cleared" + e);
		}
		return nlpResponseModel;
	}
}