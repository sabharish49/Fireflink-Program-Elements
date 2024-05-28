package Business_LogicForWeb;

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

@Component("LIC1710_PJT1014_PE_NLP1f8e87a9-3bb9-4570-8182-416e3c87aa67")
public class Removespaces implements Nlp {
	@InputParams({ @InputParam(name = "string1", type = "java.lang.String") })
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

		String value = null;
		try {
		
			String string1 = (String) attributes.get("string1");
			value = string1.replaceAll(" ", "");
			nlpResponseModel.setMessage("Removed all the spaces");
			nlpResponseModel.setStatus(CommonConstants.pass);

		} catch (Exception e) {
			
			nlpResponseModel.setMessage("Failed to Removed all the spaces"+e);
			nlpResponseModel.setStatus(CommonConstants.fail);

		}
		nlpResponseModel.getAttributes().put("string3", value);
		return nlpResponseModel;
	}
}
