package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class DateOfBirthCalender {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("noReset", "true");
        dc.setCapability("platformName", "Android");
        URL url = new URL("http://localhost:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, dc);
        System.out.println("App launched");
        Thread.sleep(5000);
        String Date = "13-Dec-1998";
        String choice="applicantDob";
        switch (choice) {
        case "applicantDob":
            setDateOfBirth(Date,driver);
            break;
        case "APACDob":
        	APAC(Date,driver);
            break;
        default:
            // Handle invalid choice here
            break;
    }
}
    
    public static void setDateOfBirth(String Date, AndroidDriver driver) throws InterruptedException {
     driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.apacfin.newalpha:id/ivCalender\"]")).click();
      Thread.sleep(1000);
        Actions act=new Actions(driver);
    	 String[] dateComponents = Date.split("-");
         String day = dateComponents[0];
         String month = dateComponents[1];
         String year = dateComponents[2];
         
         WebElement currentYear = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/date_picker_header_year']"));
         String presentYear = currentYear.getText();
         System.out.println(presentYear);
         if (!presentYear.equalsIgnoreCase(year)) {
             currentYear.click(); 
             WebElement calendarYear = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1']"));
             boolean yearFound = false;
             int maxScrollAttempts = 30;
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
    }
    
    public static void APAC(String Actualdate,AndroidDriver driver) {
    	System.out.println("Method called");
    	String[] dateComponents = Actualdate.split("-");
        List<String> dateList = Arrays.asList(dateComponents);
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
            	               .moveTo(PointOption.point(startX, endY + 100)) // Scroll a little more
            	               .release()
            	               .perform();
                
                name = element.getText();
            }

            if (name.equalsIgnoreCase(actual)) {
                System.out.println("Actual date component found: " + actual);
            }
        }
        
    }   
}
