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



@Component("LIC1710_PJT1014_PE_NLP0c16f885-d942-46a8-8c70-9372cc747d6d")
public class ToLowerCase implements Nlp {
	 @InputParams({	@InputParam(name = "StringText", type = "java.lang.String")})
     @ReturnType(name = "NewValue", type = "java.lang.String")

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
          String NewValue="";
          try {
        	  	NewValue = StringText.toLowerCase();
        	  	nlpResponseModel.setStatus(CommonConstants.pass);
        	  	nlpResponseModel.setMessage("The Formmated String Value is: "+NewValue);
          }
         catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Something Went Wrong");
		}
          nlpResponseModel.getAttributes().put("NewValue", NewValue);
          return nlpResponseModel;
      }
  } 