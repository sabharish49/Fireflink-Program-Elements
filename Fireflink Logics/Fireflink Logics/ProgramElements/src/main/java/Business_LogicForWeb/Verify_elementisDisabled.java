package Business_LogicForWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

@Component("LIC1710_PJT1014_PE_NLPcfaaf488-314e-4f8b-b2d2-927e962d75d6")
public class Verify_elementisDisabled implements Nlp {
    @InputParams({@InputParam(name = "Xpath", type = "java.lang.String")})
      @ReturnType(name = "getboolean", type = "java.lang.Boolean")

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
          String Xpath = (String) attributes.get("Xpath");
       Boolean isdisabled=true;
try {
	WebDriver driver=(WebDriver)nlpRequestModel.getWebDriver();
	
	if(driver.findElement(By.xpath(Xpath)).isEnabled()) {
		isdisabled=true;
	}else {
		isdisabled=false;
		nlpResponseModel.setStatus(CommonConstants.pass);
		nlpResponseModel.setMessage("Element is disabled");
	}

}
catch(Exception e){
	nlpResponseModel.setMessage("Element is enabled");
	nlpResponseModel.setStatus(CommonConstants.fail);
}
          nlpResponseModel.getAttributes().put("getboolean", isdisabled);
          return nlpResponseModel;
      }
  } 
