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
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;


@Component("LIC14365_PJT1001_PE_NLPb613b236-513e-4b64-85ae-45c7c553c68e")
public class GetNumbersFromInputString  implements Nlp {
    @InputParams({@InputParam(name = "string1", type = "java.lang.String")})
    @ReturnType(name = "result", type = "java.lang.String")

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
          String string1 = (String) attributes.get("string1");
          //String string2 = (String) attributes.get("string2");

          // Your program element business logic goes here ...
          String res="";
          try {
          	for (int i = 0; i <string1.length(); i++) 
      		{
      			if(string1.charAt(i)>='0' && string1.charAt(i)<='9')
      			{
      				res=res+string1.charAt(i);
      				
      			}
      		}
          	nlpResponseModel.setStatus(CommonConstants.pass);
          	nlpResponseModel.setMessage("successfully fetched the numbers");
          	
				
			} 
          catch (Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
            	nlpResponseModel.setMessage("failed to fetch the numbers");
				
			}

          String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("result", res);
          return nlpResponseModel;
      }
  } 


