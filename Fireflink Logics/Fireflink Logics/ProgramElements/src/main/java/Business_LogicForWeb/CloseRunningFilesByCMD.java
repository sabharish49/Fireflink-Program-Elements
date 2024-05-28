package Business_LogicForWeb;

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
import java.io.IOException;
import org.springframework.stereotype.Component;



@Component("LIC1710_PJT1014_PE_NLP70af6608-22ab-4e90-b7a4-e7dd3e85519d")
public class CloseRunningFilesByCMD implements Nlp {
  @InputParams({@InputParam(name = "Set Choice", type = "java.lang.String")})

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
          try
          {
              String setChoice = (String) attributes.get("Set Choice");
        	  if(setChoice.equals("cmd"))
        	  {
        	        Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
          	        Runtime.getRuntime().exec("taskkill /F /IM WindowsTerminal.exe");
        	  }
        	  else if(setChoice.equals("c"))
        	  {
        		  Runtime.getRuntime().exec("cmd /c start cmd.exe /K taskkill /F /IM chrome.exe /T");
        	  }
        	  else if(setChoice.equals("cd"))
        	  {
        		  Runtime.getRuntime().exec("cmd /c start cmd.exe /K taskkill /F /IM chromedriver.exc /T");
        	  }
        	  try {Thread.sleep(2500);} catch (InterruptedException e) {}
        	nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Custom NLP Executed");
          } 
          catch (IOException e) {}
          {
        	nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Custom NLP Not Executed");
          }
          return nlpResponseModel;
      }

  } 