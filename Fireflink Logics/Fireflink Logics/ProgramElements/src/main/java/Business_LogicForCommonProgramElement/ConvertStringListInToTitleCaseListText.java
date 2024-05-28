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
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Component;



@SuppressWarnings("deprecation")


@Component("LIC1710_PJT1014_PE_NLP687b80ed-d061-4a51-b730-6c9afb58437a")
public class ConvertStringListInToTitleCaseListText implements Nlp {
	@InputParams({@InputParam(name = "Set List Of String Value", type = "java.util.ArrayList")})
    @ReturnType(name = "Return List Of String Value", type = "java.util.ArrayList")

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
	      ArrayList<String> returnListofStringValues = new ArrayList<String>();
	      try 
	      {
	    	 ArrayList<String>  setListOfStringValues = (ArrayList<String>) attributes.get("Set List Of String Value");
	    	 Thread.sleep(2000);
	    	 for(int i = 0; i<= setListOfStringValues.size()-1; i++)
		     {
	    		 returnListofStringValues.add(i, WordUtils.capitalizeFully(setListOfStringValues.get(i)));
	    		 Thread.sleep(1500);
		     }
	  	    nlpResponseModel.setStatus(CommonConstants.pass);
	  	    nlpResponseModel.setMessage(" ArrayList of String Values are Fetched  "+returnListofStringValues);
		  }
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Program Element Not Executed ");
		}
      nlpResponseModel.getAttributes().put("Return List Of String Value", returnListofStringValues);
      return nlpResponseModel;
      }
  } 