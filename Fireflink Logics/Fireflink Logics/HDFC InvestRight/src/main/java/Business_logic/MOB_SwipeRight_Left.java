package Business_logic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.lang.Character;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;

import org.springframework.stereotype.Component;

@Component("LIC14172_PJT1001_PE_NLPa062b38d-076d-4db0-bd89-9610e3d9613a")
public class MOB_SwipeRight_Left implements Nlp {
	
	  @InputParams({@InputParam(name = "SourcesElement", type = "org.openqa.selenium.WebElement")
	  ,@InputParam(name = "DestinationElement", type = "org.openqa.selenium.WebElement")
	  ,@InputParam(name = "Xpath", type = "java.lang.String"),
	  @InputParam(name = "maxSwipeCount", type = "java.lang.Integer")})
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
          WebElement SourcesElement  = (WebElement) attributes.get("SourcesElement");
          WebElement DestinationElement  = (WebElement) attributes.get("DestinationElement");
          String Xpath=(String)attributes.get("Xpath");
      	Integer maxSwipeCount = (Integer) attributes.get("maxSwipeCount");
          int FromX=SourcesElement.getLocation().getX();
  		  int FromY=SourcesElement.getLocation().getY();
  		  int ToX=DestinationElement.getLocation().getX();
  		  int ToY=DestinationElement.getLocation().getY();
  		  
      int count=0;
      	  try {
      		 AndroidDriver driver=(AndroidDriver)nlpRequestModel.getAndroidDriver();
      		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
      		while (driver.findElements(By.xpath(Xpath)).isEmpty()) {

    			Sequence swipe =  new Sequence(finger, 1);
    			//Move finger into starting position
    			swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), FromX, FromY));


    			//Finger goes up into contact with screen
    			swipe.addAction(finger.createPointerDown(0));

    			//Finger moves to End Position
    			swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), ToX, ToY));

    			//Take out finger from screen
    			swipe.addAction(finger.createPointerUp(0));

    			driver.perform(Arrays.asList(swipe));
    			
    			
    			if(++count >= maxSwipeCount) {
					break;
				}
				if(count >= maxSwipeCount) {
					nlpResponseModel.setStatus(CommonConstants.fail);
					nlpResponseModel.setMessage("Failed to swipe horizontally until element is visible " +Xpath);

				}
				else {
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Sucessfully swiped horizontally until element is visible");
				}
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
