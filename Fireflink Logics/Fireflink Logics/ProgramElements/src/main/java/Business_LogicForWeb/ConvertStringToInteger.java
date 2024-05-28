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

@Component("LIC1710_PJT1014_PE_NLP25fbfc0e-e96a-44c8-ae7e-011366f7eb59")
public class ConvertStringToInteger implements Nlp {
	 @InputParams({	@InputParam(name = "String Value", type = "java.lang.String")})
     @ReturnType(name = "Integer Value", type = "java.lang.Integer")

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
          Integer integerValue=0;
          try
          {
        	  	String stringValue = (String) attributes.get("String Value");
        		integerValue = Integer.parseInt(stringValue);
        		nlpResponseModel.setStatus(CommonConstants.pass);
        		nlpResponseModel.setMessage("The Converted Integer Value is: "+integerValue);
          }
         catch (Exception e) 
          {
        	 	nlpResponseModel.setStatus(CommonConstants.fail);
        	 	nlpResponseModel.setMessage("Custom NLP not Executed");
		}
          nlpResponseModel.getAttributes().put("Integer Value", integerValue);
          return nlpResponseModel;
      }
  } 