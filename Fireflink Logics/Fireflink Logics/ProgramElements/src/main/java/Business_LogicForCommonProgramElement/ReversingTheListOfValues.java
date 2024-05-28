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



@Component("LIC1710_PJT1014_PE_NLPe18d290b-ccf3-4d1c-aaea-3167a85e3d8b")
public class ReversingTheListOfValues implements Nlp {
	@InputParams({	@InputParam(name = "Set List Of String Values", type = "java.util.ArrayList")})
    @ReturnType(name = "Return List Of Reversing String Values", type = "java.util.ArrayList")

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
          ArrayList<String> listOfReversingStringValues = new ArrayList<String>();
	      try 
	      {
	    	ArrayList<String>	listOfStringValues = (ArrayList<String>) attributes.get("Set List Of String Values");
			for(int i = listOfStringValues.size()-1; i >= 0; i--)
			{
				listOfReversingStringValues.add(listOfStringValues.get(i));
			}
	  	    nlpResponseModel.setStatus(CommonConstants.pass);
	  	    nlpResponseModel.setMessage(" List Of Reversing String Values  "+listOfReversingStringValues);
		  }
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Program Element Not Executed ");
		}
      nlpResponseModel.getAttributes().put("Return List Of Reversing String Values", listOfReversingStringValues);
      return nlpResponseModel;
      }
  } 