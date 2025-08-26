package bussiness_logic;


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

@Component("LIC14952_PJT1001_PE_NLP7cb7a943-6d1a-48ab-a7a8-1c4331aa2d35")
public class FetchingOtp implements Nlp {
	
	    @InputParams({@InputParam(name = "otpWithString", type = "java.lang.String"),
	    	@InputParam(name = "otpNumberCount", type = "java.lang.Integer")})
	    @ReturnType(name = "otp", type = "java.lang.Integer")

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
	          String otpWithString = (String) attributes.get("otpWithString");
	          Integer otpNumberCount = (Integer) attributes.get("otpNumberCount");

	          // Your program element business logic goes here ...
	           int otp=0;
	           
	           try {
	        	   String text =otpWithString.replaceAll("[^0-9?]", "");
	       		    otp=Integer.parseInt(text.substring(0, otpNumberCount));
	        	   
	        		   nlpResponseModel.setStatus(CommonConstants.pass);
	        		   nlpResponseModel.setMessage("otp fetched");
	        	   
	        		 
			} 
	           catch (Exception e) {
			
				nlpResponseModel.setStatus(CommonConstants.fail);
	 		   nlpResponseModel.setMessage("failed to fetch otp" +e);
			}
	         // String string3 = "Return Value";
	          nlpResponseModel.getAttributes().put("otp", otp);
	          return nlpResponseModel;
	      }
	  } 


