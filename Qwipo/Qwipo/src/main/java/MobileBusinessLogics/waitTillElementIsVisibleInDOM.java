package MobileBusinessLogics;

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
import io.appium.java_client.ios.IOSDriver;

public class waitTillElementIsVisibleInDOM implements Nlp {
	@InputParams({ 
		@InputParam(name = "Android Xpath", type = "java.lang.String"),
		@InputParam(name = "iOS Xpath", type = "java.lang.String"),
		@InputParam(name = "waitTime", type = "java.lang.Integer") 
	})
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
		String androidXpath = (String) attributes.get("Android Xpath");
		String iOSXpath = (String) attributes.get("iOS Xpath");
		Integer waitTime = (Integer) attributes.get("waitTime");

		boolean res = false;

		AndroidDriver androidDriver = nlpRequestModel.getAndroidDriver();
		IOSDriver iosDriver = nlpRequestModel.getIosDriver();

		Duration implicitWait = null;
		long startTime = System.currentTimeMillis(); 
		long endTime = startTime + waitTime * 1000; 

		try {
			if (androidDriver != null) {

				try {
					implicitWait = androidDriver.manage().timeouts().getImplicitWaitTimeout();
					androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

					while (System.currentTimeMillis() < endTime) {  
						WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(1));
						try {
							if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(androidXpath))) != null) {
								res = true;  
								nlpResponseModel.setStatus(CommonConstants.pass);
								nlpResponseModel.setMessage("Element is visible in the DOM");
								break;  
							}
						} catch (Exception e) {
							nlpResponseModel.setStatus(CommonConstants.fail);
							nlpResponseModel.setMessage("Failed to wait until element is visible.");
						}
					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
					res = false;
					nlpResponseModel.setStatus(CommonConstants.fail);
					nlpResponseModel.setMessage("Failed to wait until element is visible.");

				} finally {
					androidDriver.manage().timeouts().implicitlyWait(implicitWait);
				}

			} else {

				try {
					implicitWait = iosDriver.manage().timeouts().getImplicitWaitTimeout();
					iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

					while (System.currentTimeMillis() < endTime) {  
						WebDriverWait wait = new WebDriverWait(iosDriver, Duration.ofSeconds(1));
						try {
							if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iOSXpath))) != null) {
								res = true;  
								nlpResponseModel.setStatus(CommonConstants.pass);
								nlpResponseModel.setMessage("Element is visible in the DOM");
								break;  
							}
						} catch (Exception e) {
							nlpResponseModel.setStatus(CommonConstants.fail);
							nlpResponseModel.setMessage("Failed to wait until element is visible.");
						}
					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
					res = false;
					nlpResponseModel.setStatus(CommonConstants.fail);
					nlpResponseModel.setMessage("Failed to wait until element is visible.");
				}
				finally {
					iosDriver.manage().timeouts().implicitlyWait(implicitWait);
				}
			}

		} catch (Exception e) {
			res = false;
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to wait until element is visible.");
		}

		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}
}
