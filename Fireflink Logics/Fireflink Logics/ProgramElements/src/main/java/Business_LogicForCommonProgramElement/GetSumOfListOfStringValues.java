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



@Component("LIC1710_PJT1014_PE_NLP2b3a68d4-ea28-465c-8d2b-6c7891414a48")
public class GetSumOfListOfStringValues implements Nlp {
	@InputParams({	@InputParam(name = "Set List Of String Values", type = "java.util.ArrayList"),
		  			@InputParam(name = "Set Divisor", type = "java.lang.Integer")})
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
          Integer	sum1	=	0;
          Double	sum2	=	0.0;
	      try 
	      {
	    	ArrayList<String>	listOfStringValues = (ArrayList<String>) attributes.get("Set List Of String Values");
	    	Integer divisor 	= (Integer) attributes.get("Set Divisor");
	    	ArrayList<Double>	listofDoubleValues = new ArrayList<Double>();
			for(int i = 0; i<= listOfStringValues.size()-1; i++)
			{
				listofDoubleValues.add(i, Double.parseDouble(listOfStringValues.get(i).replaceAll("[^0-9]", ""))/divisor);
				sum2 = sum2 + listofDoubleValues.get(i);
			}
			sum1	=	(int)	Math.round(sum2);
	  	    nlpResponseModel.setStatus(CommonConstants.pass);
	  	    nlpResponseModel.setMessage(" Sum of Integer Value is  "+sum1);
		  }
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Program Element Not Executed ");
		}
      nlpResponseModel.getAttributes().put("Return Sum Of Integer Values", sum1);
      return nlpResponseModel;
      }
  } 