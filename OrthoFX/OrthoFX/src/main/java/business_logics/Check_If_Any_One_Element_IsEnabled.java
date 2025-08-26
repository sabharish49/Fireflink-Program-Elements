package business_logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import org.springframework.stereotype.Component;

@Component("LIC21068_PJT1002_PE_NLP15e3d292-0925-44ac-95bb-383640b8427a")
public class Check_If_Any_One_Element_IsEnabled implements Nlp {
	@InputParams({ @InputParam(name = "Element 1", type = "org.openqa.selenium.WebElement"),
			@InputParam(name = "Element 2", type = "org.openqa.selenium.WebElement") })

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
		WebElement string1 = (WebElement) attributes.get("Element 1");
		WebElement string2 = (WebElement) attributes.get("Element 2");
		WebDriver driver=nlpRequestModel.getWebDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		try {

			if (wait.until(ExpectedConditions.visibilityOf(string1)).isEnabled()) {
				//Thread.sleep(3000);
				js.executeScript("arguments[0].click();", string1);
			//	wait.until(ExpectedConditions.elementToBeClickable(string1)).click();
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully Clicked on " + string1.getText());
			} else if (wait.until(ExpectedConditions.visibilityOf(string2)).isEnabled()) {
				Thread.sleep(3000);
				js.executeScript("arguments[0].click();", string2);
				//wait.until(ExpectedConditions.elementToBeClickable(string2)).click();
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully Clicked on " + string2.getText());
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
