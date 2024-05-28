
package Business_Logic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

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
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.springframework.stereotype.Component;

@Component("LIC12620_PJT1003_PE_NLP4592c9d8-96c7-4838-ac11-64bc32280bb0")
public class Select_Date_From_Date_Picker implements Nlp {
    @InputParams({@InputParam(name = "ElementName", type = "java.lang.String"), @InputParam(name = "Date", type = "java.lang.String")})
    @ReturnType(name = "string3", type = "java.lang.String")

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
          String choice = (String) attributes.get("ElementName");
          String Date= (String) attributes.get("Date");
        AndroidDriver driver = nlpRequestModel.getAndroidDriver();
 
          switch (choice) {
          case "applicantDob":
              try {
				setDateOfBirth(Date,driver,nlpResponseModel);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}  
              break;
          case "esignApplicantDob":
          	APAC(Date,driver,nlpResponseModel);
              break;
          default:
              break;
      }

          return nlpResponseModel;
      }
  
      public static void setDateOfBirth(String Date, AndroidDriver driver, NlpResponseModel nlpResponseModel) throws InterruptedException {
    	  driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.apacfin.newalpha:id/ivCalender']")).click();
          Thread.sleep(1000);
     	 String[] dateComponents = Date.split("-");
          String day = dateComponents[0];
          String month = dateComponents[1];
          String year = dateComponents[2];

          WebElement currentYear = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/date_picker_header_year']"));
          String presentYear = currentYear.getText();
          if (!presentYear.equalsIgnoreCase(year)) {
              currentYear.click(); 
              WebElement calendarYear = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1']"));
              boolean yearFound = false;
              int maxScrollAttempts = 30;
              try {
              for (int i = 0; i < maxScrollAttempts; i++) {
                  try {
                      WebElement desiredYear = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='" + year + "']"));
                      if (desiredYear.isDisplayed()) { 
                          System.out.println("Desired year found: " + year);
                          desiredYear.click(); 
                          yearFound = true;
                          break;
                      }
                  } catch (org.openqa.selenium.NoSuchElementException e) {
                      Point location = calendarYear.getLocation();
                      Dimension size = calendarYear.getSize();
                      int startX = location.getX() + size.getWidth() / 2;
                      int startY = location.getY() + size.getHeight() / 2;
                      int endY = startY + 100;
                      TouchAction<?> touchAction = new TouchAction<>(driver);
                      touchAction.press(PointOption.point(startX, startY))
                              .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                              .moveTo(PointOption.point(startX, endY))
                              .release()
                              .perform();
                  }
              }
              if (!yearFound) {
                  System.out.println("Desired year not found!");
              }
       
          String currentMonth = driver.findElement(By.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View"))
                  .getAttribute("content-desc");
          if (currentMonth.contains(month)) {
              driver.findElement(By.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View[@text='" + day + "']"))
                      .click();
              driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();

          } else {
              WebElement previousButton = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Previous month']"));
              WebElement nextButton = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Next month']"));
              boolean isNextMonth = month.compareToIgnoreCase(currentMonth) > 0;

              if (isNextMonth) {
                  for (int i = 0; i < 12; i++) { 
                      nextButton.click();
                      currentMonth = driver.findElement(By.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View"))
                              .getAttribute("content-desc");
                      if (currentMonth.contains(month)) {
                          driver.findElement(By.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View[@text='" + day + "']"))
                                  .click();
                          driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
                          break;
                      }
                  
                  }
              } else {
                  for (int i = 0; i < 12; i++) { 
                      previousButton.click();
                      currentMonth = driver.findElement(By.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View"))
                              .getAttribute("content-desc");
                      if (currentMonth.contains(month)) {
                          driver.findElement(By.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View[@text='" + day + "']"))
                                  .click();
                          driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
                          break;
                      }
                  }
              }
          }
          nlpResponseModel.setStatus(CommonConstants.pass);
     	 nlpResponseModel.setMessage("Successfully selected date from date picker");
          }catch(Exception e){
        	  nlpResponseModel.setStatus(CommonConstants.fail);
	        	 nlpResponseModel.setMessage("Failed to select date from date picker"+e);
          }
     }
      }
      
      public static void APAC(String Actualdate,AndroidDriver driver,NlpResponseModel nlpResponseModel) {
      	System.out.println("Method called");
      	String[] dateComponents = Actualdate.split("-");
          List<String> dateList = Arrays.asList(dateComponents);
          driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.apacfin.apacm:id/edtDob']")).click();
          try {
          for (int i = 0; i < dateList.size(); i++) {
              WebElement element = driver.findElement(By.xpath("(//android.widget.LinearLayout//android.widget.EditText[@resource-id='android:id/numberpicker_input'])[" + (i + 1) + "]"));
              String name = element.getText();
              String actual = dateList.get(i);
              Point location = element.getLocation();
              Dimension size = element.getSize();
              int startX = location.getX() + size.getWidth() / 2;
              int startY = location.getY() + size.getHeight() / 2;
              int endY = startY + 100;
              while (!name.equalsIgnoreCase(actual)) {
            	  TouchAction<?> additionalScroll = new TouchAction<>(driver);
              	additionalScroll.press(PointOption.point(startX, startY))
              	               .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
              	               .moveTo(PointOption.point(startX, endY + 100))
              	               .release()
              	               .perform();
                  name = element.getText();
              }
              if (name.equalsIgnoreCase(actual)) {
                  System.out.println("Actual date component found: " + actual);
              }
          } 
          
          nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("successfully selected date from date picker"); 
          }
          catch (Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage("Failed to select date from date picker"); 
		}
      }   
     
  } 