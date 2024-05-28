package Business_LogicForAndroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebElement;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLPd5fd36d5-e32a-4b52-b944-18961dd2bc30")
public class MOB_FetchOnlyAmountOfProduct implements Nlp {
	@InputParams({ @InputParam(name = "Price", type = "java.lang.String") })
	@ReturnType(name = "fetchedDetails", type = "java.lang.Integer")

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
		// WebElement element = (WebElement) attributes.get("element");
		String s1 = (String) attributes.get("Price");
		// Logic start from here

		String s2 = null;
		int s3 = 0;
		try {

			char c = (char) 8377;
			s2 = s1.replace("" + c + "", "");
			s2 = s2.replaceAll(",", "");
			s2 = s2.replaceAll(" ", "");
			double d1 = Double.parseDouble(s2);
			s3 = (int) d1;

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Amount Fetched " + s3);
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Amount Not Fetched " + e);
		}
		nlpResponseModel.getAttributes().put("FetchedDetails", s3);
		return nlpResponseModel;
	}
}