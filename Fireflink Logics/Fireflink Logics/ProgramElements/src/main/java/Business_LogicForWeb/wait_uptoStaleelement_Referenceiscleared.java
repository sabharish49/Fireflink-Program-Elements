package Business_LogicForWeb;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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
import com.tyss.optimize.nlp.util.annotation.ReturnType;

@Component("LIC1710_PJT1014_PE_NLP186edc8a-598e-4ee4-b124-de1510a562ba")
public class wait_uptoStaleelement_Referenceiscleared implements Nlp {
	@InputParams({ @InputParam(name = "xpath", type = "java.lang.String"),
			@InputParam(name = "seconds", type = "java.lang.Integer") })
	@ReturnType(name = "assignstepReturntype", type = "java.lang.Boolean")

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
		Integer seconds = (Integer) attributes.get("seconds");

		boolean isdisplayed = false;

		int i = 0;

		try {

			while (i < seconds) {
				try {
					WebDriver driver = (WebDriver) nlpRequestModel.getWebDriver();
					WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(seconds));
					wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					isdisplayed = true;
					nlpResponseModel.setMessage("waited till the display of the element");
					nlpResponseModel.setStatus(CommonConstants.pass);
					break;

				} catch (StaleElementReferenceException f) {
					i++;
					isdisplayed = false;
					nlpResponseModel.setMessage("Failed to display of the element");
					nlpResponseModel.setStatus(CommonConstants.fail);
				}
			}

		} catch (Exception e) {
			nlpResponseModel.setMessage("Failed to wait till the presence of the element");
			nlpResponseModel.setStatus(CommonConstants.fail + e);
		}
		nlpResponseModel.getAttributes().put("assignstepReturntype", isdisplayed);
		return nlpResponseModel;
	}
}
