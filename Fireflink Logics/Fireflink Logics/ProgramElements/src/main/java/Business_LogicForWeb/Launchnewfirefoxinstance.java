package Business_LogicForWeb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("LIC1710_PJT1014_PE_NLP896f9041-78f7-4d67-875f-caba011067fa")
public class Launchnewfirefoxinstance implements Nlp {
	@InputParams({ @InputParam(name = "Directory", type = "java.lang.String"),
			@InputParam(name = "Port", type = "java.lang.Integer") })

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
          
          try {
        	  String dir = (String) attributes.get("Directory");
              Integer port = (Integer) attributes.get("Port");
        	  String cmdCommand="npx kill-port "+port+"";
        	  Runtime.getRuntime().exec("cmd /c start cmd.exe /K " + cmdCommand);
        	  Thread.sleep(8000);
//        	  log.info("Entered Try Block");
        	 
        	  String cmdCommand1 = "firefox.exe -remote-debugging-port="+ port +" --no-first-run --no-default-browser-check --user-data-dir="+dir;
        	  String firefoxpath = "C:\\Program Files\\Mozilla Firefox";
//        	  log.info("Started Executing Command");
        	  Thread.sleep(5000);
        	  Runtime.getRuntime().exec("cmd /c start cmd.exe /K " + cmdCommand1, null, new File(firefoxpath));
//        	  log.info("Command Executed");
        	  Thread.sleep(2000);
        		
      		nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Browser got launched successfully");
          }
          catch(Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
    			nlpResponseModel.setMessage("Failed to launch the browser because " + e);
          }

          return nlpResponseModel;
      }
}
