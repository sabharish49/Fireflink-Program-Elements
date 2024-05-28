
package logics;

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

@Component("LIC14952_PJT1007_PE_NLPdaa57283-ffd4-4b0e-9693-5f73b4613fb8")
public class Swipetilldate implements Nlp {
    @InputParams({@InputParam(name = "Days", type = "java.lang.String")})
    //@ReturnType(name = "string3", type = "java.lang.String")

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
      @SuppressWarnings("deprecation")
	@Override
      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          Map<String, Object> attributes = nlpRequestModel.getAttributes();
          String noDay = (String) attributes.get("Days");
          AndroidDriver driver = nlpRequestModel.getAndroidDriver();
          try {
              WebElement parentElement = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.View[6]"));
              LocalDate currentDate = LocalDate.now();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
              String dayOfMonth = formatter.format(currentDate);
              int additionalDay = Integer.parseInt(dayOfMonth) + Integer.parseInt(noDay);
              System.out.println("Updated day of the month (dd): " + additionalDay); 
              WebElement childElement;
			while (true) {
                  try {
                      System.out.println("Searching for element");
                      childElement = parentElement.findElement(By.xpath("//android.view.View[contains(@content-desc,'"+additionalDay+"')]"));
                      if (childElement.isDisplayed()) {
                          childElement.click();
                      } else {
                          System.out.println("Child element not found or not visible.");
                      }
                      break; 
                  } catch (org.openqa.selenium.NoSuchElementException e) {
                  	Thread.sleep(2000);
                  	System.out.println("method called");
                      Dimension size = driver.manage().window().getSize();
                      int startX = (int) (size.width * 0.8); 
                      int endX = (int) (size.width * 0.1); 
                      int startY = parentElement.getLocation().getY() + parentElement.getSize().getHeight() / 2;
                      @SuppressWarnings("deprecation")
					TouchAction<?> touchAction = new TouchAction<>(driver);
                      touchAction.press(PointOption.point(startX, startY))
                                 .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                                 .moveTo(PointOption.point(endX, startY))
                                 .release()
                                 .perform();
                  }
              }
             
              nlpResponseModel.setStatus(CommonConstants.pass);
              nlpResponseModel.setMessage("Successfully selected date");
          } catch (Exception e) {
              nlpResponseModel.setStatus(CommonConstants.fail);
              nlpResponseModel.setMessage("Failed to select date: " + e);
          } 
       return nlpResponseModel;
      }      
  } 

