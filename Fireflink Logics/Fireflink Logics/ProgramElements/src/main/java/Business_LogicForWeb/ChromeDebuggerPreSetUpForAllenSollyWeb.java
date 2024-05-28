package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;


@Component("LIC1710_PJT1014_PE_NLP107b2c41-acc2-434c-9ef7-9c3301a55bd9")
public class ChromeDebuggerPreSetUpForAllenSollyWeb implements Nlp {
						@InputParams({	@InputParam(name = "Port", type = "java.lang.Integer")})

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
        	Integer	port				= (Integer) attributes.get("Port");
      		String sourceFilePath		= "C:\\DataOfDebugger1";
    		String destinationFilePath	= "C:\\DataOfDebugger2";
    		String chromePath			= "C:\\Program Files\\Google\\Chrome\\Application";
    		String cmdCommand1A			= "cmd /c start cmd.exe /K ";
    		String cmdCommand1B			= "npx kill-port ";
    		String cmdCommand1			= cmdCommand1A+cmdCommand1B+port;
    		String cmdCommand2A			= "cmd /c start cmd.exe /K ";
    		String cmdCommand2B			= "chrome.exe -remote-debugging-port=";
    		String cmdCommand2C			= " --no-first-run --no-default-browser-check --user-data-dir=";
    		String cmdCommand2			= cmdCommand2A+cmdCommand2B+port+cmdCommand2C+destinationFilePath;

    		Runtime.getRuntime().exec(cmdCommand1);
			Thread.sleep(2500);
  			Runtime.getRuntime().exec("cmd /c start cmd.exe /K for /d %i in (*) do @rd /s /q \"%i\"", null, new File(destinationFilePath));
  			Thread.sleep(4000);
			Runtime.getRuntime().exec("cmd /c start cmd.exe /K xcopy /h /i /c /k /e /r /y "+sourceFilePath+" "+destinationFilePath);
			Thread.sleep(4000);
			Runtime.getRuntime().exec(cmdCommand2, null, new File(chromePath));
			Thread.sleep(2500);
      		nlpResponseModel.setStatus(CommonConstants.pass);
          }
          catch (IOException | InterruptedException e) 
          {
        	 nlpResponseModel.setStatus(CommonConstants.fail);
          }
          return nlpResponseModel;
      }
	}
