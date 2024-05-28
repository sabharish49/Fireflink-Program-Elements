
package Business_Logic;

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

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.springframework.stereotype.Component;

@Component("LIC12620_PJT1003_PE_NLP96f17c6f-b6cc-4d1e-8cc7-a3e9fa54241d")
public class Open_Android_or_iOS implements Nlp {
	 @InputParams({@InputParam(name = "Platform Name", type = "java.lang.String"),
	     @InputParam(name = "appPackage", type = "java.lang.String") ,
		 @InputParam(name = "appActivity", type = "java.lang.String") ,
		 @InputParam(name = "bundleId", type = "java.lang.String"),@InputParam(name="udid",type="java.lang.String") })
	 
	 
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
		String platformName = (String) attributes.get("Platform Name");
		String appPackage = (String) attributes.get("appPackage");
		String udid = (String) attributes.get("udid");
		String appActivity = (String) attributes.get("appActivity");
		String bundleId = (String) attributes.get("bundleId");
	
		try {
			
		
			 // Set desired capabilities
	        DesiredCapabilities caps = new DesiredCapabilities();

	        // For Android
	         if (platformName.equalsIgnoreCase("ios")) {
	            caps.setCapability("platformName", "iOS");
	            caps.setCapability("agentPath", "/Applications/FireFlinkClient.app/Contents/Resources/flinko-client/exec/appium-webdriveragent/WebDriverAgent.xcodeproj");
	            caps.setCapability("bootstrapPath", "/Applications/FireFlinkClient.app/Contents/Resources/flinko-client/exec/appium-webdriveragent");
	            caps.setCapability("udid", udid);
	            caps.setCapability("bundleId", bundleId);
	            // Add other iOS capabilities as needed
	        }
	        
	        // For iOS
	        else if  (platformName.equalsIgnoreCase("android")) {
	            caps.setCapability("platformName", "Android");
	        
	            caps.setCapability("appPackage", appPackage);
	            caps.setCapability("appActivity", appActivity);
	            
	            // Add other Android capabilities as needed
	        }
	        // Set Appium server URL
	        String appiumServerUrl = "http://127.0.0.1:4723/wd/hub";

	        // Initialize WebDriver
	       AndroidDriver androidDriver;
	       IOSDriver iosDriver;
	        if (platformName.equalsIgnoreCase("android")) {
	        	androidDriver = new AndroidDriver(new URL(appiumServerUrl), caps);
	        	nlpResponseModel.setAndroidDriver(androidDriver);
	        	
	        	nlpResponseModel.setMessage("Successfully Launched Pantaloons Application in Android platform");
				nlpResponseModel.setStatus(CommonConstants.pass);
	        } else {
	        	iosDriver = new IOSDriver(new URL(appiumServerUrl), caps);
	        	nlpResponseModel.setIosDriver(iosDriver);
	        	nlpResponseModel.setMessage("Successfully Launched Pantaloons Application in iOS platform");
				nlpResponseModel.setStatus(CommonConstants.pass);
	        }	
			
		} catch (Exception e) {
			e.printStackTrace();
			nlpResponseModel.setMessage("Failed to Launch Pantaloons Application "+e);
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		return nlpResponseModel;
	}
}