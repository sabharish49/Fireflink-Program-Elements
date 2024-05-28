package Business_logic;

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
import com.tyss.optimize.nlp.util.annotation.ReturnType;


public class Verify_Num_Positive implements Nlp {
    @InputParams({@InputParam(name = "Value", type = "java.lang.String")})
    @ReturnType(name = "value", type = "java.lang.String")
   

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
         // WebElement Element = (WebElement) attributes.get("Value");
          String Value = (String) attributes.get("Value"); 
          String result="";
          int testValue=Integer.parseInt(Value);
      	  try {
              result = verifyNumber(testValue);
              nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage("Given Value is positive" +result);
      	  }
   		catch(Exception e)
   		{
   		    nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Not a valid Number " +e);
   		}
      	nlpResponseModel.getAttributes().put("value", result);
          return nlpResponseModel;
      }
      
  public static String verifyNumber(int number) {
     if (number > 0) {
         return "BUY";
     } else if (number < 0) {
         return "SELL";
     } else {
         return "Zero";
     }
 }
 }

