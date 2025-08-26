package bussiness_logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;


import org.springframework.stereotype.Component;


@Component("LIC14952_PJT1001_PE_NLP0af771eb-60a9-4534-81eb-27f630a5d80d")
public class TapOnElementForNTimes implements Nlp {
	
    @InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement"),@InputParam(name = "num", type = "java.lang.Integer")})
    @ReturnType(name = "result", type = "java.lang.Boolean")

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
          WebElement element = (WebElement) attributes.get("element");
          Integer number = (Integer) attributes.get("num");

          // Your program element business logic goes here ...
      WebDriver  driver=nlpRequestModel.getWebDriver();
      boolean res=true;
      try {
    	 
    	 
    		 
    	  for (int i = 1;i <number; i++) {
			   
  			element.click();
  			
  		}
    	  
    	  res=true;
    	  nlpResponseModel.setStatus(CommonConstants.pass);
    	  nlpResponseModel.setMessage("element is clicked");
  		   
  		    
  		    
		
	} 
      catch (Exception e) {
		res=false;
  	  nlpResponseModel.setStatus(CommonConstants.fail);
  	  nlpResponseModel.setMessage("element is not clicked");
		   
		// TODO: handle exception
	}

         // String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("result",res );
          return nlpResponseModel;
      }
  } 
	


