package bussiness_logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLPf4e2ef90-8c13-478c-9e77-16a6eb869cbd")
public class ClickAndHoldAndReleaseElementToOffset implements Nlp {
	
	    @InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
	    @InputParam(name = "xOffset", type = "java.lang.Integer"),
	    @InputParam(name = "yOffset", type = "java.lang.Integer")})
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
	          Integer xOffset = (Integer) attributes.get("xOffset");
	          Integer yOffset = (Integer) attributes.get("yOffset");
	          boolean result=true;
	          WebDriver driver = nlpRequestModel.getWebDriver();
	          try {
		            Actions actions = new Actions(driver);
		            
		            actions.clickAndHold(element).build().perform();

		           
		            actions.moveByOffset(xOffset, yOffset).build().perform();

		           
		            actions.release().build().perform();
				    	  result=true;
				    	  nlpResponseModel.setStatus(CommonConstants.pass);
				    	  nlpResponseModel.setMessage("element is moved");
				      
				   
				      }
			 catch (Exception e) 
	          {
				result=false;
		    	  nlpResponseModel.setStatus(CommonConstants.fail);
		    	  nlpResponseModel.setMessage("element is not moved");

	          }
	          
	          

	          // Your program element business logic goes here ...
	          nlpResponseModel.getAttributes().put("result", result);
	          return nlpResponseModel;
	      }
	  } 
		







