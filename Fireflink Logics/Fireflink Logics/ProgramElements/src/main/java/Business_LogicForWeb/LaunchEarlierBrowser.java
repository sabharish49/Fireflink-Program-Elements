package Business_LogicForWeb;

import java.io.File;
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


import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP1cefff90-780b-42c8-a9f5-3483b8b49f98")
public class LaunchEarlierBrowser implements Nlp {
	@InputParams({ 	@InputParam(name = "Set Folder Directory Path", type = "java.lang.String"),
					@InputParam(name = "Set Chrome Foleder Path", type = "java.lang.String"),
					@InputParam(name = "Set Cmd Command Statement", type = "java.lang.String") })

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

	@SuppressWarnings("deprecation")
	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		DesiredCapabilities cap = (DesiredCapabilities) attributes.get("Desired Capabilities");

		try 
		{
			String folderDirectory = (String) attributes.get("Set Folder Directory Path");
			String chromePath = (String) attributes.get("Set Chrome Foleder Path");
			String cmdCommand = (String) attributes.get("Set Cmd Command Statement");
			Runtime.getRuntime().exec("cmd /c start cmd.exe /K " + cmdCommand + folderDirectory, null, new File(chromePath));
			Thread.sleep(2000);
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Browser Launched Successfully");
		} 
		catch (Exception e) 
		{
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed To launch The Browser");
		}
		return nlpResponseModel;
	}
}
	