
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
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;




import org.springframework.stereotype.Component;

@Component("LIC20665_PJT1001_PE_NLPe66a84f4-b25f-43bf-91dd-721a974f09d9")
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
	dir=System.getProperty("user.home");
	
        	  nlpResponseModel.setMessage("directory: " + dir);
        	
        
        		nlpResponseModel.setStatus(CommonConstants.pass);
 	
	
}
catch(Exception e) {
	  StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();
		//log.info(exceptionAsString);
		nlpResponseModel.setStatus(CommonConstants.fail);
		
  	  nlpResponseModel.setMessage("Failed "+exceptionAsString);
}    
nlpResponseModel.getAttributes().put("string3", System.getProperty("user.home"));
          return nlpResponseModel;
      }
  } 