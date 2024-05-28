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

@Component("LIC1710_PJT1014_PE_NLP0fd4e924-9491-4b53-9a50-4f0fcb152845")
public class GetSubStringTextByStringText implements Nlp {
	@InputParams({@InputParam(name = "Set String Text", type = "java.lang.String"),
				  @InputParam(name = "String From", type = "java.lang.String"),
				  @InputParam(name = "String To", type = "java.lang.String")})
    @ReturnType(name = "Get Sub String value", type = "java.lang.String")

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
          String str1 = (String) attributes.get("Set String Text");
          String fromStrValue 	= (String) attributes.get("String From");
          String toStrValue	= (String) attributes.get("String To");
          String str2 ="";
	      try 
	      	{
	    	  	int i1 = str1.indexOf(fromStrValue)+1;
	    		int i2 = str1.indexOf(toStrValue);
	    		str2 = str1.substring(i1, i2);
	    	  	nlpResponseModel.setStatus(CommonConstants.pass);
	  			nlpResponseModel.setMessage(" Sub String Text is :   "+str2);
			}
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Something Went Wrong With NLP ");
		}
      nlpResponseModel.getAttributes().put("Get Sub String value", str2);
      return nlpResponseModel;
      }
  } 