package Logics;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class swipe {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        
        try {
        	DesiredCapabilities dc = new DesiredCapabilities();
    		dc.setCapability("platformName", "Android");
    		URL url = new URL("http://localhost:4723/wd/hub");
    		AndroidDriver driver = new AndroidDriver(url, dc);
    	
    		System.out.println("app launched");
    	
    		System.out.println("started");

            // Click on the dropdown trigger to open it
            WebElement dropdownTrigger = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.choiceequitybroking.jiffy:id/tvUpiIds']"));
            dropdownTrigger.click();

            // Wait for the dropdown options to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dropdownList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView")));

            // Perform swipe gesture to ensure all elements are loaded
            swipe(driver, 677, 155, 677, 972); // Example coordinates, adjust as per your app's layout

            // Example: Find and click on a specific dropdown option
            WebElement dropdownOption = dropdownList.findElement(By.xpath("//android.widget.TextView[@text='@oksbi']"));
            dropdownOption.click();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } 
        
    }

    public static void swipe(AndroidDriver driver, int startX, int startY, int endX, int endY) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, startY))
                   .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                   .moveTo(PointOption.point(endX, endY))
                   .release()
                   .perform();
    }
}
