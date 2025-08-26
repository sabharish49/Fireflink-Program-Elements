
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


	@Component("LIC14365_PJT1001_PE_NLP01fdc022-af9f-493d-96a6-921116e09ebb")
public class MultipleOfTwoNumbers implements Nlp {
	    @InputParams({@InputParam(name = "num1", type = "java.lang.Integer"), @InputParam(name = "num2", type = "java.lang.Integer")})
	    @ReturnType(name = "result", type = "java.lang.Integer")

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
	         int num1 = (int) attributes.get("num1");
	         int num2 = (int) attributes.get("num2");

	          // Your program element business logic goes here ...
	          int res=0;
	          try {
	        	  res=num1*num2;
	        	  nlpResponseModel.setStatus(CommonConstants.pass);
	        	  nlpResponseModel.setMessage("multiplication of two numbers is successfull");
				
			} catch (Exception e) {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("invalid inputs");
			}

	          Integer result = 0;
	          nlpResponseModel.getAttributes().put("result", res);
	          return nlpResponseModel;
	      }
	}
	
	



