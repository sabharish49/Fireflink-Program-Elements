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

@Component("LIC1710_PJT1014_PE_NLP9e2dacaf-6453-4b32-b44b-ac2e49677cfa")
public class Get_Mob_OTP implements Nlp {
	@InputParams({ @InputParam(name = "otpMessage", type = "java.lang.String") })
	@ReturnType(name = "OTP", type = "java.lang.Integer")

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
		Integer otp = 0;
		try {
			String message = (String) attributes.get("otpMessage");
			message = message.replaceAll("[^0-9?]", "");
			otp = Integer.parseInt(message.substring(0, 6));
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Mobile OTP Fetched successfully " + otp);

		} catch (Exception e) {
			e.printStackTrace();
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Fetch Mobile OTP");
		}
		nlpResponseModel.getAttributes().put("OTP", otp);
		return nlpResponseModel;

	}
}
