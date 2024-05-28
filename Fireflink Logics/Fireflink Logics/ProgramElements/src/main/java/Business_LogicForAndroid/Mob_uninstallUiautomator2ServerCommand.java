package Business_LogicForAndroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;

@Component("LIC1710_PJT1014_PE_NLPae35c741-221a-4da6-9303-fc3143377d9f")
public class Mob_uninstallUiautomator2ServerCommand implements Nlp {

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
			Runtime rt = Runtime.getRuntime();
			Runtime.getRuntime().exec("cmd /c start cmd.exe /K " + "adb uninstall io.appium.uiautomator2.server.test");
			Thread.sleep(3000);
			// close CMD
			Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
			Thread.sleep(3000);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully uninstalled appium server");
		}

		catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed uninstalled appium server");
		}
		return nlpResponseModel;
	}
}
