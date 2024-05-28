
package Business_Logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;


@Component("LIC12620_PJT1003_PE_NLPe83a15ac-b9d9-4e87-999e-133bf1920c6f")
public class getValueOfAttributeOfElement implements Nlp {
    @InputParams({@InputParam(name = "attribute", type = "java.lang.String"), @InputParam(name = "element", type = "org.openqa.selenium.WebElement")})
    @ReturnType(name = "result", type = "java.lang.String")

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
          String attribute = (String) attributes.get("attribute");
          WebElement element = (WebElement) attributes.get("element");

          // Your program element business logic goes here ...
          String value=null;
          try {
        	  AndroidDriver driver = nlpRequestModel.getAndroidDriver();
        	  value=element.getAttribute(attribute);
        	  
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  nlpResponseModel.setMessage("data extracted");
          }
          catch (Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
        	  nlpResponseModel.setMessage("data is not extracted");
		}

         // String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("result", value);
          return nlpResponseModel;
      }
  } 