package Business_Logics;


import com.tyss.optimize.common.util.CommonConstants;

import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;






public class WaitForElementInvisibility implements Nlp {

	@InputParams({@InputParam(name = "Xpath", type = "java.lang.String"),@InputParam(name = "time", type = "java.lang.Integer")})
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
		String Xpath = (String) attributes.get("Xpath");
		Integer time = (Integer) attributes.get("time");
		boolean result=true;
		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
		try {	
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		 
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(Xpath))));
	        
			result=true;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Waited for Successfully element to be invisible");

		}
		catch (Exception e) 
		{
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("element is still visible in the screen");		
		}
		finally {
			driver.manage().timeouts().implicitlyWait(implicitWait);
		}
		
		nlpResponseModel.getAttributes().put("result", result);
		return nlpResponseModel;
	}
} 







