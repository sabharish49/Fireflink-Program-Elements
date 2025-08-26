package Business_Logics;


import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;


@Component("LIC17217_PJT1001_PE_NLP70284969-0749-43e6-a60a-fb00869c69e7")
public class LaunchApp implements Nlp {
	@InputParams({@InputParam(name = "Desired Capabilities", type = "openqa.selenium.remote.DesiredCapabilities")})
	@ReturnType(name = "BrowserCapablity", type = "openqa.selenium.remote.DesiredCapabilities")

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

		// Your program element business logic goes here ...
		try
		{
			//DesiredCapabilities d=nlpRequestModel.getDesiredCapabilities();
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "localhost:2708 ");
			cap.merge(options);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Browser is Launched Successfully");
		}
		catch(Exception e)
		{
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to launch the browser");
		}
		nlpResponseModel.setDesiredCapabilities(cap);
		nlpResponseModel.getAttributes().put("BrowserCapablity", cap);
		return nlpResponseModel;
	}
} 

