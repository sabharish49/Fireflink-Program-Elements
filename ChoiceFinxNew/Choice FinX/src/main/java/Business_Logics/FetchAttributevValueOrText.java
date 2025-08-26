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
import io.appium.java_client.touch.offset.PointOption;

import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;


@Component("LIC20369_PJT1002_PE_NLP966c8482-2186-473d-a9bb-35c3563e15c7")
public class FetchAttributevValueOrText implements Nlp {
	@InputParams({ @InputParam(name = "Xpath", type = "java.lang.String") })
	@ReturnType(name = "result", type = "java.lang.String")

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
		String xpath = (String) attributes.get("Xpath");

		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		
		String value = null;
		
		try {
			int count=0;
			while (value == null && count<=25) 
			{
				try
				{
					if (driver.findElement(By.xpath(xpath)).getText() != null) 
					{
						value = driver.findElement(By.xpath(xpath)).getText();
						break;
					}
					
					if (driver.findElement(By.xpath(xpath)).getAttribute("content-desc") != null)
					{
						value = driver.findElement(By.xpath(xpath)).getAttribute("content-desc");
						break;
					}	
			
				} 
				catch (Exception e) {
			
				}
				count++;
		
			}	
			
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully fetched the value from element :"+value );
			
		}catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Failed to fetch the value from element :"+ e );
		}
	
			nlpResponseModel.getAttributes().put("result", value);
	        return nlpResponseModel;
		}
}
