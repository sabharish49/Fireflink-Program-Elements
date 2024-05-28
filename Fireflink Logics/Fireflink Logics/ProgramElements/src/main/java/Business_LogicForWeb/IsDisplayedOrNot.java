package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
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



@Component("LIC1710_PJT1014_PE_NLP539b5f4e-69b1-428f-abbf-4a08fffbcf1a")
public class IsDisplayedOrNot implements Nlp {
    @InputParams({@InputParam(name = "stringXpath", type = "java.lang.String")})
    @ReturnType(name = "getBoolean", type = "java.lang.Boolean")

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
          
          WebDriver driver=nlpRequestModel.getWebDriver();
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
          Duration impWait=driver.manage().timeouts().getImplicitWaitTimeout();
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
          Boolean getBoolean =false;
          try
          {
            String stringXpath = (String) attributes.get("stringXpath");
        	WebElement ele = driver.findElement(By.xpath(stringXpath));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath)));
        	Boolean condition = ele.isDisplayed(); 
			if(condition == true)
			{
				getBoolean = true;
	          	nlpResponseModel.setStatus(CommonConstants.pass);
	          	nlpResponseModel.setMessage("Element Is Dispalyed In WebPage");
			}
			else
			{
	          	nlpResponseModel.setStatus(CommonConstants.pass);
	          	nlpResponseModel.setMessage("Element Is Not Dispalyed In WebPage");
			}
          } 
          catch (Exception e) 
          {
        	nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Element Not Exist In WebPage");
          }
          finally 
          {
        	  driver.manage().timeouts().implicitlyWait(impWait);
          }
          nlpResponseModel.getAttributes().put("getBoolean", getBoolean);
          return nlpResponseModel;
      }
  } 