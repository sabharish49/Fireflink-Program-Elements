package Business_LogicForWeb;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;


@Component("LIC1710_PJT1014_PE_NLPe75e33b6-a8e5-49da-8be6-eb3a07ebac37")
public class Verify_Stringlength_is_as_expectedvalue implements Nlp {
	@InputParams({ @InputParam(name = "String value", type = "java.lang.String"),
			@InputParam(name = "Expected Length value", type = "java.lang.Integer") })

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
			String stringValue = (String) attributes.get("String value");
			Integer expectedValue = (Integer) attributes.get("Expected Length value");
			int sLength = stringValue.length();
			if(sLength==expectedValue) {
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("String length is same as Expected length "+stringValue);
			}
			else {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("String length is not as Expected length "+stringValue);
			}
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to compare the String length"+e);
		}
		
		return nlpResponseModel;
	}
}
