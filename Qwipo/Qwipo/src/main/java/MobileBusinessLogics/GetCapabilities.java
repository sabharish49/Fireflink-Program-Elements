package MobileBusinessLogics;

import java.net.URL;
import java.time.Duration;
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

public class GetCapabilities implements Nlp {
	    @ReturnType(name = "value", type = "java.lang.String")

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
		
		AndroidDriver androidDriver=null;
		
		
		IOSDriver iosDriver=null;
		try {
			androidDriver=nlpRequestModel.getAndroidDriver();
			iosDriver=nlpRequestModel.getIosDriver();
		} catch (Exception e) {
			iosDriver=nlpRequestModel.getIosDriver();
		}
		String value=null;
		try {
			if (androidDriver != null) {
				value="Android";
			}
			else {
				value="iOS";
			}
			 
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully fetched the Capability "+ value);
			
		}
		catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to get the capability value "+ e);
		}

		
		nlpResponseModel.getAttributes().put("value", value);

		return nlpResponseModel;

	}


}