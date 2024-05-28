package Business_logics;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

@Component("LIC14952_PJT1005_PE_NLPeab9239e-d983-4896-911a-052e33f5d0a9")
public class Add_Minutes implements Nlp {


	@InputParams({@InputParam(name = "Minutes", type = "java.lang.String")})
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
          String Minutes = (String) attributes.get("Minutes"); 
         int Min = Integer.parseInt(Minutes);   
         String formattedNewTime="";
      	  try {
      		LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime newTime = currentTime.plusMinutes(Min);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedCurrentTime = currentTime.format(formatter);
            formattedNewTime = newTime.format(formatter);         
            nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Successfully added "+Min+" minutes to Current Time"  +formattedNewTime);
      	  }
   		catch(Exception e)
   		{
   		    nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Not a valid Number " +e);
   		}
      	nlpResponseModel.getAttributes().put("value", formattedNewTime);
		return nlpResponseModel;
      }

 }


