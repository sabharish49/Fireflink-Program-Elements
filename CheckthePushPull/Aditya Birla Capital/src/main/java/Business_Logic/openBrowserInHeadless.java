
package Business_Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
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

import org.springframework.stereotype.Component;

@Component("LIC23707_PJT1008_PE_NLP37c66c4b-f937-4531-9787-62e92a1cdd72")
public class openBrowserInHeadless implements Nlp {
	@InputParams({
			@InputParam(name = "Desired Capabilities", type = "org.openqa.selenium.remote.DesiredCapabilities") })

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
			if (cap.getBrowserName().contains("chrome")) {
				LoggingPreferences logPrefs = new LoggingPreferences();
				ChromeOptions headlessOptions = new ChromeOptions();
				headlessOptions.addArguments("--headless");
				headlessOptions.addArguments("--remote-allow-origins=*");
				headlessOptions.setCapability("goog:loggingPrefs", logPrefs);
				dc.setCapability(ChromeOptions.CAPABILITY, headlessOptions);
				cap.merge(dc);

			} else if (cap.getBrowserName().contains("dge")) {
				LoggingPreferences logPrefs = new LoggingPreferences();
				EdgeOptions headlessOptions = new EdgeOptions();
				headlessOptions.addArguments("--headless");
				headlessOptions.addArguments("--remote-allow-origins=*");
				headlessOptions.setCapability("goog:loggingPrefs", logPrefs);
				cap.merge(headlessOptions);
			}

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage(cap.getBrowserName() + " Headless browser launched successfully");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to launch Headless browser successfully");
		}
		return nlpResponseModel;
	}
}