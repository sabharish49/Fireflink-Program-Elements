
package Business_Logic;

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

@Component("LIC12620_PJT1003_PE_NLPe5e41fea-dabe-4e87-9b27-7ac85c562ed0")
public class testingFor implements Nlp {
  //  @InputParams({@InputParam(name = "string1", type = "java.lang.String"), @InputParam(name = "string2", type = "java.lang.String")})
    @ReturnType(name = "sum", type = "java.lang.Integer")

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
       //   String string1 = (String) attributes.get("string1");
       //   String string2 = (String) attributes.get("string2");

          // Your program element business logic goes here ...
          int sum=0;
          try {
        	  
        	 for(int i=0;i<5;i++) {
        		 sum=sum+i;
        	 }
        	 nlpResponseModel.setStatus(CommonConstants.pass);
        	 nlpResponseModel.setMessage("passed"+sum);
          }
          catch (Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
         	 nlpResponseModel.setMessage("fail"+sum);
		}

          String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("sum", sum);
          return nlpResponseModel;
      }
  } 