
package Logic;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

@Component("LIC19620_PJT1001_PE_NLP916d55ab-6f31-4cef-9d2c-69f1727135b7")
public class SwipetillElement implements Nlp {
    @InputParams({@InputParam(name = "Days", type = "java.lang.String")})

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
          String noDay = (String) attributes.get("Days");
          AndroidDriver driver = nlpRequestModel.getAndroidDriver();
          try {        	  
        	  WebElement parentElement = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.View[6]"));
              while (true) {
                  try {
                      LocalDate currentDate = LocalDate.now();
                      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
                      String dayOfMonth = formatter.format(currentDate);
                      int additionalDay = Integer.parseInt(dayOfMonth) + Integer.parseInt(noDay);
                      System.out.println("Updated day of the month (dd): " + additionalDay); 
                      System.out.println("Searching for element");
                      WebElement childElement = parentElement.findElement(By.xpath("//android.view.View[contains(@content-desc,'"+additionalDay+"')]"));               
                      childElement.click();
                      break; 
                  } catch (org.openqa.selenium.NoSuchElementException e) {       	
                  	Thread.sleep(2000);
                  	System.out.println("method called");
                      Dimension size = driver.manage().window().getSize();
                      int startX = (int) (size.width * 0.8); 
                      int endX = (int) (size.width * 0.1); 
                      int startY = parentElement.getLocation().getY() + parentElement.getSize().getHeight() / 2;
                      TouchAction<?> touchAction = new TouchAction<>(driver);
                      touchAction.press(PointOption.point(startX, startY))
                                 .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                                 .moveTo(PointOption.point(endX, startY))
                                 .release()
                                 .perform();
                  }
              }
              nlpResponseModel.setStatus(CommonConstants.pass);
          	  nlpResponseModel.setMessage("Selected date Successfully");           
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
       	  nlpResponseModel.setMessage("Failed to select date"+e);
		}
          return nlpResponseModel;
      }  
  } 