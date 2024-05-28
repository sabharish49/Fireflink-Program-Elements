package Business_logic;


import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component("LIC14172_PJT1001_PE_NLP536595d9-1698-45e4-a6f5-4fecd6a9b3a4")
public class MOB_FetchingNumbersFromString implements Nlp {
	@InputParams({@InputParam(name = "value", type = "java.lang.String")})
	@ReturnType(name = "count", type = "java.lang.Integer")

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
		String value = (String) attributes.get("value");

		int count=0;
		// Your program element business logic goes here ...
		try {
			Pattern pattern = Pattern.compile("[\\d,.]+|[^\\w\\s]");
			Matcher matcher = pattern.matcher(value);

			while (matcher.find()) {
				String numberStr = matcher.group();
				try {
					double number = Double.parseDouble(numberStr);
					count++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("sucessfully fetched the number from string:"+ count);

		}catch(Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to fetch the number from string"+" "+e);
		}


		nlpResponseModel.getAttributes().put("count", count);
		return nlpResponseModel;
	}
} 
