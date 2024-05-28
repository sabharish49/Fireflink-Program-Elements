package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;



@Component("LIC1710_PJT1014_PE_NLP472cfc57-e320-4f7f-93d7-8f1d50a18d1e")
public class DeleteDataOfDebuggerFolderDataByCMD implements Nlp {

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
          try
          {
  			try{Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /T");}catch (IOException e) {}
  			try {Thread.sleep(2000);}catch(InterruptedException e){e.printStackTrace();}
  			try{Runtime.getRuntime().exec("taskkill /F /IM chromedriver /T");}catch (IOException e) {}
  			try {Thread.sleep(2000);}catch(InterruptedException e){e.printStackTrace();}
  			Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
  	        Runtime.getRuntime().exec("taskkill /F /IM WindowsTerminal.exe");
  			try {Thread.sleep(3000);} catch (InterruptedException e) {}
  			Runtime.getRuntime().exec("cmd /c start cmd.exe /K for /d %i in (*) do @rd /s /q \"%i\"", null, new File("C:\\DataOfDebugger"));
  			try {Thread.sleep(5000);} catch (InterruptedException e) {}
  	        Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
  	        Runtime.getRuntime().exec("taskkill /F /IM WindowsTerminal.exe");
        	nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("NLP Executed");
          } 
          catch (IOException e) {}
          {
        	nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("NLP Not Executed");
          }
          return nlpResponseModel;
      }

  } 