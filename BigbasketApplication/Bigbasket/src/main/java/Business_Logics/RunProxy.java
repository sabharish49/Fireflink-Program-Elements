package Business_Logics;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.io.Files;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component("LIC20665_PJT1001_PE_NLP925e6b62-a11f-469c-ae44-33b0f1bd82f3")
public class RunProxy implements Nlp {
    @InputParams({@InputParam(name = "Port", type = "java.lang.String")
    })
  @ReturnType(name = "logFilePath", type = "java.lang.String")

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
          String port = (String) attributes.get("Port");
          String logPath="";
          String directory="";
          try {
        	  String findPidCommand = "netstat -ano | findstr "+ "\""+port+ "\"";
        	  ProcessBuilder findPidProcess = new ProcessBuilder((new String[]{"cmd.exe", "/c", findPidCommand}));
        	  Process process1=findPidProcess.start();
        	  BufferedReader reader = new BufferedReader(new InputStreamReader(process1.getInputStream()));
              String line;
              String pid = null;
              while ((line = reader.readLine()) != null) {
                  if (line.contains("LISTENING")) {
                      String[] tokens = line.trim().split("\\s+");
                      pid = tokens[tokens.length - 1];
                      break;
                  }
              }
              reader.close();
              process1.waitFor();

              // Step 2: Kill the process using the PID
              if (pid != null) {
                  String killCommand = String.format("taskkill /F /PID %s", pid);
                  Process killProcess = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", killCommand});
                  killProcess.waitFor();
                log.info("Process with PID " + pid + " using port " + port + " has been killed.");
              } else {
              log.info("No process found using port " + port);
              }
              
        	  Path filePath = Path.of(System.getProperty("user.dir").replaceAll("\\\\", "/") );
   logPath =filePath+java.io.File.separator+"log.mitm";
   if(java.nio.file.Files.exists(Path.of(logPath))==true) {
	 FileWriter fw=new FileWriter(logPath,false);
	 fw.write("");
	 
   }
        	  String command="mitmproxy -p "+port+" -w "+logPath;
        	  ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c","mitmdump", "-p",port,"-w",logPath);
        	  Process process=builder.start();

        	nlpResponseModel.setMessage("Proxy set and running on "+port );
  			nlpResponseModel.setStatus(CommonConstants.pass);
          }	
          catch(Exception e) {
        	  StringWriter sw = new StringWriter();
  			e.printStackTrace(new PrintWriter(sw));
  			String exceptionAsString = sw.toString();
  			//log.info(exceptionAsString);
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage("Failed  " + exceptionAsString);
        	  nlpResponseModel.setMessage("Failed to set Proxy and run"+exceptionAsString);
  			nlpResponseModel.setStatus(CommonConstants.fail);
        	  
          }

         nlpResponseModel.getAttributes().put("logFilePath", filePath);
          return nlpResponseModel;
      }
      public static void main(String[] args) throws NlpException {
    	  RunProxy run=new RunProxy();
    	  NlpRequestModel nlp=new NlpRequestModel();
    	
    
    nlp.getAttributes().put("Port", "8095");
    	
    	  run.execute(nlp);
	}
  } 