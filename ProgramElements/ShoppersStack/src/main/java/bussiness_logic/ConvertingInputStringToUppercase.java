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


@Component("LIC14952_PJT1001_PE_NLPc5a0bd12-772b-421f-af63-96f2203d4dae")
public class ConvertingInputStringToUppercase implements Nlp {
	
	    @InputParams({@InputParam(name = "string1", type = "java.lang.String")})
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
	          String string1 = (String) attributes.get("string1");
	         
	          // Your program element business logic goes here ...
	          String res="";
	          try {
	        	     res=string1.toUpperCase();
	        	     nlpResponseModel.setStatus(CommonConstants.pass);
	        	     nlpResponseModel.setMessage("successfull converted to uppercase");  
				
			} catch (Exception e) {
				nlpResponseModel.setStatus(CommonConstants.pass);
	   	        nlpResponseModel.setMessage("failed to convert");
				
			}

	        // String string3 = "Return Value";
	          nlpResponseModel.getAttributes().put("string3", res);
	          return nlpResponseModel;
	      }
	  } 



