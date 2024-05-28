package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;



@Component("LIC1710_PJT1014_PE_NLPa85fe1d2-67ea-470f-8ac0-331cb635a2ec")

public class WEB_LaunchBrowserinstance implements Nlp {
    @InputParams({@InputParam(name = "Directory", type = "java.lang.String"), 
    	@InputParam(name = "Port", type = "java.lang.Integer")})

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
        	  String 	dir			= (String) attributes.get("Directory");
              Integer	port		= (Integer) attributes.get("Port");
        	  String 	cmdCommand	="npx kill-port "+port+"";
        	  Runtime.getRuntime().exec("cmd /c start cmd.exe /K " + cmdCommand);
        	  Thread.sleep(1500);
        	  String cmdCommand1 = "chrome.exe -remote-debugging-port="+ port +" --no-first-run --no-default-browser-check --user-data-dir="+dir;
        	  String chromePath = "C:\\Program Files\\Google\\Chrome\\Application";
        	  Thread.sleep(1500);
        	  Runtime.getRuntime().exec("cmd /c start cmd.exe /K " + cmdCommand1, null, new File(chromePath));
        	  Thread.sleep(1000);
        		
      		 nlpResponseModel.setStatus(CommonConstants.pass);
  			 nlpResponseModel.setMessage("Browser got launched successfully");
          }
          catch(Exception e) 
          {
        	 nlpResponseModel.setStatus(CommonConstants.fail);
    		 nlpResponseModel.setMessage("Failed to launch the browser because " + e);
          }

          return nlpResponseModel;
      }
	}
