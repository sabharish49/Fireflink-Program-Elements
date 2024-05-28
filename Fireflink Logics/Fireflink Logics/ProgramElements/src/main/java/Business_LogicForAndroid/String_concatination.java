package Business_LogicForAndroid;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP4ec9126f-2622-45ca-9346-86f60a3e0a3c")
public class String_concatination implements Nlp {
	@InputParams({ @InputParam(name = "String1", type = "java.lang.String"),
			@InputParam(name = "String2", type = "java.lang.String") })
	@ReturnType(name = "Value", type = "java.lang.String")

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
		String string1 = (String) attributes.get("String1");
		String string2 = (String) attributes.get("String2");
		String s3 = null;
		try {
			s3 = string1.concat(string2);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Stringvalue is concatinated" + s3);
		} catch (Exception e) {

			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to concatinate the string");

		}
		nlpResponseModel.getAttributes().put("Value", s3);
		return nlpResponseModel;
	}
}
