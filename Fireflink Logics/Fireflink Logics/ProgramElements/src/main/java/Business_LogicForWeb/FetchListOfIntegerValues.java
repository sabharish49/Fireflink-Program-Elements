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



@Component("LIC1710_PJT1014_PE_NLP92907e3e-d263-4869-9833-bbb7a840615f")
public class FetchListOfIntegerValues implements Nlp {
	@InputParams({@InputParam(name = "Set List Of String Value", type = "java.util.ArrayList")})
    @ReturnType(name = "Return List Of Integer Value", type = "java.util.ArrayList")

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
	      ArrayList<Integer> listofIntegerValues = new ArrayList<Integer>();
	      try 
	      {
	    	 ArrayList<String>  listOfStringValues = (ArrayList<String>) attributes.get("Set List Of String Value");
	    	 Thread.sleep(2000);
	    	 for(int i = 0; i<= listOfStringValues.size()-1; i++)
		     {
	    		 listofIntegerValues.add(i, Integer.parseInt(listOfStringValues.get(i).replaceAll("[^0-9]", "")));
	    		 Thread.sleep(1500);
		     }
	  	    nlpResponseModel.setStatus(CommonConstants.pass);
	  	    nlpResponseModel.setMessage(" ArrayList of Integer Values are Fetched  "+listofIntegerValues);
		  }
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Program Element Not Executed ");
		}
      nlpResponseModel.getAttributes().put("Return List Of Integer Value", listofIntegerValues);
      return nlpResponseModel;
      }
  } 