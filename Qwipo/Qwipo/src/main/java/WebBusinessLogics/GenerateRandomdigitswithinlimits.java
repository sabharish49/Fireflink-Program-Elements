package WebBusinessLogics;


import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;
import java.util.*;

public class GenerateRandomdigitswithinlimits implements Nlp {
    @InputParams({@InputParam(name = "lowerBound", type = "java.lang.Integer"), @InputParam(name = "upperBound", type = "java.lang.Integer")})
    @ReturnType(name = "Generatedvalue", type = "java.lang.Integer")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          Integer number1 = (Integer) programElementsInput.get("lowerBound");
          Integer number2 = (Integer) programElementsInput.get("upperBound");
          int value=0; 

          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          try {
        	  Random random = new Random();
        	  int randomNumber = random.nextInt(number2 - number1) + number1;
               value=randomNumber;
              nlpResponseModel.setStatus(CommonConstants.pass);
              nlpResponseModel.setMessage("Generated Random Digits sucessfuly"+value);
          }
              
             
          catch(Exception e) {
          	
             nlpResponseModel.setStatus(CommonConstants.fail+e);
             nlpResponseModel.setMessage("Failed To Generate Digits");
          }

          // Your program element business logic ends here ...
          nlpResponseModel.getAttributes().put("Generatedvalue", value);
          return nlpResponseModel;
      }
}
  