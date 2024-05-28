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

@Component("LIC1710_PJT1014_PE_NLP6b9c5797-d37f-499a-a88d-0112240accb3")
public class VerifyStringcontainsString implements Nlp {
	@InputParams({ @InputParam(name = "string1", type = "java.lang.String"),
			@InputParam(name = "string2", type = "java.lang.String") })
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
		String string1 = (String) attributes.get("string1");
		String string2 = (String) attributes.get("string2");
        String s3=null;
		try {
			string1.replaceAll("[0-9., ]", "");
			string2.replaceAll("[0-9., ]", "");
		  if(string1.contains(string2)) {
			  nlpResponseModel.setStatus(CommonConstants.pass);
			  nlpResponseModel.setMessage(string1+" "+"contains"+string2+" ");
		  }else {
			  nlpResponseModel.setStatus(CommonConstants.fail);
			  nlpResponseModel.setMessage(string1+" "+"not contains"+string2+" ");
		  }
			
		} catch (Exception e) {
		
			nlpResponseModel.setStatus(CommonConstants.fail);
			  nlpResponseModel.setMessage(string1+" "+"not contains"+string2+" ");
		}

		String string3 = "Return Value";
		nlpResponseModel.getAttributes().put("string3", string3);
		return nlpResponseModel;
	}
}
