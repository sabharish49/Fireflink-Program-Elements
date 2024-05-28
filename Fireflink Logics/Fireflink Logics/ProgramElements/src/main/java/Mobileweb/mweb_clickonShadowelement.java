package Mobileweb;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;

import io.appium.java_client.android.AndroidDriver;


@Component("LIC1710_PJT1014_PE_NLPcab93bf8-544e-4462-bf4a-57c8efa451d7")
public class mweb_clickonShadowelement implements Nlp {

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
            AndroidDriver driver=nlpRequestModel.getAndroidDriver();
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
