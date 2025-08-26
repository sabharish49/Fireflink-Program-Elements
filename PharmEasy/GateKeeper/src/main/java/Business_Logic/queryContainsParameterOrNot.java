
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

@Component("LIC18416_PJT1001_PE_NLPb584d9bf-0ef6-4758-aa7f-190b0505ec82")
public class queryContainsParameterOrNot implements Nlp {
    @InputParams({@InputParam(name = "Query", type = "java.lang.String")})
    @ReturnType(name = "reult", type = "java.lang.Boolean")

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
          String Query = (String) attributes.get("Query");
          boolean res=false;
          try {
        	  
        	  if(Query.contains(":")) {
        		 res=true; 
        		 nlpResponseModel.setMessage("Query contains Parameter");
        	  }
        	  else {
        		  res=false;
        		  nlpResponseModel.setMessage("Query does not contain Parameter");
        	  }
        	  
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  
          }
          catch (Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  nlpResponseModel.setMessage("Query does not contains Parameter");
		}
          
          

          
          nlpResponseModel.getAttributes().put("result", res);
          return nlpResponseModel;
      }
  } 