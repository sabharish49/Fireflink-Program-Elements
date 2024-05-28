package Business_Logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import org.springframework.stereotype.Component;

@Component("LIC19046_PJT1001_PE_NLPede4a199-2edf-4e19-b88e-f00571f8214d")
public class Fetch_link_from_text implements Nlp {
    @InputParams({@InputParam(name = "Message", type = "java.lang.String")})
    @ReturnType(name = "link", type = "java.lang.String")

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
          String Message = (String) attributes.get("Message");
          String link=null;
          try {
        	  String msg = Message;
        	  link = findlink(msg);
        	  if(link!=null) {
        		  nlpResponseModel.setStatus(CommonConstants.pass);
      			nlpResponseModel.setMessage("succesfully fetched the link"+" "+link); 
        	  }      	  
          }catch(Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage("Failed to fetch the link from the text"+e); 
          }
          nlpResponseModel.getAttributes().put("link", link);
          return nlpResponseModel;
      }    
      public static String findlink(String msg) {
    	  String regex = "\\bhttps?://\\S+\\b";
          Pattern pattern = Pattern.compile(regex);
          Matcher matcher = pattern.matcher(msg);
          if (matcher.find()) {
              return matcher.group();
          } else {
              return null;
          }
      }
  } 
