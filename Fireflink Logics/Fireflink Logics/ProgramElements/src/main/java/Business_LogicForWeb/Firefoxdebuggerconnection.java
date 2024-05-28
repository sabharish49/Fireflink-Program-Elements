package Business_LogicForWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

@Component("LIC1710_PJT1014_PE_NLP2364b4cf-047a-4071-931c-0e9c9703c3f8")
public class Firefoxdebuggerconnection implements Nlp {
	@InputParams({ @InputParam(name = "Desired Capabilities", type = "openqa.selenium.remote.DesiredCapabilities"),
			@InputParam(name = "Port", type = "java.lang.Integer") })
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

		try {
			Integer port = (Integer) attributes.get("Port");
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("debuggerAddress", "localhost:" + port + " ");
			cap.merge(options);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Browser is Launched Successfully");
			Thread.sleep(2000);
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
			Runtime.getRuntime().exec("taskkill /F /FI \"WINDOWTITLE eq Terminal\"");

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Browser got launched successfully");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to launch the browser because " + e);
		}

		nlpResponseModel.setDesiredCapabilities(cap);
		nlpResponseModel.getAttributes().put("BrowserCapablity", cap);
		return nlpResponseModel;
	}
}