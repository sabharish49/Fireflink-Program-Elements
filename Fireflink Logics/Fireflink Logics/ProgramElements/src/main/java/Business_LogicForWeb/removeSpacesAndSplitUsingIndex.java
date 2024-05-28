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

@Component("LIC1710_PJT1014_PE_NLP063600f5-000e-46d9-b0bf-d9e61f1709ef")
public class removeSpacesAndSplitUsingIndex implements Nlp {
	@InputParams({ @InputParam(name = "String1", type = "java.lang.String"),
			@InputParam(name = "Indexvalue", type = "java.lang.Integer") })
	@ReturnType(name = "AssignstepReturnvalue", type = "java.lang.String")

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
		Integer index = (Integer) attributes.get("Indexvalue");
		String s2 = null;
		try {
			String ch = string1;
			String[] ch1 = ch.split(" ");
			s2 = ch1[index];

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Sucessfully splitted string " + s2);

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to split the String" + e);
		}
		nlpResponseModel.getAttributes().put("AssignstepReturnvalue", s2);
		return nlpResponseModel;
	}
}