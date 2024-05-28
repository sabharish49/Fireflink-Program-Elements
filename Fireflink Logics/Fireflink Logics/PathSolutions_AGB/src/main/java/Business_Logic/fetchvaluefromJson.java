package Business_Logic;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

// the program is for if else nlp ...where in if element is displayed it will pass and enter if block ...if element is not displayed it will pass the PM and get into else block

@Component("LIC16279_PJT1002_PE_NLPd2c2defc-21fd-4ec6-952f-dfad83643667")
public class fetchvaluefromJson implements Nlp {
	@InputParams({ @InputParam(name = "jsonResponse", type = "java.lang.String"),
		@InputParam(name = "jsonPath", type = "java.lang.String") })
	@ReturnType(name = "jsonValue", type = "java.lang.String")

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
		String jsonResponse = (String) attributes.get("jsonResponse");
		String jsonPath = (String) attributes.get("jsonPath");
		JSONObject jsonObj = new JSONObject(jsonResponse);
		String jsonValue=null;
		try {
			Object value = jsonObj.get(jsonPath);
			if (value != null) {
				if (value instanceof String) {
					jsonValue = (String) value;
				} else if (value instanceof Integer) {
					int intValue = (int) value;
					jsonValue = String.valueOf(intValue);
				} else if (value instanceof Boolean) {
					boolean boolValue = (boolean) value;
					jsonValue = String.valueOf(boolValue);
				} else {
					nlpResponseModel.setStatus(CommonConstants.fail);
					nlpResponseModel.setMessage("Unknown path");
				}
			} else {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Unknown path");
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully Fetched Value from the Json Object" + jsonValue);
			System.out.println("jsonvalue"+ jsonValue);
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Fetch the Value" + e);
		}
		// String string3 = "Return Value";
		nlpResponseModel.getAttributes().put("jsonValue", jsonValue);
		return nlpResponseModel;
	}


}