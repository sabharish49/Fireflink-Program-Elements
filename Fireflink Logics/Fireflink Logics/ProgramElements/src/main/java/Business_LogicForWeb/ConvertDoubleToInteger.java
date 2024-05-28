package Business_LogicForWeb;

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




@Component("LIC1710_PJT1014_PE_NLP4fa5c9a8-3d25-4560-bfd1-0d400f92fcab")
public class ConvertDoubleToInteger implements Nlp {
	 @InputParams({	@InputParam(name = "DoubleValue", type = "java.lang.Double")})
     @ReturnType(name = "integerValue", type = "java.lang.Integer")

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
          Double DoubleValue = (Double) attributes.get("DoubleValue");
          Integer integerValue=0;
          try
          {
	    		double		DoubleValue1 =	DoubleValue;
	    		integerValue = (int) Math.round(DoubleValue1);
	        	nlpResponseModel.setStatus(CommonConstants.pass);
	        	nlpResponseModel.setMessage("The Converted Integer Value is: "+integerValue);
          }
         catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Something Went Wrong");
		}
          nlpResponseModel.getAttributes().put("integerValue", integerValue);
          return nlpResponseModel;
      }
  } 