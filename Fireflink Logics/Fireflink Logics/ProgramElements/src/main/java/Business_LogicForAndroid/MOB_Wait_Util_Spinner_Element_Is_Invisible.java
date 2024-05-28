package Business_LogicForAndroid;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;


@Component("LIC1710_PJT1014_PE_NLPd5220a56-ff13-4e91-9748-d055497a3749")
public class MOB_Wait_Util_Spinner_Element_Is_Invisible implements Nlp {
 
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
        
          AndroidDriver driver=nlpRequestModel.getAndroidDriver();
          Duration impWait=driver.manage().timeouts().getImplicitWaitTimeout();
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
          try 
          {
        	  
          WebElement spinner=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/descendant::android.widget.FrameLayout/child::android.view.ViewGroup//android.widget.ImageView"));
          if(spinner.isDisplayed()){
        	wait.until(ExpectedConditions.invisibilityOf(spinner));
        	nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage(" Waited until Element is Invisible");
          }
          }
          catch (Exception e) {
        	nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Element is not displayed displayed");
          }
		finally {
			driver.manage().timeouts().implicitlyWait(impWait);
		}
          return nlpResponseModel;
      }
}


      
