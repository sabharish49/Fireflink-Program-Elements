package WebBusinessLogics;

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

public class Concatination implements Nlp {

	@InputParams({@InputParam(name="String1",type="java.lang.String"),@InputParam(name="String2",type="java.lang.String"),
			@InputParam(name="String3",type="java.lang.String")})
	 @ReturnType(name = "AssignStepreturnvalue", type = "java.lang.String")

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
		String String3 = (String) attributes.get("String3");
        String concatvalue=null;
	

		try {
			
			concatvalue=String1.concat(String2).concat(String3);
			
			
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("The Concat value is:"+concatvalue);
			

		}

		catch (Exception e) {

			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to concat value" + e);
		}

		nlpResponseModel.getAttributes().put("AssignStepreturnvalue",concatvalue);
		return nlpResponseModel;
	}
}
