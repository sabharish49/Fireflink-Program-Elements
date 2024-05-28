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

@Component("LIC1710_PJT1014_PE_NLPec400ebe-3fc0-4f33-86af-82a50a4e90f1")
public class StringVariableReInitialization implements Nlp {
	@InputParams({@InputParam(name = "Set String Variable", type = "java.lang.String")})
    @ReturnType(name = "Get String Variable", type = "java.lang.String")

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
          String setStringVariable = (String) attributes.get("Set String Variable");
          String getStringVariable = "";
	      try 
	      	{
	    	  	getStringVariable	=	setStringVariable;
	    	  	nlpResponseModel.setStatus(CommonConstants.pass);
	  			nlpResponseModel.setMessage(" String Variable ReInitialization is :   "+getStringVariable);
			}
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Something Went Wrong With NLP ");
		}
      nlpResponseModel.getAttributes().put("Get String Variable", getStringVariable);
      return nlpResponseModel;
      }
  } 