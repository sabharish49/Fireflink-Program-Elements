package Business_LogicForCommonProgramElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import org.apache.commons.lang3.text.WordUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;


@SuppressWarnings("deprecation")
@Component("LIC1710_PJT1014_PE_NLPb593b47c-c4ee-4fa1-aa4c-3ed5fdb5d324")
public class ConvertStringTextInToTitleCaseText implements Nlp {
	 @InputParams({	@InputParam(name = "Set String Value", type = "java.lang.String")})
     @ReturnType(name = "Return String Value", type = "java.lang.String")

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
          String returnStringValue = "";
          try
          {
        	  	String setStringValue = (String) attributes.get("Set String Value");
        	  	returnStringValue = WordUtils.capitalizeFully(setStringValue);
        	  	
        		nlpResponseModel.setStatus(CommonConstants.pass);
        		nlpResponseModel.setMessage("The Converted Title String Value is: "+returnStringValue);
          }
         catch (Exception e) 
          {
        	 	nlpResponseModel.setStatus(CommonConstants.fail);
        	 	nlpResponseModel.setMessage("Custom NLP not Executed");
		}
          nlpResponseModel.getAttributes().put("Return String Value", returnStringValue);
          return nlpResponseModel;
      }
  } 