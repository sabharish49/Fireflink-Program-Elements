
package bussiness_logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;



@Component("LIC20128_PJT1001_PE_NLP6e65b9b5-ec0f-4b39-9df6-08a8c2fc41cb")
public class GenerateRandomNumberWithinSpecifiedRange implements Nlp {
    @InputParams({@InputParam(name = "range", type = "java.lang.Integer")})
    @ReturnType(name = "value", type = "java.lang.Integer")

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
          Integer range = (Integer) attributes.get("range");
          Integer value=0;
          // Your program element business logic goes here ...
          try {
        	Random random = new Random();
  	        value= random.nextInt(range);
  	        nlpResponseModel.setMessage("Number generated successfully");
			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			nlpResponseModel.setMessage("Failed to generate number"+e.getMessage());
  			nlpResponseModel.setStatus(CommonConstants.fail);
		}
          
          nlpResponseModel.getAttributes().put("value", value);
          return nlpResponseModel;
      }
  } 