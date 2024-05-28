package Business_LogicForAndroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP39dd496b-7b8e-4f70-bee0-5a60600c72ed")
public class FetchonlyStringvalue implements Nlp {
	@InputParams({ @InputParam(name = "string1", type = "java.lang.String") })
	@ReturnType(name = "Assignreturnvalue", type = "java.lang.String")

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
		String v = null;
		try {
			v = string1.replaceAll("[0-9]", "");
			nlpResponseModel.setMessage("Fetched stringvalue");
			nlpResponseModel.setStatus(CommonConstants.pass);

		} catch (Exception e) {

			nlpResponseModel.setMessage("Failed to fetchStringvalue" + e);
			nlpResponseModel.setStatus(CommonConstants.pass);

		}
		nlpResponseModel.getAttributes().put("Assignreturnvalue", v);
		return nlpResponseModel;
	}
}
