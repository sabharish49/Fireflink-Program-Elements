package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;

import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;


import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




import org.springframework.stereotype.Component;


@Component("LIC1710_PJT1014_PE_NLP9b020cf2-e168-469a-9bd2-72177cb140a1")
public class WEB_WaitTillStaleElementExceptionToBeCleraed implements Nlp {
    @InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement"), 
    	@InputParam(name = "explicitWait", type = "java.lang.String")})

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
         try {
        	 WebElement element=(WebElement) attributes.get("element");
             String wait=(String) attributes.get("explicitWait");
             WebDriver driver=(WebDriver)nlpRequestModel.getWebDriver();
        	 WebDriverWait waitTill=new WebDriverWait(driver,Duration.ofSeconds(Long.parseLong(wait)));
        	//   waitTill.until(ExpectedConditions.stalenessOf(element));
        	 waitTill.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
        	 nlpResponseModel.setStatus(CommonConstants.pass);
        	 nlpResponseModel.setMessage("Successfully Waited till Stale Element Reference is Cleared");
         }catch(Exception e) {
        	 nlpResponseModel.setStatus(CommonConstants.fail);
        	 nlpResponseModel.setMessage("Failed to Wait the Stale Element Reference is Cleared"+e);
         }

         
          
          return nlpResponseModel;
      }
  } 