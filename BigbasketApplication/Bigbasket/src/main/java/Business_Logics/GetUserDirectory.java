
package Business_Logics;

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

public class GetUserDirectory implements Nlp {
    @ReturnType(name = "string3", type = "java.lang.String")

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
          String dir="";
          
try {
	dir=System.getProperty("user.dir");
 	  nlpResponseModel.setMessage("The User Directory is-> "+dir);
			nlpResponseModel.setStatus(CommonConstants.pass);
	
}
catch(Exception e) {
 	  nlpResponseModel.setMessage("Failed to get Directory");
			nlpResponseModel.setStatus(CommonConstants.fail);
}    
nlpResponseModel.getAttributes().put("string3", System.getProperty("user.dir"));
          return nlpResponseModel;
      }
  } 