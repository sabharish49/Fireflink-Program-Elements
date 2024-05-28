package Mobileweb;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

@Component("LIC1710_PJT1014_PE_NLP67f29cda-ae89-42c8-ba2c-e18e198b125d")
public class Mob_TestInWebView implements Nlp {
	@InputParams({ @InputParam(name = "exePath", type = "java.lang.String"),
			@InputParam(name = "deviceName", type = "java.lang.String"),
			@InputParam(name = "browserName", type = "java.lang.String"),
			@InputParam(name = "url", type = "java.lang.String") })

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

		// Path of driver executable present in client present under
		// C:\FireFlinkClient\resources\flinko-client\localnode
		String exePath = (String) attributes.get("exePath");
		String deviceName = (String) attributes.get("deviceName");
		String browserName = (String) attributes.get("browserName");
		String url = (String) attributes.get("url");
//		UiAutomator2Options options = new UiAutomator2Options();
//		options.setDeviceName(deviceName);
//		options.setChromedriverExecutable(exePath);
//		options.setCapability("browserName",browserName);

		AndroidDriver driver = null;
		try {
			UiAutomator2Options options = new UiAutomator2Options();
			options.setDeviceName(deviceName);
			options.setChromedriverExecutable(exePath);
			options.setCapability("browserName", browserName);
			driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(url);
//			IDriver driver1 = new com.tyss.optimize.data.models.dto.drivers.AndroidDriver(driver);
//			nlpResponseModel.setDriver(driver1);

			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		// IDriver driver1 = new
		// com.tyss.optimize.data.models.dto.drivers.AndroidDriver(driver);
		// nlpResponseModel.setDriver(driver1);
		nlpResponseModel.setAndroidDriver(driver);

		return nlpResponseModel;
	}
}