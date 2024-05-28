package Business_LogicForAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import org.springframework.stereotype.Component;




@Component("LIC1710_PJT1014_PE_NLP898acf23-e4f1-44e3-ab8f-fcf51b8e7ed3")
public class VerifyExpectedValueMatchesWithActualValue implements Nlp {
    @InputParams({@InputParam(name = "value1", type = "java.lang.String"), 
    @InputParam(name = "value2", type = "java.lang.String")}) 
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
          String value1 = (String) attributes.get("value1");
          String value2 = (String) attributes.get("value2");
        //  String String3 = (String) attributes.get("String3");
         
          try {
        	  
          	  if(value1.equals(value2)) {
          		  
            		nlpResponseModel.setMessage(value1 +" matches "+ value2);
            		nlpResponseModel.setStatus(CommonConstants.pass);
            	}
            	else {
            		nlpResponseModel.setMessage(value1 +"does not matches "+ value2);
            		nlpResponseModel.setStatus(CommonConstants.fail);
            	}
    		} catch (Exception e) {
    			nlpResponseModel.setMessage("Failed to compare");
        		nlpResponseModel.setStatus(CommonConstants.fail);
    		}
          return nlpResponseModel;
      }
  } 
