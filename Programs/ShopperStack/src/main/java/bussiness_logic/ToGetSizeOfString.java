package bussiness_logic;



import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component("LIC14365_PJT1001_PE_NLP61c9f379-f9fc-48a9-84eb-bacc96086184")
public class ToGetSizeOfString implements Nlp {
    @InputParams({@InputParam(name = "string1", type = "java.lang.String")})
    @ReturnType(name = "size", type = "java.lang.Integer")

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
          String string1 = (String) attributes.get("string1");
          //String string2 = (String) attributes.get("string2");

          // Your program element business logic goes here ...
          int size=0;
          try {
        	  size=string1.length();
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  nlpResponseModel.setMessage("successfull");
			
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
      	  nlpResponseModel.setMessage("failed");
			// TODO: handle exception
		}
          String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("size", size);
          return nlpResponseModel;
      }
  } 


