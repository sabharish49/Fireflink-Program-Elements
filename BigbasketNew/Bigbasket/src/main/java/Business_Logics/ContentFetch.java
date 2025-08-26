package Business_Logics;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
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
@Component("LIC20665_PJT1001_PE_NLPda654eee-841e-4056-88ab-2595b2d8e265")
public class ContentFetch implements Nlp {
    @InputParams({@InputParam(name = "Log File Path", type = "java.lang.String")
    })
  @ReturnType(name = "log", type = "org.json.JSONArray")

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
          String filePath = (String) attributes.get("Log File Path");
          String logPath="";
          JSONArray jsonArray=null;
          try {
        	//  System.load(logPath);
        	  byte[] encoded = Files.readAllBytes(Paths.get(filePath));
              String input = new String(encoded, StandardCharsets.UTF_8);
      		 String regex = "content;\\d+:\\{.*?\\}";
      	        Pattern pattern = Pattern.compile(regex);
      	        Matcher matcher = pattern.matcher(input);

      	        // List to store extracted JSON strings
      	        List<String> jsonStrings = new ArrayList<>();

      	        while (matcher.find()) {
      	            jsonStrings.add(matcher.group());
      	        }

      	        // Parse and print each JSON string
      	        for (String jsonString : jsonStrings) {
      	            // Extract the JSON part after "content;number:"
      	            String jsonPart = jsonString.substring(jsonString.indexOf('{'));
      	            try {
      	            JSONObject jsonObject=new JSONObject(jsonPart);
      	          jsonArray.put(jsonObject);
      	        log.info("Extracted JSON: " + jsonObject.toString(4)); 
      	            } catch (Exception e) {
      	            log.info("Invalid JSON: " + jsonPart);
      	            }
      	        }
        	         	  nlpResponseModel.setMessage("Extracted data from the Logs are "+jsonArray );
  			nlpResponseModel.setStatus(CommonConstants.pass);
          }
          catch(Exception e) {
        	  StringWriter sw = new StringWriter();
  			e.printStackTrace(new PrintWriter(sw));
  			String exceptionAsString = sw.toString();
  			//log.info(exceptionAsString);
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			
        	  nlpResponseModel.setMessage("Failed to extract data from logs"+exceptionAsString);
  			nlpResponseModel.setStatus(CommonConstants.fail);
        	  
          }

         nlpResponseModel.getAttributes().put("log", jsonArray);
          return nlpResponseModel;
      }
  } 