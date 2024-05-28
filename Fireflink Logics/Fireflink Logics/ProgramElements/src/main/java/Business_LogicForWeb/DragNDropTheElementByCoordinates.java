package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;




import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLPc5fbc513-d6fe-4795-be0c-bcb0f5a370ef")
public class DragNDropTheElementByCoordinates implements Nlp {
    @InputParams({	@InputParam(name = "Set String Xpath", type = "java.lang.String"),
    				@InputParam(name = "xOffset", type = "java.lang.Integer"),
    				@InputParam(name = "yOffset", type = "java.lang.Integer")})

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
            String stringXpath = (String) attributes.get("Set String Xpath");
            Integer xOffset = (Integer) attributes.get("xOffset");
            Integer yOffset = (Integer) attributes.get("yOffset");
            WebDriver driver=(WebDriver)nlpRequestModel.getWebDriver();
			WebElement ele = driver.findElement(By.xpath(stringXpath));
			Actions act = new Actions(driver);
			act.dragAndDropBy(ele,xOffset,yOffset).build().perform();	
	        nlpResponseModel.setStatus(CommonConstants.pass);
	        nlpResponseModel.setMessage("Element Is Drag & Droped");
          } 
          catch (Exception e) 
          {
        	nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage("Something went wrong");
          }
          return nlpResponseModel;
      }
  } 