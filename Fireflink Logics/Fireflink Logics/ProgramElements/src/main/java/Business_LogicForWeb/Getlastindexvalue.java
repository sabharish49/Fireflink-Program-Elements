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
import com.tyss.optimize.nlp.util.annotation.ReturnType;

@Component("LIC1710_PJT1014_PE_NLPcb750307-b66c-4a2d-8d49-33b8c64133ec")
public class Getlastindexvalue implements Nlp {
	@InputParams({ @InputParam(name = "SetList", type = "java.util.ArrayList") })
	@ReturnType(name = "GetValue", type = "java.lang.String")

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
		int GetValue = 0;
		try {
			@SuppressWarnings("unchecked")
			ArrayList<String> SetList = (ArrayList<String>) attributes.get("SetList");
			GetValue = SetList.size() - 1;

			nlpResponseModel.setMessage(" Selected Value is :  " + GetValue);
			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {

			nlpResponseModel.setMessage(" Something Went Wrong " + e);
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		nlpResponseModel.getAttributes().put("GetValue", GetValue);
		return nlpResponseModel;
	}
}
