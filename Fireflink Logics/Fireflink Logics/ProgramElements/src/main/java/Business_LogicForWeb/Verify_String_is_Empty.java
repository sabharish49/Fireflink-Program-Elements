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

@Component("LIC1710_PJT1014_PE_NLPc538bcf0-a39e-48dd-ba32-dfa07b35b5c1")
public class Verify_String_is_Empty implements Nlp {
	@InputParams({ @InputParam(name = "string1", type = "java.lang.String") })

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
		String string1 = (String) attributes.get("string1");

		try {

			if (string1.isEmpty()) {
				nlpResponseModel.setMessage("String is empty" + string1);
				nlpResponseModel.setStatus(CommonConstants.pass);
			} else {

				nlpResponseModel.setMessage("String is not empty" + string1);
				nlpResponseModel.setStatus(CommonConstants.fail);
			}

		} catch (Exception e) {
			nlpResponseModel.setMessage("String is NOT empty" + e);
			nlpResponseModel.setStatus(CommonConstants.fail);

		}

		return nlpResponseModel;
	}
}
