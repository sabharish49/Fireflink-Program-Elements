package business_logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;




public class Check_If_Any_One_Element_IsEnabled implements Nlp {
	@InputParams({ @InputParam(name = "xpath", type = "java.lang.String"),
			@InputParam(name = "xpath2", type = "java.lang.String") })

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
		String xpath2 = (String) attributes.get("xpath2");
		WebDriver driver=nlpRequestModel.getWebDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		try {

			if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isDisplayed()) {
				//Thread.sleep(3000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully Clicked on " + xpath);
			} 
			else if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath2))).isDisplayed()) 
			{
				Thread.sleep(3000);
//				js.executeScript("arguments[0].click();", string2);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath2))).click();
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully Clicked on " + xpath2);
			}
			else
			{
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Both the elements are disabled");
			}

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed To Click on Element");

		}

		return nlpResponseModel;
	}
}
