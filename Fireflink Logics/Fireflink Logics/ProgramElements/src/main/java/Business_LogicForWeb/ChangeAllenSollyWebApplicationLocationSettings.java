package Business_LogicForWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLPad128f0f-ee4c-46ca-a14d-01ff435d5155")
public class ChangeAllenSollyWebApplicationLocationSettings implements Nlp {

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
		try 
		{
			String url3						= "chrome://settings/content/siteDetails?site=https%3A%2F%2Fallensolly.abfrl.in";
			String url4						= "chrome://settings/content/siteDetails?site=https%3A%2F%2Fcheckout.abfrl.in";
			String url5						= "chrome://settings/content/siteDetails?site=https%3A%2F%2Fprofile.abfrl.in";
			String allowPopUpRadioButton 	= "document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#basicPage > settings-section.expanded > settings-privacy-page\").shadowRoot.querySelector(\"#pages > settings-subpage > settings-category-default-radio-group\").shadowRoot.querySelector(\"#enabledRadioOption\").shadowRoot.querySelector(\"#button > div.disc-border\")";
			WebDriver driver				= (WebDriver) nlpRequestModel.getWebDriver();
			JavascriptExecutor jse			=(JavascriptExecutor)driver;
			WebElement element = (WebElement) jse.executeScript("return "+allowPopUpRadioButton);
			
			driver.get(url3);
			try {Thread.sleep(2000);} catch (InterruptedException e) {}
			try {element.click();}catch (WebDriverException e) {}
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			
			driver.get(url4);
			try {Thread.sleep(2000);} catch (InterruptedException e) {}
			try {element.click();}catch (WebDriverException e) {}
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			
			driver.get(url5);
			try {Thread.sleep(2000);} catch (InterruptedException e) {}
			try {element.click();}catch (WebDriverException e) {}
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			
			nlpResponseModel.setMessage("Web Application Settings Changed");
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setWebDriver(driver);		
		} 
		catch (Exception e) 
		{
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		return nlpResponseModel;
	}
}
