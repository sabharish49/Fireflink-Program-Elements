
package Business_Logics;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("LIC20369_PJT1002_PE_NLPc1654464-67b7-4050-bdaa-895a5e5d3b39")
public class BypassFingerPrintToEmulator implements Nlp {
    @InputParams({@InputParam(name = "fingerPrintID", type = "java.lang.String")})
    @ReturnType(name = "result", type = "java.lang.Boolean")

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
          String fingerPrintID = (String) attributes.get("fingerPrintID");
          
          boolean res=false;
          try {
        	  AndroidDriver driver = nlpRequestModel.getAndroidDriver();
        	  
        	  driver.fingerPrint(Integer.parseInt(fingerPrintID));
        	  
        	  res=true;
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  nlpResponseModel.setMessage("Successfully Bypassed Finger print for Authentication");
        	  
          }
          catch (Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
        	  nlpResponseModel.setMessage("Failed to Bypass Finger print for Authentication "+e);
		}
          
          nlpResponseModel.getAttributes().put("result", res);
          return nlpResponseModel;
      }
  } 