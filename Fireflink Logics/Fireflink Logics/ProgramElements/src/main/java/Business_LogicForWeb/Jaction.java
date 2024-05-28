package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.springframework.stereotype.Component;


@Component("LIC1710_PJT1014_PE_NLP071f9799-8aba-4183-b75b-ca6ace4dab74")
public class Jaction implements Nlp {
    @ReturnType(name = "getBoolean", type = "java.lang.Boolean")

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
          Boolean getBoolean =false;
          try
          {
            WebDriver driver=(WebDriver)nlpRequestModel.getWebDriver();
      		JavascriptExecutor jse= (JavascriptExecutor) driver;
      		WebElement element = (WebElement) jse.executeScript("return document.querySelector(\"#wzrkImageOnlyDiv > ct-web-popup-imageonly\").shadowRoot.querySelector(\"#close\")");
    		getBoolean= element.isDisplayed();
  	        nlpResponseModel.setStatus(CommonConstants.pass);
  	        nlpResponseModel.setMessage("Element is available");
          } 
          catch (Exception e) 
          {
        	nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Element not exists");
          }

          nlpResponseModel.getAttributes().put("getBoolean", getBoolean);
          return nlpResponseModel;
      }
  } 