package Business_Logic;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;


@Component("LIC16279_PJT1002_PE_NLP80886a97-8aae-4daf-acbd-d2f7d5bba812")
public class getvaluefromMap implements Nlp {
	@InputParams({@InputParam(name = "JsonBody", type = "java.lang.String"), @InputParam(name = "Key", type = "java.lang.Object")})
	@ReturnType(name = "value", type = "java.lang.String")

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
		String JsonBody = (String) attributes.get("JsonBody");
		Object Key = (Object) attributes.get("Key");	
		//Key=Key.toString();
		String value="";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			@SuppressWarnings("unchecked")
			Map<String, String> jsonMap = objectMapper.readValue(JsonBody, Map.class);
			String keyToRetrieve =  Key.toString();
			if (jsonMap.containsKey(keyToRetrieve)) {
				value = jsonMap.get(keyToRetrieve);
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully fetched the value "+value );
			}

		}
		catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to fetch the value"+e);

		}
		nlpResponseModel.getAttributes().put("value", value);
		return nlpResponseModel;
	}
} 



