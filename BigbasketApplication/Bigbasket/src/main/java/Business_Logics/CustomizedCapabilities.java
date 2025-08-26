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

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.springframework.stereotype.Component;

@Component("LIC19046_PJT1001_PE_NLP12d97220-3b4f-4dbb-949a-e50a8d6b21c1")
public class CustomizedCapabilities implements Nlp {
	@InputParams({
		@InputParam(name = "Desired Capability", type = "org.openqa.selenium.remote.DesiredCapabilities"),
		@InputParam(name = "Key", type = "java.lang.String")})
	    @ReturnType(name = "value", type = "java.lang.Object")

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
		DesiredCapabilities cap = (DesiredCapabilities) attributes.get("Desired Capability");
		String key = (String) attributes.get("Key");
		
		AndroidDriver androidDriver = nlpRequestModel.getAndroidDriver();
		IOSDriver iosDriver = nlpRequestModel.getIosDriver();
		Object platformName =null;
		try {
			if (androidDriver != null) {
			
	        platformName = androidDriver.getCapabilities().getCapability(key);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("successfully fetched the Capability");
			}
			else {
				platformName = iosDriver.getCapabilities().getCapability(key);
			}
		}
		catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to get the capability"+e);
		}

		nlpResponseModel.setDesiredCapabilities(cap);
		nlpResponseModel.getAttributes().put("value", platformName);

		return nlpResponseModel;

	}


}