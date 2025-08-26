package bussiness_logic;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


public class openApp implements Nlp {
	@InputParams({
		@InputParam(name = "driverPath", type = "java.lang.String")})

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
		String driverPath = (String) attributes.get("driverPath");

		try {

			UiAutomator2Options options = new UiAutomator2Options();
			options.setChromedriverExecutable(driverPath);
			
			//options.setCapability("browserName", "chrome");
			options.setCapability("platformName", "Android");
			options.setCapability("platformVersion", "14.0");
			options.setCapability("deviceName", "Galaxy A13");
			options.setCapability("appPackage", "com.mobile.ConnectLife");
			options.setCapability("appActivity", "com.example.sbiassistedonlinepolicyselling.Startup_PageActivity");
			options.setCapability("noReset", true);
			options.setCapability("autoGrantPermissions", true);

			@SuppressWarnings("deprecation")
			URL appiumServerURL = new URL("http://localhost:4723/wd/hub");
			AndroidDriver driver = new AndroidDriver(appiumServerURL, options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
		return nlpResponseModel;
	}
}
