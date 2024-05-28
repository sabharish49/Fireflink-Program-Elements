package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.springframework.stereotype.Component;


@Component("LIC1710_PJT1014_PE_NLP9c6fbac8-6aa5-4596-a235-93940ef8c81e")
public class Jclick implements Nlp {

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
          try
          {
            WebDriver driver=(WebDriver)nlpRequestModel.getWebDriver();
      		JavascriptExecutor jse= (JavascriptExecutor) driver;
      		WebElement element = (WebElement) jse.executeScript("return document.querySelector(\"#wzrkImageOnlyDiv > ct-web-popup-imageonly\").shadowRoot.querySelector(\"#close\")");
    		element.click();
  	        nlpResponseModel.setStatus(CommonConstants.pass);
  	        nlpResponseModel.setMessage("Element is clicked");
          } 
          catch (Exception e) 
          {
        	nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Element not exists");
          }
          return nlpResponseModel;
      }
  } 