package Business_LogicForWeb;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;
@Component("LIC1710_PJT1014_PE_NLPcb1c374c-55c8-4f08-89a5-4bf534965b9e")
public class FetchNumberFromStringXpath implements Nlp {
	 @InputParams({	@InputParam(name = "String Xpath", type = "java.lang.String")})
     @ReturnType(name = "return integer value", type = "java.lang.Integer")

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
          String String_Xpath_1 = (String) attributes.get("String Xpath");
          Integer integervalue=null;
          WebDriver driver=(WebDriver)nlpRequestModel.getWebDriver();
          try {
        	  String  value1A=driver.findElement(By.xpath(String_Xpath_1)).getText();
        	  integervalue=Integer.parseInt(value1A.replaceAll("[^0-9]", ""));
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  nlpResponseModel.setMessage("The Fetched Integer Value is: "+integervalue);
          }
         catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Something Went Wrong");
		}
          nlpResponseModel.getAttributes().put("return integer value", integervalue);
          return nlpResponseModel;
      }
  } 