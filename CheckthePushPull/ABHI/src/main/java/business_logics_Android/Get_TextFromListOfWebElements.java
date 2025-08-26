package business_logics_Android;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
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




public class Get_TextFromListOfWebElements implements Nlp {
    @InputParams({ @InputParam(name = "Xpath", type = "org.openqa.String")})
    @ReturnType(name = "List of Text", type = "java.util.List")

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
         
          String xpath = (String) attributes.get("Xpath");
          
          
//         
        
          AndroidDriver driver=(AndroidDriver)nlpRequestModel.getAndroidDriver();
          ArrayList list=new ArrayList<>();
          try {
          
     
          WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
          List<WebElement> ele = driver.findElements(By.xpath(xpath));
         
          for(WebElement element:ele) {
          	String text = element.getText();
          list.add(text);
          }
          
        
         nlpResponseModel.setMessage("Get text is Successfull");
         nlpResponseModel.setStatus(CommonConstants.pass);
        
          
          
          }catch (Exception e) {
			// TODO: handle exception
        	  nlpResponseModel.setMessage("Get text is Successfull" + e +"");
              nlpResponseModel.setStatus(CommonConstants.fail);
		}
          
          
          
          
//          String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("List of Text", list);
          return nlpResponseModel;
      }
    
      
  } 
