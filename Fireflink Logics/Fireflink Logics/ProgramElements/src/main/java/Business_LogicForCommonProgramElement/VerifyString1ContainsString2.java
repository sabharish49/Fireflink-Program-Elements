package Business_LogicForCommonProgramElement;

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

@Component("LIC1710_PJT1014_PE_NLPde0f4d4d-cb0a-479f-993d-5958ad50be90")
public class VerifyString1ContainsString2 implements Nlp {
	@InputParams({ @InputParam(name = "String1", type = "java.lang.String"),
			@InputParam(name = "String2", type = "java.lang.String"), })
	  @ReturnType(name = "result", type = "java.lang.Boolean")

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
		String String1 = (String) attributes.get("String1");
		String String2 = (String) attributes.get("String2");
		Boolean result = null;

		try {
			String[] arr1 = String1.split("\\s", 0);
		    String[] arr2 = String2.split("\\s", 0);
			for (String firstString : arr1) {
				for(String secondString : arr2) {
					result=firstString.contains(secondString);
				if(result==true) {
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Successfully "+firstString+ "contains" +secondString+"");
					break;
				}
				}
				if(result==true) {
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Successfully String1 contains String2");
				    break;
				}
				else {
					result=false;
					nlpResponseModel.setStatus(CommonConstants.fail);
					nlpResponseModel.setMessage("String1 Doesnot contains String2");
				}
				}
		}
            catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Something Went Wrong" + e);
		}
	    nlpResponseModel.getAttributes().put("result", result);
		return nlpResponseModel;
	}
}
