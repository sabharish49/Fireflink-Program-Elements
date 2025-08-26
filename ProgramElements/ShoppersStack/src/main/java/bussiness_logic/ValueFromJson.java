package bussiness_logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
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



@Component("LIC14952_PJT1001_PE_NLPa47e89c9-c1b7-4520-81e4-58c037d202d9")
public class ValueFromJson implements Nlp {
	
	    @InputParams({@InputParam(name = "jsonBody", type = "java.lang.String"),
	    	@InputParam(name = "jsonPath", type = "java.lang.String")})
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
	          String jsonBody = (String) attributes.get("jsonBody");
	          String jsonPath = (String) attributes.get("jsonPath");

	          // Your program element business logic goes here ...
	           String Value="";
	           
	           try {
	        	   ObjectMapper objectMapper = new ObjectMapper();
	   	        JsonNode jsonNode = objectMapper.readTree(jsonBody);
	   	        
	   	     String[] paths = {jsonPath};
	 	        
	   	     for (String path : paths) {
		        Value  = (String) getValueByPath(jsonNode, path);    
		        }
	           
	   	     
	   	  nlpResponseModel.setStatus(CommonConstants.pass);
		   nlpResponseModel.setMessage("value fetchedsuccessfully");
	           
		    }
	           catch (Exception e) {
					
					nlpResponseModel.setStatus(CommonConstants.fail);
		 		   nlpResponseModel.setMessage("failed to  fetch the value" +e);
				}
	      
	      nlpResponseModel.getAttributes().put("value", Value);
          return nlpResponseModel;
      }

	      
	           private static Object getValueByPath(JsonNode jsonNode, String path) {
	   	        String[] keys = path.split("\\.");
	   	        for (String key : keys) {
	   	            if (key.endsWith("]")) {
	   	                String fieldName = key.substring(0, key.indexOf('['));
	   	                int index = Integer.parseInt(key.substring(key.indexOf('[') + 1, key.indexOf(']')));
	   	                jsonNode = jsonNode.get(fieldName).get(index);
	   	            } else {
	   	                jsonNode = jsonNode.get(key);
	   	            }

	   	            if (jsonNode == null) {
	   	                return null;
	   	            }
	   	        }

	   	        return jsonNode.isTextual() ? jsonNode.textValue() : jsonNode;
	   	    }
}
