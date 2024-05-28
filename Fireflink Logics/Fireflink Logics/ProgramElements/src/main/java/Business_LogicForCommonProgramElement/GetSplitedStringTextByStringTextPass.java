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

@Component("LIC1710_PJT1014_PE_NLP67d9e878-c0f8-40f5-bd23-6d27a40b0730")
public class GetSplitedStringTextByStringTextPass implements Nlp {
	@InputParams({@InputParam(name = "Set String Text", type = "java.lang.String"),
				  @InputParam(name = "Set String Split Value", type = "java.lang.String"),
				  @InputParam(name = "Set Integer Split Count", type = "java.lang.Integer"),
				  @InputParam(name = "Set Index Of Get Position", type = "java.lang.Integer")})
    @ReturnType(name = "Get Splited String Value", type = "java.lang.String")

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

          String str3 ="";	
	      try 
	      	{
	          Map<String, Object> attributes = nlpRequestModel.getAttributes();
	          String str1			= (String) attributes.get("Set String Text");
	          String str2 			= (String) attributes.get("Set String Split Value");
	          Integer splitCount 	= (Integer) attributes.get("Set Integer Split Count");
	          Integer index 		= (Integer) attributes.get("Set Index Of Get Position");
		  		String[] arr = str1.split(str2, splitCount);
				str3 = arr[index].toString();
	    	  	nlpResponseModel.setStatus(CommonConstants.pass);
	  			nlpResponseModel.setMessage(" Splited String Text is :   "+str3);
			}
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Something Went Wrong With NLP ");
		}
      nlpResponseModel.getAttributes().put("Get Splited String Value", str3);
      return nlpResponseModel;
      }
  } 