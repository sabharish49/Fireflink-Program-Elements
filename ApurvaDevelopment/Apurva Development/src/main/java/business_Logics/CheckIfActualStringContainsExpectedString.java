
package business_Logics;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;
import java.util.*;

public class CheckIfActualStringContainsExpectedString implements Nlp {
    @InputParams({@InputParam(name = "Actual String", type = "java.lang.String"), @InputParam(name = "Expected String", type = "java.lang.String")})
    @ReturnType(name = "Is Contains", type = "java.lang.Boolean")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          String actualString = (String) programElementsInput.get("Actual String");
          String expectedString = (String) programElementsInput.get("Expected String");
          Integer returnValue=null;
          boolean isContains = false;

          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          try {
            
              if(actualString.contains(expectedString)) {
            	  isContains=true;
            	  nlpResponseModel.setStatus(CommonConstants.pass);
                  nlpResponseModel.setMessage("actual string contains expceted string");
              }
              else {
            	  isContains=false;
            	  nlpResponseModel.setStatus(CommonConstants.pass);
                  nlpResponseModel.setMessage("actual string doesnot contains expceted string");
              }

             
          
          }
          catch(Exception e) {
          	
             nlpResponseModel.setStatus(CommonConstants.pass);
             nlpResponseModel.setMessage("actual string doesnot contains expceted string");
  
          }

        
          nlpResponseModel.getAttributes().put("Is Contains", isContains);
          return nlpResponseModel;
      }
    public static void main(String[] args) throws NlpException {
		NlpRequestModel nlpreq = new NlpRequestModel();
		nlpreq.getAttributes().put("Actual String", "fireflinkactivhealth@gmail.com(Dead)");
		nlpreq.getAttributes().put("Expected String", "(");
		System.out.println(new CheckIfActualStringContainsExpectedString().execute(nlpreq));
		
	}

  } 
