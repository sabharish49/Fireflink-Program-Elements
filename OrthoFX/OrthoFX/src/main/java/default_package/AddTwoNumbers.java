
package default_package;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;
import java.util.*;

import org.springframework.stereotype.Component;

@Component("LIC21068_PJT1002_PE_NLPa1e5abee-39fa-4a52-bf07-cbaf539e72bf")
public class AddTwoNumbers implements Nlp {
    @InputParams({@InputParam(name = "List", type = "java.util.List")})
    @ReturnType(name = "Result", type = "java.lang.String")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          LinkedList list = (LinkedList) programElementsInput.get("List");
          String sentence=null;

          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          try {
             
              sentence = String.join(" ", sentence);
              nlpResponseModel.setStatus(CommonConstants.pass);
              
          }
          catch(Exception e) {
          	
             nlpResponseModel.setStatus(CommonConstants.fail);

            
          }

          
          nlpResponseModel.getAttributes().put("Result", sentence);
          return nlpResponseModel;
      }

  } 