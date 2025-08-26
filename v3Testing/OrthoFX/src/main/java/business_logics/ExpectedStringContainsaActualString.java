
package business_logics;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;
import java.util.*;




public class ExpectedStringContainsaActualString implements Nlp {
    @InputParams({@InputParam(name = "Actual String", type = "java.lang.String"), @InputParam(name = "Expected String", type = "java.lang.String")})
    @ReturnType(name = "Assigning Step to Return", type = "java.lang.Boolean")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          String string1 = (String) programElementsInput.get("Actual String");
          String string2 = (String) programElementsInput.get("Expected String");
          boolean returnValue=false;

          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          try {
        	  returnValue=string1.contains(string2);
        	  nlpResponseModel.setStatus(CommonConstants.pass);
              nlpResponseModel.setMessage(string1 +" Contains "+string2);
          }
          catch(Exception e) {
        	 returnValue=false;
        	  nlpResponseModel.setStatus(CommonConstants.pass);
              nlpResponseModel.setMessage(string1 +" doesnt Contain "+string2);
          }  

          // Your program element business logic ends here ...
          nlpResponseModel.getAttributes().put("Assigning Step to Return", returnValue);
          return nlpResponseModel;
      }

  } 