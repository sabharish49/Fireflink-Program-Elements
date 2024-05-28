package Business_LogicForWeb;


import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;




@Component("LIC1710_PJT1014_PE_NLPf25a2e99-e2bf-414d-9b89-17cc3de09241")
public class AuthenticationPopUpHandlingByAutoIT implements Nlp {
	@InputParams({@InputParam(name = "Set File Path", type = "java.lang.String")})

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
     
	@SuppressWarnings("deprecation")
	@Override
      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
        
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          Map<String, Object> attributes = nlpRequestModel.getAttributes();
          try {
        	  String filePath = (String) attributes.get("Set String Text");
        	  Runtime.getRuntime().exec(filePath);
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  nlpResponseModel.setMessage("PopUp handled");
		} 
          catch (Exception e)
          {
        	  
      	  nlpResponseModel.setStatus(CommonConstants.fail);
      	  nlpResponseModel.setMessage("PopUp Not handled");	
		}
          return nlpResponseModel;
      }
  } 