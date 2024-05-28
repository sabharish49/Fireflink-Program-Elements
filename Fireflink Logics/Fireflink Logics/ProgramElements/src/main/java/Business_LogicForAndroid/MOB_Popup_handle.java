
package Business_LogicForAndroid;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;


@Component("LIC1710_PJT1014_PE_NLP01ada60a-32eb-408d-a15c-323d0360a5fe")
public class MOB_Popup_handle implements Nlp {
	@InputParams({ @InputParam(name = "xpath", type = "java.lang.String"),
			@InputParam(name = "pollingTimeInSeconds", type = "java.lang.Integer"),
			@InputParam(name = "NumberOfAttempts", type = "java.lang.Integer") })

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
		Integer pollingTimeInSeconds = (Integer) attributes.get("pollingTimeInSeconds");
		Integer noOfAttempts = (Integer) attributes.get("NumberOfAttempts");

		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		Duration impWait = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		try {

			int count = 1;
			while (count <= noOfAttempts) {
				try {
					driver.findElement(By.xpath(xpath)).isDisplayed();
					driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
					break;
				} catch (Exception e) {
					count++;
					try {
						Thread.sleep(pollingTimeInSeconds);
					} catch (Exception e2) {
						nlpResponseModel.setStatus(CommonConstants.fail);
						nlpResponseModel.setMessage("Something went Wrong");
					}
				}

			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successufully handled the Pop");
		} catch (Exception e1) {
			nlpResponseModel.setStatus(CommonConstants.warning);
			nlpResponseModel.setMessage("Failed to handle the Pop" + e1);
		} finally {
			driver.manage().timeouts().implicitlyWait(impWait);
		}
		return nlpResponseModel;
	}

}
