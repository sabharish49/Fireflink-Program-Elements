package Business_logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

@Component("LIC14172_PJT1001_PE_NLPcd2a8505-9302-4c73-a323-f45ca05e2c57")
public class MOB_BuySell implements Nlp {
    @InputParams({@InputParam(name = "Element", type = "org.openqa.selenium.WebElement"), @InputParam(name = "Option", type = "java.lang.String")})
   

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
          WebElement Element = (WebElement) attributes.get("Element");
          String Option = (String) attributes.get("Option");
      	 
      	  
      	  try {
      		 AndroidDriver driver=(AndroidDriver)nlpRequestModel.getAndroidDriver();
      		  
      	  
           Actions action = new Actions(driver);
           String command=Option;

   		if(command.equalsIgnoreCase("Buy")) {
   			action.dragAndDropBy(Element, 1080, 191).build().perform();
   			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully Swipped to " +command);

   		}	
   		else if(command.equalsIgnoreCase("Sell")){
   			WebElement des=driver.findElement(By.xpath("//android.view.View[@content-desc='Trending' or @content-desc='Last Searched']"));
   			WebElement Source=driver.findElement(By.xpath("(//android.view.View[@content-desc='Trending' or @content-desc='Last Searched']/..//android.widget.ImageView)[1]/android.view.View"));
   			
   			action.clickAndHold(Source);
   			
   			Thread.sleep(2000);
   			action.moveToElement(des).release();
   			action.build().perform();
   			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully Swipped to " +command);

      	  }
      	  }
   		catch(Exception e)
   		{
   		    nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed To Swipe " +e);
   		}
          
          return nlpResponseModel;
      }
  } 

