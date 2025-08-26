package Business_Logics;
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


import org.springframework.stereotype.Component;

@Component("LIC20369_PJT1002_PE_NLPfcd69de1-bc28-4eac-ba7a-95ce5ca16d71")
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
		 //   dc.setCapability("appium:ignoreUnimportantViews", true);
		    dc.setCapability("appium:disableWindowAnimation", true);
		    dc.setCapability("appium:waitForIdleTimeout", 10);
		 //   dc.setCapability("appium:skipDeviceInitialization", true);
		    Runtime.getRuntime().exec("adb shell settings put global window_animation_scale 0");
		    Runtime.getRuntime().exec("adb shell settings put global transition_animation_scale 0");
		    Runtime.getRuntime().exec("adb shell settings put global animator_duration_scale 0");

		    
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