package Business_LogicForCommonProgramElement;

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



@Component("LIC1710_PJT1014_PE_NLPb169fbe1-c4a1-41d8-a7af-a84ba4387276")
public class GetSumOfListOfTheIntegerValues implements Nlp {
	@InputParams({@InputParam(name = "Set List Of Integer Value", type = "java.util.ArrayList")})
    @ReturnType(name = "Return Sum Of Integer Values", type = "java.util.Integer")

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
	@SuppressWarnings("unchecked")
	@Override
      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
        
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          Map<String, Object> attributes = nlpRequestModel.getAttributes();
	      Integer sum = 0;
	      try 
	      {
	    	 ArrayList<Integer>  listOfIntegerValues = (ArrayList<Integer>) attributes.get("Set List Of Integer Value");
	    	 Thread.sleep(2000);
	    	 for(int i = 0; i<= listOfIntegerValues.size()-1; i++)
		     {
	    		 sum = sum + listOfIntegerValues.get(i);
		     }
	  	    nlpResponseModel.setStatus(CommonConstants.pass);
	  	    nlpResponseModel.setMessage(" Sum of Integer Value is  "+sum);
		  }
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Program Element Not Executed ");
		}
      nlpResponseModel.getAttributes().put("Return Sum Of Integer Values", sum);
      return nlpResponseModel;
      }
  } 