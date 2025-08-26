package Logics;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import lombok.extern.slf4j.Slf4j;



public class ExcutionSpeed implements Nlp {
	@InputParams({ @InputParam(name = "Desired Capabilities", type = "org.openqa.selenium.remote.DesiredCapabilities")})
	@ReturnType(name = "capability", type = "org.openqa.selenium.remote.DesiredCapabilities")
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
		DesiredCapabilities cap = (DesiredCapabilities) attributes.get("Desired Capabilities");
		
		try {

			DesiredCapabilities dc = new DesiredCapabilities();
			
		    dc.setCapability("appium:skipServerInstallation", true);
		    dc.setCapability("appium:disableAndroidWatchers", true);
		    dc.setCapability("appium:ignoreUnimportantViews", true);
		    dc.setCapability("appium:disableWindowAnimation", true);
		    dc.setCapability("appium:waitForIdleTimeout", 10);
		    dc.setCapability("appium:disableWindowAnimation", true);
		    dc.setCapability("appium:skipDeviceInitialization", true);
		    
			cap.merge(dc);
			
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully added the capability");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed To Add capability");
		}
		nlpResponseModel.getAttributes().put("capability",cap);
		return nlpResponseModel;
	}
}