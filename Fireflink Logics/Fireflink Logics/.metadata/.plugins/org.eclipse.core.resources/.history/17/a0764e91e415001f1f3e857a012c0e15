package Practice;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;

public class MOF_Payment {
    public static void main(String[] args) throws Exception {      
        int loopDurationMinutes = 2; 
        long duration = loopDurationMinutes * 60 * 1000;
        long startTime = System.currentTimeMillis();
        DesiredCapabilities dc = new DesiredCapabilities();       
        dc.setCapability("platformName", "Android");
        dc.setCapability("noReset","true");
        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), dc); 
        long elapsedTime;          
       try {       	
           while ((elapsedTime = System.currentTimeMillis() - startTime) < duration) {
               try {
                   WebElement prospectAlert = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'ProspectId and Prospect No is not generated')]"));
                   if (prospectAlert.isDisplayed()) {
                       driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
                       System.out.println("Alert handled.");
                   }
               } catch (org.openqa.selenium.NoSuchElementException e) {                
                   System.out.println("MOF button not found.");
               }               
               long remainingTime = duration - elapsedTime;
               if (remainingTime < 5000)
                  break;
               Thread.sleep(Math.min(5000, remainingTime)); 
               try {
               WebElement mof = driver.findElement(By.xpath("//android.widget.Button[@text='MOF']"));       
   				if (mof.isDisplayed()) {
   					mof.click();
   					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
   					WebElement add = wait.until(
   					        ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ImageButton[@resource-id=\"com.apacfin.newalpha:id/btnAddMof\"]")));
   					  add.click();
   					  break;
   				}
   			} catch (Exception e) {
   				break;
   			}
               System.out.println("MOF button clicked.");
           }        
           System.out.println(". Exiting loop.");

    }catch (Exception e) {
		// TODO: handle exception
	}
       
       System.out.println("completed");
}
}
