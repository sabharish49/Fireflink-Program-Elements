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

@Component("LIC1710_PJT1014_PE_NLP91f641a1-ca91-468e-84b5-3d8de6ce91d3")
public class GetPDPPrice implements Nlp {
	 @InputParams({	@InputParam(name = "String Xpath Value", type = "java.lang.String")})
     @ReturnType(name = "Obtained Integer value", type = "java.lang.Integer")

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
          String String_Xpath_1 = (String) attributes.get("String Xpath Value");
          Integer integervalue=null;
          try {
              WebDriver driver=(WebDriver)nlpRequestModel.getDriver().getSpecificIDriver();
           	  String value1A=driver.findElement(By.xpath(String_Xpath_1)).getText();
        	  String value1B=value1A.replaceAll("[^0-9]", "");
        	  String value1C=value1B.substring(0, value1B.length()-2);
        	  integervalue=Integer.parseInt(value1C);
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  nlpResponseModel.setMessage("Obtained Integer Value is: "+integervalue);
          }
         catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage(" Something Went Wrong ");
		}
          nlpResponseModel.getAttributes().put("Obtained Integer value", integervalue);
          return nlpResponseModel;
      }
  } 