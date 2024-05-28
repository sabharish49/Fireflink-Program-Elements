package Business_LogicForWeb;

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




@Component("LIC1710_PJT1014_PE_NLP0f94473d-e91b-4dea-a5b3-37156f5c258d")
public class OrderVerificationForListOfValues implements Nlp {
	@InputParams({@InputParam(name = "Set the List of Integer Values", type = "java.util.ArrayList"),
				  @InputParam(name = "Select Your Option(asc/desc)", type = "java.lang.String")})
    @ReturnType(name = "condition", type = "java.lang.Boolean")

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
          Boolean condition = true;
          @SuppressWarnings("unchecked")
		  ArrayList<Integer>  listofIntegerValues = (ArrayList<Integer>) attributes.get("Set the List of Integer Values");
          try 
	      {
            Thread.sleep(2000);
	    	String choice 		= (String) attributes.get("Select Your Option(asc/desc)");
			if(choice.equals("asc"))
			{
				for(int i = 0; i< listofIntegerValues.size()-1; i++)
				{
					Thread.sleep(1000);
					if(	listofIntegerValues.get(i) > listofIntegerValues.get(i+1)) 
					{
						condition = false;
						break;
					}
				}
				if (condition)
				{
		    	    nlpResponseModel.setStatus(CommonConstants.pass);
			  	    nlpResponseModel.setMessage(" List of Integer Values are in Ascending Order  "+condition);
				}
				else
				{
					 nlpResponseModel.setStatus(CommonConstants.fail);
					 nlpResponseModel.setMessage(" List of Integer Values are Not in Ascending Order  "+condition);
				}
			}
			else if(choice.equals("desc"))
			{
				for(int i = 0; i< listofIntegerValues.size()-1; i++)
				{	
					Thread.sleep(1000);
					if(	listofIntegerValues.get(i) < listofIntegerValues.get(i+1)) 
					{
						condition = false;
						break;
					}
				}
				if (condition)
				{
		    	    nlpResponseModel.setStatus(CommonConstants.pass);
			  	    nlpResponseModel.setMessage(" List of Integer Values are in Descending Order  "+condition);
				}
				else
				{
					 nlpResponseModel.setStatus(CommonConstants.fail);
					 nlpResponseModel.setMessage(" List of Integer Values are Not in Descending Order  "+condition);
				}
			}
		  }
      catch (Exception e)
      	{
			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Something Went Wrong ");
		}
      nlpResponseModel.getAttributes().put("condition", condition);
      return nlpResponseModel;
      }
  } 