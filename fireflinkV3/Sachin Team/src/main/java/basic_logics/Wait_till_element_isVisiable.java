package basic_logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

public class Wait_till_element_isVisiable implements Nlp {
	@InputParams({ @InputParam(name = "waittime", type = "java.lang.String"),
			@InputParam(name = "locatorValue", type = "java.lang.String") })
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
		String waittime = (String) attributes.get("waittime");
		String locatorValue = (String) attributes.get("locatorValue");

		boolean result = false;
		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(waittime)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			result=true;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully waited until element is visible in UI");
		}

		catch (Exception e) {
			result=false;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Failed to wait until element is visible in UI");
		}
		finally {
			driver.manage().timeouts().implicitlyWait(implicitWait);
		}

		nlpResponseModel.getAttributes().put("result", result);
		return nlpResponseModel;
	}
}