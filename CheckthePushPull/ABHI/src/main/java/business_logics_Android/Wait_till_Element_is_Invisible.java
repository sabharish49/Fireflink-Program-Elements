package business_logics_Android;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class Wait_till_Element_is_Invisible implements Nlp {
    @InputParams({ @InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
    	@InputParam(name = "device", type = "java.lang.String"),
    	@InputParam(name = "Wait Time", type = "java.lang.Integer")})

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
          WebElement el = (WebElement) attributes.get("element");
          String D = (String) attributes.get("device");
          int waitTime = (Integer) attributes.get("Wait Time");
          
//         
        
          AndroidDriver driver=(AndroidDriver)nlpRequestModel.getAndroidDriver();
          IOSDriver driver1=(IOSDriver)nlpRequestModel.getIosDriver();
          // Your program element business logic goes here ...
          String device=D.toLowerCase();
          try {
          
        if(device.contains("iphone")) {
        	WebDriverWait wait=new WebDriverWait(driver1, Duration.ofSeconds(waitTime));
        	wait.until(ExpectedConditions.invisibilityOf(el));
           
            nlpResponseModel.setMessage("Completely Waited");
            nlpResponseModel.setStatus(CommonConstants.pass);
        }else {
          WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
          wait.until(ExpectedConditions.invisibilityOf(el));
        
         nlpResponseModel.setMessage("Completely Waited");
         nlpResponseModel.setStatus(CommonConstants.pass);
        }
          
          
          }catch (Exception e) {
			// TODO: handle exception
        	  nlpResponseModel.setMessage("Not completely Waited" + e +"");
              nlpResponseModel.setStatus(CommonConstants.pass);
		}
          
          
          
          
//          String string3 = "Return Value";
//          nlpResponseModel.getAttributes().put("string3", string3);
          return nlpResponseModel;
      }
    
      
  } 
