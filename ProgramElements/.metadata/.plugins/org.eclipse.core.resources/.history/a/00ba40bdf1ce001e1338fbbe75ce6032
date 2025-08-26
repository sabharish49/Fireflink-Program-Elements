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


@Component("LIC14952_PJT1001_PE_NLP8397021f-e421-482f-b131-fbac096c8440")
public class CompareTwoStrings implements Nlp {
	
	    @InputParams({@InputParam(name = "string1", type = "java.lang.String"), @InputParam(name = "string2", type = "java.lang.String")})
	    @ReturnType(name = "result", type = "java.lang.Boolean")

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
	          String string2 = (String) attributes.get("string2");

	          // Your program element business logic goes here ...
	           Boolean result=true;
	           
	           try {
	        	   if(string1.equals(string2)) 
	        	   {
	        		   result=true;
	        		   nlpResponseModel.setStatus(CommonConstants.pass);
	        		   nlpResponseModel.setMessage("two strings are equals");
	        	   }
	        		   else
	        		   {
	        			   result=false;
	        			   nlpResponseModel.setStatus(CommonConstants.fail);
	            		   nlpResponseModel.setMessage("two strings are not equals"); 
	        		   }
			} 
	           catch (Exception e) {
				result=false;
				nlpResponseModel.setStatus(CommonConstants.fail);
	 		   nlpResponseModel.setMessage("two strings are not equals" +e);
			}
	         // String string3 = "Return Value";
	          nlpResponseModel.getAttributes().put("result", result);
	          return nlpResponseModel;
	      }
	  } 





