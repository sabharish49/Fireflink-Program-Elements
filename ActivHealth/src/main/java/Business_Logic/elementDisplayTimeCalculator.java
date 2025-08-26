
package Business_Logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.springframework.stereotype.Component;
@Slf4j
@Component("LIC19774_PJT1001_PE_NLP2a21ee16-9b1f-425d-854c-e9e7464f359f")
public class elementDisplayTimeCalculator implements Nlp {
	@InputParams({ @InputParam(name = "Xpath", type = "java.lang.String"),
			@InputParam(name = "inputTime", type = "java.lang.Integer") })
	@ReturnType(name = "timeSpan", type = "java.lang.Integer")

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
		Integer inputTime = (Integer) attributes.get("inputTime");
		long Time = 0;
		long displayTime = 0;
		try {
			
			AndroidDriver driver = nlpRequestModel.getAndroidDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			
			int timeInSeconds = inputTime;
			long startTime = System.currentTimeMillis();
			long elapsedTime = 0;
		
			boolean flag = false;
			while (elapsedTime < timeInSeconds * 1000) { // Convert totalTimeInSeconds to milliseconds
				try {
					if (driver.findElement(By.xpath(Xpath)).isDisplayed()) {
						long endTime = System.currentTimeMillis();				
						displayTime =  ((endTime - startTime) / 1000); // Convert milliseconds to seconds
						Time=displayTime;
						log.info("The element found in "+displayTime);
						flag = true;
						break;
					}
				} catch (Exception e) {

				}
				elapsedTime = System.currentTimeMillis() - startTime;
			}

			if (flag == true) {
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Element is displayed within "+displayTime+" seconds");
			} else {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("element is not displayed within specified time ");
			}

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("element is not displayed within specified time ");
		}

		
		nlpResponseModel.getAttributes().put("timeSpan", displayTime);
		return nlpResponseModel;
	}

}