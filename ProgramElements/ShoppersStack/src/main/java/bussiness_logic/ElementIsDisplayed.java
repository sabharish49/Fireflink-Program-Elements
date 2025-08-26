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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;



@Component("LIC14952_PJT1001_PE_NLP5ef9f9b0-10d6-41c7-a2a5-3e32110294f4")
public class ElementIsDisplayed implements Nlp {
    @InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement")})
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
          boolean result=true;
          WebDriver driver = nlpRequestModel.getWebDriver();
          try {
			      if(element.isDisplayed())
			      {
			    	  result=true;
			    	  nlpResponseModel.setStatus(CommonConstants.pass);
			    	  nlpResponseModel.setMessage("element is displayed successfully");
			      }
			      else{
			    	  result=false;
			    	  nlpResponseModel.setStatus(CommonConstants.fail);
			    	  nlpResponseModel.setMessage("element is not displayed");
	
			    	  
			      }
		} catch (Exception e) 
          {
			result=false;
	    	  nlpResponseModel.setStatus(CommonConstants.fail);
	    	  nlpResponseModel.setMessage("element is not displayed");

			// TODO: handle exception
		}
          
          //String string2 = (String) attributes.get("string2");

          // Your program element business logic goes here ...

          //String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("result", result);
          return nlpResponseModel;
      }
  } 
	


