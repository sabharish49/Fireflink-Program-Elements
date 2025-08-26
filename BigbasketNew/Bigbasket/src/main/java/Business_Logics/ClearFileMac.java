package Business_Logics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
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
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component("LIC20665_PJT1001_PE_NLP274bbf31-b8d1-4e4d-9f87-4d7e25aa70bc")
public class ClearFileMac implements Nlp {
    @InputParams({@InputParam(name = "File Path", type = "java.lang.String")})
  //@ReturnType(name = "logFilePath", type = "java.lang.String")

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
          String logPath = (String) attributes.get("File Path");
       //  String logPath="";
   	 // Path filePath = Path.of(System.getProperty("user.dir").replaceAll("\\\\", "/") );
//logPath =filePath+java.io.File.separator+"mitm.txt";
          try {

      Path path = Path.of(logPath);

      if (Files.exists(path)) {
          try (FileWriter fw = new FileWriter(logPath, false)) {
              System.out.println("");
              fw.write("");
              Thread.sleep(1000);
              fw.close();
              nlpResponseModel.setMessage("File exists. Cleared its contents. on "+logPath );
  			nlpResponseModel.setStatus(CommonConstants.pass);
          } catch (IOException e) {
              StringWriter sw = new StringWriter();
    			e.printStackTrace(new PrintWriter(sw));
    			String exceptionAsString = sw.toString();
    			//log.info(exceptionAsString);
    			nlpResponseModel.setStatus(CommonConstants.fail);
          	  nlpResponseModel.setMessage("Failed on Process File"+exceptionAsString);
          }
      } else {
          File myObj = new File(logPath);
          if (myObj.createNewFile()) {
           	nlpResponseModel.setMessage("Created new file as "+logPath );
        			nlpResponseModel.setStatus(CommonConstants.pass);
          } else {
           	nlpResponseModel.setMessage("File doesnt exist. Creating a new one on "+logPath );
        			nlpResponseModel.setStatus(CommonConstants.pass);
          }
      }


        
          }
          catch(Exception e) {
        	  StringWriter sw = new StringWriter();
  			e.printStackTrace(new PrintWriter(sw));
  			String exceptionAsString = sw.toString();
  			//log.info(exceptionAsString);
  			nlpResponseModel.setStatus(CommonConstants.fail);
        	  nlpResponseModel.setMessage("Failed to clear"+exceptionAsString);
  	
        	  
          }

       
          return nlpResponseModel;
      }
      
      public static void maain(String[] args) throws NlpException {
		RunProxy rp=new RunProxy();
		NlpRequestModel nlp=new NlpRequestModel();
		nlp.getAttributes().put("Port", "8085");
rp.execute(nlp);
		
		
	}
      
      public static void killProcess(String processName) {
          String line;
          String pidInfo = "";
          try {
              Process process = Runtime.getRuntime().exec("tasklist");
              BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
              while ((line = input.readLine()) != null) {
                  pidInfo += line;
              }
              input.close();
          } catch (IOException e) {
              e.printStackTrace();
          }

          if (pidInfo.contains(processName)) {
              try {
                  Runtime.getRuntime().exec("taskkill /F /IM " + processName);
                  System.out.println("Process " + processName + " killed successfully.");
              } catch (IOException e) {
                  e.printStackTrace();
              }
          } else {
              System.out.println("Process " + processName + " not found.");
          }
      }

  } 