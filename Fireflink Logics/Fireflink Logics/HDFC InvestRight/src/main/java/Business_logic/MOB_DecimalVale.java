package Business_logic;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC14172_PJT1001_PE_NLPa4c5f538-5549-4e43-9caa-ef58291c9cb9")
public class MOB_DecimalVale  implements Nlp{
	
	@InputParams({@InputParam(name = "Value", type = "java.lang.String")})
   
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
          String Value =(String)attributes.get("Value");
         int index;
 
         String decimalValue=null;
      		try {
      			index=Value.indexOf(".");
      			decimalValue =Value.substring(index);
      			

        nlpResponseModel.setStatus(CommonConstants.pass);
		nlpResponseModel.setMessage("Successfully Fetched the decimal value" +decimalValue);
    			}
      		
      		catch (Exception e) 
      		{
      			nlpResponseModel.setStatus(CommonConstants.fail);
    			nlpResponseModel.setMessage("Failed to Fetch the decimal Value" +" "+e);
      		}
 
          return nlpResponseModel;
      }
  } 