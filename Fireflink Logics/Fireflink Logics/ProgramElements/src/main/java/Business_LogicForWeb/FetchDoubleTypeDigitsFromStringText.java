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




@Component("LIC1710_PJT1014_PE_NLP1bd58435-fe4e-4ae0-a0f0-e9149a6e858d")
public class FetchDoubleTypeDigitsFromStringText implements Nlp {
	 @InputParams({	@InputParam(name = "StringText", type = "java.lang.String")})
     @ReturnType(name = "doubleValue", type = "java.lang.Double")

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
          String StringText = (String) attributes.get("StringText");
          Double doubleValue=0.0;
          try
          {
	    		String[]	StringText1 =	StringText.split("? ");
	    		String		StringText2 =	StringText1[1].toString();
	    					doubleValue	=	Double.parseDouble(StringText2.replaceAll("[^0-9]", ""));
	    					doubleValue = 	doubleValue/100;
	        	nlpResponseModel.setStatus(CommonConstants.pass);
	        	nlpResponseModel.setMessage("The Fetched Double Value is: "+doubleValue);
          }
         catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Something Went Wrong");
		}
          nlpResponseModel.getAttributes().put("doubleValue", doubleValue);
          return nlpResponseModel;
      }
  } 