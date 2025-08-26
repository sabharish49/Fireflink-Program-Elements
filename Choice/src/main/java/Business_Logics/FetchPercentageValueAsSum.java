
package Business_Logics;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("LIC20369_PJT1001_PE_NLP9cdf2987-4330-4b53-b0e7-ebcfcd4b786c")
public class FetchPercentageValueAsSum implements Nlp {
	@InputParams({ @InputParam(name = "inputValue", type = "java.lang.String"),
			@InputParam(name = "percentage", type = "java.lang.String") })
	@ReturnType(name = "result", type = "java.lang.String")

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
		String inputValue = (String) attributes.get("inputValue");
		String percentage = (String) attributes.get("percentage");

		String result = "";
		// Your program element business logic goes here ...
		try {
			result = calculatePercentageValue(percentage, Double.parseDouble(inputValue));
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Resultant value fetched successfully ");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to fetch value " + e);
		}

		nlpResponseModel.getAttributes().put("result", result);
		return nlpResponseModel;
	}

	public static String calculatePercentageValue(String percentageInput, double principalAmount) {

		String percentageString = percentageInput.replace("%", "");
		double percentage = Double.parseDouble(percentageString);
		int result = 0;
		// Calculate the percentage value
		double percentageValue = (percentage / 100) * principalAmount;
		result = (int) (principalAmount + percentageValue);

		return result + "";
	}
}