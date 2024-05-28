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

@Component("LIC1710_PJT1014_PE_NLP564e7a79-3850-4bce-981c-78d215330445")
public class ActualStringMatchingWithExpectedStringOrNot implements Nlp {
    @InputParams({@InputParam(name = "stringText_1", type = "java.lang.String"), 
    			  @InputParam(name = "stringText_2", type = "java.lang.String")})
    @ReturnType(name = "getBoolean", type = "java.lang.Boolean")

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
          String stringText_1 = (String) attributes.get("stringText_1");
          String stringText_2 = (String) attributes.get("stringText_2");
          Boolean getBoolean =false;
          try
          {
        	String stringText_1A = stringText_1.toLowerCase();
        	String stringText_2A = stringText_2.toLowerCase();
        	if(stringText_1A.equals(stringText_2A))
        	{
        		getBoolean =true;
	          	nlpResponseModel.setStatus(CommonConstants.pass);
	          	nlpResponseModel.setMessage("String 1 Matches String 2");
        	}
        	else 
        	{
	          	nlpResponseModel.setStatus(CommonConstants.pass);
	          	nlpResponseModel.setMessage("String 1 Not Matches String 2");
        	}
          } 
          catch (Exception e) 
          {
        	nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Something went wrong");
          }

          nlpResponseModel.getAttributes().put("getBoolean", getBoolean);
          return nlpResponseModel;
      }
  } 