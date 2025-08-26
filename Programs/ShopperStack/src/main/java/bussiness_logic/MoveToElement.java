package bussiness_logic;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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

public class MoveToElement implements Nlp {
	@InputParams({ @InputParam(name = "Element", type = "org.openqa.selenium.WebElement")})
	
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
          WebElement element = (WebElement) attributes.get("Element");
         boolean res=true;
      	  try {
      		   WebDriver driver = nlpRequestModel.getWebDriver();
      		   Actions ac= new Actions(driver);
      		   ac.moveToElement(element).click().perform();
      		   res=true;     
            nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Successfully moved on element");
      	  }
   		catch(Exception e)
   		{
   			res=false;
   		    nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("failed to move on element" +e);
   		}
      	nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
      }

 }


