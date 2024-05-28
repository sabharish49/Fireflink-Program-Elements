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

import org.springframework.stereotype.Component;

@Component("LIC14172_PJT1001_PE_NLPf3b6cf05-a54e-44be-ab9f-b5c4ebeaaffc")
public class MOB_Fetch_Funds  implements Nlp{



	  @InputParams({@InputParam(name = "Funds", type = "java.lang.String")
	  ,@InputParam(name = "startChar", type = "java.lang.Character")
	  ,@InputParam(name = "endChar", type = "java.lang.Character")})
    @ReturnType(name = "FundLimits", type = "java.lang.String")
		

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
          String Funds = (String) attributes.get("Funds");
          char startChar= (char)attributes.get("startChar");
          char endChar= (char)attributes.get("endChar");
       String limitsAmount=null;
  
      		try {

      			int match=Funds.indexOf(startChar);
      			int per=Funds.indexOf(endChar);	
      			limitsAmount= Funds.substring(match, per+3);	
        nlpResponseModel.setStatus(CommonConstants.pass);
		nlpResponseModel.setMessage("Successfully fetch the Funds Amount " +limitsAmount);
    			}
      		
      		catch (Exception e) 
      		{
      			nlpResponseModel.setStatus(CommonConstants.fail);
    			nlpResponseModel.setMessage("Failed to Fetch the Fund Amount "+e);
      		}
  
          

      nlpResponseModel.getAttributes().put("FundLimits",limitsAmount );
          return nlpResponseModel;
      }
  } 