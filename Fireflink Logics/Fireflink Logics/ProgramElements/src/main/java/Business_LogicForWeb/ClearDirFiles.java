package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
//import com.tyss.optimize.nlp.util.annotation.InputParam;
//import com.tyss.optimize.nlp.util.annotation.InputParams;
//import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLPafe86032-9fec-4d1d-8eb8-365c861e622d")
public class ClearDirFiles implements Nlp {
//  @InputParams({@InputParam(name = "Dir File Path", type = "java.lang.String")})

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
//          Map<String, Object> attributes = nlpRequestModel.getAttributes();
//          	String dirFilePath = (String) attributes.get("Dir File Path");
          try
          {

  	        Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
  	        Runtime.getRuntime().exec("taskkill /F /IM WindowsTerminal.exe");
  			try {Thread.sleep(7000);} catch (InterruptedException e) {}
  			Runtime.getRuntime().exec("cmd /c start cmd.exe /K del /q /s *", null, new File("C:\\DataOfDebugger\\Default\\Code Cache\\js"));
  			try {Thread.sleep(7000);} catch (InterruptedException e) {}
  	        Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
  	        Runtime.getRuntime().exec("taskkill /F /IM WindowsTerminal.exe");
  	        try {Thread.sleep(7000);} catch (InterruptedException e) {}
  			Runtime.getRuntime().exec("cmd /c start cmd.exe /K del /q /s *", null, new File("C:\\DataOfDebugger\\Default\\Cache\\Cache_Data"));
  			try {Thread.sleep(7000);} catch (InterruptedException e) {}
  			Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
  	        Runtime.getRuntime().exec("taskkill /F /IM WindowsTerminal.exe");
  	        try {Thread.sleep(7000);} catch (InterruptedException e) {}
  	        Runtime.getRuntime().exec("cmd /c start cmd.exe /K del /q /s *", null, new File("C:\\DataOfDebugger\\Default\\IndexedDB\\https_allensolly.abfrl.in_0.indexeddb.leveldb"));
  	        try {Thread.sleep(7000);} catch (InterruptedException e) {}
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