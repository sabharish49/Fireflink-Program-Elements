
package Business_LogicForAndroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLPa029a901-5684-440b-820b-da7acfbde7f3")
public class generateRandomalphabets implements Nlp {
	@InputParams({ @InputParam(name = "Alphabetcount", type = "java.lang.Integer") })
	@ReturnType(name = "string3", type = "java.lang.String")

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
		int digits = (Integer) attributes.get("Alphabetcount");
		String generatedString = null;
		try {
			generatedString = RandomStringUtils.randomAlphabetic(digits);

			nlpResponseModel.setMessage("Generated RandomAlphabets");
			nlpResponseModel.setStatus(CommonConstants.pass);

		} catch (Exception e) {
			nlpResponseModel.setMessage("Failed to Generated RandomAlphabets");
			nlpResponseModel.setStatus(CommonConstants.fail);

		}
		nlpResponseModel.getAttributes().put("string3", generatedString);
		return nlpResponseModel;
	}
}