package bussiness_logic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

public class openAndroidApp implements Nlp {
	@InputParams({
		@InputParam(name = "driverPath", type = "java.lang.String")})
	    @ReturnType(name="cap",type="org.openqa.selenium.remote.DesiredCapabilities")

	@Override
	public List<String> getTestParameters() throws NlpException {
		return new ArrayList<>();
	}

	@Override
	public StringBuilder getTestCode() throws NlpException {
		return new StringBuilder();
	}

	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		@SuppressWarnings("unused")
		String driverPath = (String) attributes.get("driverPath");
		DesiredCapabilities cap =null;
		try {

			cap= new DesiredCapabilities();
	        cap.setCapability("automationName", "UiAutomator2");
	        cap.setCapability("platformName", "Android");
	        cap.setCapability("appPackage", "com.mobile.ConnectLife");
			cap.setCapability("appActivity", "com.example.sbiassistedonlinepolicyselling.Startup_PageActivity");
	        cap.setCapability("noReset", true);
	        cap.setCapability("autoGrantPermissions", true);
//	        cap.setCapability("chromedriverExecutable", driverPath);
	        
	        @SuppressWarnings("deprecation")
			AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), cap);
			nlpResponseModel.setAndroidDriver(driver);
			
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("App launched successfully using the specified capabilities.");
		} catch (MalformedURLException e) {

			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to launch app: Invalid Appium server URL. " + e.getMessage());
		} catch (Exception e) {

			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to launch app: " + e.getMessage());
		}
        nlpResponseModel.getAttributes().put("cap", cap);
		return nlpResponseModel;
	}
}
