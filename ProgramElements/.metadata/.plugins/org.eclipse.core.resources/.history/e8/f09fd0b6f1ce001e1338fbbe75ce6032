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

@Component("LIC14952_PJT1001_PE_NLP2dc98736-e247-406e-8287-7375c7bf9baf")
public class WEB_String implements Nlp {
	
	    @InputParams({@InputParam(name = "length", type = "java.lang.Integer")})
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
	         // String inputString = (String) attributes.get("inputString");
	         // String domain = (String) attributes.get("domain");
	          Integer size = (Integer) attributes.get("length");

	          // Your program element business logic goes here ...
	          
	           StringBuilder stringBuilder = new StringBuilder(size);
	           try {
	        	   
	            String chara = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	   	        
	   	        Random random = new Random();

	   	        

	   	        
	   	        for (int i = 0; i < size; i++) {
	   	            int randomIndex = random.nextInt(chara.length());
	   	            char randomChar = chara.charAt(randomIndex);
	   	            stringBuilder.append(randomChar);
	        	  
	   	        }   
	        		   nlpResponseModel.setStatus(CommonConstants.pass);
	        		   nlpResponseModel.setMessage("generated");
	        	   
	        		   
	        		   
			} 
	           catch (Exception e) {
				
				nlpResponseModel.setStatus(CommonConstants.fail);
	 		   nlpResponseModel.setMessage(" not generated" +e);
			}
	         // String string3 = "Return Value";
	          nlpResponseModel.getAttributes().put("result", stringBuilder);
	          return nlpResponseModel;
	      }
	  } 
