package business_Logics;
import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import com.jayway.jsonpath.JsonPath;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

public class JsonPathExample implements Nlp {
    @InputParams({@InputParam(name = "json object", type = "java.lang.String"),
    @InputParam(name = "keyPath", type = "java.lang.String")})
    @ReturnType(name = "value", type = "java.lang.String")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          String jsonFile = (String) programElementsInput.get("json object");
          String keyPath = (String) programElementsInput.get("keyPath");
          
          String value=null;

          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          try {
              
        	  File file = new File(jsonFile);

              // Read the file content into a String
        	  String json = new String(Files.readAllBytes(file.toPath()), "UTF-8");

              value = JsonPath.read(json, keyPath);
              
              nlpResponseModel.setStatus(CommonConstants.pass);
             
              nlpResponseModel.setMessage("json value: "+value);
          }
          catch(Exception e) {
          	 
             nlpResponseModel.setStatus(CommonConstants.fail);

            
             nlpResponseModel.setMessage("Failed to fetch json value "+e);
          }

         
          nlpResponseModel.getAttributes().put("value", value);
          return nlpResponseModel;
      }

  } 