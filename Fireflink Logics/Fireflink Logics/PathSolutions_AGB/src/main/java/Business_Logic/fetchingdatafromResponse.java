package Business_Logic;


import com.jayway.jsonpath.JsonPath;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
@Log4j2

@Component("LIC16279_PJT1002_PE_NLPe7146872-62dc-4e50-8d23-2951749eb11c")
public class fetchingdatafromResponse implements Nlp {
	@InputParams({@InputParam(name = "MapData", type = "java.util.Map"), @InputParam(name = "Response", type = "java.lang.String")})
	@ReturnType(name = "list", type = "java.util.List")

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
		@SuppressWarnings("unchecked")
		Map<String, String> MapData = (Map<String, String>) attributes.get("MapData");
		String Response = (String) attributes.get("Response");
		// List<String> returnList=null;
		List<String>  list = new ArrayList();
		try {
			Response=Response.replace("\\", "");
			for (Map.Entry<String, String> mapData : MapData.entrySet()) {
				String key = mapData.getKey();
				if (key.startsWith("JsonPath.")) {
					String jsonPath = key.replace("JsonPath.", "$.");
					try{
						String jsonValue = JsonPath.read(Response, jsonPath).toString();
						list.add(key+">>>"+jsonValue);
					}
					catch (Exception e){
						// list.add(key+">>>"+null);
					}
				}
			}  

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Sucessfully Fetched the Data");
		}
		catch(Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail+" "+e);
			nlpResponseModel.setMessage("Failed due to Exception");
		}

		nlpResponseModel.getAttributes().put("list", list);
		return nlpResponseModel;
	}

} 

