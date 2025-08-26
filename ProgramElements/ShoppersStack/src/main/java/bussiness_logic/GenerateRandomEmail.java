package bussiness_logic;


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

@Component("LIC14952_PJT1001_PE_NLPee1f5104-a599-40e2-9d1c-e1b2985f3df9")
public class GenerateRandomEmail implements Nlp {
	
	    @InputParams({@InputParam(name = "inputString", type = "java.lang.String"),
	    @InputParam(name = "domain", type = "java.lang.String"),
	    @InputParam(name = "Digits", type = "java.lang.Integer") })
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
	          String inputString = (String) attributes.get("inputString");
	          String domain = (String) attributes.get("domain");
	          Integer Digits = (Integer) attributes.get("Digits");

	          // Your program element business logic goes here ...
	           Boolean result=true;
	           String randomEmail = inputString;
	           try {
	        	   
	   	           Random random = new Random();
	   	        for (int i = 0; i < Digits; i++) {
	   	            int randomDigit = random.nextInt(10); 
	   	            randomEmail += randomDigit;
	   	        }

	   	      
	   	        randomEmail += domain;
	        	  
	        		   result=true;
	        		   nlpResponseModel.setStatus(CommonConstants.pass);
	        		   nlpResponseModel.setMessage("email generated");
	        	   
	        		   
	        		   
			} 
	           catch (Exception e) {
				result=false;
				nlpResponseModel.setStatus(CommonConstants.fail);
	 		   nlpResponseModel.setMessage("email not generated" +e);
			}
	         // String string3 = "Return Value";
	          nlpResponseModel.getAttributes().put("result", randomEmail);
	          return nlpResponseModel;
	      }
	  } 






	
	


