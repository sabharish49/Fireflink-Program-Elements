
package default_package;

import java.util.Map;

import org.json.JSONObject;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

public class AddTwoNumbers implements Nlp {
    @InputParams({@InputParam(name = "json object", type = "java.lang.String")})
    @ReturnType(name = "Number of key-value pairs", type = "java.lang.Integer")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          String json = (String) programElementsInput.get("json object");
          
          Integer count=null;

          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          try {
              
        	  String jsonString = json;
              JSONObject jsonObject = new JSONObject(jsonString);

              count = jsonObject.length();
              nlpResponseModel.setStatus(CommonConstants.pass);
             
              nlpResponseModel.setMessage("Number of key-value pairs: "+count);
          }
          catch(Exception e) {
          	 
             nlpResponseModel.setStatus(CommonConstants.fail);

            
             nlpResponseModel.setMessage("Failed to fetch Number of key-value pairs");
          }

         
          nlpResponseModel.getAttributes().put("Number of key-value pairs", count);
          return nlpResponseModel;
      }

  } 