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

@Component("LIC1710_PJT1014_PE_NLPd6cedd9c-4bf2-45b8-a284-a1085432c752")
public class GetSubStringTextByStringValue implements Nlp {
	@InputParams({@InputParam(name = "Set String Text", type = "java.lang.String"),
				  @InputParam(name = "From Value", type = "java.lang.String"),
				  @InputParam(name = "To Value", type = "java.lang.String")})
    @ReturnType(name = "GetNewText", type = "java.lang.String")

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
          String SetStringText = (String) attributes.get("Set String Text");
          String fromValue 	= (String) attributes.get("From Value");
          String toValue	= (String) attributes.get("To Value");
          String GetNewText ="";
	      try 
	      	{
	    	  	GetNewText = SetStringText.substring(SetStringText.indexOf(fromValue)+1, SetStringText.indexOf(toValue));
	  			nlpResponseModel.setStatus(CommonConstants.pass);
	  			nlpResponseModel.setMessage(" Sub String Text is :   "+GetNewText);
			}
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Something Went Wrong With NLP ");
		}
      nlpResponseModel.getAttributes().put("GetNewText", GetNewText);
      return nlpResponseModel;
      }
  } 