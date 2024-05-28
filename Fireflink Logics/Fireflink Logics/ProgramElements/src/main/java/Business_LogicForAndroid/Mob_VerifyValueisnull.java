package Business_LogicForAndroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;

@Component("LIC1710_PJT1014_PE_NLPc37eaf76-6d60-4da6-aa93-7be678c6174f")
public class Mob_VerifyValueisnull implements Nlp {
	@InputParams({ @InputParam(name = "String", type = "java.lang.String") })

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
			String string1 = (String) attributes.get("String");
			AndroidDriver driver = nlpRequestModel.getAndroidDriver();
			String value = "";
			value = string1;
			if (value.isEmpty()) {
				nlpResponseModel.setMessage("String is empty");
				nlpResponseModel.setStatus(CommonConstants.pass);
			} else {

				nlpResponseModel.setMessage("String is not empty");
				nlpResponseModel.setStatus(CommonConstants.fail);
			}

		} catch (NullPointerException e) {
			nlpResponseModel.setMessage("String is NOT empty" + e);
			nlpResponseModel.setStatus(CommonConstants.fail);

		}

		return nlpResponseModel;
	}
}