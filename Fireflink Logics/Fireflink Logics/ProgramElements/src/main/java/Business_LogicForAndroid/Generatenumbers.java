package Business_LogicForAndroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLPefd98636-b68e-4d60-95bc-4277f53678f7")
public class Generatenumbers implements Nlp {
	@InputParams({ @InputParam(name = "Startcount", type = "java.lang.Integer"),
			@InputParam(name = "Endcount", type = "java.lang.Integer") })
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
		int startpoint = (Integer) attributes.get("Startcount");
		int endpoint = (Integer) attributes.get("Endcount");
		String num = null;
		try {
			Random random = new Random();
			long randomNumber = Math.abs(random.nextLong());
			String randomString = Long.toString(randomNumber);
			num = randomString.substring(startpoint, endpoint);

			nlpResponseModel.setMessage("Generated Randomnumbers");
			nlpResponseModel.setStatus(CommonConstants.pass);

		} catch (Exception e) {
			nlpResponseModel.setMessage("Failed to Generate Randomnumbers");
			nlpResponseModel.setStatus(CommonConstants.fail);

		}
		nlpResponseModel.getAttributes().put("string3", num);
		return nlpResponseModel;
	}
}
