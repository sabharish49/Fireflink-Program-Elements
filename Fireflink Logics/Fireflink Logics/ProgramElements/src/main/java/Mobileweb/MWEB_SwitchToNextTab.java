package Mobileweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("LIC1710_PJT1014_PE_NLP25db81ba-8987-4d47-b884-82e0ac879566")
public class MWEB_SwitchToNextTab implements Nlp {

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

		try {
			AndroidDriver driver = (AndroidDriver) nlpRequestModel.getDriver().getSpecificIDriver();
			String currentHandle = driver.getWindowHandle();
			Set<String> allWindowHandles = driver.getWindowHandles();
//			log.info("All available window handles are: {}", allWindowHandles);
			boolean ifAnotherTabAvailable = false;
			for (String h : allWindowHandles) {
				if (!h.equals(currentHandle)) {
					driver.switchTo().window(h);
					ifAnotherTabAvailable = true;
				}
			}
			if (ifAnotherTabAvailable) {
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Switched tab successfully");
			} else {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Another tab not available");
			}
		} catch (Exception e) {
//			log.error("Exception is: ", e);
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to switch tab");
		}
		return nlpResponseModel;
	}
}
